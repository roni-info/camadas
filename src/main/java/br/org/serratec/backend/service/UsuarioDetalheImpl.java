package br.org.serratec.backend.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.model.Usuario;
import br.org.serratec.backend.repository.UsuarioRepository;
import br.org.serratec.backend.security.UsuarioDetalhe;

@Service
public class UsuarioDetalheImpl implements UserDetailsService {
	private UsuarioRepository usuarioRepository;

	public UsuarioDetalheImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("LOADUSERBYUSERNAME");
		Optional<Usuario> usuario = usuarioRepository.findByNome(username);

		if (!usuario.isPresent()) {
			throw new RuntimeException();
		}
		System.out.println(usuario.get().getEmail());
		return new UsuarioDetalhe(usuario);
	}

}
