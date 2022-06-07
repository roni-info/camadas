package br.org.serratec.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;
import br.org.serratec.backend.dto.UsuarioDTO;
import br.org.serratec.backend.dto.UsuarioInserirDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.model.Usuario;
import br.org.serratec.backend.model.UsuarioPerfil;
import br.org.serratec.backend.repository.PerfilRepository;
import br.org.serratec.backend.repository.UsuarioPerfilRepository;
import br.org.serratec.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MailConfig mailConfig;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UsuarioPerfilRepository usuarioPerfilRepository;

	@Autowired
	private PerfilService perfilService;

	public List<UsuarioDTO> listar() {
		List<UsuarioDTO> usuarioDTOs = new ArrayList<UsuarioDTO>();
		List<Usuario> usuarios = usuarioRepository.findAll();
		for (Usuario usuario : usuarios) {
			UsuarioDTO dto = new UsuarioDTO(usuario);
			usuarioDTOs.add(dto);
		}
		return usuarioDTOs;
	}

	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException {
		Usuario u = usuarioRepository.findByEmail(usuarioInserirDTO.getEmail());
		if (u != null) {
			throw new EmailException("Email existente ! Insira outro");
		}
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSenha(bCryptPasswordEncoder.encode(usuarioInserirDTO.getSenha()));
		usuario = usuarioRepository.save(usuario);

		for (UsuarioPerfil usuarioPerfil : usuarioInserirDTO.getUsuarioPerfis()) {
			usuarioPerfil.setUsuario(usuario);
			usuarioPerfil.setPerfil(perfilService.buscar(usuarioPerfil.getPerfil().getId()));
			usuarioPerfil.setDataCriacao(LocalDate.now());
		}
		usuarioPerfilRepository.saveAll(usuarioInserirDTO.getUsuarioPerfis());
		// mailConfig.enviarEmail(usuarioInserirDTO.getEmail(), "Cadastro de Usuário!!",
		// usuario.toString());
		return new UsuarioDTO(usuario);

	}

}


