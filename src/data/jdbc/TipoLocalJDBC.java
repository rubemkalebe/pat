package data.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.TipoLocalDAO;
import data.util.DBTables;
import domain.local.TipoLocal;

public class TipoLocalJDBC implements TipoLocalDAO {

	private DBUtil dbUtil;
	
	public TipoLocalJDBC(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	
	@Override
	public TipoLocal addTipoLocal(String nome) {
		dbUtil.connect();
		String sqlForInsertion = "INSERT INTO " + DBTables.TIPOLOCAL_TABLE_NAME + "(Nome)"
				+ " VALUES ("
				+ "\'" + nome + "\'" + ")";
		
		int last_id = 0;
		try {
			dbUtil.getStatement().executeUpdate(sqlForInsertion);
			String sqlForRetrievingID = "SELECT LAST_INSERT_ID() AS last_id";
			ResultSet resultSet = dbUtil.getStatement().executeQuery(sqlForRetrievingID);
			if(resultSet.next()) {
				last_id = resultSet.getInt("last_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbUtil.disconnect();
		
		if(last_id == 0) {
			return null;
		} else {
			return new TipoLocal(last_id, nome);
		}
	}

	@Override
	public void removeTipoLocal(TipoLocal tipoLocal) {
		dbUtil.connect();
		String sql = "DELETE FROM " + DBTables.TIPOLOCAL_TABLE_NAME
				+ " WHERE " + DBTables.TIPOLOCAL_ATTR_ID + "=" + String.valueOf(tipoLocal.getId());

		try {
			dbUtil.getStatement().executeUpdate(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		dbUtil.disconnect();
	}

	@Override
	public void editTipoLocal(TipoLocal tipoLocal) {
		dbUtil.connect();
		String sql = "UPDATE " + DBTables.TIPOLOCAL_TABLE_NAME + " SET "
				+ DBTables.TIPOLOCAL_ATTR_NOME + "=" + "\'" + tipoLocal.getNome() + "\'"
				+ " WHERE " + DBTables.TIPOLOCAL_ATTR_ID + "=" + String.valueOf(tipoLocal.getId());

		try {
			dbUtil.getStatement().executeUpdate(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		dbUtil.disconnect();
	}

	@Override
	public List<TipoLocal> getAllTiposLocal() {
		dbUtil.connect();
		String sql = "SELECT * FROM " + DBTables.TIPOLOCAL_TABLE_NAME;
		List<TipoLocal> tipos = new ArrayList<TipoLocal>();

		try {
			ResultSet resultSet = dbUtil.getStatement().executeQuery(sql);
			while(resultSet.next()) {
				TipoLocal t = new TipoLocal(
						Integer.valueOf(resultSet.getString(DBTables.TIPOLOCAL_ATTR_ID)),
						resultSet.getString(DBTables.TIPOLOCAL_ATTR_NOME)
				);
				
				tipos.add(t);
			}
			return tipos;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.disconnect();
		}
		
		return null;
	}

}
