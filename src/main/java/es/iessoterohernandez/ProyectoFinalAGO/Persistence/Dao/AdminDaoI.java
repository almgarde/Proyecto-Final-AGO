package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Admin;



/**
 * Interfaz de Persistencia. Entidad: Administradores
 * 
 * @author agadelao
 *
 */
@Repository
public interface AdminDaoI extends JpaRepository<Admin, Long> {
	
	

}
