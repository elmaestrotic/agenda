package com.narvasoft.agenda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Entity
@Table(name = "salas")
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Salas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fecha;

    @NotEmpty
    private String grado;

    @NotEmpty
    private String seccion;

    @NotEmpty
    private String asignatura;

    private boolean pc1;
    private boolean pc2;
    private boolean pc3;
    private boolean pc4;
    private boolean pc5;
    private boolean pc6;
    private boolean pc7;
    private boolean pc8;
    private boolean pc9;
    private boolean pc10;

    private String novedad;
}