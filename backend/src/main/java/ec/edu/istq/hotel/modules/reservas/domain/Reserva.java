package ec.edu.istq.hotel.modules.reservas.domain;

import ec.edu.istq.hotel.modules.habitaciones.domain.Habitacion;
import ec.edu.istq.hotel.modules.usuarios.domain.Huesped;
import ec.edu.istq.hotel.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
@Data
@EqualsAndHashCode(callSuper = true)
public class Reserva extends BaseEntity {
    
    private LocalDate fecha_checkin;
    private LocalDate fecha_checkout;
    private String estado; // "ACTIVA", "CANCELADA", "COMPLETADA"
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "huesped_id", nullable = false)
    private Huesped huesped;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitacion_id", nullable = false)
    private Habitacion habitacion;
}
