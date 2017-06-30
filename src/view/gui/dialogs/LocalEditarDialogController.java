package view.gui.dialogs;

import java.util.List;

import control.LocalManager;
import control.LocalManagerInterface;
import control.TipoLocalManager;
import control.TipoLocalManagerInterface;
import domain.local.Local;
import domain.local.StatusLocal;
import domain.local.TipoLocal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LocalEditarDialogController {

	@FXML
	private ComboBox<String> local_tipo_cb;
	
	@FXML
	private TextField local_nome_txt;
	
	@FXML
	private TextField local_desc_txt;
	
	@FXML
	private ComboBox<String> local_status_cb;
	
	@FXML
	private TextField local_total_txt;
		
	private LocalManagerInterface localManager = new LocalManager();
	private List<TipoLocal> tiposLocaisList;
	private Stage dialogStage;
	private Local local;
	
	/**
     * Inicializa a classe controller. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    	ObservableList<String> tiposLocais = FXCollections.observableArrayList();
    	TipoLocalManagerInterface tipoLocalManager = new TipoLocalManager();
    	tiposLocaisList = tipoLocalManager.getAllTiposLocal();
    	
    	for(TipoLocal l : tiposLocaisList) {
    		tiposLocais.add(l.getNome());
    	}
    	local_tipo_cb.setItems(tiposLocais);
    }
    
    /**
     * Define o palco deste dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    

    public void setLocal(Local local) {
    	this.local = local;
    	
    	local_tipo_cb.setValue(local.getTipo().getNome());
    	local_nome_txt.setText(local.getNome());
    	local_desc_txt.setText(local.getDescricao());
    	local_status_cb.setValue(local.getStatus().getValue());
    	local_total_txt.setText(String.valueOf(local.getTotalBens()));
    }
    
    @FXML
    private void handleOk() {
    	if(isInputValid()) {
    		if(local != null) {
    			local.setTipo(searchTipo(local_tipo_cb.getSelectionModel().getSelectedItem()));
    			local.setTipoProperty(searchTipo(local_tipo_cb.getSelectionModel().getSelectedItem()));
        		local.setNome(local_nome_txt.getText());
        		local.setDescricao(local_desc_txt.getText());
        		local.setStatus(convertStringToStatus(local_status_cb.getSelectionModel().getSelectedItem()));
        		local.setTotalBens(Integer.valueOf(local_total_txt.getText()));
        		
        		localManager.editLocal(local);
    		} else {    			
    			local = localManager.addLocal(
    					searchTipo(local_tipo_cb.getSelectionModel().getSelectedItem()),
    					local_nome_txt.getText(),
    					local_desc_txt.getText(),
    					convertStringToStatus(local_status_cb.getSelectionModel().getSelectedItem()),
        				Integer.valueOf(local_total_txt.getText())
        		);
    			System.out.println(local);
    		}    		
    		
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

	public Local getLocal() {
		return local;
	}
	
	private StatusLocal convertStringToStatus(String s) {
		if(s.equals(StatusLocal.DISPONIVEL.getValue())) {
			return StatusLocal.DISPONIVEL;
		} else {
			return StatusLocal.MANUTENCAO;
		}
	}
	
	private TipoLocal searchTipo(String s) {
		for(TipoLocal tl : tiposLocaisList) {
			if(tl.getNome().equals(s)) {
				return tl;
			}
		}
		return null;
	}
}
