package control;

import java.util.List;

import domain.local.Local;
import domain.local.StatusLocal;
import domain.local.TipoLocal;

public interface LocalManagerInterface {

	public Local addLocal(TipoLocal tipo, String nome, String descricao, StatusLocal status, int total);
	
	public void removeLocal(Local local);
	
	public void editLocal(Local local);
	
	public List<Local> getAllLocais();
	
}
