package view.gui.dialogs;

import control.UsuarioManager;
import domain.usuario.TipoUsuario;
import domain.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UsuarioNovoDialogController {

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
	
	@FXML
	private PasswordField usuario_pass1_txt;
	
	@FXML
	private PasswordField usuario_pass2_txt;
	
	private UsuarioManager usuarioManager = new UsuarioManager();
	
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
    
    @FXML
    private void handleOk() {
    	if(isInputValid()) {
    		this.usuario = usuarioManager.addUsuario(
    				TipoUsuario.valueOf(usuario_tipo_cb.getSelectionModel().getSelectedItem().toUpperCase()),
    				usuario_nome_txt.getText(),
    				usuario_fone_txt.getText(),
    				usuario_email_txt.getText(),
    				usuario_login_txt.getText(),
    				usuario_pass1_txt.getText()
    		);
    		
    		dialogStage.close();
    	}
    }
    
    @FXML
    private void handleCancel() {
    	dialogStage.close();
    }
    
    private boolean isInputValid() {
    	// TODO Validação
    	return true;
    }

	public Usuario getUsuario() {
		return usuario;
	}

}
