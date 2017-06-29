package view.gui.dialogs;

import control.UsuarioManager;
import domain.usuario.TipoUsuario;
import domain.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AcautelamentoEditarDialogController {

	@FXML
	private ComboBox<String> usuario_tipo_cb;
	
	@FXML
	private TextField usuario_nome_txt;
	
	@FXML
	private TextField usuario_fone_txt;
	
	@FXML
	private TextField usuario_email_txt;
	
	@FXML
	private TextField usuario_login_txt;
	
	private Stage dialogStage;
	private Usuario usuario;
	
	/**
     * Inicializa a classe controller. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {}
    
    /**
     * Define o palco deste dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Define o usuario a ser editado no dialog.
     * 
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
    	this.usuario = usuario;
    	
    	usuario_tipo_cb.setValue(usuario.getTipo().getValue());
    	usuario_nome_txt.setText(usuario.getNome());
    	usuario_fone_txt.setText(usuario.getTelefone());
    	usuario_email_txt.setText(usuario.getEmail());
    	usuario_login_txt.setText(usuario.getLogin());
    }
    
    @FXML
    private void handleOk() {
    	if(isInputValid()) {
    		usuario.setTipo(TipoUsuario.valueOf(usuario_tipo_cb.getSelectionModel().getSelectedItem().toUpperCase()));
    		usuario.setNome(usuario_nome_txt.getText());
    		usuario.setTelefone(usuario_fone_txt.getText());
    		usuario.setEmail(usuario_email_txt.getText());
    		usuario.setLogin(usuario_login_txt.getText());
    		
    		UsuarioManager usuarioManager = new UsuarioManager();
			usuarioManager.editUsuario(usuario);
    		
    		dialogStage.close();
    	}
    }
    
    @FXML
    private void handleCancel() {
    	dialogStage.close();
    }
    
    private boolean isInputValid() {
    	// TODO Validacao
    	return true;
    }
}
