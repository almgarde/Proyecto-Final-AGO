package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Publication;

/**
 * Implementación Interfaz de persistencia con Criteria. Entidad: Publicaciones
 * 
 * @author agadelao
 *
 */
@Repository
public class CustomizedPublicationDaoImpl implements CustomizedPublicationDao {

	/** Logger */
	final static Logger LOGGER = LoggerFactory.getLogger(CustomizedPublicationDaoImpl.class);

	@Autowired
	EntityManager em;

	/**
	 * Recupera los años asociados a las publicaciones sin repetir ordenados
	 * 
	 * @param active
	 * @return List<Integer>
	 */
	@Override
	public List<Integer> getYearsPublicationsActiveOrdered(Boolean active, Boolean ascendente) {

		LOGGER.info("CustomizedPublicationDaoImpl getYearsPublicationsActive .- Inicio");

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> cQuery = cb.createQuery(Integer.class);
		final Root<Publication> rootC = cQuery.from(Publication.class);

		final Predicate pr1 = cb.equal(rootC.get("active"), active);

		cQuery.select(rootC.get("yearPublication")).where(pr1);

		if (ascendente == true) {
			cQuery.orderBy(cb.asc(rootC.get("yearPublication")));
		} else {
			cQuery.orderBy(cb.desc(rootC.get("yearPublication")));
		}

		List<Integer> years = em.createQuery(cQuery.distinct(true)).getResultList();

		LOGGER.info("CustomizedPublicationDaoImpl getYearsPublicationsActive .- Fin");

		return years;
	}

}
