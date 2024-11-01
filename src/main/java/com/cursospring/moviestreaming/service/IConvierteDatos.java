package com.cursospring.moviestreaming.service;

public interface IConvierteDatos {

    public <T> T convertirDatos(String json, Class<T> clase);
}
