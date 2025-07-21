package lk.ijse.aad.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter {
    private final JwtUtil jwtUtil;
}
