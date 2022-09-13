package com.dh.movie.service.api.service;

import com.dh.movie.service.domain.models.Movie;
import com.dh.movie.service.domain.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository movieRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //Buscar lista por generos -- queda trabajar error
    public List<Movie> findByGenre(String genre){
        LOG.info("Buscando peliculas por genero " + genre);
        return movieRepository.findByGenre(genre);
    }

    //Peliculas por genero -- verificar Resilience4j
    public List<Movie> findByGenre(String genre, Boolean throwError){
        LOG.info("Buscando peliculas por genero " + genre);
        if (throwError){
            LOG.error("Error al buscar peliculas por genero " + genre);
            throw new RuntimeException("Error forzado");
        }
        return movieRepository.findByGenre(genre);
    }

}
