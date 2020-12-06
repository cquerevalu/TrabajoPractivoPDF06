package com.ditec.migranparte6;

public class Cliente  {
    private String codigo;
    private String nombre;
    private String Apellido;
    public Cliente(String codigo, String nombre, String Apellido) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.Apellido= Apellido;
    }
    public String getcodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return Apellido;
    }
}
