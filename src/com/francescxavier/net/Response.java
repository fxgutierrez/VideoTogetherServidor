package com.francescxavier.net;


import com.francescxavier.utils.NetUtils;

public class Response {

    private ResponseType responseType;
    private String contenido;

    public Response(){

    }

    public Response(ResponseType responseType, String contenido) {
        this.responseType = responseType;
        this.contenido = contenido;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public ResponseType getResponseType(){
        return responseType;
    }

    public String getContenido(){
        return contenido;
    }

    @Override
    public String toString() {
        return responseType.getValue()+ NetUtils.Separator+contenido;
    }
}
