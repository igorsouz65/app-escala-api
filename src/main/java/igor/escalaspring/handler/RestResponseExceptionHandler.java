package igor.escalaspring.handler;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class RestResponseExceptionHandler extends DefaultResponseErrorHandler{

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		
		return super.hasError(response);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		
		super.handleError(response);
	}
	

}
