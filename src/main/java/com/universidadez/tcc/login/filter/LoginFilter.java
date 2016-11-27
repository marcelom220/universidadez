package com.universidadez.tcc.login.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.universidadez.tcc.login.model.Usuario;

/**
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(LoginFilter.class);

	/**
	 * 
	 */
	public LoginFilter() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		logger.info("Entrou no método doFilter.");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

		if (usuario == null && !req.getRequestURI().endsWith("/Login.xhtml")
				&& !req.getRequestURI().endsWith("/AccessDenied.xhtml")
				&& !req.getRequestURI().contains("/javax.faces.resource/")) {
			logger.info("Entrou no IF do método doFilter. Usuário não está logado.");
			res.sendRedirect(req.getContextPath() + "/Login.xhtml");
		} else {
			logger.info("Entrou no ELSE do método doFilter. Sessão do usuário já existe.");
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}

