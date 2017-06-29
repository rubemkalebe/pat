package view.gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import control.TipoLocalManager;
import control.TipoLocalManagerInterface;
import domain.local.TipoLocal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;

public class GerenciarTiposLocaisController implements Initializable {

	private ObservableList<TipoLocal> tipoLocalData = FXCollections.observableArrayList();
	
	private TipoLocalManagerInterface tipoLocalManager = new TipoLocalManager();
	
	@FXML
	private TableView<TipoLocal> tl_table;
	
	@FXML
	private TableColumn<TipoLocal, Integer> tab_tl_id;
	
	@FXML
	private TableColumn<TipoLocal, String> tab_tl_nome;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tipoLocalData.addAll(tipoLocalManager.getAllTiposLocal());
		tl_table.setItems(tipoLocalData);
		
		tab_tl_id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		tab_tl_nome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
	}
	
	@FXML
	private void handleDeleteTipo(ActionEvent ae) {
		int selectedIndex = tl_table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmar ação");
			alert.setHeaderText("Confirme a remoção");
			alert.setContentText("Você deseja realmente remover " +
								tl_table.getItems().get(selectedIndex).getNome() + "?");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK) {
				tipoLocalManager.removeTipoLocal(tl_table.getItems().get(selectedIndex));
				tl_table.getItems().remove(selectedIndex);
			} else {
				// do nothing
			}
		} else {
			// Nada selecionado
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhum tipo selecionado");
			alert.setContentText("Por favor, selecione um tipo na tabela.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleEditarTipo(ActionEvent ae) {
		int selectedIndex = tl_table.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			TextInputDialog dialog = new TextInputDialog(tipoLocalData.get(selectedIndex).getNome());
			dialog.setTitle("Editar Tipo de Local");
			dialog.setHeaderText("Editar tipo de local");
			dialog.setContentText("Entre com o nome do tipo do local:");
			
			Optional<String> result = dialog.showAndWait();
			if(result.isPresent()) {
				tipoLocalData.get(selectedIndex).setNome(result.get());
				tipoLocalManager.editTipoLocal(tipoLocalData.get(selectedIndex));
			}
		} else {
			// Nada selecionado
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nenhuma seleção");
			alert.setHeaderText("Nenhum tipo selecionado");
			alert.setContentText("Por favor, selecione um tipo na tabela.");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleNovoTipo(ActionEvent ae) {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Novo Tipo de Local");
		dialog.setHeaderText("Cadastrar novo tipo de local");
		dialog.setContentText("Entre com o nome do tipo do local:");
		
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()) {
			TipoLocal t = tipoLocalManager.addTipoLocal(result.get());
			tipoLocalData.add(t);
		}
	}
}
