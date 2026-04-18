package com.example.usuarios.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.usuarios.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	// calocar a mas argumentos foto,username,data de nascimento,bio / title

	// fazer uma verificação de email e senha @va

	// mudar o nome dos controller

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome obrigatório")
	private String name;

	private String usernameCustom;

	private LocalDate dataNascimento;

	@Email(message = "Email invalido")
	private String email;

	@Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
	private String senha;

	@OneToMany(mappedBy = "user")
	private List<Link> link;

	private String bio;
	private String foto;

	
	@Enumerated(EnumType.STRING)
	private Role role;

	public User() {

	}

	public User(String name, String usernameCustom, LocalDate dataNascimento,  String email, String senha) {
		super();
		this.name = name;
		this.email = email;
		this.senha = senha;
		this.usernameCustom = usernameCustom;
		this.dataNascimento = dataNascimento;



	}

	public String getUsernameCustom(){
		return  usernameCustom;
	}
	public void setUsernameCustom(String username) {
		this.usernameCustom = username;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Link> getLink() {
		return link;
	}

	public void setLink(List<Link> link) {
		this.link = link;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
