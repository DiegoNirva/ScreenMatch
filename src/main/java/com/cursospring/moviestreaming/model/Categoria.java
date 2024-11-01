package com.cursospring.moviestreaming.model;

public enum Categoria {

    ACCION("Action"),

    ROMANCE("Romance"),

    CRIMEN("Crime"),

    DRAMA("Drama"),

    COMEDIA("Comedy");

    private String categoriaOmdb;

    Categoria(String categoriaOmdb){
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString(String text){
        for(Categoria categoria : Categoria.values()){
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
}
