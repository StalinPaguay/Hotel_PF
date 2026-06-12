package ec.edu.istq.hotel.modules.reservas.controller;

import ec.edu.istq.hotel.modules.habitaciones.domain.Habitacion;
import ec.edu.istq.hotel.modules.habitaciones.repository.HabitacionRepository;
import ec.edu.istq.hotel.modules.reservas.domain.Reserva;
import ec.edu.istq.hotel.modules.reservas.repository.ReservaRepository;
import ec.edu.istq.hotel.modules.usuarios.domain.Huesped;
import ec.edu.istq.hotel.modules.usuarios.domain.Usuario;
import ec.edu.istq.hotel.modules.usuarios.repository.UsuarioRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final HabitacionRepository habitacionRepository;
    private final UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaRequest req, Authentication auth) {
        // Obtenemos el usuario autenticado a partir del token JWT
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                
        if (!(usuario instanceof Huesped huesped)) {
            throw new RuntimeException("Solo los huéspedes pueden crear reservas en línea");
        }

        Habitacion habitacion = habitacionRepository.findById(req.getHabitacionId())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        // Validar disponibilidad (básico)
        // Omitimos la consulta completa de disponibilidad por brevedad, asumimos validación en frontend
        // Pero el requerimiento pide "validar disponibilidad", así que la validaremos
        boolean disponible = habitacionRepository.findDisponibles(req.getCheckin(), req.getCheckout())
                .stream().anyMatch(h -> h.getId().equals(habitacion.getId()));

        if (!disponible) {
            throw new RuntimeException("La habitación no está disponible en las fechas seleccionadas");
        }

        long dias = ChronoUnit.DAYS.between(req.getCheckin(), req.getCheckout());
        BigDecimal total = habitacion.getPrecio_noche().multiply(BigDecimal.valueOf(dias));

        Reserva reserva = new Reserva();
        reserva.setHabitacion(habitacion);
        reserva.setHuesped(huesped);
        reserva.setFecha_checkin(req.getCheckin());
        reserva.setFecha_checkout(req.getCheckout());
        reserva.setEstado("ACTIVA");
        reserva.setTotal(total);

        return ResponseEntity.ok(reservaRepository.save(reserva));
    }
}

@Data
class ReservaRequest {
    private Long habitacionId;
    private LocalDate checkin;
    private LocalDate checkout;
}
