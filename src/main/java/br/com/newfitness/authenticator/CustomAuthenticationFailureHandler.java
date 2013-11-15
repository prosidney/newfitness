package br.com.newfitness.authenticator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final Logger log = Logger.getLogger(CustomAuthenticationFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
		StringBuffer url = new java.lang.StringBuffer().append("/index.jsp")
													   .append("?mensagemErroLogin=");
		try {
			url.append(exception.getMessage());
			/*setUseForward(true);*/
			super.setDefaultFailureUrl(url.toString());
			super.onAuthenticationFailure(request, response, exception);
		} catch (IOException e) {
			log.error("Ocorreu um erro ao redirecionar para a pagina principal ["+e.getMessage()+"].", e);
		} catch (ServletException e) {
			log.error("Ocorreu um erro ao redirecionar para a pagina principal ["+e.getMessage()+"].", e);
		}
	}
}
