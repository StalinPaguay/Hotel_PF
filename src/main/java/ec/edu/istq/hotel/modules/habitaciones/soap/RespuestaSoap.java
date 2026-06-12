package ec.edu.istq.hotel.modules.habitaciones.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "RespuestaSoap")
@XmlAccessorType(XmlAccessType.FIELD)
public class RespuestaSoap {
    
    @XmlElement(name = "codigo")
    private int codigo;
    
    @XmlElement(name = "mensaje")
    private String mensaje;
    
    @XmlElement(name = "estadoActual")
    private String estadoActual;
}
