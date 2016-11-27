package com.universidadez.tcc.forum.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.universidadez.tcc.forum.model.Resposta;
import com.universidadez.tcc.forum.repository.RespostaRepository;
import com.universidadez.tcc.util.JpaUtil;


@FacesConverter(forClass = Resposta.class)
public class RespostaConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Resposta retorno = null;
		EntityManager em = JpaUtil.getEntityManager();

		try {
			if (value != null && !" ".equals(value)) {
				RespostaRepository rr = new RespostaRepository(em);
				retorno = rr.buscaPorId(new Long(value));
			}
			return retorno;
		} finally {
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Resposta resposta = (Resposta) value;
			return resposta.getId() == null ? null : resposta.getId().toString();
		}
		return null;
	}

}
