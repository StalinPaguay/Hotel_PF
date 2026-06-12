package ec.edu.istq.hotel.modules.habitaciones.domain;

import ec.edu.istq.hotel.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Entity
@Table(name = "habitaciones")
@Data
@EqualsAndHashCode(callSuper = true)
public class Habitacion extends BaseEntity {
    
    private String numero;
    private String tipo;
    private BigDecimal precio_noche;
    private Integer capacidad;
    private String estado; // "LIBRE", "OCUPADA", "MANTENIMIENTO"
}
