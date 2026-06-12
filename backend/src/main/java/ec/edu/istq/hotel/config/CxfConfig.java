package ec.edu.istq.hotel.config;

import ec.edu.istq.hotel.modules.habitaciones.soap.HabitacionWebService;
import ec.edu.istq.hotel.security.soap.SoapJaasCallbackHandler;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.xml.ws.Endpoint;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class CxfConfig {

    private final Bus bus;
    private final HabitacionWebService habitacionWebService;
    private final SoapJaasCallbackHandler soapJaasCallbackHandler;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, habitacionWebService);
        
        // WS-Security Interceptor
        Map<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        inProps.put(WSHandlerConstants.PW_CALLBACK_REF, soapJaasCallbackHandler);
        
        endpoint.getInInterceptors().add(new WSS4JInInterceptor(inProps));
        
        // Interceptor de Salida (Ida y vuelta)
        Map<String, Object> outProps = new HashMap<>();
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.TIMESTAMP);
        endpoint.getOutInterceptors().add(new org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor(outProps));
        
        endpoint.publish("/habitaciones");
        return endpoint;
    }
}
