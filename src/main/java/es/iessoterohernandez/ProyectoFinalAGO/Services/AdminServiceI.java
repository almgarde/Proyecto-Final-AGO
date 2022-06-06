package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.List;
import java.util.Map;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Admin;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.AdminDto;
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
	 * @throws Exception
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
	public void addAdmin(Map<String, String> adminData) throws Exception;

	/**
	 * Recupera los datos personales de un administrador por su nombre de usuario
	 * 
	 * @param usernameAdmin
	 * @return AdminDto
	 * @throws Exception
	 */
	public AdminDto getAdminDataByUsername(String usernameAdmin) throws Exception;
	
	/**
	 * Actualiza los datos personales de un administrador en BD
	 * 
	 * @param adminData
	 * @return Admin
	 * @throws Exception
	 */
	public void updateDataAdmin(Map<String, String> adminData) throws Exception;
	
	/**
	 * Actualiza la contraseña de un administrador en BD
	 * 
	 * @param adminData
	 * @throws Exception
	 */
	public void changePwdAdmins(Map<String, String> adminData) throws Exception;
	
	/**
	 * Elimina un admin almacenado en BDD
	 * 
	 * @param adminsData
	 * @throws Exception
	 */
	public void deleteAdmins(Map<String, String> adminData) throws Exception;

}
