package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Thesis;

/**
 * Interfaz de Persistencia. Entidad: Tesis
 * 
 * @author agadelao
 *
 */
@Repository
public interface ThesisDaoI extends JpaRepository<Thesis, Long> {

	/**
	 * Recupera una tesis por su Id
	 * 
	 * @param Id
	 * @return Thesis
	 */
	public Thesis findByIdThesis(Long Id);

	/**
	 * Recupera las tesis s/n activos
	 * 
	 * @param activo
	 * @return List<Thesis>
	 */
	public List<Thesis> findByActive(Boolean activo);

}
