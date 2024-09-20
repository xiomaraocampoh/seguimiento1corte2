package org.Ejercicio1.infraestructure.repository;

import org.Ejercicio1.Interfaces.PacienteRepository;
import org.Ejercicio1.domain.Cita;
import org.Ejercicio1.domain.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePacienteRepository implements PacienteRepository {
    private static final String PACIENTES_FILE_NAME = "pacientes.dat";
    private static final String CITAS_FILE_NAME = "citas.dat";

    @Override
    public void savePaciente(Paciente paciente) {
        List<Paciente> pacientes = findAllPacientes();
        pacientes.add(paciente);
        saveAll(pacientes, PACIENTES_FILE_NAME);
    }

    public Paciente findPacienteByName(String nombre) {
        return findAllPacientes().stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Paciente> findAllPacientes() {
        return readFromFile(PACIENTES_FILE_NAME);
    }

    @Override
    public void updatePaciente(Paciente paciente) {
        List<Paciente> pacientes = findAllPacientes();
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getNombre().equals(paciente.getNombre())) {
                pacientes.set(i, paciente);
                break;
            }
        }
        saveAll(pacientes, PACIENTES_FILE_NAME);
    }

    @Override
    public void deletePaciente(String nombre) {
        List<Paciente> pacientes = findAllPacientes();
        pacientes.removeIf(p -> p.getNombre().equals(nombre));
        saveAll(pacientes, PACIENTES_FILE_NAME);
    }

    @Override
    public void saveCita(Cita cita) {
        List<Cita> citas = readFromFile(CITAS_FILE_NAME);
        citas.add(cita);
        saveAll(citas, CITAS_FILE_NAME);
    }

    @Override
    public List<Cita> findCitasByPaciente(Paciente paciente) {
        List<Cita> citas = readFromFile(CITAS_FILE_NAME);
        List<Cita> citasPaciente = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getPaciente().equals(paciente)) {
                citasPaciente.add(cita);
            }
        }
        return citasPaciente;
    }


    @Override
    public void deleteCita(Cita cita) {
        List<Cita> citas = readFromFile(CITAS_FILE_NAME);
          citas.remove(cita);
          saveAll(citas, CITAS_FILE_NAME);

    }

    private <T> List<T> readFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private <T> void saveAll(List<T> list, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}