package org.Ejercicio1.aplication;

import org.Ejercicio1.aplication.service.PacienteService;
import org.Ejercicio1.domain.Cita;
import org.Ejercicio1.domain.Paciente;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsolaUi {

    private final PacienteService pacienteService;

    public ConsolaUi(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    public void iniciar() {
        while (true) {
            String[] options = {
                    "Registrar Paciente",
                    "Actualizar Paciente",
                    "Registrar Cita",
                    "Eliminar Cita",
                    "Mostrar Lista de Pacientes",
                    "Mostrar Citas de un Paciente",
                    "Salir"
            };

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción",
                    "Gestión de Pacientes",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (opcion) {
                case 0:
                    registrarPaciente();
                    break;
                case 1:
                    actualizarPaciente();
                    break;
                case 2:
                    registrarCita();
                    break;
                case 3:
                    eliminarCita();
                    break;
                case 4:
                    mostrarListaPacientes();
                    break;
                case 5:
                    mostrarCitasPaciente();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema. ¡Hasta luego!");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void registrarPaciente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del paciente:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del paciente:"));
        String genero = JOptionPane.showInputDialog("Ingrese el género del paciente:");
        String direccion = JOptionPane.showInputDialog("Ingrese la dirección del paciente:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del paciente:");

        Paciente paciente = new Paciente(nombre, apellido, edad, genero, direccion, telefono);
        pacienteService.crearPaciente(paciente);
        JOptionPane.showMessageDialog(null, "Paciente registrado con éxito.");
    }

    private void actualizarPaciente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente a actualizar:");
        Paciente paciente = pacienteService.obtenerPaciente(nombre);
        if (paciente != null) {
            String nuevoTelefono = JOptionPane.showInputDialog("Ingrese el nuevo teléfono del paciente:");
            paciente.setTelefono(nuevoTelefono);
            pacienteService.actualizarPaciente(paciente);
            JOptionPane.showMessageDialog(null, "Paciente actualizado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
        }
    }

    private void registrarCita() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
        Paciente paciente = pacienteService.obtenerPaciente(nombre);
        if (paciente != null) {
            String fecha = JOptionPane.showInputDialog("Ingrese la fecha y hora de la cita (yyyy-MM-dd HH:mm):");
            String motivo = JOptionPane.showInputDialog("Ingrese el motivo de la cita:");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fechaHora = LocalDateTime.parse(fecha, formatter);

            // Convertir LocalDateTime a String
            String fechaString = fechaHora.toLocalDate().toString();
            String horaString = fechaHora.toLocalTime().toString();

            // Usar el constructor existente que recibe String
            Cita cita = new Cita(fechaString, horaString, motivo, paciente);
            pacienteService.registrarCita(cita);
            JOptionPane.showMessageDialog(null, "Cita registrada con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
        }
    }

    private void eliminarCita() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
        Paciente paciente = pacienteService.obtenerPaciente(nombre);
        if (paciente != null) {
            mostrarCitasPaciente(); // Mostrar citas antes de eliminar
            String motivo = JOptionPane.showInputDialog("Ingrese el motivo de la cita a eliminar:");
            List<Cita> citas = pacienteService.listarCitasPorPaciente(paciente);
            Cita citaAEliminar = citas.stream()
                    .filter(c -> c.getMotivo().equals(motivo))
                    .findFirst()
                    .orElse(null);
            if (citaAEliminar != null) {
                pacienteService.eliminarCita(citaAEliminar);
                JOptionPane.showMessageDialog(null, "Cita eliminada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Cita no encontrada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
        }
    }

    private void mostrarListaPacientes() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        StringBuilder lista = new StringBuilder("Lista de pacientes:\n");
        pacientes.forEach(p -> lista.append(p).append("\n"));
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    private void mostrarCitasPaciente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
        Paciente paciente = pacienteService.obtenerPaciente(nombre);
        if (paciente != null) {
            List<Cita> citas = pacienteService.listarCitasPorPaciente(paciente);
            StringBuilder lista = new StringBuilder("Citas del paciente:\n");
            citas.forEach(c -> lista.append(c).append("\n"));
            JOptionPane.showMessageDialog(null, lista.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
        }
    }
}

