package com.dh.serie.service.service;


import com.dh.serie.service.model.Serie;
import com.dh.serie.service.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;
    @Autowired
    public SeriesService(SeriesRepository seriesRepository){
        this.seriesRepository = seriesRepository;
    }

    public Serie findById(String id){
        return seriesRepository.findById(id).orElse(null);
    }

    public List<Serie> findAll(){
        return seriesRepository.findAll();
    }

    public Serie saveSeries(Serie serie){
        return seriesRepository.save(serie);
    }
}
