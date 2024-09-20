package org.Ejercicio2.aplication;

import org.Ejercicio2.aplication.service.EmpleadoService;
import org.Ejercicio2.domain.Empleado;
import org.Ejercicio2.domain.Tarea;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class ConsolaUi {
    private final EmpleadoService empleadoService;

    public ConsolaUi(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public void iniciar() {
        while (true) {
            String[] opciones = {
                    "Registrar Empleado",
                    "Actualizar Empleado",
                    "Asignar Tarea",
                    "Eliminar Tarea",
                    "Mostrar Empleados",
                    "Mostrar Tareas por Empleado",
                    "Salir"
            };

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción",
                    "Gestión de Empleados",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {
                case 0:
                    registrarEmpleado();
                    break;
                case 1:
                    actualizarEmpleado();
                    break;
                case 2:
                    asignarTarea();
                    break;
                case 3:
                    eliminarTarea();
                    break;
                case 4:
                    mostrarEmpleados();
                    break;
                case 5:
                    mostrarTareasPorEmpleado();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    private void registrarEmpleado() {
        String nombre = JOptionPane.showInputDialog("Nombre del empleado:");
        String apellido = JOptionPane.showInputDialog("Apellido del empleado:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        String cargo = JOptionPane.showInputDialog("Cargo:");
        String departamento = JOptionPane.showInputDialog("Departamento:");
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Salario:"));

        Empleado empleado = new Empleado(nombre, apellido, edad, cargo, departamento, salario);
        empleadoService.crearEmpleado(empleado);
        JOptionPane.showMessageDialog(null, "Empleado registrado correctamente.");
    }

    private void actualizarEmpleado() {
        String nombre = JOptionPane.showInputDialog("Nombre del empleado a actualizar:");
        Empleado empleado = empleadoService.obtenerEmpleado(nombre);
        if (empleado != null) {
            String nuevoCargo = JOptionPane.showInputDialog("Nuevo cargo:", empleado.getCargo());
            String nuevoDepartamento = JOptionPane.showInputDialog("Nuevo departamento:", empleado.getDepartamento());
            double nuevoSalario = Double.parseDouble(JOptionPane.showInputDialog("Nuevo salario:", empleado.getSalario()));

            empleado.setCargo(nuevoCargo);
            empleado.setDepartamento(nuevoDepartamento);
            empleado.setSalario(nuevoSalario);

            empleadoService.actualizarEmpleado(empleado);
            JOptionPane.showMessageDialog(null, "Empleado actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
        }
    }

    private void asignarTarea() {
        String nombreEmpleado = JOptionPane.showInputDialog("Nombre del empleado:");
        Empleado empleado = empleadoService.obtenerEmpleado(nombreEmpleado);
        if (empleado != null) {
            String titulo = JOptionPane.showInputDialog("Título de la tarea:");
            String descripcion = JOptionPane.showInputDialog("Descripción:");
            LocalDate fechaInicio = LocalDate.parse(JOptionPane.showInputDialog("Fecha de inicio (YYYY-MM-DD):"));
            LocalDate fechaFin = LocalDate.parse(JOptionPane.showInputDialog("Fecha de fin (YYYY-MM-DD):"));
            String estado = JOptionPane.showInputDialog("Estado (Pendiente/Completada):");

            Tarea tarea = new Tarea(titulo, descripcion, fechaInicio, fechaFin, estado, empleado);
            empleadoService.asignarTarea(tarea);
            JOptionPane.showMessageDialog(null, "Tarea asignada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
        }
    }

    private void eliminarTarea() {
        String nombreEmpleado = JOptionPane.showInputDialog("Nombre del empleado:");
        Empleado empleado = empleadoService.obtenerEmpleado(nombreEmpleado);
        if (empleado != null) {
            List<Tarea> tareas = empleadoService.listarTareasPorEmpleado(empleado);
            if (tareas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Este empleado no tiene tareas asignadas.");
            } else {
                String[] opcionesTareas = new String[tareas.size()];
                for (int i = 0; i < tareas.size(); i++) {
                    opcionesTareas[i] = tareas.get(i).getTitulo();
                }

                String tituloTarea = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione la tarea a eliminar:",
                        "Eliminar Tarea",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionesTareas,
                        opcionesTareas[0]
                );

                Tarea tareaAEliminar = tareas.stream()
                        .filter(t -> t.getTitulo().equals(tituloTarea))
                        .findFirst()
                        .orElse(null);

                if (tareaAEliminar != null) {
                    empleadoService.eliminarTarea(tareaAEliminar);
                    JOptionPane.showMessageDialog(null, "Tarea eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Tarea no encontrada.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
        }
    }

    private void mostrarEmpleados() {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        if (empleados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
        } else {
            StringBuilder sb = new StringBuilder("Empleados:\n");
            for (Empleado empleado : empleados) {
                sb.append(empleado).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private void mostrarTareasPorEmpleado() {
        String nombreEmpleado = JOptionPane.showInputDialog("Nombre del empleado:");
        Empleado empleado = empleadoService.obtenerEmpleado(nombreEmpleado);
        if (empleado != null) {
            List<Tarea> tareas = empleadoService.listarTareasPorEmpleado(empleado);
            if (tareas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Este empleado no tiene tareas asignadas.");
            } else {
                StringBuilder sb = new StringBuilder("Tareas de " + empleado.getNombre() + ":\n");
                for (Tarea tarea : tareas) {
                    sb.append(tarea).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado.");
        }
    }
}