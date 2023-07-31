package com.narvasoft.agenda.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity
@Table(name = "usuarios")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //@Column(name = "id")
    private Long id;
    @NotBlank
    //@Column(name = "nombre")
    private String nombre;
    @NotEmpty
    //@Column(name = "password")
    private String password;
    @NotEmpty
    //@Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    //@Column(name = "telefono")
    private String telefono;
}