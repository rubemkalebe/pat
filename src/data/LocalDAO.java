package data;

import java.util.List;

import domain.local.Local;

public interface LocalDAO {

	public void addLocal(Local local);
	
	public int removeLocal(Local local);
	
	public void editLocal(Local local);
	
	public List<Local> getAllLocais();
	
}
