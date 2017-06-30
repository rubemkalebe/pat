package domain.local;

public enum StatusLocal {
	DISPONIVEL("Disponível"),
	MANUTENCAO("Manutenção");
	
	private final String value;
	
	private StatusLocal(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getAsInt() {
		if(value.equals("Disponível")) {
			return 1;
		} else {
			return 0;
		}
	}
}
