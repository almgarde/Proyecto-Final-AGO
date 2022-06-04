package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Admin;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.AdminsDatatableDto;

/**
 * Interfaz de Servicios. Entidad: Administrador
 * 
 * @author agadelao
 *
 */
public interface AdminServiceI {

	/**
	 * Recupera un admin por su usuario
	 * 
	 * @param usernameAdmin
	 * @return Admin
	 */
	public Admin getAdminByUsername(String usernameAdmin) throws Exception;

	/**
	 * Recupera todos los datos de los administradores almacenados en BDD
	 * 
	 * @return List<AdminsDatatableDto>
	 * @throws Exception
	 */
	public List<AdminsDatatableDto> getAllAdminsData() throws Exception;

	/**
	 * Almacena un administrador en BD
	 * 
	 * @param adminData
	 * @return Admin
	 * @throws Exception
	 */
	public Admin addAdmin(Map<String, String> adminData) throws Exception;

}
