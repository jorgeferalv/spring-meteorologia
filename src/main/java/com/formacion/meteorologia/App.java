package com.formacion.meteorologia;

import com.formacion.meteorologia.data.Init;
import com.formacion.meteorologia.model.RegistroDatos;
import com.formacion.meteorologia.model.TemperaturaHora;
import com.formacion.meteorologia.service.ServicioMeteorologia;
import com.formacion.meteorologia.util.PrintUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@SpringBootApplication
public class App {

	static ServicioMeteorologia servicio;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		servicio = ServicioMeteorologia.getInstance(Init.getAll());

		imprimirInformeMeteorologico();
	}

	private static void imprimirInformeMeteorologico() {

		System.out.println("\n\n**************************************************");
		System.out.println("DATOS METEOROLÓGICOS DEL DÍA 31 DE OCTUBRE DE 2017");
		System.out.println("**************************************************\n");
		maximaYMinimaTotal();
		maximaPorProvincias();
		minimaPorProvincias();
		mediasPorProvincias();

		datosPorProvincia("León");
	}

	private static void maximaYMinimaTotal() {

		System.out.println("TEMPERATURAS MÁXIMA Y MÍNIMA DE ESPAÑA");
		System.out.println("-----------------------------------------------------");

		Optional<RegistroDatos> maxima, minima;

		maxima = servicio.maximaTotal();
		minima = servicio.minimaTotal();

		if (maxima.isPresent()) {
			System.out.printf("MÁXIMA -> Estación meteorológica: %s (%s) | Temperatura: %.2f°C | Hora: %s%n",
					maxima.get().getEstacionMeteorologica(),
					maxima.get().getProvincia(),
					maxima.get().getMaxima().getTemperatura(),
					maxima.get().getMaxima().getHora());
		}else
			System.out.println("No tenemos datos sobre la temperatura máxima");

		if (minima.isPresent()) {
			System.out.printf("MÍNIMA -> Estación meteorológica: %s (%s) | Temperatura: %.2f°C | Hora: %s%n",
					minima.get().getEstacionMeteorologica(),
					minima.get().getProvincia(),
					minima.get().getMinima().getTemperatura(),
					minima.get().getMinima().getHora());
		}else
			System.out.println("No tenemos datos sobre la temperatura máxima");

		System.out.println("");

	}

	private static void datosPorProvincia(String provincia) {
		System.out.println("DATOS DE LA PROVINCIA DE " + provincia.toUpperCase());
		System.out.println("-----------------------------------------------------");

		servicio.datosProvincia(provincia).stream()
				.forEach(r -> System.out.printf(
						"Estación meteorológica: %-35s | Máxima: % 6.2f (%s) | Mínima: % 6.2f (%s) | Precipitación: %.2f%n",
						r.getEstacionMeteorologica(), r.getMaxima().getTemperatura(), r.getMaxima().getHora(),
						r.getMinima().getTemperatura(), r.getMinima().getHora(), r.getPrecipitacion()));
	}



	private static void maximaPorProvincias() {
		System.out.println("TEMPERATURA MÁXIMA POR PROVINCIAS");
		System.out.println("-----------------------------------------------------");
		Map<String, Optional<TemperaturaHora>> maximas = servicio.temperaturaHoraMaximaProvincias();

		PrintUtil.printMapProvinciaTemperaturaHora(maximas);
		System.out.println("");
	}

	private static void minimaPorProvincias() {
		System.out.println("TEMPERATURA MÍNIMA POR PROVINCIAS");
		System.out.println("-----------------------------------------------------");
		Map<String, Optional<TemperaturaHora>> minimas = servicio.temperaturaHoraMinimaProvincias();

		PrintUtil.printMapProvinciaTemperaturaHora(minimas);
		System.out.println("");

	}

	private static void mediasPorProvincias() {
		System.out.println("TEMPERATURA MEDIA POR PROVINCIAS");
		System.out.println("-----------------------------------------------------");
		Map<String, Double> medias = servicio.temperaturaMediaProvincias();

		medias.forEach(
				(provincia, media) -> System.out.printf("Provincia: %-25s | Media: %5.2f°C%n", provincia, media)
		);
		System.out.println("");
	}

}
