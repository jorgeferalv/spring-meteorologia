package com.formacion.meteorologia.util;

import com.formacion.meteorologia.model.TemperaturaHora;

import java.util.Map;
import java.util.Optional;

public class PrintUtil {
    public static void printMapProvinciaTemperaturaHora(Map<String, Optional<TemperaturaHora>> map){

        //Cada provincia con su temperatura/hora
        map.keySet().stream()
                .forEach(x -> {
                    if (map.get(x).isPresent()){
                        TemperaturaHora th = map.get(x).get();
                        System.out.printf("Provincia: %-25s | Temperatura: % 6.2f°C | Hora: %s%n", x, th.getTemperatura(),
                                th.getHora());
                    }
                }
        );

    }
}
