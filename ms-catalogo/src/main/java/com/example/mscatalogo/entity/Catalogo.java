package com.example.mscatalogo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    @OneToMany(mappedBy = "catalogo", cascade = CascadeType.ALL)
    private List<Producto> productos;

    @ManyToOne
    @JoinColumn(name = "Producto_id")
    private Producto producto;
}
