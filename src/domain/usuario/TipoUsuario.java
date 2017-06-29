package domain.usuario;

public enum TipoUsuario {
	SERVIDOR("Servidor"),
	BOLSISTA("Bolsista"),
	PROFESSOR("Professor"),
	ALUNO("Aluno");
	
	private final String value;
	
	private TipoUsuario(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getAsInt() {
		if(value.equals("Servidor")) {
			return 1;
		} else if(value.equals("Bolsista")) {
			return 2;
		} else if(value.equals("Professor")) {
			return 3;
		} else {
			return 4;
		}
	}
}
