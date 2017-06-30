package view.gui.dialogs;

import java.util.List;

import control.BemManager;
import control.BemManagerInterface;
import control.LocalManager;
import control.LocalManagerInterface;
import domain.bem.Bem;
import domain.bem.StatusBem;
import domain.local.Local;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BemEditarDialogController {

	@FXML
	private TextField bem_tombo_txt;
	
	@FXML
	private ComboBox<String> bem_status_cb;
	
	@FXML
	private TextField bem_desc_txt;
		
	@FXML
	private ComboBox<String> bem_local_cb;
	
	private BemManagerInterface bemManager = new BemManager();
	private List<Local> locaisList;
	private Stage dialogStage;
	private Bem bem;
	
	/**
     * Inicializa a classe controller. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {    	
    	ObservableList<String> locais = FXCollections.observableArrayList();
    	LocalManagerInterface localManager = new LocalManager();
    	locaisList = localManager.getAllLocais();
    	
    	for(Local l : locaisList) {
    		locais.add(l.getNome());
    	}
    	bem_local_cb.setItems(locais);
    }
    
    /**
     * Define o palco deste dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setBem(Bem bem) {
    	this.bem = bem;
    	
    	bem_tombo_txt.setText(bem.getTombo());
    	bem_status_cb.setValue(bem.getStatus().getValue());    	
    	bem_desc_txt.setText(bem.getDescricao());
    	bem_local_cb.setValue(bem.localProperty().get());
    }
    
    @FXML
    private void handleOk() {
    	if(isInputValid()) {
    		if(bem != null) {
    			bem.setTombo(bem_tombo_txt.getText());
    			bem.setStatus(convertStringToStatus(bem_status_cb.getSelectionModel().getSelectedItem()));
    			bem.setLocal(searchLocal(bem_local_cb.getSelectionModel().getSelectedItem()));
    			bem.setLocalString(bem_local_cb.getSelectionModel().getSelectedItem());
    			bem.setDescricao(bem_desc_txt.getText());
    			
    			bemManager.editBem(bem, null, null);
    		} else {
    			bem = bemManager.addBem(
    					bem_tombo_txt.getText(),
    					convertStringToStatus(bem_status_cb.getSelectionModel().getSelectedItem()),
    					bem_desc_txt.getText(),
    					searchLocal(bem_local_cb.getSelectionModel().getSelectedItem()),
    					null,
    					null    					
    			);
    		}
    		dialogStage.close();
    	}
    }
    
    private StatusBem convertStringToStatus(String s) {
    	if(s.equals(StatusBem.MANUTENCAO.getValue())) {
			return StatusBem.MANUTENCAO;
		} else {
			return StatusBem.valueOf(s.toUpperCase());
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
    
    public Bem getBem() {
    	return bem;
    }
    
    private Local searchLocal(String nome) {
    	for(Local l : this.locaisList) {
    		if(l.getNome().equals(nome)) {
    			return l;
    		}
    	}
    	return null;
    }
}
