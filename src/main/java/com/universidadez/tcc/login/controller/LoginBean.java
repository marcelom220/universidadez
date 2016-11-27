package com.universidadez.tcc.login.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.universidadez.tcc.login.model.Usuario;
import com.universidadez.tcc.login.repository.UsuarioRepository;
import com.universidadez.tcc.util.JpaUtil;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LoginBean.class);

	private Usuario usuarioLogin = new Usuario();

	/**
	 * Construtor
	 */
	public LoginBean() {
	}

	/**
	 * @return String
	 */
	public String login() {
		logger.info("iniciou o método login.");
		FacesContext context = FacesContext.getCurrentInstance();
		EntityManager em = JpaUtil.getEntityManager();
		UsuarioRepository ur = new UsuarioRepository(em);

		Usuario usuarioAutenticado = ur.login(usuarioLogin);

		if (usuarioAutenticado != null) {

			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			request.getSession().setAttribute("usuarioLogado", usuarioAutenticado);
			return "/Usuario?faces-redirect=true";

		} else {

			FacesMessage mensagem = new FacesMessage("Usuário e/ou senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			return null;
		}
	}

	/**
	 * @return String
	 */
	public String logout() {
		logger.info("iniciou o método logout.");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";
	}

	/**
	 * @return the usuarioLogin
	 */
	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}

	/**
	 * @param usuarioLogin
	 *            the usuarioLogin to set
	 */
	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

}

