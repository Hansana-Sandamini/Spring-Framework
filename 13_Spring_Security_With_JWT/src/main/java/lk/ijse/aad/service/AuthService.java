package lk.ijse.aad.service;

import lk.ijse.aad.dto.AuthDTO;
import lk.ijse.aad.dto.AuthResponseDTO;
import lk.ijse.aad.dto.RegisterDTO;
import lk.ijse.aad.entity.Role;
import lk.ijse.aad.entity.User;
import lk.ijse.aad.repository.UserRepository;
import lk.ijse.aad.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO authenticate(AuthDTO authDTO) {
        User user = userRepository.findByUsername(authDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if (!passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseDTO(token);
    }

    public String register(RegisterDTO registerDTO) {
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.valueOf(registerDTO.getRole()))
                .build();
        userRepository.save(user);
        return "User Registration Success";
    }

}
