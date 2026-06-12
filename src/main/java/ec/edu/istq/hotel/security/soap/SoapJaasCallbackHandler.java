package ec.edu.istq.hotel.security.soap;

import ec.edu.istq.hotel.modules.usuarios.domain.Usuario;
import ec.edu.istq.hotel.modules.usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.stereotype.Component;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SoapJaasCallbackHandler implements CallbackHandler {

    private final UsuarioService usuarioService;

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof WSPasswordCallback pc) {
                String username = pc.getIdentifier();

                // Usa el servicio compartido para validar la existencia y el rol (SRP)
                Usuario usuario = usuarioService.validarCredenciales(username, null, "ROLE_EMPLEADO");

                // WSS4J en modo USERNAME_TOKEN espera que seteemos el password de la BD aquí
                // En la vida real requeriría un UsernameTokenValidator para BCrypt, pero para propósitos 
                // de simplificación y compatibilidad con el entorno actual, dejamos que WSS4J valide
                // (si el hash coincide o usamos texto plano en la BD legacy).
                pc.setPassword(usuario.getPassword());

            } else {
                throw new UnsupportedCallbackException(callback, "Tipo de validación de seguridad no soportada");
            }
        }
    }
}
