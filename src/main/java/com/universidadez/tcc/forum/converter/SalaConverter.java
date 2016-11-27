package com.universidadez.tcc.forum.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.universidadez.tcc.forum.model.Sala;
import com.universidadez.tcc.forum.repository.SalaRepository;
import com.universidadez.tcc.util.JpaUtil;



@FacesConverter(forClass = Sala.class)
public class SalaConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Sala retorno = null;
		EntityManager em = JpaUtil.getEntityManager();

		try {
			if (value != null && !" ".equals(value)) {
				SalaRepository sr = new SalaRepository(em);
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
			Sala sala = (Sala) value;
			return sala.getId() == null ? null : sala.getId().toString();
		}
		return null;
	}

}
