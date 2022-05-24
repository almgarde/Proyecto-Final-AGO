package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.News;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;

/**
 * Interfaz de Persistencia. Entidad: Miembros
 * 
 * @author agadelao
 *
 */
@Repository
public interface MemberDaoI extends JpaRepository<Member, Long> {
	
	/**
	 * Recupera las miembros s/n activos  
	 * 
	 * @param proCat
	 * @param activo
	 * @return List<Member>
	 */
	public List<Member> findByActive(Boolean activo);
	
	/**
	 * Recupera las miembros s/n activos por su categoría profesional 
	 * 
	 * @param proCat
	 * @param activo
	 * @return List<Member>
	 */
	public List<Member> findByIdProCatAndActive(ProfessionalCategory proCat, Boolean activo);
	
	/**
	 * Recupera las miembros s/n activos por su nombre corto 
	 * 
	 * @param proCat
	 * @param activo
	 * @return List<Member>
	 */
	public List<Member> findByShortNameMemberAndActive(String shortNameMember, Boolean activo);
	
	/**
	 * Recupera un miembro s/n activa por su Id
	 * 
	 * @param id
	 * @return Member
	 */
	public Member findByIdMemberAndActive(Long id, Boolean activo);
	
	/**
	 * Recupera los miembros por su Id
	 * 
	 * @param techCat
	 * @param activo
	 * @return List<Facility>
	 */
	public Member findByIdMember(Long Id);
	
	/**
	 * Recupera las miembros s/n activos por su categoría profesional 
	 * 
	 * @param proCat
	 * @param activo
	 * @return List<Member>
	 */
	public List<Member> findByIdProCat(ProfessionalCategory proCat);

}
