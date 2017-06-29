package data;

import java.util.List;

import domain.usuario.TipoUsuario;
import domain.usuario.Usuario;

public interface UsuarioDAO {

	public Usuario addUsuario(TipoUsuario tipo, String nome, String telefone, String email,
			String login, String senha);
	
	public void removeUsuario(Usuario usuario);
	
	public void editUsuario(Usuario usuario);
	
	public List<Usuario> getAllUsuarios();
	
	public Usuario getUsuarioByLogin(String login);
	
}
