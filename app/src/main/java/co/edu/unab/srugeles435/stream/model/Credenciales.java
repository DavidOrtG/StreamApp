package co.edu.unab.srugeles435.stream.model;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Credenciales implements Serializable {

    @Exclude
    private String idFireStore;

    String usuario;
    String contrasena;

    public Credenciales(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Credenciales(){

    }

    public String getIdFireStore() {
        return idFireStore;
    }

    public void setIdFireStore(String idFireStore) {
        this.idFireStore = idFireStore;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
