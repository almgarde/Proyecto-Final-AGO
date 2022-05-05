package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;

/**
 * Interfaz de Persistencia. Entidad: Categorías profesionales
 * 
 * @author agadelao
 *
 */
@Repository
public interface ProfessionalCategoryDaoI extends JpaRepository<ProfessionalCategory, Long> {

}
