package com.francescxavier.threads;

import com.francescxavier.Usuario;
import com.francescxavier.utils.NetUtils;

import java.util.HashSet;

public class SalaThread extends Thread {

    private Usuario administrador;
    private HashSet<Usuario> miembros;

    public SalaThread(Usuario usuario){
        this.administrador = usuario;
        this.miembros = new HashSet<>();
    }


    public void addUsuario(Usuario usuario){
        miembros.add(usuario);
    }

    public void removeUsuario(Usuario usuario){
        miembros.remove(usuario);
    }

    public String getListaUsuarios(){
        String listaUsuarios = administrador.getNombre()+ NetUtils.Separator;

        for (Usuario usuario : miembros){
            listaUsuarios+=usuario.getNombre()+NetUtils.Separator;
        }

        listaUsuarios = listaUsuarios.substring(0,listaUsuarios.length()-1);
        return listaUsuarios;
    }



}
