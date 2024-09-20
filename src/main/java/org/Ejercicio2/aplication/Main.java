package org.Ejercicio2.aplication;

import org.Ejercicio2.Interface.EmpleadoRepository;
import org.Ejercicio2.aplication.service.EmpleadoService;
import org.Ejercicio2.infraestructure.repository.EmpleadoRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        EmpleadoRepository empleadoRepositorio = new EmpleadoRepositoryImpl();
        EmpleadoService empleadoService = new EmpleadoService(empleadoRepositorio);
        ConsolaUi consolaUI = new ConsolaUi(empleadoService);
        consolaUI.iniciar();
    }
}
