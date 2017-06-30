package view.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import control.BemManager;
import control.LocalManager;
import control.UsuarioManager;
import domain.bem.Bem;
import domain.local.Local;
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
import view.gui.dialogs.BemEditarDialogController;
import view.gui.dialogs.LocalEditarDialogController;
import view.gui.dialogs.UsuarioEditarDialogController;
import view.gui.dialogs.UsuarioNovoDialogController;

public class MainScreenController implements Initializable {

	private ObservableList<Usuario> usuarioData = FXCollections.observableArrayList();
	private ObservableList<Local> localData = FXCollections.observableArrayList();
	private ObservableList<Bem> bemData = FXCollections.observableArrayList();
	
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
	private TableView<Local> local_table;
	
	@FXML
	private TableColumn<Local, Integer> tab_local_id;
	
	@FXML
	private TableColumn<Local, String> tab_local_nome;
	
	@FXML
	private TableColumn<Local, String> tab_local_desc;
	
	@FXML
	private TableColumn<Local, String> tab_local_tipo;
	
	@FXML
	private TableColumn<Local, String> tab_local_status;
	
	@FXML
	private TableColumn<Local, Integer> tab_local_total;
	
	@FXML
	private TableView<Bem> bem_table;
	
	@FXML
	private TableColumn<Bem, String> tab_bem_id;
	
	@FXML
	private TableColumn<Bem, String> tab_bem_local;
	
	@FXML
	private TableColumn<Bem, String> tab_bem_desc;
	
	@FXML
	private TableColumn<Bem, String> tab_bem_status;
	
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
		
		LocalManager localManager = new LocalManager();
		
		localData.addAll(localManager.getAllLocais());
		local_table.setItems(localData);
		tab_local_id.setCellValueFactory(cellData -> cellData.getValue().numeroProperty());
		tab_local_nome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		tab_local_desc.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
		tab_local_tipo.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
		tab_local_status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		tab_local_total.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
		
		BemManager bemManager = new BemManager();
		
		bemData.addAll(bemManager.getAllBens());
		bem_table.setItems(bemData);
		tab_bem_id.setCellValueFactory(cellData -> cellData.getValue().tomboProperty());
		tab_bem_local.setCellValueFactory(cellData -> cellData.getValue().localProperty());
		tab_bem_desc.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
		tab_bem_status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
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
	
	@FXML
	private void handleDeleteLocal(ActionEvent ae) {
		int selectedIndex = local_table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmar ação");
			alert.setHeaderText("Confirme a remoção");
			alert.setContentText("Você deseja realmente remover " +
								local_table.getItems().get(selectedIndex).getNome() + "?");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK) {
				LocalManager localManager = new LocalManager();
				localManager.removeLocal(local_table.getItems().get(selectedIndex));
				
				local_table.getItems().remove(selectedIndex);
			} else {
				// do nothing
			}
		} else {
			// Nada selecionado
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhum local selecionado");
			alert.setContentText("Por favor, selecione um local na tabela.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleEditarLocal(ActionEvent ae) {
		int selectedIndex = local_table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/gui/dialogs/LocalEditarDialog.fxml"));
			try {
				AnchorPane page = (AnchorPane) loader.load();
				
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Editar local");
				dialogStage.initModality(Modality.WINDOW_MODAL);
				
				Node source = (Node) ae.getSource();
			    Window theStage = source.getScene().getWindow();
				
				dialogStage.initOwner(theStage);
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);
				
				LocalEditarDialogController controller = loader.getController();
				controller.setDialogStage(dialogStage);
				controller.setLocal(localData.get(selectedIndex));
								
				dialogStage.showAndWait();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// Nada selecionado
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhum local selecionado");
			alert.setContentText("Por favor, selecione um local na tabela.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleNovoLocal(ActionEvent ae) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/gui/dialogs/LocalEditarDialog.fxml"));
		try {
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Novo local");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			
			Node source = (Node) ae.getSource();
		    Window theStage = source.getScene().getWindow();
			
			dialogStage.initOwner(theStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			LocalEditarDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			if(controller.getLocal() != null) {
				this.localData.add(controller.getLocal());	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleDeleteBem(ActionEvent ae) {
		int selectedIndex = bem_table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmar ação");
			alert.setHeaderText("Confirme a remoção");
			alert.setContentText("Você deseja realmente remover " +
								bem_table.getItems().get(selectedIndex).getDescricao() + "?");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK) {
				BemManager bemManager = new BemManager();
				bemManager.removeBem(bem_table.getItems().get(selectedIndex), null, null);
				
				bem_table.getItems().remove(selectedIndex);
			} else {
				// do nothing
			}
		} else {
			// Nada selecionado
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhum bem selecionado");
			alert.setContentText("Por favor, selecione um bem na tabela.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleEditarBem(ActionEvent ae) {
		int selectedIndex = bem_table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/gui/dialogs/BemEditarDialog.fxml"));
			try {
				AnchorPane page = (AnchorPane) loader.load();
				
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Editar bem");
				dialogStage.initModality(Modality.WINDOW_MODAL);
				
				Node source = (Node) ae.getSource();
			    Window theStage = source.getScene().getWindow();
				
				dialogStage.initOwner(theStage);
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);
				
				BemEditarDialogController controller = loader.getController();
				controller.setDialogStage(dialogStage);
				controller.setBem(bemData.get(selectedIndex));
								
				dialogStage.showAndWait();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// Nada selecionado
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhum bem selecionado");
			alert.setContentText("Por favor, selecione um bem na tabela.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleNovoBem(ActionEvent ae) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/gui/dialogs/BemEditarDialog.fxml"));
		try {
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Novo bem");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			
			Node source = (Node) ae.getSource();
		    Window theStage = source.getScene().getWindow();
			
			dialogStage.initOwner(theStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			BemEditarDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			dialogStage.showAndWait();
			
			System.out.println(controller.getBem());
			if(controller.getBem() != null) {
				this.bemData.add(controller.getBem());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ObservableList<Usuario> getUsuarioData() {
		return usuarioData;
	}

	public ObservableList<Local> getLocalData() {
		return localData;
	}

	public ObservableList<Bem> getBemData() {
		return bemData;
	}
}
