package control;

import java.util.List;

import domain.local.Local;

public interface LocalManagerInterface {

	public void addLocal();
	
	public int removeLocal();
	
	public void editLocal();
	
	public List<Local> getAllLocais();
	
}
