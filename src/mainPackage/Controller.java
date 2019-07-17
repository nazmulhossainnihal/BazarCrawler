package mainPackage;

import debugging.Debug;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.*;


public class Controller {

    private String keyWord;
    public TextField SearchBox;

    public void SearchButtonClicked(ActionEvent event)throws Exception
    {
        if(!SearchBox.getText().equals("")){
            keyWord = this.SearchBox.getText();
            Debug.log(keyWord);
            FXMLLoader resultXML = new FXMLLoader(getClass().getResource("ResultScene.fxml"));
            Parent root = (Parent) resultXML.load();
            Scene resultScene = new Scene(root, 1080, 720);
            resultController RES = resultXML.getController();
            RES.setText(keyWord);
            Stage resultStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            resultStage.setScene(resultScene);
            SearchBox.setText(keyWord);
        }
    }

}
