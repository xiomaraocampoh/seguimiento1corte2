package org.Ejercicio1.aplication;

import org.Ejercicio1.Interfaces.PacienteRepository;
import org.Ejercicio1.aplication.service.PacienteService;
import org.Ejercicio1.infraestructure.repository.FilePacienteRepository;

public class Main {

    public static void main(String[] args) {
        PacienteRepository repository = new FilePacienteRepository();
        PacienteService service = new PacienteService(repository);
        ConsolaUi consolaUi = new ConsolaUi(service);
        consolaUi.iniciar();
    }
}

