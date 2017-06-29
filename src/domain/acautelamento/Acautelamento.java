package domain.acautelamento;

import java.util.Calendar;

import domain.bem.Bem;
import domain.local.Local;
import domain.usuario.Usuario;

public class Acautelamento {

	private int id;
	private Usuario usuario;
	private Bem bem;
	private Calendar dataInicio;
	private Calendar dataFim;
	private Local local;
	private StatusAcautelamento status;
	private String observacao;
	
	public Acautelamento() {
		// Empty constructor
	}
	
	public Acautelamento(int id, Usuario usuario, Bem bem, Calendar dataInicio,
			Calendar dataFim, Local local, StatusAcautelamento status, String observacao) {
		this.id = id;
		this.usuario = usuario;
		this.bem = bem;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.local = local;
		this.status = status;
		this.observacao = observacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Bem getBem() {
		return bem;
	}

	public void setBem(Bem bem) {
		this.bem = bem;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public StatusAcautelamento getStatus() {
		return status;
	}

	public void setStatus(StatusAcautelamento status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "Acautelamento [id=" + id + ", usuario=" + usuario + ", bem=" + bem + ", dataInicio=" + dataInicio
				+ ", dataFim=" + dataFim + ", local=" + local + ", status=" + status + ", observacao=" + observacao
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Acautelamento))
			return false;
		Acautelamento other = (Acautelamento) obj;
		if (bem == null) {
			if (other.bem != null)
				return false;
		} else if (!bem.equals(other.bem))
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
