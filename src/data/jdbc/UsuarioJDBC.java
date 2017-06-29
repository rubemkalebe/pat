package data.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.UsuarioDAO;
import data.util.DBTables;
import domain.usuario.TipoUsuario;
import domain.usuario.Usuario;

public class UsuarioJDBC implements UsuarioDAO {

	private DBUtil dbUtil;
	
	public UsuarioJDBC(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	
	@Override
	public Usuario addUsuario(TipoUsuario tipo, String nome, String telefone, String email, String login,
				String senha) {
		
		dbUtil.connect();
		String sqlForInsertion = "INSERT INTO " + DBTables.USUARIO_TABLE_NAME + "(Tipo,Nome,Login,Senha,Telefone,Email)" + " VALUES ("
					+ String.valueOf(tipo.getAsInt()) + ","
					+ "\'" + nome + "\'" + ","
					+ "\'" + login + "\'" + ","
					+ "\'" + senha + "\'" + ","
					+ "\'" + telefone + "\'" + ","
					+ "\'" + email+ "\'"
				+ ")";
		
		int last_id = 0;
		try {
			dbUtil.getStatement().executeUpdate(sqlForInsertion);
			String sqlForRetrievingID = "SELECT LAST_INSERT_ID() AS last_id";
			ResultSet resultSet = dbUtil.getStatement().executeQuery(sqlForRetrievingID);
			if(resultSet.next()) {
				last_id = resultSet.getInt("last_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbUtil.disconnect();
		
		if(last_id == 0) {
			return null;
		} else {
			return new Usuario(
					last_id,
					tipo,
					nome,
					login,
					senha,
					telefone,
					email
			);
		}
	}

	@Override
	public void removeUsuario(Usuario usuario) {
		dbUtil.connect();
		String sql = "DELETE FROM " + DBTables.USUARIO_TABLE_NAME
				+ " WHERE " + DBTables.USUARIO_ATTR_ID  + "=" + String.valueOf(usuario.getId());
		try {
			dbUtil.getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbUtil.disconnect();
	}

	@Override
	public void editUsuario(Usuario usuario) {

		dbUtil.connect();
		String sql = "UPDATE " + DBTables.USUARIO_TABLE_NAME + " SET "
				+ DBTables.USUARIO_ATTR_TIPO + "=" + String.valueOf(usuario.getTipo().getAsInt()) + ","
				+ DBTables.USUARIO_ATTR_NOME + "=" + "\'" + usuario.getNome() + "\'" + ","
				+ DBTables.USUARIO_ATTR_TELEFONE + "=" + "\'" + usuario.getTelefone() + "\'" + ","
				+ DBTables.USUARIO_ATTR_EMAIL + "=" + "\'" + usuario.getEmail() + "\'" + ","
				+ DBTables.USUARIO_ATTR_LOGIN + "=" + "\'" + usuario.getLogin() + "\'"
				+ " WHERE " + DBTables.USUARIO_ATTR_ID  + "=" + String.valueOf(usuario.getId());
		
		try {
			dbUtil.getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbUtil.disconnect();
	}

	@Override
	public List<Usuario> getAllUsuarios() {
		dbUtil.connect();
		String sql = "SELECT * FROM " + DBTables.USUARIO_TABLE_NAME;
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			ResultSet resultSet = dbUtil.getStatement().executeQuery(sql);
			while(resultSet.next()) {
				Usuario u = new Usuario(
						Integer.valueOf(resultSet.getString(DBTables.USUARIO_ATTR_ID)),
						convertTipoInfo(Integer.valueOf(resultSet.getString(DBTables.USUARIO_ATTR_TIPO))),
						resultSet.getString(DBTables.USUARIO_ATTR_NOME),
						resultSet.getString(DBTables.USUARIO_ATTR_LOGIN),
						resultSet.getString(DBTables.USUARIO_ATTR_SENHA),
						resultSet.getString(DBTables.USUARIO_ATTR_TELEFONE),
						resultSet.getString(DBTables.USUARIO_ATTR_EMAIL)
				);
				
				usuarios.add(u);
			}
			return usuarios;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.disconnect();
		}
		
		return null;
	}

	@Override
	public Usuario getUsuarioByLogin(String login) {
		dbUtil.connect();
		String sql = "SELECT * FROM " + DBTables.USUARIO_TABLE_NAME +
					 " WHERE " + DBTables.USUARIO_ATTR_LOGIN + " = \"" + login + "\"";
		
		try {
			ResultSet resultSet = dbUtil.getStatement().executeQuery(sql);
			if(resultSet.next()) {
				Usuario u = new Usuario(
						Integer.valueOf(resultSet.getString(DBTables.USUARIO_ATTR_ID)),
						convertTipoInfo(Integer.valueOf(resultSet.getString(DBTables.USUARIO_ATTR_TIPO))),
						resultSet.getString(DBTables.USUARIO_ATTR_NOME),
						resultSet.getString(DBTables.USUARIO_ATTR_LOGIN),
						resultSet.getString(DBTables.USUARIO_ATTR_SENHA),
						resultSet.getString(DBTables.USUARIO_ATTR_TELEFONE),
						resultSet.getString(DBTables.USUARIO_ATTR_EMAIL)
				);
				
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.disconnect();
		}
		
		return null;
	}
	
	private TipoUsuario convertTipoInfo(Integer i) {
		switch (i) {
		case DBTables.USUARIO_ATTR_TIPO_SERVIDOR:
			return TipoUsuario.SERVIDOR;
		case DBTables.USUARIO_ATTR_TIPO_BOLSISTA:
			return TipoUsuario.BOLSISTA;
		case DBTables.USUARIO_ATTR_TIPO_PROFESSOR:
			return TipoUsuario.PROFESSOR;
		default:
			return TipoUsuario.ALUNO;
		}
	}

}
