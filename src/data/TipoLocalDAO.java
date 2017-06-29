package data;

import java.util.List;

import domain.local.TipoLocal;

public interface TipoLocalDAO {

	public TipoLocal addTipoLocal(String nome);
	
	public void removeTipoLocal(TipoLocal tipoLocal);
	
	public void editTipoLocal(TipoLocal tipoLocal);
	
	public List<TipoLocal> getAllTiposLocal();
	
}
