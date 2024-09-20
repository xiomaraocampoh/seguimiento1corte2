package org.Ejercicio1.domain;

import java.io.Serializable;

public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fecha;
    private String hora;
    private String motivo;
    private Paciente paciente;

    public Cita(String fecha, String hora, String motivo, Paciente paciente) {
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.paciente = paciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", motivo='" + motivo + '\'' +
                ", paciente=" + paciente +
                '}';
    }
}
