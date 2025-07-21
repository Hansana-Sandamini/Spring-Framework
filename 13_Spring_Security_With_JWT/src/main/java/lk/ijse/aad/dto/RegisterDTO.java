package lk.ijse.aad.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String role; // USER or ADMIN
}
