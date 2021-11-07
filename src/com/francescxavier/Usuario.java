package com.francescxavier;

import com.francescxavier.threads.SalaThread;

import java.util.UUID;

public class Usuario {

    String nombre;
    String id;
    SalaThread sala;

    public Usuario(String nombre){
        this.nombre = nombre;
        this.id = UUID.randomUUID().toString();
    }

    public String getNombre(){
        return nombre;
    }

    public String getId(){
        return id;
    }

    public void setSala(SalaThread sala){

    }

    public SalaThread getSala(){
        return sala;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario){
            if (this.id.equals(((Usuario) obj).id)){
                return true;
            }
        }
        return false;
    }
}
