package cl.praxis.ExamenFinal.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="comandas")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_comanda")
    private int id;
    @Column(name="fecha_emision")
    private Date fechaEmision;
    @Column(name="estado")
    private boolean estado;
    @Column(name="propina_sugerida")
    private int propinaSugerida;

    @ManyToOne
    @JoinColumn(name = "id_garzon")
    private Garzon garzon;

    public boolean isEstado() { //ingresado manualmente dado que no reconocía al atributo
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
