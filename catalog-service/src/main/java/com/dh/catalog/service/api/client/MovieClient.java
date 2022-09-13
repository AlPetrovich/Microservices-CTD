package com.dh.catalog.service.api.client;


import com.dh.catalog.service.domain.dto.MovieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "movie-service")
public interface MovieClient {

    @GetMapping("/movies/{genre}")
    ResponseEntity<List<MovieDTO>> getMovieByGenre(@PathVariable(value = "genre") String genre);

    @GetMapping("/movies/withErrors/{genre}")
    ResponseEntity<List<MovieDTO>> getMovieByGenreWithThrowError(@PathVariable(value = "genre") String genre,
                                                                 @PathVariable(value = "throwError") boolean throwError);

}
