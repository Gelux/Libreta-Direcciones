/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.LibretaDirecciones;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Persona;

/**
 *
 * @author Jes
 */
public class VistaPersonaController {
    
    @FXML
    private TableView tablaPersonas;
    @FXML
    private TableColumn nombreColumn;
    @FXML
    private TableColumn apellidosColumn;

    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label codigoPostalLabel;
    @FXML
    private Label ciudadLabel;
    @FXML
    private Label fechaDeNacimientoLabel;

    // Referencia a la clase principal
    private LibretaDirecciones libretaDirecciones;
    
    //El constructor es llamado ANTES del método initialize
    public VistaPersonaController() {
    }
    
     //Inicializa la clase controller y es llamado justo después de cargar el archivo FXML
    @FXML
    private void initialize() {
        
        //Inicializo la tabla con las dos primera columnas
        String nombre = "nombre";
        String apellido = "apellidos";
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>(nombre));
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>(apellido));
        
    }

    //Es llamado por la apliación principal para tener una referencia de vuelta de si mismo
    public void setLibretaDirecciones(LibretaDirecciones libretaDirecciones) {
        
        this.libretaDirecciones = libretaDirecciones;

        //Añado la lista obervable a la tabla
        tablaPersonas.setItems(libretaDirecciones.getDatosPersona());
    }
    
    private void mostrarDetallesPersona(Persona persona) {
        
        if (persona != null) {
            //Relleno los labels desde el objeto persona
            nombreLabel.setText(persona.getNombre());
            apellidosLabel.setText(persona.getApellidos());
            direccionLabel.setText(persona.getDireccion());
            codigoPostalLabel.setText(Integer.toString(persona.getCodigoPostal()));
            ciudadLabel.setText(persona.getCiudad());
            //TODO: Tenemos que convertir la fecha de nacimiento en un String 
            //fechaDeNacimientoLabel.setText(...);
        } else {
            //Persona es null, vacío todos los labels.
            nombreLabel.setText("");
            apellidosLabel.setText("");
            direccionLabel.setText("");
            codigoPostalLabel.setText("");
            ciudadLabel.setText("");
            fechaDeNacimientoLabel.setText("");
        }
    }
    
    
}
