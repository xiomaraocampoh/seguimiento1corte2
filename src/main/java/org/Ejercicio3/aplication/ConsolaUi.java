package org.Ejercicio3.aplication;

import org.Ejercicio3.aplication.service.UsuarioService;
import org.Ejercicio3.domain.Usuarios;

import javax.swing.*;
import java.util.List;

public class ConsolaUi {

    private final UsuarioService usuarioService;

    public ConsolaUi(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void iniciar() {
        String[] options = {"Crear Usuario", "Actualizar Usuario", "Eliminar Usuario", "Actualizar Contraseña", "Listar Usuarios", "Salir"};
        int option;
        do {
            option = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Gestión de Usuarios",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (option) {
                case 0:
                    crearUsuario();
                    break;
                case 1:
                    actualizarUsuario();
                    break;
                case 2:
                    eliminarUsuario();
                    break;
                case 3:
                    actualizarContrasena();
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;
                default:
                    break;
            }
        } while (option != 5);
    }

    private void crearUsuario() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
        String usuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña:");

        Usuarios nuevoUsuario = new Usuarios(nombre, usuario, contrasena);
        usuarioService.crearUsuario(nuevoUsuario);

        JOptionPane.showMessageDialog(null, "Usuario creado con éxito.");
    }

    private void actualizarUsuario() {
        String usuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario a actualizar:");
        Usuarios usuarioExistente = buscarUsuario(usuario);
        if (usuarioExistente != null) {
            String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:", usuarioExistente.getNombre());
            usuarioExistente.setNombre(nuevoNombre);

            usuarioService.actualizarUsuario(usuarioExistente);
            JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    }

    private void eliminarUsuario() {
        String usuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario a eliminar:");
        usuarioService.eliminarUsuario(usuario);
        JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito.");
    }

    private void actualizarContrasena() {
        String usuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario para actualizar contraseña:");
        String nuevaContrasena = JOptionPane.showInputDialog("Ingrese la nueva contraseña:");
        usuarioService.actualizarContrasena(usuario, nuevaContrasena);
        JOptionPane.showMessageDialog(null, "Contraseña actualizada con éxito.");
    }

    private void listarUsuarios() {
        List<Usuarios> usuarios = usuarioService.listarUsuarios();
        StringBuilder sb = new StringBuilder("Lista de Usuarios:\n");
        for (Usuarios usuario : usuarios) {
            sb.append(usuario).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private Usuarios buscarUsuario(String usuario) {
        return usuarioService.listarUsuarios().stream()
                .filter(u -> u.getUsuario().equals(usuario))
                .findFirst().orElse(null);
    }
}
