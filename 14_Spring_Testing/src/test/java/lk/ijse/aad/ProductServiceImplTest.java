package lk.ijse.aad;

import lk.ijse.aad.entity.Product;
import lk.ijse.aad.repo.ProductRepository;
import lk.ijse.aad.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .id(1L)
                .name("Test Product")
                .price(100.00)
                .quantity(5)
                .build();
    }

    @Test
    void shouldSaveProduct() {
        // arrange
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // action
        Product saveProduct = productService.createProduct(product);

        // asserts
        Assertions.assertNotNull(saveProduct);
        Assertions.assertEquals(product,saveProduct);
//        Assertions.assertEquals(1L,saveProduct.getId());
        verify(productRepository,times(1)).save(product);
    }

    @Test
    void shouldUpdateProduct() {
        //arrange
        Product updateProduct = Product.builder()
                .name("Update Product")
                .price(20.30)
                .quantity(10)
                .build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        //action
        Product result = productService.updateProduct(updateProduct);

        // asserts
        Assertions.assertEquals("Update Product",result.getName());
        Assertions.assertEquals(20.30, result.getPrice());
        Assertions.assertEquals(10,result.getQuantity());
        verify(productRepository,times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(Product.class));
    }

}
