package org.Ejercicio2.Interface;

import org.Ejercicio2.domain.Empleado;
import org.Ejercicio2.domain.Tarea;

import java.util.List;

public interface EmpleadoRepository {
    void guardarEmpleado(Empleado empleado);
    Empleado buscarEmpleadoPorNombre(String nombre);
    List<Empleado> listarEmpleados();
    void actualizarEmpleado(Empleado empleado);
    void eliminarEmpleado(String nombre);

    void guardarTarea(Tarea tarea);
    List<Tarea> listarTareasPorEmpleado(Empleado empleado);
    void eliminarTarea(Tarea tarea);
}
