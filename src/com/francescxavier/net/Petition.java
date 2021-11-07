package com.francescxavier.net;

import com.francescxavier.Main;
import com.francescxavier.Usuario;
import com.francescxavier.utils.Authenticator;
import com.francescxavier.utils.NetUtils;

public class Petition {
    private PetitionType petitionType;
    private String contenido;
    private Usuario usuario;

    public Petition(String rawPetition) {
        proccessRawPetition(rawPetition);
    }

    private void proccessRawPetition(String rawPetition){
        String[] petitionSplitted = rawPetition.split(NetUtils.Separator,4);

        petitionType = getPetitionType(petitionSplitted[0]);

        if (petitionType != PetitionType.INVALID){
            if (petitionType == PetitionType.LOGIN && petitionSplitted.length > 1){
                    contenido = rawPetition.split(NetUtils.Separator,2)[1];

            } else if (petitionSplitted.length > 2) {
                usuario = Authenticator.getUsuario(petitionSplitted[1],petitionSplitted[2]);
                //Si la ID proporcionada no coincide con la del usuario o el usuario no se ha autenticado establece la peticion como "Usuario inválido"
                if (usuario == null){
                    petitionType = PetitionType.INVALIDUSER;
                } else {
                    contenido = petitionSplitted[3];
                }

            }
        }

    }

    private PetitionType getPetitionType (String rawPetition){

        switch (rawPetition){

            case "login":
                petitionType = PetitionType.LOGIN;
                break;
            case "joinSala":
                petitionType = PetitionType.JOINSALA;
                break;
            case "createSala":
                petitionType = PetitionType.CREATESALA;
                break;
            default:
                petitionType = PetitionType.INVALID;
                break;
        }

        return petitionType;
    }

    public void setPetitionType(PetitionType petitionType) {
        this.petitionType = petitionType;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public PetitionType getPetitionType(){
        return petitionType;
    }

    public String getContenido(){
        return contenido;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    @Override
    public String toString() {
        if (usuario != null){
            return petitionType.getValue()+ NetUtils.Separator+usuario.getId()+NetUtils.Separator+contenido;
        } else {
            return petitionType.getValue()+NetUtils.Separator+contenido;
        }

    }
}
