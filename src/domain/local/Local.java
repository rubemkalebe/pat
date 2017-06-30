package domain.local;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Local {

	private ObservableValue<Integer> numero;
	private StringProperty nome;
	private StringProperty descricao;
	private StringProperty status;
	private TipoLocal tipo;
	private StringProperty tipoString;
	private ObjectProperty<Integer> totalBens;
	
	public Local(int numero, String nome, String descricao, StatusLocal status, TipoLocal tipo, int totalBens) {
		this.numero = new SimpleIntegerProperty(numero).asObject();
		this.nome = new SimpleStringProperty(nome);
		this.descricao = new SimpleStringProperty(descricao);
		this.status = new SimpleStringProperty(status.getValue());
		this.tipo = tipo;
		this.tipoString = new SimpleStringProperty(tipo.getNome());
		this.totalBens = new SimpleObjectProperty<Integer>(totalBens);
	}

	public int getNumero() {
		return numero.getValue();
	}

	public void setNumero(int numero) {
		this.numero = new SimpleIntegerProperty(numero).asObject();
	}
	
	public ObservableValue<Integer> numeroProperty() {
		return this.numero;
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}
	
	public StringProperty nomeProperty() {
		return this.nome;
	}
	

	public String getDescricao() {
		return descricao.get();
	}

	public void setDescricao(String descricao) {
		this.descricao.set(descricao);
	}
	
	public StringProperty descricaoProperty() {
		return descricao;
	}

	public StatusLocal getStatus() {
		if(this.status.get().equals(StatusLocal.DISPONIVEL.getValue())) {
			return StatusLocal.DISPONIVEL;
		} else {
			return StatusLocal.MANUTENCAO;
		}
	}

	public void setStatus(StatusLocal status) {
		this.status.set(status.getValue());
	}
	
	public StringProperty statusProperty() {
		return this.status;
	}

	public TipoLocal getTipo() {
		return tipo;
	}

	public void setTipo(TipoLocal tipo) {
		this.tipo = tipo;
	}
	
	public StringProperty tipoProperty() {
		return tipoString;
	}

	public void setTipoProperty(TipoLocal tipo) {
		this.tipoString.set(tipo.getNome());
	}

	public int getTotalBens() {
		return totalBens.get();
	}

	public void setTotalBens(int totalBens) {
		this.totalBens.set(totalBens);
	}
	
	public ObjectProperty<Integer> totalProperty() {
		return this.totalBens;
	}

	@Override
	public String toString() {
		return "Local [numero=" + numero.getValue() + ", nome=" + nome.get() + ", descricao=" + descricao.get()
				+ ", status=" + status.getValue() + ", tipo=" + tipo.getNome() + ", totalBens=" + totalBens.getValue() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Local))
			return false;
		Local other = (Local) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.get().equals(other.getNome()))
			return false;
		if (numero.getValue() != other.getNumero())
			return false;
		return true;
	}	
	
}
