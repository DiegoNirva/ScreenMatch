package com.cursospring.moviestreaming.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {

    private Integer temporada;

    private String titulo;

    private Integer numeroDeEpisodio;

    private Double evaluacion;

    private LocalDate fechaDeLanzamiento;

    public Episodio(Integer temporada, DatosEpisodio d) {

        this.temporada = temporada;
        this.titulo = d.titulo();
        this.numeroDeEpisodio = d.numeroEpisodio();

        try {
            this.evaluacion = Double.valueOf(d.evaluacion());

        }catch (NumberFormatException e){
            this.evaluacion = 0.0;
        }
        try {
            this.fechaDeLanzamiento = LocalDate.parse(d.fechaLansamiento());
        }catch (DateTimeParseException e){
            this.fechaDeLanzamiento = null;
        }
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Integer getNumeroDeEpisodio() {
        return numeroDeEpisodio;
    }

    public void setNumeroDeEpisodio(Integer numeroDeEpisodio) {
        this.numeroDeEpisodio = numeroDeEpisodio;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return
                "temporada=" + temporada +
                ", evaluacion=" + evaluacion +
                ", titulo='" + titulo + '\'' +
                ", numeroDeEpisodio=" + numeroDeEpisodio +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento
                ;
    }
}
