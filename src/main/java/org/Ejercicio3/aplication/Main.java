package org.Ejercicio3.aplication;

import org.Ejercicio3.aplication.service.UsuarioService;
import org.Ejercicio3.infraestructure.repository.UsuarioRepositoryImpl;

public class Main {

    public static void main(String[] args) {

        UsuarioRepositoryImpl repository = new UsuarioRepositoryImpl();
        UsuarioService service = new UsuarioService(repository);
        ConsolaUi ui = new ConsolaUi(service);

        ui.iniciar();


    }

}
