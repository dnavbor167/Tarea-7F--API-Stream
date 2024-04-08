/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author daniel
 */
public class MetodosTarea7f {

    //método para leer el fichero de la raíz
    public static List<String> leerFichero(String fichero) {
        List<String> lineas = new ArrayList<>();

        try {
            lineas = Files.readAllLines(Paths.get(fichero),
                    StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Error leyendo el fichero " + fichero);
        }
        //eliminamos la posición 0 
        lineas.remove(0);

        return lineas;

    }

    public static void escribir(String escribir, String idFichero) {
        try {
            Files.write(Paths.get(idFichero), (escribir + "\n").getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Error creando el fichero");
        }
    }

}
