package domain.bem;

public enum StatusBem {
	CADASTRADO("Cadastrado"),
	ATIVO("Ativo"),
	MANUTENCAO("Manutenção"),
	ACAUTELADO("Acautelado"),
	RESERVADO("Reservado"),
	ALIENADO("Alienado");
	
	private final String value;
	
	private StatusBem(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getAsInt() {
		if(value.equals("Cadastrado")) {
			return 1;
		} else if(value.equals("Ativo")) {
			return 2;
		} else if(value.equals("Manutenção")) {
			return 3;
		} else if(value.equals("Acautelado")) {
			return 4;
		} else if(value.equals("Reservado")) {
			return 5;
		} else {
			return 6;
		}
	}
}
