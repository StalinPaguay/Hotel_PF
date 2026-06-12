package ec.edu.istq.hotel.modules.habitaciones.soap;

import ec.edu.istq.hotel.modules.habitaciones.domain.Habitacion;
import ec.edu.istq.hotel.modules.habitaciones.repository.HabitacionRepository;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@WebService(
        endpointInterface = "ec.edu.istq.hotel.modules.habitaciones.soap.HabitacionWebService",
        serviceName = "HabitacionService",
        portName = "HabitacionPort",
        targetNamespace = "http://soap.hotel.istq.edu.ec/"
)
public class HabitacionWebServiceImpl implements HabitacionWebService {

    private final HabitacionRepository habitacionRepository;

    @Override
    @Transactional
    public RespuestaSoap cambiarEstadoHabitacion(Long id, String nuevoEstado) {
        try {
            Habitacion hab = habitacionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + id));
            hab.setEstado(nuevoEstado);
            habitacionRepository.save(hab);
            return new RespuestaSoap(200, "Estado actualizado exitosamente", nuevoEstado);
        } catch (Exception e) {
            return new RespuestaSoap(500, "Error: " + e.getMessage(), "Desconocido");
        }
    }
}
