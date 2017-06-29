package view.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import control.UsuarioManager;
import control.UsuarioManagerInterface;
import domain.usuario.Usuario;
import domain.usuario.exceptions.SenhaInvalida;
import domain.usuario.exceptions.UsuarioNotFound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	
	@FXML
	private TextField txtLogin;
	
	@FXML
	private PasswordField txtSenha;
	
	@FXML
	private Label lblLoginStatus;
	
	public static Usuario usuarioLogado = null;
	
	@FXML
	private void btnEntrarAction(ActionEvent event) throws IOException {
		if(txtLogin.getText().length() == 0 || txtSenha.getText().length() == 0) {
			lblLoginStatus.setText("Campo inválido");
			return;
		}
		
		UsuarioManagerInterface usuarioManager = new UsuarioManager();
		try {
			usuarioLogado = usuarioManager.autenticarUsuario(txtLogin.getText(), txtSenha.getText());
			if(usuarioLogado != null) {
				((Node) (event.getSource())).getScene().getWindow().hide();
				Parent parent = FXMLLoader.load(getClass().getResource("/view/gui/MainScreen.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				//stage.resizableProperty().setValue(Boolean.FALSE);
				stage.setTitle("PAT");
				stage.show();
			}
		} catch(UsuarioNotFound e1) {
			lblLoginStatus.setText("Usuário não encontrado!");
		} catch(SenhaInvalida e2) {
			lblLoginStatus.setText("Senha inválida!");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
