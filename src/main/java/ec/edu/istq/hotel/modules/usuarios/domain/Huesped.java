package ec.edu.istq.hotel.modules.usuarios.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "huespedes")
@Data
@EqualsAndHashCode(callSuper = true)
public class Huesped extends Usuario {
    
    private String nombre;
    private String pasaporte_cedula;
    private String nacionalidad;
    private String email;
}
