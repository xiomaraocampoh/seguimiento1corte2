package org.Ejercicio2.aplication.service;

import org.Ejercicio2.Interface.EmpleadoRepository;
import org.Ejercicio2.domain.Empleado;
import org.Ejercicio2.domain.Tarea;

import java.util.List;

public class EmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public void crearEmpleado(Empleado empleado) {
        repository.guardarEmpleado(empleado);
    }

    public Empleado obtenerEmpleado(String nombre) {
        return repository.buscarEmpleadoPorNombre(nombre);
    }

    public List<Empleado> listarEmpleados() {
        return repository.listarEmpleados();
    }

    public void actualizarEmpleado(Empleado empleado) {
        repository.actualizarEmpleado(empleado);
    }

    public void eliminarEmpleado(String nombre) {
        repository.eliminarEmpleado(nombre);
    }

    public void asignarTarea(Tarea tarea) {
        repository.guardarTarea(tarea);
    }

    public List<Tarea> listarTareasPorEmpleado(Empleado empleado) {
        return repository.listarTareasPorEmpleado(empleado);
    }

    public void eliminarTarea(Tarea tarea) {
        repository.eliminarTarea(tarea);
    }
}