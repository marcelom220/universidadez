package com.universidadez.tcc.universidade.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.universidadez.tcc.universidade.model.Campus;
import com.universidadez.tcc.universidade.repository.CampusRepository;
import com.universidadez.tcc.util.JpaUtil;

/**
 */
@FacesConverter(forClass = Campus.class)
public class CampusConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Campus retorno = null;
		EntityManager em = JpaUtil.getEntityManager();

		try {
			if (value != null && !" ".equals(value)&& !"Selecione".equals(value)) {
				CampusRepository cr = new CampusRepository(em);
				retorno = cr.buscaPorId(new Long(value));
			}
			return retorno;
		} finally {
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null&& !"Selecione".equals(value)) {
			Campus campus = (Campus) value;
			return campus.getIdCampus() == null ? null : campus.getIdCampus().toString();
		}
		return null;
	}

}
