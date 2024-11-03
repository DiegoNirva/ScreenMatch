package com.cursospring.moviestreaming.repository;

import com.cursospring.moviestreaming.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Serie, Long> {
}
