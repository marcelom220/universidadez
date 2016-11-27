package com.universidadez.tcc.login.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.universidadez.tcc.login.model.Usuario;
import com.universidadez.tcc.login.repository.UsuarioRepository;
import com.universidadez.tcc.util.JpaUtil;

/**
 */
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Usuario retorno = null;
		EntityManager em = JpaUtil.getEntityManager();

		try {
			if (value != null && !" ".equals(value)) {
				UsuarioRepository ur = new UsuarioRepository(em);
				retorno = ur.buscaPorId(new Long(value));
			}
			return retorno;
		} finally {
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		return null;
	}
}
