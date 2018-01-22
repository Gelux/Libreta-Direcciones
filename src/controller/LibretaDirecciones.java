/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Persona;
import view.EditarPersonaController;
import view.VistaPersonaController;

/**
 *
 * @author Jes
 */
public class LibretaDirecciones extends Application {
    
    private ObservableList datosPersona = FXCollections.observableArrayList();
    private Stage escenarioPrincipal;
    private BorderPane layoutPrincipal;
    private AnchorPane vistaPersona, editarPersona;
    
    public LibretaDirecciones(){
        
        datosPersona.add(new Persona("Jesus", "Sanchez"));
        datosPersona.add(new Persona("YO", "Si"));
        datosPersona.add(new Persona("JEJE", "BUENO"));
        datosPersona.add(new Persona("VENGA", "VAMOS"));
        datosPersona.add(new Persona("VAMONOS", "ATOMOS"));
        
    }
    
    public ObservableList getDatosPersona(){
        return datosPersona;
    }
    
    @Override
    public void start(Stage escenarioPrincipal) {
        
        this.escenarioPrincipal = escenarioPrincipal;
        
        //Establezco el titulo
        this.escenarioPrincipal.setTitle("Libreta de Direcciones");
        
        //
        initLayoutPrincipal();
        
        //Muestra la vista persona
        muestraVistaPersona();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initLayoutPrincipal() {
        
        //Cargar el layout principal
        FXMLLoader loader = new FXMLLoader();
        URL location = LibretaDirecciones.class.getResource("../view/VistaPrincipal.fxml");
        loader.setLocation(location);
        
        try {
            layoutPrincipal = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        //creo nueva escena y muestro la escena que contiene ese layoutPrincipal
        Scene escena = new Scene(layoutPrincipal);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
        
    }

    private void muestraVistaPersona() {
        
        FXMLLoader loader = new FXMLLoader();
        URL location = LibretaDirecciones.class.getResource("../view/VistaPersona.fxml");
        
        loader.setLocation(location);
        
        try {
            vistaPersona = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        layoutPrincipal.setCenter(vistaPersona);
        
        //
        //
        VistaPersonaController controller = loader.getController();
        controller.setLibretaDirecciones(this);
        
    }
    
    public Stage getPrimaryStage(){
        return escenarioPrincipal;
    }
    
    //Vista editarPersona
    public boolean muestraEditarPersona(Persona persona) {
        
        //Cargo la vista persona a partir de VistaPersona.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = LibretaDirecciones.class.getResource("../view/EditarPersona.fxml");
        loader.setLocation(location);
        try {
            editarPersona = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LibretaDirecciones.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        //Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioEdicion = new Stage();
        escenarioEdicion.setTitle("Editar Persona");
        //cuando le de a editar con el window modal no podras hacer nada en la ventana principal
        escenarioEdicion.initModality(Modality.WINDOW_MODAL);
        escenarioEdicion.initOwner(escenarioPrincipal);
        Scene escena = new Scene(editarPersona);
        escenarioEdicion.setScene(escena);
        
        //Asigno el escenario de edición y la persona seleccionada al controlador
        EditarPersonaController controller = loader.getController();
        controller.setEscenarioEdicion(escenarioEdicion);
        controller.setPersona(persona);

        //Muestro el diálogo ahjsta que el ussuario lo cierre
        escenarioEdicion.showAndWait();
        
        //devuelvo el botón pulsado
        return controller.isGuardarClicked();
    
    }
    
    
}
