package com.cursospring.moviestreaming.model;

import com.cursospring.moviestreaming.repository.SeriesRepository;
import com.cursospring.moviestreaming.service.ConsumoApi;
import com.cursospring.moviestreaming.service.ConvertirDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private ConsumoApi consumoApi = new ConsumoApi();

    private ConvertirDatos convertirDatos = new ConvertirDatos();

    private Scanner in = new Scanner(System.in);

    private final String URL_BASE = "http://www.omdbapi.com/?t=";

    private final String KEY_API = "&apikey=69fb1797";

    private SeriesRepository seriesRepository;

    private List<DatosSerie> datosSeries = new ArrayList<>();

    public Principal(SeriesRepository seriesRepository) {
           this.seriesRepository = seriesRepository;
    }


    //muestra el menu.

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            System.out.println("Ingrese la opcion: " + '\n'
                    + "1) Buscar serie" + '\n'
                    + "2) Buscar Episodio" + '\n'
                    + "3) Mostrar serie buscadas" + '\n'
                    + "0) Salir");
            opcion = in.nextInt();
            in.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodio();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    break;
                default:

            }
        }
    }

    //metodo para buscar serie y covertir en un record
    private DatosSerie getDatosSerie() {
        System.out.println("Ingrese el nombre de la serie que desea buscar");
        String buscar = in.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + buscar.replace(" ", "+") + KEY_API);
        System.out.println(json);
        var datos = convertirDatos.convertirDatos(json, DatosSerie.class);
        return datos;
    }

    //metodo para buscar serie y episodio.
    private void buscarEpisodio() {
        DatosSerie datosSerie = getDatosSerie();
        List<DatosTemporada> datosTemporadas = new ArrayList<>();

        for (int i = 1; i <= datosSerie.totalDeTemporadas(); i++) {
            var json = consumoApi.obtenerDatos(URL_BASE + datosSerie.titulo().replace(" ", "+") + "&Season=" + i + KEY_API);
            DatosTemporada datosTemporadas1 = convertirDatos.convertirDatos(json, DatosTemporada.class);
            datosTemporadas.add(datosTemporadas1);
        }
        datosTemporadas.forEach(System.out::println);
    }

    //metodo para agregar la serie buscada en una list de tipo Datoserie (record)
    private void buscarSerieWeb(){
        DatosSerie datos = getDatosSerie();
        //datosSeries.add(datos);
        Serie serie = new Serie(datos);
        seriesRepository.save(serie);
        System.out.println(datosSeries);
    }

    //metodo para recorrer e imprimir las series buscadas
    private void mostrarSeriesBuscadas() {
        List<Serie> series = seriesRepository.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie ::getGenero))
                .forEach(System.out::println);

    }


}






