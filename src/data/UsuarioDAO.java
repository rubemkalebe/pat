package data;

import java.util.List;

import domain.usuario.Usuario;

public interface UsuarioDAO {

	public void addUsuario(Usuario usuario);
	
	public int removeUsuario(Usuario usuario);
	
	public void editUsuario(Usuario usuario);
	
	public List<Usuario> getAllUsuarios();
	
}
