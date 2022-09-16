package com.dh.catalog.service.api.controller;

import com.dh.catalog.service.api.service.CatalogService;
import com.dh.catalog.service.domain.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final CatalogService catalogService;
    @Autowired
    public CatalogController(CatalogService catalogService){
        this.catalogService = catalogService;
    }

    // por medio de cliente feign obtengo la lista de peliculas por genero
    @GetMapping("/{genre}")
    public ResponseEntity<List<MovieDTO>> getGenre(@PathVariable String genre){
        return catalogService.findMovieByGenre(genre);
    }

    @GetMapping("/withErrors/{genre}")
    public ResponseEntity<List<MovieDTO>> getGenre(@PathVariable String genre, @RequestParam("throwError") boolean throwError){
        return catalogService.findMovieByGenre(genre, throwError);
    }
}
