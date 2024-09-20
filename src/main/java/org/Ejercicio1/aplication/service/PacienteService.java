package org.Ejercicio1.aplication.service;

import org.Ejercicio1.Interfaces.PacienteRepository;
import org.Ejercicio1.domain.Cita;
import org.Ejercicio1.domain.Paciente;

import java.util.List;

public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public void crearPaciente(Paciente paciente) {
        repository.savePaciente(paciente);
    }

    public Paciente obtenerPaciente(String nombre) {
        return repository.findPacienteByName(nombre);
    }

    public List<Paciente> listarPacientes() {
        return repository.findAllPacientes();
    }

    public void actualizarPaciente(Paciente paciente) {
        repository.updatePaciente(paciente);
    }

    public void eliminarPaciente(String nombre) {
        repository.deletePaciente(nombre);
    }

    public void registrarCita(Cita cita) {
        repository.saveCita(cita);
    }

    public List<Cita> listarCitasPorPaciente(Paciente paciente) {
        return repository.findCitasByPaciente(paciente);
    }

    public void eliminarCita(Cita cita) {
        repository.deleteCita(cita);
    }
}

