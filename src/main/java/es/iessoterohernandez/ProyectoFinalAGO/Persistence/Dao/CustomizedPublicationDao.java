package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

/**
 * Interfaz de persistencia con Criteria. Entidad: Publicaciones
 * 
 * @author agadelao
 *
 */
public interface CustomizedPublicationDao {

	/**
	 * Recupera los años ordenados asociados a las publicaciones sin repetir
	 * 
	 * @param active
	 * @param ascendente
	 * @return List<Integer>
	 */
	public List<Integer> getYearsPublicationsActiveOrdered(Boolean active, Boolean ascendente);

}
