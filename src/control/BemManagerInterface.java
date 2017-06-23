package control;

import java.util.List;

import domain.Bem;

public interface BemManagerInterface {

	public void addBem();
	
	public int removeBem();
	
	public void editBem();
	
	public List<Bem> getAllBens();
	
}
