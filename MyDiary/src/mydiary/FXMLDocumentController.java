/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydiary;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

/**
 *
 * @author Student
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private TextArea content;
    @FXML
    private Slider slider;
    @FXML
    private ColorPicker fontColor, backColor;
    @FXML
    private ImageView imageView;

    @FXML
    private void clear(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Clear Confirmation");
        alert.setContentText("Are you sure to clear?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            content.clear();
        } else {
            
        }
        
    }

    @FXML
    private void setFontColor(ActionEvent event) {
        Color color = fontColor.getValue();
        content.setStyle("-fx-text-fill: " + format(color) + "; ");
        title.setStyle("-fx-text-fill: " + format(color) + "; ");
    }

    @FXML
    private void setBackgroundColor(ActionEvent event) {
        Color color = backColor.getValue();
        Region region = (Region) content.lookup(".content");
        region.setStyle("-fx-background-color: " + format(color));
        title.setStyle("-fx-background-color: " + format(color));
    }

    private String format(Color c) {
        int r = (int) (255 * c.getRed());
        int g = (int) (255 * c.getGreen());
        int b = (int) (255 * c.getBlue());
        return String.format("#%02x%02x%02x", r, g, b);
    }
    
    @FXML
    private void changeImage(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        Image image = new Image(getClass().getResourceAsStream(file.getAbsolutePath()));;
        imageView.setImage(image);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                content.setFont(new Font(newValue.doubleValue()));
            }
        });
    }

}
