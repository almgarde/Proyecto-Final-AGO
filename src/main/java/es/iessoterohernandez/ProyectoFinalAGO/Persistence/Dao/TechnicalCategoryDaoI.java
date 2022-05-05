package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.TechnicalCategory;

/**
 * Interfaz de Persistencia. Entidad: Categorías técnicas
 * 
 * @author agadelao
 *
 */
@Repository
public interface TechnicalCategoryDaoI extends JpaRepository<TechnicalCategory, Long>{

}
