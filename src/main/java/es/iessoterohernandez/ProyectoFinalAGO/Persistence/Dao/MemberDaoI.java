package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
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
	 * @param activo
	 * @return List<Member>
	 */
	public List<Member> findByActive(Boolean activo);

	/**
	 * Recupera los miembros s/n activos por su categoría profesional
	 * 
	 * @param proCat
	 * @param activo
	 * @return List<Member>
	 */
	public List<Member> findByIdProCatAndActive(ProfessionalCategory proCat, Boolean activo);

	/**
	 * Recupera un miembro s/n activo por su Id
	 * 
	 * @param id
	 * @param activo
	 * @return Member
	 */
	public Member findByIdMemberAndActive(Long id, Boolean activo);

	/**
	 * Recupera un miembros por su Id
	 * 
	 * @param Id
	 * @return Member
	 */
	public Member findByIdMember(Long Id);

	/**
	 * Recupera los miembros por su categoría profesional
	 * 
	 * @param proCat
	 * @return List<Member>
	 */
	public List<Member> findByIdProCat(ProfessionalCategory proCat);

}
