package com.cursospring.moviestreaming.controllers;


import com.cursospring.moviestreaming.dto.EpisodioDTO;
import com.cursospring.moviestreaming.dto.SerieDTO;
import com.cursospring.moviestreaming.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping()
    public List<SerieDTO> obtenerTodasLasSeries(){
        return serieService.obtenerTodasLasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtenerListaTop5(){
        return  serieService.obtenerListaTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> ultimosLanzamientos(){
        return serieService.lanzamientosMasRecientes();
    }

    @GetMapping("/{id}")
    public SerieDTO buscarPorId(@PathVariable Long id){
        return serieService.buscarPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obtenerTodasLasTemporadas(@PathVariable Long id){
        return serieService.obtenerTodasLasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numeroDeTemporada}")
    public List<EpisodioDTO> obtenerEpisodioPorTemporada(@PathVariable long id,
                                                         @PathVariable long numeroDeTemporada){
        return serieService.obtenerEpisodioPorTemporada(id, numeroDeTemporada);
    }

    @GetMapping("/categoria/{categoriaSeleccionada}")
    public List<SerieDTO> obtenerSeriePorCategoria(@PathVariable String categoriaSeleccionada){
        return serieService.obtenerSeriePorCategoria(categoriaSeleccionada);
    }

}
