package ec.edu.istq.hotel.modules.usuarios.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "empleados")
@Data
@EqualsAndHashCode(callSuper = true)
public class Empleado extends Usuario {
    
    private String nombre;
    private String cargo;
    private String turno;
    private String extension_interna;
}
