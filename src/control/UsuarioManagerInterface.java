package control;

import java.util.List;

import domain.usuario.TipoUsuario;
import domain.usuario.Usuario;
import domain.usuario.exceptions.SenhaInvalida;
import domain.usuario.exceptions.UsuarioNotFound;

public interface UsuarioManagerInterface {

	public Usuario addUsuario(TipoUsuario tipo, String nome, String telefone, String email,
			String login, String senha);
	
	public void removeUsuario(Usuario usuario);
	
	public void editUsuario(Usuario usuario);
	
	public List<Usuario> getAllUsuarios();
	
	public Usuario autenticarUsuario(String login, String senha) throws UsuarioNotFound, SenhaInvalida;
	
}
