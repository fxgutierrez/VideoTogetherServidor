package com.francescxavier;

import com.francescxavier.net.Petition;
import com.francescxavier.net.PetitionType;
import com.francescxavier.net.Response;
import com.francescxavier.net.ResponseType;
import com.francescxavier.threads.SalaThread;
import com.francescxavier.utils.NetUtils;
import com.francescxavier.utils.Respuestas;
import com.francescxavier.utils.Authenticator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Main {
    private static Map<String,SalaThread> SalaList;

    public static void main(String[] args) {
        Authenticator.Initialize();

        SalaList = Collections.synchronizedMap(new HashMap<>());


        try {
            ServerSocket ssocket = new ServerSocket(4444);

            while (true) {
                System.out.println("Esperando clientes...");

                Socket socket = ssocket.accept();
                System.out.println("Cliente encontrado.");

                BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //System.out.println("Esperando mensaje...");
                String entrada = inputStream.readLine();

                System.out.println("Mensaje recibido: " + entrada);
                Petition petition = new Petition(entrada);

                //String[] entradaSegmentada = entrada.split("_");
                System.out.println(petition.getPetitionType());
                if (petition.getPetitionType() != PetitionType.INVALID) {
                    switch (petition.getPetitionType()) {
                        case LOGIN:
                            //1: Usuario, 2: Contraseña
                            System.out.println(petition.getContenido());
                            String[] credenciales = petition.getContenido().split(NetUtils.Separator,2);

                            if (credenciales.length > 1) {
                                if (Authenticator.getUsuario(credenciales[0]) == null){
                                    Usuario usuario = Authenticator.login(credenciales[0],credenciales[1]);

                                    if (usuario != null) {
                                        Response response = new Response(ResponseType.OK,usuario.getId());
                                        SalaThread salaTest = new SalaThread(usuario);

                                        SalaList.put("123",salaTest);

                                        NetUtils.sendRespuesta(socket,response);
                                    } else {
                                        NetUtils.sendRespuesta(socket,new Response(ResponseType.NO,"USER ALREADY LOGIN"));
                                    }
                                } else {
                                    NetUtils.sendRespuesta(socket,new Response(ResponseType.ALREADYLOGED,""));
                                }

                            }

                            break;

                        case JOINSALA:
                            if (petition.getUsuario() != null){
                                String idSala = petition.getContenido();
                                System.out.println(idSala);
                                SalaThread sala = SalaList.get(idSala);

                                if (sala != null){
                                    sala.addUsuario(petition.getUsuario());
                                    String listaUsuarios = sala.getListaUsuarios();
                                    Response response = new Response(ResponseType.OK,listaUsuarios);
                                    NetUtils.sendRespuesta(socket,response);
                                    //outputStream.println(Respuestas.OK+listaUsuarios);
                                } else {
                                    Response response = new Response(ResponseType.NO,"");
                                    System.out.println(response);
                                    NetUtils.sendRespuesta(socket,response);
                                }
                            } else {
                                NetUtils.sendRespuesta(socket,new Response(ResponseType.FAIL,""));
                            }
                            break;

                        case CREATESALA:
                            if (petition.getUsuario() != null){
                                SalaThread salaThread = new SalaThread(petition.getUsuario());
                                String id = UUID.randomUUID().toString();

                                SalaList.put(id,salaThread);

                                System.out.println(id);
                                Response response = new Response(ResponseType.OK, id);
                                NetUtils.sendRespuesta(socket,response);

                            }
                            break;


                        case INVALIDUSER:
                            NetUtils.sendRespuesta(socket,new Response(ResponseType.FAIL,"INVALID USER ID"));
                            break;
                    }
                }

                inputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
