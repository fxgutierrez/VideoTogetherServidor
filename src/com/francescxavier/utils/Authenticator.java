package com.francescxavier.utils;

import com.francescxavier.Usuario;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Authenticator {

    private static Map<String, Usuario> UserList;

    public static void Initialize(){
        UserList = Collections.synchronizedMap(new HashMap<>());
    }

    private static boolean verifyCredentials(String user, String password){
        return true;
//        if (user.equals("usuario") && password.equals("password")){
//            return true;
//        }
//        return false;
    }

    public static Usuario login (String user, String password){
        //Comprueba que las credenciales sean válidas y que el usuario no esté registrado
        if (verifyCredentials(user,password) && UserList.get(user) == null){
            Usuario usuario = new Usuario(user);
            UserList.put(usuario.getNombre(),usuario);
            return usuario;
        }
        return null;
    }

    public static Usuario getUsuario(String nombreUsuario){
        return UserList.get(nombreUsuario);
    }

    public static Usuario getUsuario(String nombreUsuario, String id){
        Usuario usuario = UserList.get(nombreUsuario);
        if (usuario != null && usuario.getId().equals(id)){
            return usuario;
        }
        return null;
    }

}
