package control;

import java.time.LocalDate;
import java.util.List;

import domain.bem.Bem;
import domain.bem.StatusBem;
import domain.local.Local;
import domain.usuario.Usuario;

public interface BemManagerInterface {

	public Bem addBem(String tombo, StatusBem status, String descricao, Local local,
			Usuario usuarioCadastro, LocalDate dataCadastro);
	
	public void removeBem(Bem bem, Usuario usuarioRemocao, LocalDate dataRemocao);
	
	public void editBem(Bem bem, Usuario usuarioEdit, LocalDate dataEdit);
	
	public List<Bem> getAllBens();
	
}
