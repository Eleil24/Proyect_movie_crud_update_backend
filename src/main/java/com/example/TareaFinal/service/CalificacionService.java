package com.example.TareaFinal.service;

import com.example.TareaFinal.dto.request.RateRequest;
import com.example.TareaFinal.dto.response.RateResponse;
import com.example.TareaFinal.dto.response.ResponseBase;

import java.util.List;

public interface CalificacionService {
    ResponseBase<RateResponse> calificarPelicula(String username, RateRequest request);
    ResponseBase<List<RateResponse>> listarCalificacionesUsuario(String username);
    ResponseBase<String> eliminarCalificacion(String username, String nombrePelicula);
}
