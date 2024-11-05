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

    private List<Serie> series;

    public Principal(SeriesRepository seriesRepository) {
           this.seriesRepository = seriesRepository;
    }


    //muestra el menu.

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            System.out.println("---------------------------------");
            System.out.println("Ingrese la opcion: " + '\n'
                    + "1) Buscar serie" + '\n'
                    + "2) Buscar Episodio" + '\n'
                    + "3) Mostrar serie buscadas" + '\n'
                    + "4) Buscar serie por nombre" + '\n'
                    + "5) Top 5 mejores series" + '\n'
                    + "6) Buscar por genero/categoria" + '\n'
                    + "7) Buscar por numero de temporada y evaluacion" + '\n'
                    + "8) Buscar por nombre de episodio" + '\n'
                    + "0) Salir");
            System.out.println("---------------------------------");
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
                case 4:
                    buscarSerie();
                    break;
                case 5:
                    buscartop5Series();
                case 6:
                    buscarPorCategoria();
                    break;
                case 7:
                    buscarPorNumeroTemporadaYEvaluacion();
                    break;
                case 8:
                    buscarEpisodioPorNombre();
                    break;
                default:
                    System.out.println("Opcion no valida");

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
        mostrarSeriesBuscadas();
        System.out.println("Ingrese el nombre de la serie a buscar");
        String buscar = in.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s->s.getTitulo().toLowerCase().contains(buscar.toLowerCase()))
                .findFirst();

        if(serie.isPresent()){
            List<DatosTemporada> datosTemporadas = new ArrayList<>();
            var serieEcontrada = serie.get();
            for (int i = 1; i <= serieEcontrada.getTotalDeTemporadas(); i++) {
                var json = consumoApi.obtenerDatos(URL_BASE + serieEcontrada.getTitulo().replace(" ", "+") + "&Season=" + i + KEY_API);
                DatosTemporada datosTemporadas1 = convertirDatos.convertirDatos(json, DatosTemporada.class);
                datosTemporadas.add(datosTemporadas1);
            }
            datosTemporadas.forEach(System.out::println);

            List<Episodio> episodios = datosTemporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e-> new Episodio(d.temporada(), e)))
                    .collect(Collectors.toList());
            serieEcontrada.setEpisodios(episodios);
            seriesRepository.save(serieEcontrada);

        }
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
        series = seriesRepository.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie ::getGenero))
                .forEach(System.out::println);

    }

    //metodo para buscar serie.
    private void buscarSerie(){
        System.out.println("Ingrese el nombre de la serie a buscar");
        String buscar = in.nextLine();

        Optional<Serie> serie = seriesRepository.findByTituloContainsIgnoreCase(buscar);
        System.out.println("Se busco la serie:" + serie.get());
    }
    //metodo top 5 mejores series.
    private void buscartop5Series() {
        series = seriesRepository.findTop5ByOrderByEvaluacionDesc();
        series.forEach(serie -> System.out.println("Serie: "+ serie.getTitulo() + " Evaluacion: " + serie.getEvaluacion()));
    }

    //metodo para buscar por categoria
    private  void buscarPorCategoria(){
        System.out.println("Ingrese el genero/categoria de la serie a buscar");
        String buscarCategoria = in.nextLine();
        var categoria = Categoria.fromEspaniol(buscarCategoria);
        List<Serie> seriesPorCategoria = seriesRepository.findByGenero(categoria);

        System.out.println("Series por categoria/genero " + buscarCategoria + " son:");
        seriesPorCategoria.forEach(System.out::println);
    }

    //metodo para buscar por nº de temporada y evaluacion
    private void buscarPorNumeroTemporadaYEvaluacion(){
        System.out.println("Ingrese el numero de temporada a buscar");
        int numTemporada = in.nextInt();
        System.out.println("Ingrese la evaluacion a buscar");
        double evaluacion = in.nextDouble();
        in.nextLine();

        List<Serie> seriePorTemYEva = seriesRepository.buscarSeriePorTemporadaYEvaluacion(numTemporada, evaluacion);

        if(!seriePorTemYEva.isEmpty()){
            System.out.println("Las series con "+ numTemporada + " Temporadas y de evaluacion "+ evaluacion + " son:");
            seriePorTemYEva.forEach(System.out::println);
        }else {
            System.out.println("No se encontro la serie.");
        }

    }

    //metodo para buscar episodio por nombre
    private void buscarEpisodioPorNombre(){
        System.out.println("Ingresa el nombre del episodio a buscar");
        String nombreEpisodio = in.nextLine();
        List<Episodio> episodios = seriesRepository.buscarEpisodioPorNombre(nombreEpisodio);
        episodios.forEach(episodio -> System.out.println("Serie:" + episodio.getSerie() + '\n'
                +" Episodio:" + episodio.getTitulo() + '\n'
                +" Temporada:" + episodio.getTemporada() + '\n'
                +" Evaluacion:" + episodio.getEvaluacion()));
    }

}






