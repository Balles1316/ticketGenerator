package Controlador.Cliente;

import Modelo.ClientesModel;
import Vista.Cliente.InsertarClienteView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class InsertarClienteController {
    private final InsertarClienteView vista;
    private final ClientesModel modelo;

    public InsertarClienteController(InsertarClienteView vista, ClientesModel modelo) {
        this.vista = vista;
        this.modelo = modelo;

        guardarCliente();
    }

    private void guardarCliente(){
        vista.guardarListener(e -> {
            String nombre = vista.getNombre();
            String apellido = vista.getApellido();
            String email = vista.getEmail();
            Date fecha;
            try {
                fecha = getFechaNacimiento(vista.getNacimiento());
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            String telefono = vista.getTelefono();
            String cp = vista.getCP();

            if (!nombre.isEmpty()) {
                if(!apellido.isEmpty()){
                    if (fecha != null) {
                        if(!email.isEmpty()){
                            if(!telefono.isEmpty()){
                                if(!cp.isEmpty()){
                                    modelo.guardarCliente(nombre, apellido, fecha, telefono, email, cp);
                                    vista.mostrarMensaje("Cliente guardado correctamente.");
                                    vista.limpiarCampos();
                                } else {
                                    vista.mostrarMensaje("Debe ingresar un código postal");
                                }
                            } else {
                                vista.mostrarMensaje("Debe ingresar un telefono.");
                            }
                        } else {
                            vista.mostrarMensaje("Debe ingresar un correo electrónico.");
                        }
                    } else {
                        vista.mostrarMensaje("Debe ingresar una fecha de nacimiento.");
                    }
                } else {
                    vista.mostrarMensaje("Debe ingresar un apellido.");
                }
            } else {
                vista.mostrarMensaje("Debe ingresar un nombre.");
            }
        });
    }

    public Date getFechaNacimiento(String fechaTexto) throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = (Date) formatoFecha.parse(fechaTexto);
        return fechaNacimiento;
    }
}
