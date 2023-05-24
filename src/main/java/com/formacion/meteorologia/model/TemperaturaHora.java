package com.formacion.meteorologia.model;

import java.time.LocalTime;
import java.util.Objects;

public class TemperaturaHora {


    private Float temperatura;
    private LocalTime hora;

    public TemperaturaHora() {
    }

    public TemperaturaHora(Float temperatura, LocalTime hora) {
        this.temperatura = temperatura;
        this.hora = hora;
    }

    public Float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Float temperatura) {
        this.temperatura = temperatura;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemperaturaHora that = (TemperaturaHora) o;
        return Objects.equals(temperatura, that.temperatura) && Objects.equals(hora, that.hora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperatura, hora);
    }

    @Override
    public String toString() {
        return "TemperaturaHora{" +
                "temperatura=" + temperatura +
                ", hora=" + hora +
                '}';
    }
}
