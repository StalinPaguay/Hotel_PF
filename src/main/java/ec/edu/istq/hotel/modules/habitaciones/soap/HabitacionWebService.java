package ec.edu.istq.hotel.modules.habitaciones.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(targetNamespace = "http://soap.hotel.istq.edu.ec/")
public interface HabitacionWebService {
    @WebMethod
    RespuestaSoap cambiarEstadoHabitacion(
            @WebParam(name = "habitacionId") Long id,
            @WebParam(name = "nuevoEstado") String nuevoEstado);
}
