package data.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.BemDAO;
import data.util.DBTables;
import domain.bem.Bem;
import domain.bem.StatusBem;
import domain.local.Local;
import javafx.beans.property.SimpleStringProperty;

public class BemJDBC implements BemDAO {

	private DBUtil dbUtil;
	
	public BemJDBC(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	
	@Override
	public Bem addBem(String tombo, StatusBem status, String descricao, Local local) {
		dbUtil.connect();
		String sqlForInsertion = "INSERT INTO " + DBTables.BEM_TABLE_NAME +
				"(TomboBem, StatusBem, DescricaoBem, Bem_IdLocal)"
				+ " VALUES ("
				+ "\'" + tombo + "\'" + ","
				+ String.valueOf(status.getAsInt()) + ","
				+ "\'" + descricao + "\'" + ","
				+ String.valueOf(local.getNumero()) 
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
		} catch(SQLException e) {
			e.printStackTrace();
		}
		dbUtil.disconnect();
		
		if(last_id == 0) {
			return null;
		} else {
			return new Bem(last_id, tombo, status, descricao, local);
		}
	}

	@Override
	public void removeBem(Bem bem) {
		dbUtil.connect();
		String sql = "DELETE FROM " + DBTables.BEM_TABLE_NAME
				+ " WHERE " + DBTables.BEM_ATTR_ID + "=" + bem.getId();
		System.out.println(sql);
		
		try {
			dbUtil.getStatement().executeUpdate(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		dbUtil.disconnect();
	}

	@Override
	public void editBem(Bem bem) {
		// TODO Auto-generated method stub
		dbUtil.connect();
		String sql = "UPDATE " + DBTables.BEM_TABLE_NAME + " SET "
				+ DBTables.BEM_ATTR_TOMBO + "=" + "\'" + bem.getTombo() + "\'" + ","
				+ DBTables.BEM_ATTR_STATUS + "=" + String.valueOf(bem.getStatus().getAsInt()) + ","
				+ DBTables.BEM_ATTR_DESCRICAO + "=" + "\'" + bem.getDescricao() + "\'" + ","
				+ DBTables.BEM_ATTR_LOCAL + "=" + String.valueOf(bem.getLocal().getNumero())
				+ " WHERE " + DBTables.BEM_ATTR_ID + "=\'" + bem.getId() + "\'";
		System.out.println(sql);
		
		try {
			dbUtil.getStatement().executeUpdate(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		dbUtil.disconnect();
	}

	@Override
	public List<Bem> getAllBens() {
		dbUtil.connect();
		String sql = "SELECT "
					+ DBTables.BEM_ATTR_ID + ","
					+ DBTables.BEM_ATTR_TOMBO + ","
					+ DBTables.BEM_ATTR_STATUS + ","
					+ DBTables.BEM_ATTR_DESCRICAO + ","
					+ DBTables.BEM_ATTR_LOCAL + ","
					+ DBTables.LOCAL_ATTR_NUMERO + ","
					+ DBTables.LOCAL_ATTR_NOME
				+ " FROM " + DBTables.BEM_TABLE_NAME + ", " + DBTables.LOCAL_TABLE_NAME
				+ " WHERE " + DBTables.BEM_ATTR_LOCAL + "=" + DBTables.LOCAL_ATTR_NUMERO;
		List<Bem> bens = new ArrayList<Bem>();
		
		System.out.println(sql);
		try {
			ResultSet resultSet = dbUtil.getStatement().executeQuery(sql);
			while(resultSet.next()) {
				Bem b = new Bem(
						Integer.valueOf(resultSet.getString(DBTables.BEM_ATTR_ID)),
						resultSet.getString(DBTables.BEM_ATTR_TOMBO),
						convertStatusInfo(Integer.valueOf(resultSet.getString(DBTables.BEM_ATTR_STATUS))),
						resultSet.getString(DBTables.BEM_ATTR_DESCRICAO),
						new SimpleStringProperty(resultSet.getString(DBTables.LOCAL_ATTR_NOME))
				);
				bens.add(b);
			}
			return bens;
		} catch(SQLException e) {
			
		} finally {
			dbUtil.disconnect();
		}
		
		return null;
	}
	
	private StatusBem convertStatusInfo(Integer i) {
		switch(i) {
		case DBTables.BEM_ATTR_STATUS_CADASTRADO:
			return StatusBem.CADASTRADO;
		case DBTables.BEM_ATTR_STATUS_ATIVO:
			return StatusBem.ATIVO;
		case DBTables.BEM_ATTR_STATUS_MANUTENCAO:
			return StatusBem.MANUTENCAO;
		case DBTables.BEM_ATTR_STATUS_ACAUTELADO:
			return StatusBem.ACAUTELADO;
		case DBTables.BEM_ATTR_STATUS_RESERVADO:
			return StatusBem.RESERVADO;
		default:
			return StatusBem.ALIENADO;
		}
	}

}
