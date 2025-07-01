package lk.ijse.aad.controller;

import lk.ijse.aad.dto.CustomerDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    // save customer -> form data (x-www form  URL Encoded)
    // CID - Customer ID
    // CName - Customer Name
    // CAddress - Customer Address
    @PostMapping()
    public String saveCustomer(@ModelAttribute CustomerDTO customerDTO) {
        System.out.println(customerDTO.getCID());
        System.out.println(customerDTO.getCName());
        System.out.println(customerDTO.getCAddress());
        return "Customer Saved";
    }

    // save customer - query params
    @PostMapping(params = {"CID", "CName", "CAddress"})
    public String saveCustomerQueryParams(@ModelAttribute @RequestParam("CID") String CID,
                                          @RequestParam("CName") String CName,
                                          @RequestParam("CAddress") String CAddress){
        System.out.println(CID);
        System.out.println(CName);
        System.out.println(CAddress);
        return "Customer Saved";
    }

    // save customer - path variables
    @PostMapping(path="saveWithPathVariable/{CID}/{CName}/{CAddress}")
    public String saveCustomerWithPathVariable(@ModelAttribute @PathVariable("CID") String CID,
                                               @PathVariable("CName") String CName,
                                               @PathVariable("CAddress") String CAddress){
        System.out.println(CID);
        System.out.println(CName);
        System.out.println(CAddress);
        return "saveCustomerWithPathVariable";

    }

    // save customer - JSON
    @PostMapping(path = "saveWithJSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveCustomerUsingJson(@RequestBody CustomerDTO customerDTO){
        System.out.println(customerDTO.getCID());
        System.out.println(customerDTO.getCName());
        System.out.println(customerDTO.getCAddress());
        return "saveCustomerWithJSON";
    }

    // Return JSON Object
    @GetMapping(path = "getCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCustomerUsingJson(){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCID("1q");
        customerDTO.setCName("qq");
        customerDTO.setCAddress("qq");
        return customerDTO.toString();
    }

}
