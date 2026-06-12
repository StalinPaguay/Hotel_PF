package ec.edu.istq.hotel.modules.usuarios.service;

import ec.edu.istq.hotel.modules.usuarios.domain.Usuario;
import ec.edu.istq.hotel.modules.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario validarCredenciales(String username, String rawPassword, String rolRequerido) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new SecurityException("Usuario no encontrado: " + username));

        // En Spring Security (JWT) la contraseña se valida internamente en el AuthenticationManager.
        // Pero para SOAP (JAAS custom), podemos usar este método si el cliente envía texto plano o 
        // simplemente para tener la lógica de negocio centralizada (SRP).
        // Si rawPassword es nulo, significa que la validación de password se delega (ej. WSSecurity callback),
        // pero sí validamos la existencia y el rol.
        
        if (rawPassword != null && !passwordEncoder.matches(rawPassword, usuario.getPassword())) {
            throw new SecurityException("Credenciales inválidas");
        }

        if (rolRequerido != null && !usuario.getRol().equals(rolRequerido)) {
            throw new SecurityException("Acceso denegado. Se requiere el rol: " + rolRequerido);
        }

        return usuario;
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new SecurityException("Usuario no encontrado: " + username));
    }
}
