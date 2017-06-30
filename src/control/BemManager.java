package control;

import java.time.LocalDate;
import java.util.List;

import data.BemDAO;
import data.jdbc.BemJDBC;
import data.jdbc.ConnectionFactory;
import data.jdbc.DBUtil;
import data.util.DBInfo;
import domain.bem.Bem;
import domain.bem.StatusBem;
import domain.local.Local;
import domain.usuario.Usuario;

public class BemManager implements BemManagerInterface {

	private BemDAO bemDAO;
	
	public BemManager() {
		DBUtil dbUtil = new DBUtil(
				DBInfo.MySQL_URL,
				DBInfo.MySQL_USER,
				DBInfo.MySQL_PASS,
				ConnectionFactory.MySQL
		);
		bemDAO = new BemJDBC(dbUtil);
	}
	
	@Override
	public Bem addBem(String tombo, StatusBem status, String descricao, Local local, Usuario usuarioCadastro,
			LocalDate dataCadastro) {
		return bemDAO.addBem(tombo, status, descricao, local);
	}

	@Override
	public void removeBem(Bem bem, Usuario usuarioRemocao, LocalDate dataRemocao) {
		bemDAO.removeBem(bem);
	}

	@Override
	public void editBem(Bem bem, Usuario usuarioEdit, LocalDate dataEdit) {
		bemDAO.editBem(bem);
	}

	@Override
	public List<Bem> getAllBens() {
		return bemDAO.getAllBens();
	}

}
