package data;

import java.util.List;

import domain.bem.Bem;

public interface BemDAO {

	public void addBem(Bem bem);
	
	public int removeBem(Bem bem);
	
	public void editBem(Bem bem);
	
	public List<Bem> getAllBens();
	
}
