package data.util;

public class DBTables {

	public static String USUARIO_TABLE_NAME = "Usuario";
	public static String USUARIO_ATTR_ID = "IdUsuario";
	public static String USUARIO_ATTR_TIPO = "TipoUsuario";
	public static String USUARIO_ATTR_NOME = "NomeUsuario";
	public static String USUARIO_ATTR_LOGIN = "LoginUsuario";
	public static String USUARIO_ATTR_SENHA = "SenhaUsuario";
	public static String USUARIO_ATTR_TELEFONE = "TelefoneUsuario";
	public static String USUARIO_ATTR_EMAIL = "EmailUsuario";
	public final static int USUARIO_ATTR_TIPO_SERVIDOR = 1;
	public final static int USUARIO_ATTR_TIPO_BOLSISTA = 2;
	public final static int USUARIO_ATTR_TIPO_PROFESSOR = 3;
	public final static int USUARIO_ATTR_TIPO_ALUNO = 4;
	
	public static String TIPOLOCAL_TABLE_NAME = "TipoLocal";
	public static String TIPOLOCAL_ATTR_ID = "IdTipoLocal";
	public static String TIPOLOCAL_ATTR_NOME = "NomeTipoLocal";
	
	public static String LOCAL_TABLE_NAME = "Local";
	public static String LOCAL_ATTR_NUMERO = "IdLocal";
	public static String LOCAL_ATTR_NOME = "NomeLocal";
	public static String LOCAL_ATTR_DESCRICAO = "DescricaoLocal";
	public static String LOCAL_ATTR_STATUS = "StatusLocal";
	public static String LOCAL_ATTR_TIPO = "Local_IdTipoLocal";
	public static String LOCAL_ATTR_TOTAL = "TotalBensLocal";
	public final static int LOCAL_ATTR_STATUS_MANUTENCAO = 0;
	public final static int LOCAL_ATTR_STATUS_DISPONIVEL = 1;
	
	public static String BEM_TABLE_NAME = "Bem";
	public static String BEM_ATTR_ID = "IdBem";
	public static String BEM_ATTR_TOMBO = "TomboBem";
	public static String BEM_ATTR_STATUS = "StatusBem";
	public static String BEM_ATTR_DESCRICAO = "DescricaoBem";
	public static String BEM_ATTR_LOCAL = "Bem_IdLocal";
	public final static int BEM_ATTR_STATUS_CADASTRADO = 1;
	public final static int BEM_ATTR_STATUS_ATIVO = 2;
	public final static int BEM_ATTR_STATUS_MANUTENCAO = 3;
	public final static int BEM_ATTR_STATUS_ACAUTELADO = 4;
	public final static int BEM_ATTR_STATUS_RESERVADO = 5;
	public final static int BEM_ATTR_STATUS_ALIENADO = 6;

}
