package domain.usuario;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Usuario {

	private ObservableValue<Integer> id;
	private StringProperty tipo;
	private StringProperty nome;
	private StringProperty login;
	private String senha;
	private StringProperty telefone;
	private StringProperty email;
	
	public Usuario(int id, TipoUsuario tipo, String nome, String login, String senha,
			String telefone, String email) {
		this.id = new SimpleIntegerProperty(id).asObject();
		this.tipo = new SimpleStringProperty(tipo.getValue());
		this.nome = new SimpleStringProperty(nome);
		this.login = new SimpleStringProperty(login);
		this.senha = senha;
		this.telefone = new SimpleStringProperty(telefone);
		this.email = new SimpleStringProperty(email);
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

	public TipoUsuario getTipo() {
		return TipoUsuario.valueOf(tipo.get().toUpperCase());
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo.set(tipo.getValue());
	}
	
	public StringProperty tipoProperty() {
		return this.tipo;
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

	public String getLogin() {
		return login.get();
	}

	public void setLogin(String login) {
		this.login.set(login);
	}
	
	public StringProperty loginProperty() {
		return this.login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone.get();
	}

	public void setTelefone(String telefone) {
		this.telefone.set(telefone);
	}
	
	public StringProperty telefoneProperty() {
		return this.telefone;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}
	
	public StringProperty emailProperty() {
		return this.email;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.get().equals(other.email.get()))
			return false;
		if (id.getValue() != other.id.getValue())
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.get().equals(other.login.get()))
			return false;
		if (tipo.get() != other.tipo.get())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id.getValue() + ", tipo=" + tipo.getValue() + ", nome=" + nome.get() +
				", login=" + login.get() + ", senha=" + senha
				+ ", telefone=" + telefone.get() + ", email=" + email.get() + "]";
	}
	
}
