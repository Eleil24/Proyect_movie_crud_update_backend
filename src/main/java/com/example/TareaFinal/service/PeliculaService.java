package com.example.TareaFinal.service;

import com.example.TareaFinal.dto.request.PeliculaCreateRequest;
import com.example.TareaFinal.dto.request.PeliculaUpdateRequest;
import com.example.TareaFinal.dto.response.PeliculaResponse;
import com.example.TareaFinal.dto.response.ResponseBase;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface PeliculaService {
    ResponseBase <PeliculaResponse> crearPelicula(PeliculaCreateRequest peliculaCreateRequest, String correo);
    ResponseBase<PeliculaResponse> actualizarPelicula(int idPelicula, PeliculaUpdateRequest peliculaUpdateRequest);
    ResponseBase <String> eliminarPelicula(int nombrePelicula);
    List<PeliculaResponse> findPeliculas(Pageable pageable, String nombre, Integer anioEstreno, String categoria);
    List<PeliculaResponse> findAll();
    List<PeliculaResponse> buscarPorId(int id);

}
