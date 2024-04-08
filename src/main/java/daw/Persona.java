/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package daw;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author daniel
 */
public record Persona(int id, String nombre, String apellido, String email,
        String genero, LocalDate fecha_nacimiento, boolean jubilado, String ciudad) {

    //método que devuelve la edad
    public int edad() {
        return (int)ChronoUnit.YEARS.between(this.fecha_nacimiento, LocalDate.now());
    }
}
