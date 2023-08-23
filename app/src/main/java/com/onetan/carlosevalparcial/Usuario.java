package com.onetan.carlosevalparcial;

public class Usuario {
    private Integer usuidusuario;
    private String usuNombres;
    private String usuAppaterno;
    private String usuapMaterno;
    private String usudocumentodeidentidad;
    private String usucelular;
    private String usucontrasena;
    private String usucorreoelectronico;
    private String usuidrol;

    public Usuario() {
    }

    public Usuario(Integer usuidusuario, String usuNombres, String usuAppaterno, String usuapMaterno, String usudocumentodeidentidad, String usucelular, String usucontrasena, String usucorreoelectronico, String usuidrol) {
        this.usuidusuario = usuidusuario;
        this.usuNombres = usuNombres;
        this.usuAppaterno = usuAppaterno;
        this.usuapMaterno = usuapMaterno;
        this.usudocumentodeidentidad = usudocumentodeidentidad;
        this.usucelular = usucelular;
        this.usucontrasena = usucontrasena;
        this.usucorreoelectronico = usucorreoelectronico;
        this.usuidrol = usuidrol;
    }

    public Integer getUsuidusuario() {
        return usuidusuario;
    }

    public void setUsuidusuario(Integer usuidusuario) {
        this.usuidusuario = usuidusuario;
    }

    public String getUsuNombres() {
        return usuNombres;
    }

    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
    }

    public String getUsuAppaterno() {
        return usuAppaterno;
    }

    public void setUsuAppaterno(String usuAppaterno) {
        this.usuAppaterno = usuAppaterno;
    }

    public String getUsuapMaterno() {
        return usuapMaterno;
    }

    public void setUsuapMaterno(String usuapMaterno) {
        this.usuapMaterno = usuapMaterno;
    }

    public String getUsudocumentodeidentidad() {
        return usudocumentodeidentidad;
    }

    public void setUsudocumentodeidentidad(String usudocumentodeidentidad) {
        this.usudocumentodeidentidad = usudocumentodeidentidad;
    }

    public String getUsucelular() {
        return usucelular;
    }

    public void setUsucelular(String usucelular) {
        this.usucelular = usucelular;
    }

    public String getUsucontrasena() {
        return usucontrasena;
    }

    public void setUsucontrasena(String usucontrasena) {
        this.usucontrasena = usucontrasena;
    }

    public String getUsucorreoelectronico() {
        return usucorreoelectronico;
    }

    public void setUsucorreoelectronico(String usucorreoelectronico) {
        this.usucorreoelectronico = usucorreoelectronico;
    }

    public String getUsuidrol() {
        return usuidrol;
    }

    public void setUsuidrol(String usuidrol) {
        this.usuidrol = usuidrol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuidusuario=" + usuidusuario +
                ", usuNombres='" + usuNombres + '\'' +
                ", usuAppaterno='" + usuAppaterno + '\'' +
                ", usuapMaterno='" + usuapMaterno + '\'' +
                ", usudocumentodeidentidad='" + usudocumentodeidentidad + '\'' +
                ", usucelular='" + usucelular + '\'' +
                ", usucontrasena='" + usucontrasena + '\'' +
                ", usucorreoelectronico='" + usucorreoelectronico + '\'' +
                ", usuidrol='" + usuidrol + '\'' +
                '}';
    }
}
