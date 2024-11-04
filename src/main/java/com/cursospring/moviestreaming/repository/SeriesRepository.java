package com.cursospring.moviestreaming.repository;

import com.cursospring.moviestreaming.model.Categoria;
import com.cursospring.moviestreaming.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Serie, Long> {


    //Query de busqueda por titulo
    Optional<Serie> findByTituloContainsIgnoreCase(String nombre);

    //Query de mejores 5 seres por evaluacion
    List<Serie> findTop5ByOrderByEvaluacionDesc();

    //Query de busqueda por genero
    List<Serie> findByGenero(Categoria categoria);

    //Query de busqueda por numero de temporadas y evaluacion
    List<Serie> findBytotalDeTemporadasAndEvaluacion(Integer numeroDeTemporadas, Double evaluacion);
}
