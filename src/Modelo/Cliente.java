package Modelo;

import java.util.Date;

public class Cliente {

    private String nombre;
    private String apellido;
    private Date fNacimiento;
    private String telefono;
    private String email;

    public Cliente() {
        // Constructor por defecto
    }

    public Cliente(String nombre, String apellido, Date fNacimiento, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fNacimiento = fNacimiento;
        this.telefono = telefono;
        this.email = email;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para apellido
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Getter y Setter para teléfono
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Getter y Setter para email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
