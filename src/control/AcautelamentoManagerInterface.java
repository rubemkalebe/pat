package control;

import java.util.List;

import domain.acautelamento.Acautelamento;

public interface AcautelamentoManagerInterface {

	public void addAcautelamento();
	
	public void inativarAcautelamento();
	
	public void editAcautelamento();
	
	public List<Acautelamento> getAllAcautelamentos();
	
}
