package domain.bem;

import domain.local.Local;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bem {

	private ObjectProperty<Integer> id;
	private StringProperty tombo;
	private StringProperty status;
	private StringProperty descricao;
	private Local local;
	private StringProperty localString;
	
	public Bem(int id, String tombo, StatusBem status, String descricao, Local local) {
		this.id = new SimpleObjectProperty<Integer>(id);
		this.tombo = new SimpleStringProperty(tombo);
		this.status = new SimpleStringProperty(status.getValue());
		this.descricao = new SimpleStringProperty(descricao);
				
		this.local = local;
		this.localString = new SimpleStringProperty(local.getNome());
	}
	
	public Bem(int id, String tombo, StatusBem status, String descricao, StringProperty localString) {
		this.id = new SimpleObjectProperty<Integer>(id);
		this.tombo = new SimpleStringProperty(tombo);
		this.status = new SimpleStringProperty(status.getValue());
		this.descricao = new SimpleStringProperty(descricao);
				
		this.local = null;
		this.localString = localString;
	}
	
	public ObjectProperty<Integer> idProperty() {
		return this.id;
	}
	
	public int getId() {
		return this.id.get();
	}
	
	public void setId(int id) {
		this.id.set(id);
	}

	public String getTombo() {
		return tombo.get();
	}

	public void setTombo(String tombo) {
		this.tombo.set(tombo);
	}
	
	public StringProperty tomboProperty() {
		return this.tombo;
	}

	public StatusBem getStatus() {
		if(this.status.get().equals(StatusBem.MANUTENCAO.getValue())) {
			return StatusBem.MANUTENCAO;
		} else {
			return StatusBem.valueOf(status.get().toUpperCase());
		}
	}

	public void setStatus(StatusBem status) {
		this.status.set(status.getValue());
	}
	
	public StringProperty statusProperty() {
		return this.status;
	}

	public String getDescricao() {
		return descricao.get();
	}

	public void setDescricao(String descricao) {
		this.descricao.set(descricao);
	}
	
	public StringProperty descricaoProperty() {
		return this.descricao;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}
	
	public StringProperty localProperty() {
		return this.localString;
	}
	
	public void setLocalString(String local) {
		this.localString.set(local);
	}

	@Override
	public String toString() {
		return "Bem [tombo=" + tombo.get() + ", status=" + status.get() + ", descricao=" + descricao.get() 
				+ ", local=" + local.getNome() + "]";
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
		if (!tombo.get().equals(other.getTombo()))
			return false;
		return true;
	}
	
}
