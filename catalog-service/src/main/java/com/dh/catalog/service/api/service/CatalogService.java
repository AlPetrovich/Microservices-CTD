package com.dh.catalog.service.api.service;

import com.dh.catalog.service.api.client.MovieClient;
import com.dh.catalog.service.domain.dto.MovieDTO;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {

    private final Logger LOG = LoggerFactory.getLogger(CatalogService.class);
    private final MovieClient movieClient;

    @Autowired
    public CatalogService(MovieClient movieClient){
        this.movieClient = movieClient;
    }

    //obtener peliculas por genero
    public ResponseEntity<List<MovieDTO>> findMovieByGenre(String genre){
        LOG.info("Buscando peliculas por genero" + genre);
        return movieClient.getMovieByGenre(genre);
    }

    //obtener peliculas por genero -- prueba CircuitBreaker
    @CircuitBreaker(name = "movies", fallbackMethod = "moviesFallBackMethod")
    public ResponseEntity<List<MovieDTO>> findMovieByGenre(String genre, Boolean throwError){
        LOG.info("Buscando peliculas por genero" + genre);
        return movieClient.getMovieByGenreWithThrowError(genre, throwError);
    }

    //metodo de fallback
    public ResponseEntity<List<MovieDTO>> moviesFallBackMethod(CallNotPermittedException exception){
        LOG.info("Error al buscar peliculas por genero, CIRCUIT BREAKER ACTIVADO");
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK); //devuelve lista vacia
    }

    //guardar pelicula con RabbitMQ - no implementado


}