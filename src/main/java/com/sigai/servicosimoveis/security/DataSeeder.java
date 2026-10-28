package com.sigai.servicosimoveis.security;
import com.sigai.servicosimoveis.model.Role;
import com.sigai.servicosimoveis.model.Usuario;
import com.sigai.servicosimoveis.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByLogin("admin").isEmpty()) {
            Usuario admin = Usuario.builder()
                    .login("admin")
                    .senha(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build();
            usuarioRepository.save(admin);
        }
    }
}