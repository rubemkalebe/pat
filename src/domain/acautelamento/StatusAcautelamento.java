package domain.acautelamento;

public enum StatusAcautelamento {
	ATIVO("Ativo"), ENCERRADO("Encerrado");
	
	private final String value;
	
	private StatusAcautelamento(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getAsInt() {
		if(value.equals("Ativo")) {
			return 1;
		} else {
			return 0;
		}
	}
}
