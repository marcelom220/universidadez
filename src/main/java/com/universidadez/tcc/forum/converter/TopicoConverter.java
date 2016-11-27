package com.universidadez.tcc.forum.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.universidadez.tcc.forum.model.Topico;
import com.universidadez.tcc.forum.repository.TopicoRepository;
import com.universidadez.tcc.util.JpaUtil;


@FacesConverter(forClass = Topico.class)
public class TopicoConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Topico retorno = null;
		EntityManager em = JpaUtil.getEntityManager();

		try {
			if (value != null && !" ".equals(value)) {
				TopicoRepository sr = new TopicoRepository(em);
				retorno = sr.buscaPorId(new Long(value));
			}
			return retorno;
		} finally {
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Topico topico = (Topico) value;
			return topico.getId() == null ? null : topico.getId().toString();
		}
		return null;
	}

}
