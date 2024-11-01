package com.cursospring.moviestreaming.model;


import java.util.OptionalDouble;

public class Serie {

    private String titulo;
    private Categoria genero;
    private String Sinopsis;
    private String poster;
    private String actor;
    private Integer totalDeTemporadas;
    private Double evaluacion;


    public Serie(DatosSerie datosSerie) {
        this.actor = datosSerie.actor();
        this.evaluacion = OptionalDouble.of(Double.valueOf(datosSerie.evaluacion())).orElse(0.0);
        this.genero = Categoria.fromString(datosSerie.genero().split(",")[0]);
        this.poster = datosSerie.poster();
        this.Sinopsis = datosSerie.sinopsis();
        this.titulo = datosSerie.titulo();
        this.totalDeTemporadas = datosSerie.totalDeTemporadas();
    }


    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        Sinopsis = sinopsis;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalDeTemporadas() {
        return totalDeTemporadas;
    }

    public void setTotalDeTemporadas(Integer totalDeTemporadas) {
        this.totalDeTemporadas = totalDeTemporadas;
    }

    @Override
    public String toString() {
        return

                "Titulo='" + titulo + '\'' +
                ", actor='" + actor + '\'' +
                ", genero=" + genero +
                ", Sinopsis='" + Sinopsis + '\'' +
                ", poster='" + poster + '\'' +
                ", totalDeTemporadas=" + totalDeTemporadas +
                ", evaluacion=" + evaluacion;

    }
}
