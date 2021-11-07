package com.francescxavier.utils;

import com.francescxavier.Usuario;
import com.francescxavier.net.Response;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class NetUtils {

    public static String Separator = "_";

    public static void sendRespuesta(Socket socket, Response response){
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(response);
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


