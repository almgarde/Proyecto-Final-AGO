package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.MembersDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.ProCatMembersDto;

/**
 * Interfaz de Servicios. Entidad: Miembros
 * 
 * @author agadelao
 *
 */
public interface MembersServiceI {

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
