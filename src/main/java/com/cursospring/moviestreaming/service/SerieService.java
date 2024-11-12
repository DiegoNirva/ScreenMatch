package com.cursospring.moviestreaming.service;

import com.cursospring.moviestreaming.dto.SerieDTO;
import com.cursospring.moviestreaming.model.Serie;
import com.cursospring.moviestreaming.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SeriesRepository repository;

    public List<SerieDTO> obtenerTodasLasSeries(){
        return convertirDatos(repository.findAll());
    }

    public List<SerieDTO> obtenerListaTop5() {
        return convertirDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDTO> lanzamientosMasRecientes(){
        return convertirDatos(repository.lanzamientosMasRecientes());
    }


    public List<SerieDTO> convertirDatos(List<Serie> series){
        return series.stream()
                .map(s-> new SerieDTO(s.getTitulo(), s.getGenero(), s.getSinopsis(), s.getPoster(),
                        s.getActor(), s.getTotalDeTemporadas(), s.getEvaluacion()))
                .collect(Collectors.toList());
    }
}
