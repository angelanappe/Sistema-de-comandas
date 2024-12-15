package cl.praxis.ExamenFinal.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="mesas")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_mesa")
    private int id;
    @Column(name="numero_mesa")
    private int numeroMesa;
    @Column(name="cantidad_persona")
    private int cantidadPersonas;
    @Column(name="disponible")
    private boolean disponible;

}
