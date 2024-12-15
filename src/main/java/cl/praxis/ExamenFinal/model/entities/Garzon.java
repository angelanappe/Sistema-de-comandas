package cl.praxis.ExamenFinal.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="garzones")
public class Garzon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_garzon")
    private int id;
    @Column(name="rut")
    private String rut;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="correo")
    private String email;
    @Column(name="direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "id_comuna")
    private Comuna comuna;
}
