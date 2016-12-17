package com.universidadez.tcc.universidade.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.universidadez.tcc.universidade.model.Universidade;
import com.universidadez.tcc.universidade.repository.UniversidadeRepository;
import com.universidadez.tcc.util.JpaUtil;

/**
 */
@FacesConverter(forClass = Universidade.class)
public class UniversidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Universidade retorno = null;
		EntityManager em = JpaUtil.getEntityManager();

		try {
			if (value != null && !" ".equals(value) && !"Selecione".equals(value)) {
				UniversidadeRepository ur = new UniversidadeRepository(em);
				retorno = ur.buscaPorId(new Long(value));
			}
			return retorno;
		} finally {
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null  && !"Selecione".equals(value) ) {
			Universidade universidade = (Universidade) value;
			return universidade.getId() == null ? null : universidade.getId().toString();
		}
		return null;
	}
}
