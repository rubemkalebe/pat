package view.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import control.UsuarioManager;
import domain.usuario.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.Main;
import view.gui.dialogs.UsuarioEditarDialogController;
import view.gui.dialogs.UsuarioNovoDialogController;

public class MainScreenController implements Initializable {

	private ObservableList<Usuario> usuarioData = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Usuario> usuario_table;
	
	@FXML
	private TableColumn<Usuario, Integer> tab_usuario_id;
	
	@FXML
	private TableColumn<Usuario, String> tab_usuario_tipo;
	
	@FXML
	private TableColumn<Usuario, String> tab_usuario_nome;
	
	@FXML
	private TableColumn<Usuario, String> tab_usuario_login;
	
	@FXML
	private TableColumn<Usuario, String> tab_usuario_telefone;
	
	@FXML
	private TableColumn<Usuario, String> tab_usuario_email;
	
	@FXML
	private void handleAboutAction(final ActionEvent event) {
		provideAboutFunctionality();
	}
	  
	private void provideAboutFunctionality() {
		System.out.println("You clicked on About!");
	}
	
	@FXML
	private void handleKeyInput(final InputEvent event) {
		if (event instanceof KeyEvent) {
			final KeyEvent keyEvent = (KeyEvent) event;
			if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
				provideAboutFunctionality();
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		UsuarioManager usuarioManager = new UsuarioManager();
		
		usuarioData.addAll(usuarioManager.getAllUsuarios());
		usuario_table.setItems(usuarioData);
		
		tab_usuario_id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		tab_usuario_tipo.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
		tab_usuario_nome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		tab_usuario_login.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
		tab_usuario_telefone.setCellValueFactory(cellData -> cellData.getValue().telefoneProperty());
		tab_usuario_email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
	}
	
	@FXML
	private void handleDeleteUsuario(ActionEvent ae) {
		int selectedIndex = usuario_table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmar ação");
			alert.setHeaderText("Confirme a remoção");
			alert.setContentText("Você deseja realmente remover " +
								usuario_table.getItems().get(selectedIndex).getNome() + "?");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK) {
				
				UsuarioManager usuarioManager = new UsuarioManager();
				usuarioManager.removeUsuario(usuario_table.getItems().get(selectedIndex));
				
				usuario_table.getItems().remove(selectedIndex);
			} else {
				// do nothing
			}
		} else {
			// Nada selecionado
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhum usuário selecionado");
			alert.setContentText("Por favor, selecione um usuário na tabela.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleEditarUsuario(ActionEvent ae) {
		int selectedIndex = usuario_table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/gui/dialogs/UsuarioEditarDialog.fxml"));
			try {
				AnchorPane page = (AnchorPane) loader.load();
				
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Editar usuário");
				dialogStage.initModality(Modality.WINDOW_MODAL);
				
				Node source = (Node) ae.getSource();
			    Window theStage = source.getScene().getWindow();
				
				dialogStage.initOwner(theStage);
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);
				
				UsuarioEditarDialogController controller = loader.getController();
				controller.setDialogStage(dialogStage);
				controller.setUsuario(usuarioData.get(selectedIndex));
				
				dialogStage.showAndWait();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// Nada selecionado
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhum usuário selecionado");
			alert.setContentText("Por favor, selecione um usuário na tabela.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleNovoUsuario(ActionEvent ae) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/gui/dialogs/UsuarioNovoDialog.fxml"));
		try {
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Novo usuário");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			
			Node source = (Node) ae.getSource();
		    Window theStage = source.getScene().getWindow();
			
			dialogStage.initOwner(theStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			UsuarioNovoDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			if(controller.getUsuario() != null) {
				this.usuarioData.add(controller.getUsuario());	
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleGerenciarTipos(ActionEvent ae) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/gui/GerenciarTiposLocais.fxml"));
		try {
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Gerenciar Tipos de Locais");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			
			Node source = (Node) ae.getSource();
		    Window theStage = source.getScene().getWindow();
			
			dialogStage.initOwner(theStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			dialogStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
