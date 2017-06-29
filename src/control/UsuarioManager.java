package control;

import java.util.List;

import data.UsuarioDAO;
import data.jdbc.ConnectionFactory;
import data.jdbc.DBUtil;
import data.jdbc.UsuarioJDBC;
import data.util.DBInfo;
import domain.usuario.TipoUsuario;
import domain.usuario.Usuario;
import domain.usuario.exceptions.SenhaInvalida;
import domain.usuario.exceptions.UsuarioNotFound;

public class UsuarioManager implements UsuarioManagerInterface {

	private UsuarioDAO usuarioDAO;
	
	public UsuarioManager() {
		DBUtil dbUtil = new DBUtil(
				DBInfo.MySQL_URL,
				DBInfo.MySQL_USER,
				DBInfo.MySQL_PASS,
				ConnectionFactory.MySQL
		);
		usuarioDAO = new UsuarioJDBC(dbUtil);
	}
	
	@Override
	public Usuario addUsuario(TipoUsuario tipo, String nome, String telefone, String email, String login,
			String senha) {
		return usuarioDAO.addUsuario(tipo, nome, telefone, email, login, senha);
	}
	

	@Override
	public void removeUsuario(Usuario usuario) {
		usuarioDAO.removeUsuario(usuario);
	}

	@Override
	public void editUsuario(Usuario usuario) {
		usuarioDAO.editUsuario(usuario);
	}

	@Override
	public List<Usuario> getAllUsuarios() {
		return usuarioDAO.getAllUsuarios();
	}

	@Override
	public Usuario autenticarUsuario(String login, String senha) throws UsuarioNotFound,
					SenhaInvalida {
		Usuario u = usuarioDAO.getUsuarioByLogin(login);
		
		if(u == null) {
			throw new UsuarioNotFound();
		} else if(checkPass(senha, u.getSenha())) {
			return u;
		} else {
			throw new SenhaInvalida();
		}
		
	}
	
	private boolean checkPass(String input, String stored) {
		return input.equals(stored);
	}
	
}
