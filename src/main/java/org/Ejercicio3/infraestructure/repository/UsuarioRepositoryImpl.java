package org.Ejercicio3.infraestructure.repository;

import org.Ejercicio3.domain.Usuarios;
import org.Ejercicio3.interfaces.UsuarioRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private static final String FILE_NAME = "usuarios.dat";


    @Override
    public void guardarUsuario(Usuarios usuario) {
        List<Usuarios> usuarios = listarUsuarios();
        usuarios.add(usuario);
        guardarTodos(usuarios);
    }

    @Override
    public List<Usuarios> listarUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Usuarios>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void actualizarUsuarios(Usuarios usuarioActualizado) {
        List<Usuarios> usuarios = listarUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsuario().equals(usuarioActualizado.getUsuario())) {
                usuarios.set(i, usuarioActualizado);
                break;
            }
        }
        guardarTodos(usuarios);
    }

    @Override
    public void eliminarUsuarios(String nombreUsuario) {
        List<Usuarios> usuarios = listarUsuarios();
        usuarios.removeIf(u -> u.getUsuario().equals(nombreUsuario));
        guardarTodos(usuarios);
    }

    private void guardarTodos(List<Usuarios> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}