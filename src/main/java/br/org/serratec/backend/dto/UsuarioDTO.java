package br.org.serratec.backend.dto;

import java.util.HashSet;
import java.util.Set;

import br.org.serratec.backend.model.Usuario;
import br.org.serratec.backend.model.UsuarioPerfil;

public class UsuarioDTO {
	private Long id;
	private String nome;
	private String email;

	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
