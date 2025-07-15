package lk.ijse.aad.back_end.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {

    private Integer id;

    @NotBlank(message = "Job Title is mandatory")
    private String jobTitle;

    @NotBlank(message = "Company Name is mandatory")
//    @Pattern()
    private String company;

    @NotNull(message = "Location is mandatory")
    private String location;

    @NotNull(message = "Type is mandatory")
    private String type;

    @NotNull(message = "Description is mandatory")
    private String jobDescription;

    @NotNull(message = "Status is mandatory")
    private String status;
}
