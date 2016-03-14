/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.swing.event.HyperlinkEvent;


/**
 *
 * @author rosiengo
 */
public class GUIComponents extends BorderPane{
  protected StackPane settingsPane;
  protected StackPane simulationPane;
  public final Canvas canvas = new Canvas(800,600);
  public GraphicsContext gcontext;
    
  protected HBox policyOptionsBox;  
  protected VBox policySettings;
  protected Label lblSelectPolicy = new Label("Select Policy Option:");
  final protected ToggleGroup policiesOptions = new ToggleGroup();
  protected RadioButton defaultPolicy = new RadioButton("Default");
  protected RadioButton randomPolicy = new RadioButton("Random");
  protected RadioButton customisedPolicy = new RadioButton("Customised") ;
  
  protected GridPane policyPane;
  protected Label lblMin = new Label("Min");
  protected Label lblMax = new Label("Max");
  protected Label lblStraight = new Label("Straight Road Speed Limit:");
  protected Label lblJunction = new Label("Junction Speed Limit:");
  protected Label lblCurvy = new Label("Curvy Road Speed Limit:");
  protected Label lblGreen = new Label("Green Light Duration:");
  protected Label lblAmber = new Label("Amber Light Duration:");
  protected Label lblRed = new Label("Red Light Duration:");
  
  protected TextField txtMinStraight = new IntegerTextField();
  protected TextField txtMaxStraight  = new IntegerTextField() ;
  protected TextField txtMinJunction = new IntegerTextField();
  protected TextField txtMaxJunction  = new IntegerTextField() ;  
  protected TextField txtMinCurvy = new IntegerTextField();
  protected TextField txtMaxCurvy  = new IntegerTextField() ;
  protected TextField txtMinGreen = new IntegerTextField();
  protected TextField txtMaxGreen = new IntegerTextField();
  protected TextField txtMinAmber = new IntegerTextField();
  protected TextField txtMaxAmber = new IntegerTextField();
  protected TextField txtMinRed = new IntegerTextField();
  protected TextField txtMaxRed = new IntegerTextField();
  
  
  protected Label lblDensity = new Label("Traffic Density:");
  protected ComboBox txtDensity = new ComboBox();
  protected Label lblDuration = new Label("Session Duration (s):");
  protected TextField txtDuration =  new IntegerTextField();
  
  

  protected HBox button_box = new HBox(20);
  public Button btnStart = new Button("Start");
  public Button btnTerminate = new Button("Terminate");
  public Button btnReport = new Button("Report");
  public Label blank3 = new Label("");
      
  
 public GUIComponents()
 {
     this.setLeft(setSimulationPanel());
     this.setRight(setCofigurationPanel());
 }
  public StackPane setSimulationPanel(){
    simulationPane = new StackPane();       
    simulationPane.setStyle("-fx-background-color: darkgreen");
    
    simulationPane.getChildren().add(canvas);  //(800,600)
    gcontext = canvas.getGraphicsContext2D();
    
    return simulationPane;        
  }
  
  public StackPane setCofigurationPanel(){
      settingsPane = new StackPane();
      settingsPane.getChildren().add(setPolicyBox());
      
      return settingsPane;
           
  }
  
  public VBox setPolicyBox(){
      defaultPolicy.setToggleGroup(policiesOptions);
      randomPolicy.setToggleGroup(policiesOptions);
      customisedPolicy.setToggleGroup(policiesOptions);
      defaultPolicy.setUserData(0);
      randomPolicy.setUserData(1);
      customisedPolicy.setUserData(2);
      
      defaultPolicy.setSelected(true);
      disableMin();
      disableMax();
      policiesOptions.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
        @Override
        public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
            switch (Integer.parseInt(policiesOptions.getSelectedToggle().getUserData().toString())) {
                case 0:
                    disableMin();
                    disableMax();
                    break;
                case 1:
                    enableMin();
                    enableMax();
                    break;
                case 2:
                    enableMin();
                    disableMax();
                    clearMax();
                    break;
                default:
                    break;
            }
            
        }
      }
      );
      
    
      policyOptionsBox = new HBox(8);
      policyOptionsBox.getChildren().addAll(defaultPolicy, randomPolicy, customisedPolicy);
      policySettings = new VBox();
      policySettings.setPadding(new Insets(20,20,20,20));
      policySettings.getChildren().addAll(lblSelectPolicy, policyOptionsBox);
      
      policyPane = new GridPane();
      policyPane.setPadding(new Insets(10,10,10,10));
      policyPane.add(lblMin, 1, 0);
      policyPane.add(lblMax, 2, 0);
      policyPane.add(lblJunction, 0, 1);
      policyPane.add(lblStraight,0,2);
      policyPane.add(lblCurvy, 0, 3);
      policyPane.add(txtMinJunction,1,1);
      policyPane.add(txtMaxJunction, 2, 1);
      policyPane.add(txtMinStraight,1,2);
      policyPane.add(txtMaxStraight, 2, 2);
      policyPane.add(txtMinCurvy,1,3);
      policyPane.add(txtMaxCurvy, 2, 3);
      policyPane.add(lblGreen,0,4);
      policyPane.add(lblAmber,0,5);
      policyPane.add(lblRed,0,6);
      policyPane.add(txtMinGreen,1,4);
      policyPane.add(txtMaxGreen, 2, 4);
      policyPane.add(txtMinAmber,1,5);
      policyPane.add(txtMaxAmber, 2, 5);
      policyPane.add(txtMinRed,1,6);
      policyPane.add(txtMaxRed, 2,6);
      
      Label blank = new Label("");
      policyPane.add(blank, 0, 7);
      
      txtDensity.getItems().addAll("High", "Normal", "Low");
      
      policyPane.add(lblDensity, 0, 8);
      txtDensity.setValue("Normal");
      policyPane.add(txtDensity, 1, 8);
      
      Label blank2 = new Label("");
      policyPane.add(blank2, 0, 9);
      policyPane.add(lblDuration, 0, 10);
      policyPane.add(txtDuration,1,10);
      
          
      policySettings.getChildren().add(policyPane);
      
      btnTerminate.setDisable(true);
      
      button_box.setStyle("-fx-border-color: lightgrey" );
      button_box.setPadding(new Insets(10,10,10,10));
      
     
      button_box.getChildren().addAll(btnStart, btnTerminate, btnReport);
      blank3.setStyle("-fx-font-color : red");
            
      policySettings.getChildren().add(blank3);
      policySettings.getChildren().add(button_box);
      
   
      return policySettings;
             
      
  }
  
    
    public int getSelectedPolicy()
    {
        return Integer.parseInt(policiesOptions.getSelectedToggle().getUserData().toString());
    }
    
    public int[] getMinSpeedLimitSettings()
    {
        
        int[] speedLimits = new int[3];
        int selected = getSelectedPolicy();
        if (selected == 1 || selected == 2) 
           
        {
            speedLimits[0] = Integer.parseInt(txtMinJunction.getText());
            speedLimits[1] = Integer.parseInt(txtMinStraight.getText());
            speedLimits[2] = Integer.parseInt(txtMinCurvy.getText());
            return speedLimits;
        }
        else 
        {
            return null;
        }
           
        
              
    }
    
    public int[] getMaxSpeedLimitSettings()
    {
        
        int[] speedLimits = new int[3];
        int selected = getSelectedPolicy();
        if (selected == 1)
        {
                        
            
            speedLimits[0] = Integer.parseInt(txtMaxJunction.getText());
            speedLimits[1] = Integer.parseInt(txtMaxStraight.getText());
            speedLimits[2] = Integer.parseInt(txtMaxCurvy.getText());
            return speedLimits;
        }
        else 
        {
            return null;
        }
              
    }
    
    public int[] getMinTrafficLightSettings()
    {
        int[] trafficLights = new int[3];
        int selected = getSelectedPolicy();
        if (selected == 1 || selected == 2)
        {
            trafficLights [0] = Integer.parseInt(txtMinGreen.getText());
            trafficLights [1] = Integer.parseInt(txtMinAmber.getText());
            trafficLights [2] = Integer.parseInt(txtMinRed.getText());
            return trafficLights;
        }
        else 
            return null;
              
    }
    public int[] getMaxTrafficLightSettings()
    {
        int[] trafficLights = new int[3];
        int selected = getSelectedPolicy();
        
        if (selected == 1) 
        {
        trafficLights [0] = Integer.parseInt(txtMaxGreen.getText());
        trafficLights [1] = Integer.parseInt(txtMaxAmber.getText());
        trafficLights [2] = Integer.parseInt(txtMaxRed.getText());
        return trafficLights;
        }
        else
            return null;
              
    }
    
    public int getSessionDuration()
    {
        if (txtDuration.getText().isEmpty())
        {
            txtDuration.setText("60");
        }
            
        return Integer.parseInt(txtDuration.getText());
        
    }
 
  
    public String getTrafficDesity()
    {
        return txtDensity.getValue().toString();
    }
    
    //disable Mininum policy value textfields
    private void disableMin(){
        txtMinStraight.setDisable(true);
        txtMinJunction.setDisable(true);
        txtMinCurvy.setDisable(true);
        txtMinGreen.setDisable(true);
        txtMinAmber.setDisable(true);
        txtMinRed.setDisable(true);
               
    }
    
    //disable maximum policy value textfields
    private void disableMax(){
        txtMaxStraight.setDisable(true);
        txtMaxJunction.setDisable(true);
        txtMaxCurvy.setDisable(true);
        txtMaxGreen.setDisable(true);
        txtMaxAmber.setDisable(true);
        txtMaxRed.setDisable(true);
    }
    
    private void enableMin(){
        txtMinStraight.setDisable(false);
        txtMinJunction.setDisable(false);
        txtMinCurvy.setDisable(false);
        txtMinGreen.setDisable(false);
        txtMinAmber.setDisable(false);
        txtMinRed.setDisable(false);
               
    }
    
    private void enableMax(){
        txtMaxStraight.setDisable(false);
        txtMaxJunction.setDisable(false);
        txtMaxCurvy.setDisable(false);
        txtMaxGreen.setDisable(false);
        txtMaxAmber.setDisable(false);
        txtMaxRed.setDisable(false);
    }
    
    public  boolean validateBlank(){
        int selected = getSelectedPolicy();
        boolean valid = true;
        if (((selected == 1) || (selected ==2)) &&
                !validateMinValues()
                )
            valid = false;
        
        if (selected == 1 && !validateMaxValues())
            valid = false;
        
        return valid;         
                
    }
    
    public  boolean validateRange(){
        int selected = getSelectedPolicy();
        boolean valid = true;
        if ((selected == 1) &&
            (Integer.parseInt(txtMinJunction.getText()) > Integer.parseInt(txtMaxJunction.getText()) ||
             Integer.parseInt(txtMinStraight.getText()) > Integer.parseInt(txtMaxStraight.getText()) ||
             Integer.parseInt(txtMinCurvy.getText()) > Integer.parseInt(txtMaxCurvy.getText()) ||
             Integer.parseInt(txtMinGreen.getText()) > Integer.parseInt(txtMaxGreen.getText()) ||
             Integer.parseInt(txtMinAmber.getText()) > Integer.parseInt(txtMaxAmber.getText()) ||
             Integer.parseInt(txtMinRed.getText()) > Integer.parseInt(txtMaxRed.getText())       
            )
            )
            valid = false;
        
      
        
        return valid;         
                
    }
    
    private void clearMax(){
        txtMaxStraight.setText("");
        txtMaxJunction.setText("");
        txtMaxCurvy.setText("");
        txtMaxGreen.setText("");
        txtMaxAmber.setText("");
        txtMaxRed.setText("");
    }
        
    
    private boolean validateMinValues()
    {
         if (txtMinStraight.getText().isEmpty()||
             txtMinJunction.getText().isEmpty() ||
             txtMinCurvy.getText().isEmpty() ||
             txtMinGreen.getText().isEmpty() ||
             txtMinAmber.getText().isEmpty() ||
             txtMinRed.getText().isEmpty()
            )
             
             return false;
         else 
             return true;
                     
    }
    
    private boolean validateMaxValues()
    {
         if (txtMaxStraight.getText().isEmpty()||
             txtMaxJunction.getText().isEmpty() ||
             txtMaxCurvy.getText().isEmpty() ||
             txtMaxGreen.getText().isEmpty() ||
             txtMaxAmber.getText().isEmpty() ||
             txtMaxRed.getText().isEmpty()
            )
             
             return false;
         else 
             return true;
                     
    }
  
}
