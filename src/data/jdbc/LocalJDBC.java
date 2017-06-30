package data.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.LocalDAO;
import data.util.DBTables;
import domain.local.Local;
import domain.local.StatusLocal;
import domain.local.TipoLocal;

public class LocalJDBC implements LocalDAO {

	private DBUtil dbUtil;
	
	public LocalJDBC(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	
	@Override
	public Local addLocal(TipoLocal tipo, String nome, String descricao, StatusLocal status, int total) {
		dbUtil.connect();
		String sqlForInsertion = "INSERT INTO " + DBTables.LOCAL_TABLE_NAME + "("
					+ DBTables.LOCAL_ATTR_NOME + ","
					+ DBTables.LOCAL_ATTR_DESCRICAO + ","
					+ DBTables.LOCAL_ATTR_STATUS + ","
					+ DBTables.LOCAL_ATTR_TIPO + ","
					+ DBTables.LOCAL_ATTR_TOTAL + ")"
				+ " VALUES ("
				+ "\'" + nome + "\'" + ","
				+ "\'" + descricao + "\'" + ","
				+ String.valueOf(status.getAsInt()) + ","
				+ String.valueOf(tipo.getId()) + ","
				+ String.valueOf(total)
			+ ")";
		System.out.println(sqlForInsertion);
		
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
			return new Local(last_id, nome, descricao, status, tipo, total);
		}		
	}

	@Override
	public void removeLocal(Local local) {
		dbUtil.connect();
		String sql = "DELETE FROM " + DBTables.LOCAL_TABLE_NAME
				+ " WHERE " + DBTables.LOCAL_ATTR_NUMERO  + "=" + String.valueOf(local.getNumero());
		System.out.println(sql);
		
		try {
			dbUtil.getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbUtil.disconnect();
	}

	@Override
	public void editLocal(Local local) {
		dbUtil.connect();
		String sql = "UPDATE " + DBTables.LOCAL_TABLE_NAME + " SET "
				+ DBTables.LOCAL_ATTR_NOME + "=" + "\'" + local.getNome() + "\'" + ","
				+ DBTables.LOCAL_ATTR_DESCRICAO + "=" + "\'" + local.getDescricao() + "\'" + ","
				+ DBTables.LOCAL_ATTR_STATUS + "=" + "\'" + String.valueOf(local.getStatus().getAsInt()) + "\'" + ","
				+ DBTables.LOCAL_ATTR_TIPO + "=" + String.valueOf(local.getTipo().getId()) + ","
				+ DBTables.LOCAL_ATTR_TOTAL + "=" + String.valueOf(local.getTotalBens())
				+ " WHERE " + DBTables.LOCAL_ATTR_NUMERO  + "=" + String.valueOf(local.getNumero());
		System.out.println(sql);
		
		try {
			dbUtil.getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbUtil.disconnect();
	}

	@Override
	public List<Local> getAllLocais() {
		dbUtil.connect();
		String sql = "SELECT * FROM " + DBTables.LOCAL_TABLE_NAME + ", " + DBTables.TIPOLOCAL_TABLE_NAME
				+ " WHERE " + DBTables.LOCAL_ATTR_TIPO + "=" + DBTables.TIPOLOCAL_ATTR_ID;
		List<Local> locais = new ArrayList<Local>();
		
		try {
			ResultSet resultSet = dbUtil.getStatement().executeQuery(sql);
			while(resultSet.next()) {
				Local l = new Local(
						Integer.valueOf(resultSet.getString(DBTables.LOCAL_ATTR_NUMERO)),
						resultSet.getString(DBTables.LOCAL_ATTR_NOME),
						resultSet.getString(DBTables.LOCAL_ATTR_DESCRICAO),
						convertStatusInfo(Integer.valueOf(resultSet.getString(DBTables.LOCAL_ATTR_STATUS))),
						new TipoLocal(Integer.valueOf(resultSet.getString(DBTables.TIPOLOCAL_ATTR_ID)),
								resultSet.getString(DBTables.TIPOLOCAL_ATTR_NOME)),
						Integer.valueOf(resultSet.getString(DBTables.LOCAL_ATTR_TOTAL))
				);

				locais.add(l);
			}
			return locais;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtil.disconnect();
		}
		
		return null;
	}

	private StatusLocal convertStatusInfo(Integer i) {
		switch (i) {
		case DBTables.LOCAL_ATTR_STATUS_MANUTENCAO:
			return StatusLocal.MANUTENCAO;
		default:
			return StatusLocal.DISPONIVEL;
		}
	}
	
}
