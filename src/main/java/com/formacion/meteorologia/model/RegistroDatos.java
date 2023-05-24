package com.formacion.meteorologia.model;

import java.time.LocalDate;
import java.util.Objects;

public class RegistroDatos {

    private LocalDate fecha;
    private String estacionMeteorologica;
    private String provincia;
    private TemperaturaHora maxima;
    private TemperaturaHora minima;
    private float precipitacion;

    public RegistroDatos() {
    }

    public RegistroDatos(LocalDate fecha, String estacionMeteorologica, String provincia, TemperaturaHora maxima, TemperaturaHora minima, float precipitacion) {
        this.fecha = fecha;
        this.estacionMeteorologica = estacionMeteorologica;
        this.provincia = provincia;
        this.maxima = maxima;
        this.minima = minima;
        this.precipitacion = precipitacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstacionMeteorologica() {
        return estacionMeteorologica;
    }

    public void setEstacionMeteorologica(String estacionMeteorologica) {
        this.estacionMeteorologica = estacionMeteorologica;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public TemperaturaHora getMaxima() {
        return maxima;
    }

    public void setMaxima(TemperaturaHora maxima) {
        this.maxima = maxima;
    }

    public TemperaturaHora getMinima() {
        return minima;
    }

    public void setMinima(TemperaturaHora minima) {
        this.minima = minima;
    }

    public float getPrecipitacion() {
        return precipitacion;
    }

    public void setPrecipitacion(float precipitacion) {
        this.precipitacion = precipitacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroDatos that = (RegistroDatos) o;
        return Float.compare(that.precipitacion, precipitacion) == 0 && Objects.equals(fecha, that.fecha) && Objects.equals(estacionMeteorologica, that.estacionMeteorologica) && Objects.equals(provincia, that.provincia) && Objects.equals(maxima, that.maxima) && Objects.equals(minima, that.minima);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, estacionMeteorologica, provincia, maxima, minima, precipitacion);
    }

    @Override
    public String toString() {
        return "RegistroDatos{" +
                "fecha=" + fecha +
                ", estacionMeteorologica='" + estacionMeteorologica + '\'' +
                ", provincia='" + provincia + '\'' +
                ", maxima=" + maxima +
                ", minima=" + minima +
                ", precipitación=" + precipitacion +
                '}';
    }
}
