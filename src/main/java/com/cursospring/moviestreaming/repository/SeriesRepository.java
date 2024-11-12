package com.cursospring.moviestreaming.repository;

import com.cursospring.moviestreaming.model.Categoria;
import com.cursospring.moviestreaming.model.Episodio;
import com.cursospring.moviestreaming.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Serie, Long> {


    //Query de busqueda por titulo
    Optional<Serie> findByTituloContainsIgnoreCase(String nombre);

    //Query de mejores 5 seres por evaluacion
    List<Serie> findTop5ByOrderByEvaluacionDesc();

    //Query de busqueda por genero
    List<Serie> findByGenero(Categoria categoria);

    //JPQL de busqueda por numero de temporadas y evaluacion
    @Query("SELECT s FROM Serie s WHERE s.totalDeTemporadas <= :totalDeTemporadas AND s.evaluacion >= :evaluacion")
    List<Serie> buscarSeriePorTemporadaYEvaluacion(Integer totalDeTemporadas, Double evaluacion);

    //JPQL de busqueda por nombre de episodio
    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreEpisodio%")
    List<Episodio> buscarEpisodioPorNombre(String nombreEpisodio);

    @Query(" SELECT s FROM Serie s " + " JOIN s.episodios e " + " GROUP BY s " + " ORDER BY MAX(e.fechaDeLanzamiento) DESC LIMIT 5 ")
    List<Serie> lanzamientosMasRecientes();

}
