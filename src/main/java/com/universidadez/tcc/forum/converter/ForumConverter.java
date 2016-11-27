package com.universidadez.tcc.forum.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import com.universidadez.tcc.forum.model.Forum;
import com.universidadez.tcc.forum.repository.ForumRepository;
import com.universidadez.tcc.util.JpaUtil;



@FacesConverter(forClass = Forum.class)
public class ForumConverter implements Converter {

	Logger logger = Logger.getLogger(ForumConverter.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		logger.info("Entrou no método getAsObject, recebe uma string com o id e retorna um objeto.");
		Forum retorno = null;
		EntityManager em = JpaUtil.getEntityManager();

		try {
			if (value != null && !" ".equals(value)) {
				ForumRepository sr = new ForumRepository(em);
				retorno = sr.buscaPorId(new Long(value));
			}
			return retorno;
		} finally {
			em.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		logger.info("Entrou no método getAsString, recebe um objeto e retorna uma string com o id.");
		if (value != null) {
			Forum forum = (Forum) value;
			return forum.getId() == null ? null : forum.getId().toString();
		}
		return null;
	}
}
