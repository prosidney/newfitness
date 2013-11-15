package br.com.newfitness.authenticator;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
/*
 * 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
*/

public class MyAuthenticationProvider implements AuthenticationProvider,Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean supports ( Class<? extends Object> authentication ) {
		return true;
	}

/*	@Transactional(readOnly = true)
	public Authentication authenticate ( Authentication authentication ) throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = null ;
		try{
			String passMD5 = new Util().createMD5(authentication.getCredentials().toString());
			Usuario usuario = usuarioDao.findByUsuario(authentication.getName(), passMD5);
			
			if(usuario == null){
				throw new UsernameNotFoundException("Usuário não encontrado");
			}
			if(!usuario.getSenha().equals(passMD5)){
				throw new BadCredentialsException("Senha invalida");
			}
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new GrantedAuthorityImpl("ROLE_ACCESS"));
			
			auth = new UsernamePasswordAuthenticationToken(
					authentication, 
					authentication.getCredentials(), 
					authorities);
			
			auth.setDetails(usuario);
		}catch (NoSuchAlgorithmException e) {
			throw new AuthenticationServiceException(e.getMessage(), e);
		}
			
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return auth;
	}*/
	
	public Authentication authenticate ( Authentication authentication ) throws AuthenticationException {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_ACCESS"));
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
								authentication, 
								authentication.getCredentials(), 
								authorities);
		
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		auth.setDetails(new User().setNome(authentication.getCredentials().toString()));
		
		return auth;
	}
	
	public static void main(String[] args) {
		String senha = "sidney";
		
		MessageDigest md = null;
        try {  
            md = MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));	
        String s2 = hash.toString(16);
        
        System.out.println(s2);         
	}
	
	public class User implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String nome;

		public User() {
		}
		
		public String getNome() {
			return nome;
		}

		public User setNome(String nome) {
			this.nome = nome;
			
			return this;
		}
	}
}