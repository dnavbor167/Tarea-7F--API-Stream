/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import static daw.MetodosTarea7f.escribir;
import static daw.MetodosTarea7f.leerFichero;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author daniel
 */
public class Tarea7FDanielNavasBorjas {

    public static void main(String[] args) {
        //mostramos los datos de la lista de persona por consola
        listaObjetoPersona().forEach(System.out::println);

        System.out.println("\nSiguiente método\n");

        //método que nos enseña por consola los géneros que hay
        Set<String> conjunto = conjuntoGenero(listaObjetoPersona());
        for (String string : conjunto) {
            System.out.println(string);
            escribir(string, "generos.txt");
        }

        System.out.println("\nSiguiente método\n");

        //método para contar géneros y guardarlo en un fichero .csv
        //lo guardamos en un map para luego recorrerlo e ir escribiendo y mostrando
        Map<String, Integer> map = contadorGeneros(listaObjetoPersona());

        //hacemos un for eache para ir recorriendo e ir escribiendo en el
        //fichero "contadorGeneros.csv"
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            //mostramos por consola
            System.out.println(entry.getKey() + "," + entry.getValue());
            //llamamos al método escribir para ir escribiendo como nos dice
            //el examen
            escribir(entry.getKey() + "," + entry.getValue(), "contadorGeneros.csv");

        }
    }

    //método para pasar la lista de String a lista de personas
    public static List<Persona> listaObjetoPersona() {
        List<String> lineas = leerFichero("personas.csv");
        //creo la lista que voy a devolver
        List<Persona> persona = new ArrayList<>();
        for (int i = 0; i < lineas.size(); i++) {
            //separamos cada línea por , y lo guardamos en un array
            String[] array = lineas.get(i).split(",");

            //le damos un formato a la fecha de (día-mes-año)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            //creamos la fecha, le pasamos el string de la fecha y el formatter
            //creado anterior
            LocalDate fecha = LocalDate.parse(array[5], formatter);

            //creamos una persona y cada vez que itere este for cambiarán
            //los datos
            Persona per = new Persona(Integer.parseInt(array[0]),
                    array[1], array[2], array[3], array[4], fecha,
                    Boolean.parseBoolean(array[6]), array[7]);
            //añadimos a la lista la persona creada anteriormente
            //(esta irá modificandose en cada iteración)
            persona.add(per);
        }
        return persona;
    }

    //método que muestra los géneros que hay y escribe en un fichero cada género
    public static Set<String> conjuntoGenero(List<Persona> lista) {
        //creamos el map para ir añadiendo el id y el género
        Set<String> set = new HashSet<>();

        //recorremos la lista de personas
        for (int i = 0; i < lista.size(); i++) {
            //cada iteración se añade al set el género
            set.add(lista.get(i).genero());
        }

        //devolvemos el set
        return set;

    }

    //método para contar cuantos géneros hay
    public static Map<String, Integer> contadorGeneros(List<Persona> lista) {
        //SE PUEDE USAR TAMBIÉN EL MAP.GETORDEFAULT()
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            if (map.containsKey(lista.get(i).genero())) {
                map.put(lista.get(i).genero(), map.get(lista.get(i).genero()) + 1);
            } else {
                map.put(lista.get(i).genero(), 1);
            }
        }

        return map;

    }
    
    //método para obtener una lista de personas nacidas en 1985, ordenadas por su email.
    public static List<Persona> persona1985(List<Persona> lista) {
        //creamos la lista que vamos a devolver
        List<Persona> listDevolver = new ArrayList<>();
        //hacemos un for each para ver si la fecha coincide cpn 1985
        for (Persona persona : lista) {
            if (persona.fecha_nacimiento().getYear() == 1985) {
                //si coincide se añade
                listDevolver.add(persona);
            }
        }
        //ordenamos por mail
        listDevolver.sort((p1,p2)->p1.email().compareTo(p2.email()));
        //lo devolmvemos
        return listDevolver;
    }
    
    //método para obtener una lista de personas nacidas en 1985, ordenadas por su email.
    //con stream y lambda
    
    public static List<Persona> persona1985Stream(List<Persona> lista) {
        //hacemos el stream y lo devolvemos
        return lista.stream()
                .filter(p -> p.fecha_nacimiento().getYear() == 1985)
                .sorted((p1,p2)->p1.email().compareTo(p2.email()))
                .collect(Collectors.toList());
    }
    
    //Obtener un set de correos electrónicos, ordenados alfabéticamente, 
    //de aquellas personas cuyo género es "non-binary" y estén jubiladas.
    public static Set<String> correroElectronicos(List<Persona> lista) {
        //creamos el set que vamos  a devolver y le ponemos el ordenado
        Set<String> correo = new TreeSet<>((p1,p2)->p1.compareToIgnoreCase(p2));
        
        //hacemos un for each para ir recorriendo la lista
        for (Persona persona : lista) {
            if (persona.genero().equalsIgnoreCase("non-binary")
                    && persona.jubilado()) {
                correo.add(persona.email());
            }
        }
        return correo;
    }
}
