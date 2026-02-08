package com.example.TareaFinal.config;

import com.example.TareaFinal.entity.RoleEntity;
import com.example.TareaFinal.entity.UsuarioEntity;
import com.example.TareaFinal.repository.RoleRepository;
import com.example.TareaFinal.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InitialConfig {
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;

    public InitialConfig(UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            RoleEntity roleEntity = roleRepository.findByRole("ADMIN").orElse(null);
            if(usuarioRepository.existsByCorreo("eleil@gmail.com")) {
                return;
            }
            usuarioEntity.setCorreo("eleil@gmail.com");
            usuarioEntity.setPassword(new BCryptPasswordEncoder().encode("12345"));
            usuarioEntity.setRoleEntity(roleEntity);
            usuarioRepository.save(usuarioEntity);
        };
    }
}
