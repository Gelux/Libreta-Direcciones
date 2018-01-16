/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Jes
 */
public class UtilidadDeFechas {
    
    //El patr�n utilizado para la conversi�n
    private static final String FECHA_PATTERN = "dd/MM/yyyy";
    
    //El formateador de fecha
    private static final DateTimeFormatter FECHA_FORMATTER = DateTimeFormatter.ofPattern(FECHA_PATTERN);
    
    //Devuelve la fecha de entrada como un string formateado
    public static String formato(LocalDate fecha){
        
        if (fecha == null){
            return null;
        }
        return FECHA_FORMATTER.format(fecha);
        
    }
    
    //Convierte un string en un objeto de tipo LocalDate (o null si no puede convertirlo)
    public static LocalDate convertir(String fecha) {
        try {
            return FECHA_FORMATTER.parse(fecha, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    //Comprueba si un string de fecha es una fecha v�lida y devuelve 1 o 0
    //Usamos el m�todo anterior para la comprobaci�n
    public static boolean fechaValida(String fecha) {
        
        return UtilidadDeFechas.convertir(fecha) != null;
        
    }
}
