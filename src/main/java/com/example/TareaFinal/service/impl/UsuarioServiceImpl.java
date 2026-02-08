package com.example.TareaFinal.service.impl;

import com.example.TareaFinal.dto.request.LoginRequest;
import com.example.TareaFinal.dto.request.UsuarioCreateRequest;
import com.example.TareaFinal.dto.response.UsuarioResponse;
import com.example.TareaFinal.entity.RoleEntity;
import com.example.TareaFinal.entity.UsuarioEntity;
import com.example.TareaFinal.repository.RoleRepository;
import com.example.TareaFinal.repository.UsuarioRepository;
import com.example.TareaFinal.service.JwtService;
import com.example.TareaFinal.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetaisServiceImpl userDetaisService;

    public UsuarioServiceImpl(RoleRepository roleRepository, UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager, JwtService jwtService, UserDetaisServiceImpl userDetaisService) {
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetaisService = userDetaisService;
    }

    @Override
    public UsuarioResponse createUser(UsuarioCreateRequest usuarioCreateRequest) {
        // verificar que el rol exista en la base de datos
        // verificar que el username no sea duplicado
        String role = usuarioCreateRequest.getRole();
        String username = usuarioCreateRequest.getCorreo();
        Optional<RoleEntity> roleEntityOptional = roleRepository.findByRole(role);
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findByCorreo(username);

        if (roleEntityOptional.isEmpty() || usuarioEntityOptional.isPresent()) {
            return null;
        }
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setCorreo(usuarioCreateRequest.getCorreo());
        usuarioEntity.setPassword(new BCryptPasswordEncoder().encode(usuarioCreateRequest.getPassword()));
        RoleEntity roleEntity = roleEntityOptional.get();
        usuarioEntity.setRoleEntity(roleEntity);
        usuarioRepository.save(usuarioEntity);
        return new UsuarioResponse(usuarioEntity.getUsuarioId(),usuarioEntity.getCorreo(), role);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getCorreo(),
                        loginRequest.getPassword()));
        if (auth.isAuthenticated()) {
            UserDetails correo = userDetaisService.loadUserByUsername(loginRequest.getCorreo());
            String token = jwtService.generateToken(correo);
            return token;
        }
        return null;
    }
}
