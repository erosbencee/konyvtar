
package hu.rft.model;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

public class MyResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse chr) throws IOException {
        
        if(chr.getStatusCode() != HttpStatus.OK) {
            
            if(chr.getStatusCode() == HttpStatus.CONFLICT) {
                
                return false;
            }
            
            return true;
        }
        
        return false;
        
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        
        HttpStatus statusCode = response.getStatusCode();
        
        DefaultResponseErrorHandler dreh = new DefaultResponseErrorHandler();
        
        dreh.handleError(response);
    }
    
    
}
