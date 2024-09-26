package org.Ejercicio3.aplication.service;

import org.Ejercicio3.domain.Usuarios;
import org.Ejercicio3.interfaces.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void crearUsuario(Usuarios usuario) {
        repository.guardarUsuario(usuario);
    }

    public void actualizarUsuario(Usuarios usuario) {
        repository.actualizarUsuarios(usuario);
    }

    public void eliminarUsuario(String usuario) {
        repository.eliminarUsuarios(usuario);
    }

    public List<Usuarios> listarUsuarios() {
        return repository.listarUsuarios();
    }

    public void actualizarContrasena(String usuario, String nuevaContrasena) {
        Usuarios user = repository.listarUsuarios().stream()
                .filter(u -> u.getUsuario().equals(usuario))
                .findFirst().orElse(null);

        if (user != null) {
            user.setContrasena(nuevaContrasena);
            repository.actualizarUsuarios(user);
        }
    }
}
