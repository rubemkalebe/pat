package domain.local;

public class Local {

	private int numero;
	private String nome;
	private String descricao;
	private StatusLocal status;
	private TipoLocal tipo;
	private int totalBens;
	
	public Local(int numero, String nome, String descricao, StatusLocal status, TipoLocal tipo, int totalBens) {
		this.numero = numero;
		this.nome = nome;
		this.descricao = descricao;
		this.status = status;
		this.tipo = tipo;
		this.totalBens = totalBens;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusLocal getStatus() {
		return status;
	}

	public void setStatus(StatusLocal status) {
		this.status = status;
	}

	public TipoLocal getTipo() {
		return tipo;
	}

	public void setTipo(TipoLocal tipo) {
		this.tipo = tipo;
	}

	public int getTotalBens() {
		return totalBens;
	}

	public void setTotalBens(int totalBens) {
		this.totalBens = totalBens;
	}

	@Override
	public String toString() {
		return "Local [numero=" + numero + ", nome=" + nome + ", descricao=" + descricao + ", status=" + status
				+ ", tipo=" + tipo + ", totalBens=" + totalBens + "]";
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
		} else if (!nome.equals(other.nome))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}	
	
}
