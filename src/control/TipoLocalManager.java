package control;

import java.util.List;

import data.TipoLocalDAO;
import data.jdbc.ConnectionFactory;
import data.jdbc.DBUtil;
import data.jdbc.TipoLocalJDBC;
import data.util.DBInfo;
import domain.local.TipoLocal;

public class TipoLocalManager implements TipoLocalManagerInterface {

	private TipoLocalDAO tipoLocalDAO;
	
	public TipoLocalManager() {
		DBUtil dbUtil = new DBUtil(
				DBInfo.MySQL_URL,
				DBInfo.MySQL_USER,
				DBInfo.MySQL_PASS,
				ConnectionFactory.MySQL
		);
		tipoLocalDAO = new TipoLocalJDBC(dbUtil);
	}
	
	@Override
	public TipoLocal addTipoLocal(String nome) {
		return tipoLocalDAO.addTipoLocal(nome);
	}

	@Override
	public void removeTipoLocal(TipoLocal tipoLocal) {
		tipoLocalDAO.removeTipoLocal(tipoLocal);
	}

	@Override
	public void editTipoLocal(TipoLocal tipoLocal) {
		tipoLocalDAO.editTipoLocal(tipoLocal);
	}

	@Override
	public List<TipoLocal> getAllTiposLocal() {
		return tipoLocalDAO.getAllTiposLocal();
	}

}
