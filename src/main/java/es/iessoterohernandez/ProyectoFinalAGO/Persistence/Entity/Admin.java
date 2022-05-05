package es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad: Administradores. Tabla: ADMINS
 * 
 * @author agadelao
 *
 */
@Entity
@Table(name = "ADMINS")
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID de Categoría Tecnica */
	private Long idAdmin;

	/** Nombre */
	private String nameAdmin;

	/** Nombre */
	private String emailAdmin;

	/** Nombre */
	private String usernameAdmin;

	/** Nombre */
	private String pwdAdmin;

	@Id
	@GeneratedValue(generator = "genSeqAdmin")
	@SequenceGenerator(name = "genSeqAdmin", sequenceName = "SEQ_ADMIN", allocationSize = 1)
	public Long getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
	}

	@Column(name = "NAME_ADMIN", nullable = false, unique = true)
	public String getNameAdmin() {
		return nameAdmin;
	}

	public void setNameAdmin(String nameAdmin) {
		this.nameAdmin = nameAdmin;
	}

	@Column(name = "EMAIL_ADMIN", nullable = false, unique = true)
	public String getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	@Column(name = "USER_ADMIN", nullable = false, unique = true)
	public String getUserAdmin() {
		return usernameAdmin;
	}

	public void setUserAdmin(String usernameAdmin) {
		this.usernameAdmin = usernameAdmin;
	}

	@Column(name = "PWD_ADMIN", nullable = false, unique = true)
	public String getPwdAdmin() {
		return pwdAdmin;
	}

	public void setPwdAdmin(String pwdAdmin) {
		this.pwdAdmin = pwdAdmin;
	}

}
