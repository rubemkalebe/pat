package control;

import java.util.List;

import domain.Usuario;

public interface UsuarioManagerInterface {

	public void addUsuario();
	
	public int removeUsuario();
	
	public void editUsuario();
	
	public List<Usuario> getAllUsuarios();
	
}
