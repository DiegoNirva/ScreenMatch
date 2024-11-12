package com.cursospring.moviestreaming.controllers;


import com.cursospring.moviestreaming.dto.SerieDTO;
import com.cursospring.moviestreaming.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
