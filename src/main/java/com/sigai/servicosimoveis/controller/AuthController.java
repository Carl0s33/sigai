package com.sigai.servicosimoveis.controller;

import com.sigai.servicosimoveis.dto.LoginRequestDTO;
import com.sigai.servicosimoveis.dto.TokenResponseDTO;
import com.sigai.servicosimoveis.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getLogin());
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}