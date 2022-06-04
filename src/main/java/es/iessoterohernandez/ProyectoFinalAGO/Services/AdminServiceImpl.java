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
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

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
	public Admin addAdmin(Map<String, String> adminData) throws Exception {
		
		LOGGER.info("AdminServiceImpl addAdmin .- Inicio");

		Admin adminSaved = null;

		try {

			if (adminData != null && !adminData.isEmpty()) {

				Admin ad = new Admin();
				
				ad.setNameAdmin(adminData.get("nameAdmin"));
				ad.setEmailAdmin(adminData.get("emailAdmin"));
				ad.setUsernameAdmin(adminData.get("usernameAdmin"));
				ad.setPwdAdmin(passwordEncoder.encode(adminData.get("pwdAdmin")));

				adminSaved = adminDao.save(ad);

				LOGGER.info("AdminServiceImpl addAdmin .- Administrador almacenado correctamente");

			} else {
				LOGGER.error("AdminServiceImpl addAdmin .- Error: Parámetros de entrada nulos");
			}

		} catch (Exception e) {
			LOGGER.error("AdminServiceImpl addAdmin .- Error no controlado al añadir el administrador");
			throw e;
		}

		LOGGER.info("AdminServiceImpl addAdmin .- Fin");

		return adminSaved;
		
		
		
	}

}
