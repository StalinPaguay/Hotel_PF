package ec.edu.istq.hotel.modules.habitaciones.controller;

import ec.edu.istq.hotel.modules.habitaciones.domain.Habitacion;
import ec.edu.istq.hotel.modules.habitaciones.repository.HabitacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

    private final HabitacionRepository habitacionRepository;

    @GetMapping("/disponibles")
    public ResponseEntity<List<Habitacion>> getDisponibles(
            @RequestParam("checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin,
            @RequestParam("checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout) {
        
        List<Habitacion> disponibles = habitacionRepository.findDisponibles(checkin, checkout);
        return ResponseEntity.ok(disponibles);
    }
}
