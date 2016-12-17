package com.universidadez.tcc.universidade.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.universidadez.tcc.universidade.model.Curso;
import com.universidadez.tcc.universidade.repository.CursoRepository;
import com.universidadez.tcc.util.JpaUtil;

/**
 */
@FacesConverter(forClass = Curso.class)
public class CursoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Curso retorno = null;
		EntityManager em = JpaUtil.getEntityManager();

		try {
			if (value != null && !" ".equals(value)&& !"Selecione".equals(value)) {
				CursoRepository cr = new CursoRepository(em);
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
			Curso curso = (Curso) value;
			return curso.getId() == null ? null : curso.getId().toString();
		}
		return null;
	}
}
