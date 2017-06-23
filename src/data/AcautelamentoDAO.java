package data;

import java.util.List;

import domain.acautelamento.Acautelamento;

public interface AcautelamentoDAO {

	public void addAcautelamento(Acautelamento acautelamento);
	
	public void inativarAcautelamento(Acautelamento acautelamento);
	
	public void editAcautelamento(Acautelamento acautelamento);
	
	public List<Acautelamento> getAllAcautelamentos();
	
}
