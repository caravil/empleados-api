package com.parameta.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, length = 20)
    private String tipoDocumento;

    @Column(nullable = false, unique = true, length = 30)
    private String numeroDocumento;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private LocalDate fechaVinculacion;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private Double salario;
}