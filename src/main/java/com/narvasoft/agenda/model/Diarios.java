package com.narvasoft.agenda.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Entity
@Table(name = "diarios")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Diarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //@Column(name = "id")
    private Long id;
    @NotBlank
    //@Column(name = "nombre")
    private String fecha;
    @NotEmpty
    //@Column(name = "password")
    private String grado;
    @NotEmpty
    //@Column(name = "email", nullable = false, length = 50, unique = true)
    private String seccion;

    //@Column(name = "telefono")
    @NotEmpty
    private String asignatura;

    @NotEmpty
    private String descripcion;

    @NotEmpty
    private String reflexion;
}