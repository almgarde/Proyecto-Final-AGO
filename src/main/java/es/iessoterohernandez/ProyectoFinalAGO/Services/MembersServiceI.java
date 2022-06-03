package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Facility;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.MembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatMembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.MembersDatatableDto;

/**
 * Interfaz de Servicios. Entidad: Miembros
 * 
 * @author agadelao
 *
 */
public interface MembersServiceI {

	/**
	 * Recupera todos los datos de los miembros almacenados en BDD
	 * 
	 * @return List<MembersDatatableDto>
	 * @throws Exception
	 */
	public List<MembersDatatableDto> getAllMembersData() throws Exception;

	/**
	 * Almacena un miembro en BDD
	 * 
	 * @param membersData
	 * @param imageMembers
	 * @return Member
	 * @throws Exception
	 */
	public Member addMembers(Map<String, String> membersData, String imageMembers) throws Exception;

	/**
	 * Actualiza los datos de un miembro almacenado en BDD
	 * 
	 * @param membersData
	 * @return Member
	 * @throws Exception
	 */
	public Member updateMembers(Map<String, String> membersData) throws Exception;

	/**
	 * Actualiza la foto de un miembro almacenado en BDD
	 * 
	 * @param membersData
	 * @param photoMembers
	 * @return Member
	 * @throws Exception
	 */
	public Member updatePhotoMembers(Map<String, String> membersData, String photoMembers) throws Exception;

	/**
	 * Elimina un miembro almacenado en BDD
	 * 
	 * @param membersData
	 * @throws Exception
	 */
	public void deleteMembers(Map<String, String> membersData) throws Exception;

	/**
	 * Recupera los miembros activos
	 * 
	 * @return List<MembersDto>
	 * @throws Exception
	 */
	public List<MembersDto> getMembersByActive() throws Exception;

	/**
	 * Recupera todos los miembros activos agrupados por categorías profesionales
	 * 
	 * @return List<ProCatMembersDto>
	 * @throws Exception
	 */
	public List<ProCatMembersDto> getAllMembersByProCatActive() throws Exception;

	/**
	 * Recupera los miembros activos de una categoría profesional
	 * 
	 * @param proCat
	 * @return ProCatMembersDto
	 * @throws Exception
	 */
	public ProCatMembersDto getMembersByProCatActive(ProfessionalCategory proCat) throws Exception;

	/**
	 * Recupera un miembro activo por su Id
	 * 
	 * @param id
	 * @return MembersDto
	 * @throws Exception
	 */
	public MembersDto getMemberByIdActive(Long id) throws Exception;

}
