package es.iessoterohernandez.ProyectoFinalAGO.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.AdminDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Admin;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Link;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Member;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.ProfessionalCategory;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.AdminDto;
import es.iessoterohernandez.ProyectoFinalAGO.Services.Dto.Datatables.AdminsDatatableDto;

/**
 * Servicios. Entidad: Administrador
 * 
 * @author agadelao
 *
 */
@Service
public class AdminServiceImpl implements AdminServiceI {

	final static Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	AdminDaoI adminDao;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);

	/**
	 * Recupera un admin por su usuario
	 * 
	 * @param usernameAdmin
	 * @return Admin
	 * @throws Exception
	 */
	@Override
	public Admin getAdminByUsername(String usernameAdmin) throws Exception {

		LOGGER.info("AdminServiceImpl getAdminByUsername .- Inicio");

		Admin admin = null;

		try {

			if (usernameAdmin != null) {
				admin = adminDao.findByUsernameAdmin(usernameAdmin);
			} else {
				LOGGER.error("AdminServiceImpl getAdminByUsername .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminServiceImpl getAdminByUsername .- Error no controlado al recuperar el admin");
			throw e;
		}

		LOGGER.info("AdminServiceImpl getAdminByUsername .- Fin");

		return admin;

	}

	/**
	 * Recupera todos los datos de los administradores almacenados en BDD
	 * 
	 * @return List<AdminsDatatableDto>
	 * @throws Exception
	 */
	@Override
	public List<AdminsDatatableDto> getAllAdminsData() throws Exception {

		LOGGER.info("AdminServiceImpl getAllAdminsData .- Inicio");

		List<AdminsDatatableDto> listaAdminsDatatableDto = new ArrayList<AdminsDatatableDto>();

		try {

			List<Admin> listaAdmins = adminDao.findAll();

			if (listaAdmins != null && !listaAdmins.isEmpty()) {

				for (Admin a : listaAdmins) {

					AdminsDatatableDto adminDatatableDto = new AdminsDatatableDto();

					adminDatatableDto.setIdAdmin(a.getIdAdmin());
					adminDatatableDto.setNameAdmin(a.getNameAdmin());
					adminDatatableDto.setEmailAdmin(a.getEmailAdmin());
					adminDatatableDto.setUsernameAdmin(a.getUsernameAdmin());

					listaAdminsDatatableDto.add(adminDatatableDto);
				}

			} else {
				LOGGER.error("AdminServiceImpl getAllAdminsData .- Error: Sin administradores registrados");
			}

		} catch (Exception e) {
			LOGGER.error("AdminServiceImpl getAllAdminsData .- Error no controlado al recuperar los administradores");
			throw e;
		}

		LOGGER.info("AdminServiceImpl getAllAdminsData .- Fin");

		return listaAdminsDatatableDto;
	}

	/**
	 * Almacena un administrador en BD
	 * 
	 * @param adminData
	 * @return Admin
	 * @throws Exception
	 */
	public void addAdmin(Map<String, String> adminData) throws Exception {

		LOGGER.info("AdminServiceImpl addAdmin .- Inicio");

		try {

			if (adminData != null && !adminData.isEmpty()) {
				
				List<Admin> listaAdmin = adminDao.findAll();
				for (Admin admin : listaAdmin) {
					
					if (adminData.get("usernameAdmin").equals(admin.getUsernameAdmin())) {
						throw new Exception("usernameAdminUnique");
					}
					
					if (adminData.get("emailAdmin").equals(admin.getEmailAdmin())) {
						throw new Exception("emailAdminUnique");
					}	
					
					if (!adminData.get("pwdAdmin").equals(adminData.get("pwdAdminConfirm"))) {
						throw new Exception("pswConfirmNoCoincide");
					}	
					
				}

				Admin ad = new Admin();

				ad.setNameAdmin(adminData.get("nameAdmin"));
				ad.setEmailAdmin(adminData.get("emailAdmin"));
				ad.setUsernameAdmin(adminData.get("usernameAdmin"));
				ad.setPwdAdmin(passwordEncoder.encode(adminData.get("pwdAdmin")));

				adminDao.save(ad);

				LOGGER.info("AdminServiceImpl addAdmin .- Administrador almacenado correctamente");

			} else {
				LOGGER.error("AdminServiceImpl addAdmin .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminServiceImpl addAdmin .- Error no controlado al añadir el administrador");
			throw e;
		}

		LOGGER.info("AdminServiceImpl addAdmin .- Fin");

	}

	/**
	 * Recupera los datos personales de un administrador por su nombre de usuario
	 * 
	 * @param usernameAdmin
	 * @return AdminDto
	 * @throws Exception
	 */
	public AdminDto getAdminDataByUsername(String usernameAdmin) throws Exception {

		LOGGER.info("AdminServiceImpl getAdminDataByUsername .- Inicio");

		AdminDto adminDto = null;

		try {

			Admin admin = adminDao.findByUsernameAdmin(usernameAdmin);

			if (admin != null) {

				adminDto = new AdminDto();

				adminDto.setNameAdmin(admin.getNameAdmin());
				adminDto.setEmailAdmin(admin.getEmailAdmin());

			} else {
				LOGGER.error("AdminServiceImpl getAdminDataByUsername .- Error: Sin administradores registrados");
			}

		} catch (Exception e) {
			LOGGER.error(
					"AdminServiceImpl getAdminDataByUsername .- Error no controlado al recuperar los administradores");
			throw e;
		}

		LOGGER.info("AdminServiceImpl getAdminDataByUsername .- Fin");

		return adminDto;

	}

	/**
	 * Actualiza los datos personales de un administrador en BD
	 * 
	 * @param adminData
	 * @return Admin
	 * @throws Exception
	 */
	public void updateDataAdmin(Map<String, String> adminData) throws Exception {

		LOGGER.info("AdminServiceImpl updateDataAdmin .- Inicio");

		try {

			if (adminData != null && !adminData.isEmpty()) {

				Admin ad = adminDao.findByUsernameAdmin(adminData.get("usernameAdmin"));

				if (ad != null) {

					if (!adminData.get("emailAdmin").equals(ad.getEmailAdmin())) {
						List<Admin> listaAdmin = adminDao.findAll();
						for (Admin admin : listaAdmin) {
							if (adminData.get("emailAdmin").equals(admin.getEmailAdmin())) {
								throw new Exception("emailAdminUnique");
							}
						}
					}

					ad.setNameAdmin(adminData.get("nameAdmin"));
					ad.setEmailAdmin(adminData.get("emailAdmin"));

					adminDao.save(ad);

				} else {
					LOGGER.error("AdminServiceImpl updateDataAdmin .- Administrador no encontrado");
				}

				LOGGER.info("AdminServiceImpl updateDataAdmin .- Administrador actualizado correctamente");

			} else {
				LOGGER.error("AdminServiceImpl updateDataAdmin .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminServiceImpl updateDataAdmin .- Error no controlado al actualizar el administrador");
			throw e;
		}

		LOGGER.info("AdminServiceImpl updateDataAdmin .- Fin");

	}

	/**
	 * Actualiza la contraseña de un administrador en BD
	 * 
	 * @param adminData
	 * @throws Exception
	 */
	public void changePwdAdmins(Map<String, String> adminData) throws Exception {

		LOGGER.info("AdminServiceImpl changePwdAdmins .- Inicio");

		try {

			if (adminData != null && !adminData.isEmpty()) {

				Admin ad = adminDao.findByUsernameAdmin(adminData.get("usernameAdmin"));

				if (ad != null) {

					if (!passwordEncoder.matches(adminData.get("pwdAdminActual"), ad.getPwdAdmin())) {
						throw new Exception("pswActualMal");
					}

					if (adminData.get("pwdAdminActual").equals(adminData.get("pwdAdminNueva"))) {
						throw new Exception("pswNuevaViejaIgual");
					}

					if (!adminData.get("pwdAdminNueva").equals(adminData.get("pwdAdminConfirm"))) {
						throw new Exception("pswConfirmNoCoincide");
					}

					ad.setPwdAdmin(passwordEncoder.encode(adminData.get("pwdAdminNueva")));

					adminDao.save(ad);

				} else {
					LOGGER.error("AdminServiceImpl changePwdAdmins .- Administrador no encontrado");
				}

				LOGGER.info("AdminServiceImpl changePwdAdmins .- Administrador actualizado correctamente");

			} else {
				LOGGER.error("AdminServiceImpl changePwdAdmins .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminServiceImpl changePwdAdmins .- Error no controlado al actualizar el administrador");
			throw e;
		}

		LOGGER.info("AdminServiceImpl changePwdAdmins .- Fin");

	}
	
	/**
	 * Elimina un admin almacenado en BDD
	 * 
	 * @param adminsData
	 * @throws Exception
	 */
	@Override
	public void deleteAdmins(Map<String, String> adminData) throws Exception {

		LOGGER.info("AdminServiceImpl deleteAdmins .- Inicio");

		try {

			if (adminData != null && !adminData.isEmpty()) {

				Admin ad = adminDao.findByUsernameAdmin(adminData.get("usernameAdmin"));

				if (ad != null) {
					adminDao.delete(ad);
				}

				LOGGER.info("AdminServiceImpl deleteAdmins .- Link eliminado correctamente");

			} else {
				LOGGER.error("AdminServiceImpl deleteAdmins .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminServiceImpl deleteAdmins .- Error no controlado al eliminar el link");
			throw e;
		}

		LOGGER.info("AdminServiceImpl deleteLinks .- Fin");

	}


}
