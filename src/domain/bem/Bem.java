package domain.bem;

import java.util.Calendar;

import domain.local.Local;
import domain.usuario.Usuario;

public class Bem {

	private int tombo;
	private StatusBem status;
	private String descricao;
	private Usuario usuarioCadastro;
	private Usuario usuarioRemocao;
	private Calendar dataCadastro;
	private Calendar dataRemocao;
	private Local local;
	
	public Bem(int tombo, StatusBem status, String descricao, Usuario usuarioCadastro,
			Calendar dataCadastro, Local local) {
		this.tombo = tombo;
		this.status = status;
		this.descricao = descricao;
		this.usuarioCadastro = usuarioCadastro;
		this.dataCadastro = dataCadastro;
		this.local = local;
		this.usuarioRemocao = null;
		this.dataRemocao = null;
	}

	public int getTombo() {
		return tombo;
	}

	public void setTombo(int tombo) {
		this.tombo = tombo;
	}

	public StatusBem getStatus() {
		return status;
	}

	public void setStatus(StatusBem status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Usuario getUsuarioRemocao() {
		return usuarioRemocao;
	}

	public void setUsuarioRemocao(Usuario usuarioRemocao) {
		this.usuarioRemocao = usuarioRemocao;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(Calendar dataRemocao) {
		this.dataRemocao = dataRemocao;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Bem [tombo=" + tombo + ", status=" + status + ", descricao=" + descricao + ", usuarioCadastro="
				+ usuarioCadastro + ", usuarioRemocao=" + usuarioRemocao + ", dataCadastro=" + dataCadastro
				+ ", dataRemocao=" + dataRemocao + ", local=" + local + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bem))
			return false;
		Bem other = (Bem) obj;
		if (tombo != other.tombo)
			return false;
		return true;
	}
	
}
