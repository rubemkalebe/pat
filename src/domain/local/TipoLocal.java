package domain.local;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class TipoLocal {

	private ObservableValue<Integer> id;
	private StringProperty nome;

	public TipoLocal(int id, String tipo) {
		this.id = new SimpleIntegerProperty(id).asObject();
		this.nome = new SimpleStringProperty(tipo);
	}

	public int getId() {
		return id.getValue();
	}

	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id).asObject();
	}

	public ObservableValue<Integer> idProperty() {
		return this.id;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TipoLocal))
			return false;
		TipoLocal other = (TipoLocal) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.get().equals(other.nome.get()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoLocal [id=" + id.getValue() + ", nome=" + nome.get() + "]";
	}
	
}
