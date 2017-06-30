package control;

import java.util.List;

import data.LocalDAO;
import data.jdbc.ConnectionFactory;
import data.jdbc.DBUtil;
import data.jdbc.LocalJDBC;
import data.util.DBInfo;
import domain.local.Local;
import domain.local.StatusLocal;
import domain.local.TipoLocal;

public class LocalManager implements LocalManagerInterface {

	private LocalDAO localDAO;
	
	public LocalManager() {
		DBUtil dbUtil = new DBUtil(
				DBInfo.MySQL_URL,
				DBInfo.MySQL_USER,
				DBInfo.MySQL_PASS,
				ConnectionFactory.MySQL
		);
		localDAO = new LocalJDBC(dbUtil);
	}
	
	@Override
	public Local addLocal(TipoLocal tipo, String nome, String descricao, StatusLocal status, int total) {
		return localDAO.addLocal(tipo, nome, descricao, status, total);
	}

	@Override
	public void removeLocal(Local local) {
		localDAO.removeLocal(local);
	}

	@Override
	public void editLocal(Local local) {
		localDAO.editLocal(local);
	}

	@Override
	public List<Local> getAllLocais() {
		return localDAO.getAllLocais();
	}
	
}
