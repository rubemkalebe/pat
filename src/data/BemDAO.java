package data;

import java.util.List;

import domain.bem.Bem;
import domain.bem.StatusBem;
import domain.local.Local;

public interface BemDAO {

	public Bem addBem(String tombo, StatusBem status, String descricao, Local local);
	
	public void removeBem(Bem bem);
	
	public void editBem(Bem bem);
	
	public List<Bem> getAllBens();
	
}
