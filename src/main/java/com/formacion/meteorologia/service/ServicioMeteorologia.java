package com.formacion.meteorologia.service;

import com.formacion.meteorologia.model.RegistroDatos;
import com.formacion.meteorologia.model.TemperaturaHora;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class ServicioMeteorologia {

    private static ServicioMeteorologia instance;
    private List<RegistroDatos> datos;

    private ServicioMeteorologia(List<RegistroDatos> datos) {
        this.datos = datos;
    }

    public static ServicioMeteorologia getInstance(List<RegistroDatos> init) {
        if (instance == null) {
            instance = new ServicioMeteorologia(init);
        }
        return instance;
    }


    public Optional<RegistroDatos> maximaTotal(){
        return datos.stream().max(Comparator.comparing(x -> x.getMaxima().getTemperatura()));
    }

    public Optional<RegistroDatos> minimaTotal(){
        return datos.stream().min(Comparator.comparing(x -> x.getMinima().getTemperatura()));
    }



    public Map<String, Optional<TemperaturaHora>> temperaturaHoraMaximaProvincias(){
        Collector<RegistroDatos, ?, Optional<TemperaturaHora>> c = Collectors.mapping(RegistroDatos::getMaxima,
                Collectors.maxBy(((o1, o2) -> Float.compare(o1.getTemperatura(), o2.getTemperatura()))));

        return datos.stream().collect(Collectors.groupingBy(RegistroDatos::getProvincia,
                TreeMap::new,
                c));
    }

    public Map<String, Optional<TemperaturaHora>> temperaturaHoraMinimaProvincias(){
        Collector<RegistroDatos, ?, Optional<TemperaturaHora>> c = Collectors.mapping(RegistroDatos::getMinima,
                Collectors.minBy(((o1, o2) -> Float.compare(o1.getTemperatura(), o2.getTemperatura()))));

        return datos.stream().collect(Collectors.groupingBy(RegistroDatos::getProvincia,
                TreeMap::new,
                c));
    }

    public Map<String, Double> temperaturaMediaProvincias(){
        return datos.stream()
                .collect(Collectors.groupingBy(RegistroDatos::getProvincia,
                TreeMap::new,//Con esto indicamos de que tipo queremos el objeto, en este caso un Map ordenado segun la clave
                Collectors.averagingDouble(
                        x -> (x.getMaxima().getTemperatura() + x.getMinima().getTemperatura())/2
                )
                ));
    }


    public List<RegistroDatos> datosProvincia(String provincia){
        return datos.stream().filter(x -> x.getProvincia().equals(provincia))
                .sorted(Comparator.comparing(RegistroDatos::getEstacionMeteorologica))
                .collect(Collectors.toList());
    }


}
