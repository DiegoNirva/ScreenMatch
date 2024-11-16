package com.cursospring.moviestreaming.service;

import com.cursospring.moviestreaming.dto.EpisodioDTO;
import com.cursospring.moviestreaming.dto.SerieDTO;
import com.cursospring.moviestreaming.model.Categoria;
import com.cursospring.moviestreaming.model.Episodio;
import com.cursospring.moviestreaming.model.Serie;
import com.cursospring.moviestreaming.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .map(s-> new SerieDTO(s.getId(), s.getTitulo(), s.getGenero(), s.getSinopsis(), s.getPoster(),
                        s.getActor(), s.getTotalDeTemporadas(), s.getEvaluacion()))
                .collect(Collectors.toList());
    }

    public SerieDTO buscarPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()){
             Serie s = serie.get();
             return new SerieDTO(s.getId(), s.getTitulo(), s.getGenero(), s.getSinopsis(), s.getPoster(),
                     s.getActor(), s.getTotalDeTemporadas(), s.getEvaluacion());
        }
        return null;
    }

    public List<EpisodioDTO> obtenerTodasLasTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e->new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroDeEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obtenerEpisodioPorTemporada(long id, long numeroDeTemporada) {
        return repository.obtenerEpisodioPorTemporada(id, numeroDeTemporada).stream()
                .map(e->new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroDeEpisodio()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obtenerSeriePorCategoria(String categoria) {
        Categoria categoria1 = Categoria.fromEspaniol(categoria);
        return convertirDatos(repository.findByGenero(categoria1));
    }
}
