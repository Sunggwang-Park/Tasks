/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint_app;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 *
 * @author USER
 */
public class paintfxmlController implements Initializable {
    
    @FXML
    private ColorPicker colorpicker;
    
    @FXML
    private TextField bsize;
    
    @FXML
    private Canvas canvas;
  
    
 
    
    boolean tool = false, toolSelected = false , toolCircle = false , toolEraser = false;
    boolean smallEraser = false, largeEraser = false;
    boolean toolRect = false, toolRoundrect = false;
    
    GraphicsContext brushTool;
    private Object path;
   
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        brushTool = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e ->{
            double size = Double.parseDouble(bsize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
           
            
            if(toolSelected && !bsize.getText().isEmpty()){
                brushTool.setFill(colorpicker.getValue());
                brushTool.fillRoundRect(x, y, size, size, size, size);
            }
            
            if(toolCircle && !bsize.getText().isEmpty()){
               
               
                brushTool.setStroke(colorpicker.getValue());
                brushTool.strokeOval(x, y, size, size);
             }
            
            if(toolRect && !bsize.getText().isEmpty()){
                
                brushTool.setStroke(colorpicker.getValue());
                brushTool.strokeRect(x, y, size, size);
            }
            
            if(toolRoundrect && !bsize.getText().isEmpty()){
                
                brushTool.setStroke(colorpicker.getValue());
                brushTool.strokeRoundRect(x, y, y, y, size, size);
            }
            
            if(smallEraser ){
               
               brushTool.setFill(Color.WHITESMOKE);       
               brushTool.fillOval(e.getX() - 5 , e.getY(), 20, 20);
            }
            if(largeEraser ){
               
               brushTool.setFill(Color.WHITESMOKE);
               brushTool.fillOval(e.getX() - 5, e.getY(), 50, 50);
            }
            
        });
    }    
    
    @FXML
    public void newcanvas (ActionEvent e) {
        TextField getCanvasWidth = new TextField();
        getCanvasWidth.setPromptText("Width");
        getCanvasWidth.setPrefWidth(150);
        getCanvasWidth.setAlignment(Pos.CENTER);
        
        TextField getCanvasheight = new TextField();
        getCanvasheight.setPromptText("Height");
        getCanvasheight.setPrefWidth(150);
        getCanvasheight.setAlignment(Pos.CENTER);
        
        Button createButton = new Button();
        createButton.setText("Create Canvas");
        
        Button createallcanvas = new Button();
        createallcanvas.setText("Create all new Canvas");
        
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(getCanvasWidth,getCanvasheight,createButton, createallcanvas);
        
        Stage createStage = new Stage();
        AnchorPane root = new AnchorPane();
        root.setPrefWidth(200);
        root.setPrefHeight(100);
        root.getChildren().add(vBox);
        
        Scene CanvasScene = new Scene(root);
        createStage.setTitle("Create Canvas");
        createStage.setScene(CanvasScene);
        createStage.show();
        
        createButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                double canvasWidthReceived = Double.parseDouble(getCanvasWidth.getText());
                double canvasHeightReceived = Double.parseDouble(getCanvasheight.getText());
                canvas = new Canvas();
                canvas.setWidth(canvasWidthReceived);
                canvas.setHeight(canvasHeightReceived);
                canvas.resize(canvasWidthReceived, canvasHeightReceived);
                brushTool.setFill(Color.WHITESMOKE);
                brushTool.setLineWidth(1);
                brushTool.clearRect(0, 0, canvasWidthReceived, canvasHeightReceived);
                
        
       
                
                vBox.getChildren().add(canvas);
                
                createStage.close();
                
            }
        });
        
          createallcanvas.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               
                brushTool.setFill(Color.WHITESMOKE);
                brushTool.setLineWidth(1);
                brushTool.clearRect(0, 0, 10000, 10000);
                canvas = new Canvas();
                canvas.setWidth(10000);
                canvas.setHeight(10000);
        
       
                
                vBox.getChildren().add(canvas);
                
                createStage.close();
                
                
            }
        });
        
        
        
    }
    
    @FXML
    public void toolselected(ActionEvent e) {
        
        toolEraser =false;
        smallEraser =false;
        largeEraser =false;
        toolCircle = false;
        toolRect = false;
        toolRoundrect = false;
        
        toolSelected =true;
        
    }
    
    @FXML
    public void toolcircle(ActionEvent e){
        toolSelected =false;
        toolEraser =false;
        smallEraser =false;
        largeEraser =false;
        toolRect = false;
        toolRoundrect = false;
        
        toolCircle = true;
    }
    
    @FXML
    public void toolroundrect(ActionEvent e){
        toolSelected =false;
        toolEraser =false;
        smallEraser =false;
        largeEraser =false;
        toolCircle = false;
        toolRect = false;
        
        toolRoundrect = true;
    }
      @FXML
    public void toolrect(ActionEvent e){
        toolSelected =false;
        toolEraser =false;
        smallEraser =false;
        largeEraser =false;
        toolCircle = false;
        toolRoundrect = false;
        
        toolRect = true;
    }
    
   
    @FXML
    public void tooleraser(ActionEvent e){
        toolEraser = !toolEraser;
        
    }
    @FXML
    public void smalleraser(ActionEvent e){
        toolSelected =false;
        toolEraser =false;
        largeEraser =false;
        toolCircle = false;
        toolRect = false;
        toolRoundrect = false;
        
        smallEraser =true;
        
    }
     @FXML
    public void largeeraser(ActionEvent e){
        toolSelected =false;
        toolEraser =false;
        toolCircle = false;
        smallEraser = false;
        toolRect = false;
        toolRoundrect = false;
        
        largeEraser = true;
        
    }
   
    
    
}
