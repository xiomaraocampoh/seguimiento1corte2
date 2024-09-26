package org.Ejercicio3.interfaces;

import org.Ejercicio3.domain.Usuarios;

import java.util.List;

public interface UsuarioService {
    List<Usuarios> listarUsuarios(Usuarios usuarios);

    List<Usuarios> findAll();
    void ActualizarContraseña(String contrasena);
    void BorrarUsuario (String usuario);
    void LeerUsuarios (Usuarios usuarios);
}
