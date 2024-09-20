package org.Ejercicio2.infraestructure.repository;

import org.Ejercicio2.Interface.EmpleadoRepository;
import org.Ejercicio2.domain.Empleado;
import org.Ejercicio2.domain.Tarea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepositoryImpl implements EmpleadoRepository {
    private static final String EMPLEADOS_FILE_NAME = "empleados.dat";
    private static final String TAREAS_FILE_NAME = "tareas.dat";

    @Override
    public void guardarEmpleado(Empleado empleado) {
        List<Empleado> empleados = listarEmpleados();
        empleados.add(empleado);
        guardarTodo(empleados, EMPLEADOS_FILE_NAME);
    }

    @Override
    public Empleado buscarEmpleadoPorNombre(String nombre) {
        return listarEmpleados().stream()
                .filter(e -> e.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Empleado> listarEmpleados() {
        return leerArchivo(EMPLEADOS_FILE_NAME);
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        List<Empleado> empleados = listarEmpleados();
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getNombre().equals(empleado.getNombre())) {
                empleados.set(i, empleado);
                break;
            }
        }
        guardarTodo(empleados, EMPLEADOS_FILE_NAME);
    }

    @Override
    public void eliminarEmpleado(String nombre) {
        List<Empleado> empleados = listarEmpleados();
        empleados.removeIf(e -> e.getNombre().equals(nombre));
        guardarTodo(empleados, EMPLEADOS_FILE_NAME);
    }

    @Override
    public void guardarTarea(Tarea tarea) {
        List<Tarea> tareas = listarTareas();
        tareas.add(tarea);
        guardarTodo(tareas, TAREAS_FILE_NAME);
    }

    @Override
    public List<Tarea> listarTareasPorEmpleado(Empleado empleado) {
        List<Tarea> tareasEmpleado = new ArrayList<>();
        for (Tarea tarea : listarTareas()) {
            if (tarea.getEmpleado().equals(empleado)) {
                tareasEmpleado.add(tarea);
            }
        }
        return tareasEmpleado;
    }

    @Override
    public void eliminarTarea(Tarea tarea) {
        List<Tarea> tareas = listarTareas();
        tareas.remove(tarea);
        guardarTodo(tareas, TAREAS_FILE_NAME);
    }

    private <T> List<T> leerArchivo(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private <T> void guardarTodo(List<T> list, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Tarea> listarTareas() {
        return leerArchivo(TAREAS_FILE_NAME);
    }
}
