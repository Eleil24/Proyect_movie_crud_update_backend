package com.example.TareaFinal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UsuarioResponse {
    private int usuarioId;
    private String correo;
    private String role;
}
