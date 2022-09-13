package com.dh.catalog.service.api.controller;

import com.dh.catalog.service.api.service.CatalogService;
import com.dh.catalog.service.domain.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final CatalogService catalogService;
    @Autowired
    public CatalogController(CatalogService catalogService){
        this.catalogService = catalogService;
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<MovieDTO>> getGenre(@PathVariable String genre){
        return catalogService.findMovieByGenre(genre);
    }

    @GetMapping("/withErrors/{genre}")
    public ResponseEntity<List<MovieDTO>> getGenre(@PathVariable String genre, @PathVariable boolean throwError){
        return catalogService.findMovieByGenre(genre, throwError);
    }
}
