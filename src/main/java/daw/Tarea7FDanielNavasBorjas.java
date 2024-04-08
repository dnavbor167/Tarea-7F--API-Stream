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
import java.util.stream.IntStream;
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
        List<Persona> lista = listaObjetoPersona();
        System.out.println("\nEJERCICIOS 7F\n");
        
        System.out.println("\nEjercicio 1\n");
        System.out.println("Ejercicio 1.1\n");
        persona1985(lista).forEach(System.out::println);
        System.out.println("Ejercicio 1.2\n");
        persona1985Stream(lista).forEach(System.out::println);
        
        System.out.println("\nEjercicio 2\n");
        System.out.println("Ejercicio 2.1\n");
        correroElectronicos(lista).forEach(System.out::println);
        System.out.println("Ejercicio 2.2\n");
        correroElectronicosStream(lista).forEach(System.out::println);
        
        System.out.println("\nEjercicio 3\n");
        System.out.println("Ejercicio 3.1\n");
        numeroCiudadesDiferentes(lista);
        System.out.println("Ejercicio 3.2\n");
        numeroCiudadesDiferentesStream(lista);
        
        System.out.println("\nEjercicio 4\n");
        System.out.println("Ejercicio 4.1\n");
        System.out.println(seLLamaAlguienZondra(lista));
        System.out.println("Ejercicio 4.2\n");
        System.out.println(seLLamaAlguienZondraStream(lista));
        
        System.out.println("\nEjercicio 5\n");
        System.out.println("Ejercicio 5.1\n");
        System.out.println(viveEstepona(lista));
        System.out.println("Ejercicio 5.2\n");
        System.out.println(viveEsteponaStream(lista));
        
        System.out.println("\nEjercicio 6\n");
        System.out.println("Ejercicio 6.1\n");
        System.out.println(terminacionCia(lista));
        System.out.println("Ejercicio 6.2\n");
        System.out.println(terminacionCiaStream(lista));
        
        System.out.println("\nEjercicio 7\n");
        System.out.println("Ejercicio 7.1\n");
        todasEdades(lista).forEach(System.out::println);
        System.out.println("Ejercicio 7.2\n");
        todasEdadesStream(lista).forEach(System.out::println);
        
        System.out.println("\nEjercicio 8\n");
        System.out.println("Ejercicio 8.1\n");
        System.out.println(sumaTotalEdades(lista));
        System.out.println("Ejercicio 8.2\n");
        System.out.println(sumaTotalEdadesStream(lista));
        
        System.out.println("\nEjercicio 9\n");
        System.out.println("Ejercicio 9.1\n");
        System.out.println(mediaEdad(lista));
        System.out.println("Ejercicio 9.2\n");
        System.out.println(mediaEdadStream(lista));
        
        System.out.println("\nEjercicio 10\n");
        System.out.println("Ejercicio 10.1\n");
        System.out.println(todosNombres(lista));
        System.out.println("Ejercicio 10.2\n");
        System.out.println(todosNombresStream(lista));
        
       
        
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
    
    //Obtener un set de correos electrónicos, ordenados alfabéticamente, 
    //de aquellas personas cuyo género es "non-binary" y estén jubiladas.
    //con stream y lambda
    public static Set<String> correroElectronicosStream(List<Persona> lista) {

        return lista.stream()
                .filter(p->p.genero().equalsIgnoreCase("non-binary") &&p.jubilado())
                .sorted((p1,p2)->p1.email().compareTo(p2.email()))
                .map(p->p.email())
                .collect(Collectors.toSet());
    }
    
    //Obtener el número de ciudades diferentes.
    public static void numeroCiudadesDiferentes(List<Persona> lista) {
        Set<String> setCiudades = new HashSet<>();
        
        //vamos recorriendo la lista y vamos añadiendo en el set anteriormente
        //creado, por lo que si hay algún repetido no lo incluye
        for (Persona persona : lista) {
            setCiudades.add(persona.ciudad());
        }
        
        //mostramos el resultado del set
        System.out.println("El número de ciudades diferentes es " + setCiudades.size());
    }
    
    //Obtener el número de ciudades diferentes.
    //con stream y lambda
    public static void numeroCiudadesDiferentesStream(List<Persona> lista) {
        int numero = (int)lista.stream()
                .map(p->p.ciudad())
                .distinct()
                .count();
        System.out.println("El número de ciudades diferentes es " + numero);
    }
    
    //Comprobar si alguna persona se llama "Zondra".
    public static boolean seLLamaAlguienZondra(List<Persona> lista) {
        for (Persona persona : lista) {
            if (persona.nombre().equalsIgnoreCase("Zondra")) {
                return true;
            }
        }
        return false;
    }
    
    //Comprobar si alguna persona se llama "Zondra".
    //con stream y lambda
    public static boolean seLLamaAlguienZondraStream(List<Persona> lista) {
        return lista.stream()
                .anyMatch((p)->p.nombre().equalsIgnoreCase("Zondra"));
    }
    
    //Comprobar si ninguna persona vive en "Estepona".
    public static boolean viveEstepona(List<Persona> lista) {
        for (Persona persona : lista) {
            if (persona.ciudad().equalsIgnoreCase("Estepona")) {
                return false;
            } 
        }
        return true;
    }
    
    //Comprobar si ninguna persona vive en "Estepona".
    //con stream y lambda
    public static boolean viveEsteponaStream(List<Persona> lista) {
        return lista.stream()
                .noneMatch((p)->p.ciudad().equalsIgnoreCase("estepona"));
    }
    
    //Saber cuantas personas tienen en su apellido la terminación "cía".
    public static int terminacionCia(List<Persona> lista) {
        int contador = 0;
        for (Persona persona : lista) {
            if (persona.apellido().endsWith("cía")) {
                contador++;
            }
        }
        return contador;
    }
    
    //Saber cuantas personas tienen en su apellido la terminación "cía".
    //con stream y lambda
    public static int terminacionCiaStream(List<Persona> lista) {
        return (int)lista.stream()
                .filter((p)->p.apellido().endsWith("cía"))
                .count();
    }
    
    //Obtener una lista con todas las edades de cada persona.
    public static List<Integer> todasEdades(List<Persona> lista) {
        List<Integer> listaFinal = new ArrayList<>();
        for (Persona persona : lista) {
            listaFinal.add(persona.edad());
        }
        return listaFinal;
    }
    
    //Obtener una lista con todas las edades de cada persona.
    //con stream y lambda
    public static List<Integer> todasEdadesStream(List<Persona> lista) {
        return lista.stream()
                .map((p)->p.edad())
                .collect(Collectors.toList());
    }
    
    //Obtener la suma de todas las edades de las personas de la lista.
    public static int sumaTotalEdades(List<Persona> lista) {
        int contador = 0;
        for (Persona persona : lista) {
            contador += persona.edad();
        }
        return contador;
    }
    
    //Obtener la suma de todas las edades de las personas de la lista.
    //con stream y lambda
    public static int sumaTotalEdadesStream(List<Persona> lista) {
         return lista.stream()
                .mapToInt((p)->p.edad())
                .sum();
    }
    
    //Obtener la media de edad de las personas de la lista.
    public static double mediaEdad(List<Persona> lista) {
        int suma = sumaTotalEdadesStream(lista);
        return (double)suma / lista.size();
    }
    
    //Obtener la media de edad de las personas de la lista.
    //con stream y lambda
    public static double mediaEdadStream(List<Persona> lista) {
        return lista.stream()
                .mapToInt((p)->p.edad())
                .average()
                .getAsDouble();
    }
    
    //Obtener en un String todos los nombres de las personas concatenados.
    public static String todosNombres(List<Persona> lista) {
        StringBuilder sr = new StringBuilder();
        for (Persona persona : lista) {
            sr.append(persona.nombre()).append(", ");
        }
        return sr.toString();
    }
    
    //Obtener en un String todos los nombres de las personas concatenados.
    //con stream y lambda
    public static String todosNombresStream(List<Persona> lista) {
        return lista.stream()
                .map((p)->p.nombre())
                .collect(Collectors.joining(", "));
    }
}
