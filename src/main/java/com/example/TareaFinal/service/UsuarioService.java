package com.example.TareaFinal.service;

import com.example.TareaFinal.dto.request.LoginRequest;
import com.example.TareaFinal.dto.request.UsuarioCreateRequest;
import com.example.TareaFinal.dto.response.UsuarioResponse;

public interface UsuarioService {
    UsuarioResponse createUser(UsuarioCreateRequest usuarioCreateRequest);
    String login(LoginRequest loginRequest);
}
