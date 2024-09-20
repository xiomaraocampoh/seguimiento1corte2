package org.Ejercicio1.Interfaces;

import org.Ejercicio1.domain.Cita;
import org.Ejercicio1.domain.Paciente;

import java.util.List;

public interface PacienteRepository {
    void savePaciente(Paciente paciente);
    Paciente findPacienteByName(String nombre);
    List<Paciente> findAllPacientes();
    void updatePaciente(Paciente paciente);
    void deletePaciente(String nombre);

    void saveCita(Cita cita);
    List<Cita> findCitasByPaciente(Paciente paciente);
    void deleteCita(Cita cita);
}
