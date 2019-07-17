package mainPackage;

import debugging.Debug;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import java.net.*;
import java.awt.*;
import java.util.ArrayList;


public class resultController {

    private String keyWord;
    public TextField SearchBox;
    public WebView imageViewer;
    private ArrayList<webcrawler.Product> myProducts;
    public ListView productList;
    public TextArea details;
    private int pindex;

    public void SearchButtonClicked(ActionEvent event)throws Exception
    {
        if(!SearchBox.getText().equals("")){
            keyWord = this.SearchBox.getText();
            Debug.log(keyWord + "A");
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

    public void setText(String key)
    {
        SearchBox.setText(key);
        myProducts = webcrawler.Crawler.crawlAll(key, 0);
        for(int i = 0; i < myProducts.size(); i++) {
            productList.getItems().add(myProducts.get(i).getName());
        }

        setProduct(0);
    }

    private void setProduct(int index)
    {
        index = index;
        WebEngine engine = imageViewer.getEngine();
        String url = "" + myProducts.get(index).getImageUrl() + "";
        String detailsText =
                "Title: " + myProducts.get(index).getName()  + "\n"
                + "Price: " + myProducts.get(index).getPrice() + "\n"
                + "Old Price: " + myProducts.get(index).getOldPrice() + "\n"
                + "Percentage: " + myProducts.get(index).getPercentage() + "% \n"
                + "Source: " + myProducts.get(index).getSource() + "\n";
        details.setText(detailsText);
        engine.load(url);
    }

    public void buy()
    {
        try{
            Desktop.getDesktop().browse(new URI(myProducts.get(pindex).getProductPage()));
        }catch(Exception e){}
    }

    public void loadNext()
    {
        if( pindex < myProducts.size()){
            pindex++;
            setProduct(pindex);
            productList.scrollTo(pindex);
            productList.getSelectionModel().select(pindex);
        }
    }

    public void loadPrevious()
    {
        if( pindex > 0){
            pindex--;
            setProduct(pindex);
            productList.scrollTo(pindex);
            productList.getSelectionModel().select(pindex);
        }
    }

    public void listSelection()
    {
        pindex = productList.getSelectionModel().getSelectedIndex();
        setProduct(pindex);
    }

}
