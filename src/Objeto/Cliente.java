package Objeto;

import java.sql.Date;

public class Cliente {

    private String nombre;
    private String apellido;
    private final Date fNacimiento;
    private String telefono;
    private final String email;
    private final String cp;

    public Cliente(String nombre, String apellido, Date fNacimiento, String telefono, String email, String cp) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fNacimiento = fNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.cp = cp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getCp() {
        return cp;
    }

    public Date getfNacimiento() {
        return fNacimiento;
    }
}
