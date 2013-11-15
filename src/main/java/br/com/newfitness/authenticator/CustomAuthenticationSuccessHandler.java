package br.com.newfitness.authenticator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private static final Logger log = Logger.getLogger(CustomAuthenticationSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		log.info("Usuário ["+authentication.getName()+"] autenticado com sucesso.");
		request.getSession().setAttribute("usuarioLogado", authentication.getDetails());
		try {
			super.onAuthenticationSuccess(request, response, authentication);
		} catch (ServletException e) {
			log.error("Ocorreu um erro ao redirecionar para a pagina principal ["+e.getMessage()+"].", e);
		} catch (IOException e) {
			log.error("Ocorreu um erro ao redirecionar para a pagina principal ["+e.getMessage()+"].", e);
		}
	}
	
}
