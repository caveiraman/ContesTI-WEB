	package br.com.contesti.entidades;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLInsert;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5959348604658622025L;

	@Id
	@GeneratedValue
	private Long idUsuario;
	
	@NotNull(message="Campo nome vazio")
	@Size(min=2,message="Digite no mínimo 2 caracteres")
	@Column(nullable=true, length=30)
	private String nome_usuario;
	
	@NotNull(message="Campo usuário vazio")
	@Size(min=5,message="Digite no mínimo 5 caracteres")
	@Column(nullable=true, length=20)
	private String login;
	
	@NotNull(message="Campo senha vazio")
	@Size(min=8,message="Digite no mínimo 8 caracteres")
	@Column(nullable=true, length=800)
	private String senha;
	
	public String criptografar(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
		StringBuilder hexString = new StringBuilder();
		for(byte b: messageDigest){
			hexString.append(String.format("%02X",  0xFF & b));
		}
		senha = hexString.toString();
		return senha;
	}
	
	@NotNull(message="Campo email vazio")
	@Column(nullable=true, length=45)
	private String email;
	
	 public boolean isEmailValid(String email) {
	        if ((email == null) || (email.trim().length() == 0))
	            return false;
	 	
	        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
	        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
	
	@Column(nullable=true)
	private Boolean ativo=true;
	
	@ManyToMany
	@JoinTable(name="usuario_roles")
	private Collection<Role> roles;
	
	public Usuario(String nome_usuario, String login, String senha, String email,Role role) {
		// TODO Auto-generated constructor stub
		this.nome_usuario = nome_usuario;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.roles = Arrays.asList(role);		
	}
	
	public Usuario(){
		
	}
	

	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome_usuario() {
		return nome_usuario;
	}
	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome_usuario == null) ? 0 : nome_usuario.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome_usuario == null) {
			if (other.nome_usuario != null)
				return false;
		} else if (!nome_usuario.equals(other.nome_usuario))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nome_usuario=" + nome_usuario + ", login=" + login + ", senha="
				+ senha + ", email=" + email + "]";
	}

	
	
	
	

	
}
