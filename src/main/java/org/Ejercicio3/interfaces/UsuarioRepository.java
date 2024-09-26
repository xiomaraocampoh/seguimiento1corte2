package org.Ejercicio3.interfaces;

import org.Ejercicio3.domain.Usuarios;

import java.util.List;

public interface UsuarioRepository {

    void guardarUsuario(Usuarios usuario);
    List<Usuarios> listarUsuarios();
    void actualizarUsuarios(Usuarios usuario);
    void eliminarUsuarios(String usuario);

}
