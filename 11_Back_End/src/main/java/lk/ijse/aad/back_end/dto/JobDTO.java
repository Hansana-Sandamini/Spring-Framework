package lk.ijse.aad.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private String jobTitle;
    private String company;
    private String location;
    private String type;
    private String jobDescription;
}
