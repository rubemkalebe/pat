package domain.usuario;

public class Usuario {

	private int id;
	private TipoUsuario tipo;
	private String nome;
	private String login;
	private String senha;
	private String telefone;
	private String email;
	
	public Usuario(int id, TipoUsuario tipo, String nome, String login, String senha,
			String telefone, String email) {
		this.id = id;
		this.tipo = tipo;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.telefone = telefone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", tipo=" + tipo + ", nome=" + nome + ", login=" + login + ", senha=" + senha
				+ ", telefone=" + telefone + ", email=" + email + "]";
	}
	
}
