package com.universidadez.tcc.universidade.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.universidadez.tcc.universidade.model.Turma;
import com.universidadez.tcc.universidade.repository.TurmaRepository;
import com.universidadez.tcc.util.JpaUtil;

/**
 */
@FacesConverter(forClass = Turma.class)
public class TurmaConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Turma retorno = null;
		EntityManager em = JpaUtil.getEntityManager();

		try {
			if (value != null && !" ".equals(value)) {
				TurmaRepository tr = new TurmaRepository(em);
				retorno = tr.buscaPorId(new Long(value));
			}
			return retorno;
		} finally {
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Turma turma = (Turma) value;
			return turma.getId() == null ? null : turma.getId().toString();
		}
		return null;
	}

}
