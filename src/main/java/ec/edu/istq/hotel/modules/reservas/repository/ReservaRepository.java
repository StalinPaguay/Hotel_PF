package ec.edu.istq.hotel.modules.reservas.repository;

import ec.edu.istq.hotel.modules.reservas.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
