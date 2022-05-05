package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;

/**
 * Interfaz de Persistencia. Entidad: Miembros
 * 
 * @author agadelao
 *
 */
@Repository
public interface MemberDaoI extends JpaRepository<Member, Long> {

}
