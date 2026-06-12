package ec.edu.istq.hotel.modules.habitaciones.repository;

import ec.edu.istq.hotel.modules.habitaciones.domain.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    
    @Query("SELECT h FROM Habitacion h WHERE h.estado = 'LIBRE' AND h.id NOT IN (" +
           "SELECT r.habitacion.id FROM Reserva r WHERE " +
           "(r.estado = 'ACTIVA' OR r.estado = 'CONFIRMADA') AND " +
           "((r.fecha_checkin <= :checkout AND r.fecha_checkout >= :checkin))" +
           ")")
    List<Habitacion> findDisponibles(@Param("checkin") LocalDate checkin, @Param("checkout") LocalDate checkout);
}
