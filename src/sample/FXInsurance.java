package sample;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXInsurance extends Application{

    // Declare components at class level scope

    Label lblInsuranceDetails;
    Label lblHomeValue;
    Label lblHomeValueAmount;
    Label lblInsuranceOptions;
    Label lblInsuranceOptionsChosen1;
    Label lblInsuranceOptionsChosen2;
    Label lblInsuranceOptionsChosen3;
    Label lblTotalCost;
    Label lblTotalCostAmount;

    TextField tfHomeValue;

    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;

    Button btnQuote;

    Stage secondaryStage; // declare it at class level scope so I can close it from anywhere!

    @Override

    public void init(){

        // Instantiate the components

        lblInsuranceDetails = new Label("Insurance Details");
        lblHomeValue = new Label("Home Value:");
        lblHomeValueAmount = new Label("Not Entered");
        lblInsuranceOptions = new Label("Insurance Options Selected:");
        lblInsuranceOptionsChosen1 = new Label("NO Home Emergency Cover");
        lblInsuranceOptionsChosen2 = new Label("NO Legal Expenses Cover");
        lblInsuranceOptionsChosen3 = new Label("NO Personal Injury Cover");
        lblTotalCost = new Label("Total Cost:");
        lblTotalCostAmount = new Label("0.00");

        tfHomeValue = new TextField();

        cb1 = new CheckBox("Home Emergency Cover");
        cb2 = new CheckBox("Legal Expenses Cover");
        cb3 = new CheckBox("Personal Injury Cover");

        btnQuote = new Button("Insurance Quote");
        btnQuote.setOnAction(actionEvent -> dialog());


    } // end of init


    public void dialog(){


        // Create a stage

        secondaryStage = new Stage();
        secondaryStage.setTitle("Input Values");
        secondaryStage.setWidth(500);
        secondaryStage.setHeight(250);

        // Create a layout

        HBox hbd1 = new HBox();
        hbd1.getChildren().addAll(new Label("Home Value:"), tfHomeValue);
        hbd1.setSpacing(100);

        VBox vbd1 = new VBox();
        vbd1.getChildren().addAll(cb1, cb2, cb3);
        vbd1.setSpacing(5);

        HBox hbd2 = new HBox();
        hbd2.getChildren().addAll(new Label("Insurance Options:"), vbd1);
        hbd2.setSpacing(50);

        Button btnOK = new Button("OK");

        HBox hbd3 = new HBox();
        hbd3.getChildren().add(btnOK);
        hbd3.setAlignment(Pos.CENTER);

        VBox vbdAll = new VBox();
        vbdAll.getChildren().addAll(hbd1, hbd2, hbd3);
        vbdAll.setSpacing(15);
        vbdAll.setPadding(new Insets(10));

        //Create a scene

        Scene sc2 = new Scene(vbdAll);

        //Add the scene to the stage

        secondaryStage.setScene(sc2);

        // Show the stage

        secondaryStage.show();


        btnOK.setOnAction(actionEvent -> calculate());


    }


    public void calculate (){

        // First thing - check a numerical value has been entered in the tf

        if (!tfHomeValue.getText().isEmpty()){

            try{

                lblHomeValueAmount.setText(tfHomeValue.getText());

                double value = Double.parseDouble(tfHomeValue.getText());

                double basicPremium = value*0.002;

                double homeEmergencyCover = ((basicPremium*15)/100);
                double legalExpenses = ((basicPremium*10)/100);
                double personalInjury = ((basicPremium*10)/100);

                double totalCostOfInsurance = basicPremium;

                if (cb1.isSelected()){
                    totalCostOfInsurance = totalCostOfInsurance + homeEmergencyCover;
                    lblInsuranceOptionsChosen1.setText("YES Home Emergency Cover");
                }

                if (cb2.isSelected()){
                    totalCostOfInsurance = totalCostOfInsurance + legalExpenses;
                    lblInsuranceOptionsChosen2.setText("YES Legal Expenses Cover");
                }

                if(cb3.isSelected()){
                    totalCostOfInsurance = totalCostOfInsurance + personalInjury;
                    lblInsuranceOptionsChosen3.setText("Yes Personal Injury Cover");
                }

                lblTotalCostAmount.setText(Double.toString(totalCostOfInsurance));

                secondaryStage.close();

            } catch (Exception notANumber){
                notANumber.getMessage();
            }



        } // end of isEmpty TF if


    } // end of calculate

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Set the stage

        primaryStage = new Stage();
        primaryStage.setTitle("FXInsurance");
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);

        // Create a layout

        HBox hb1 = new HBox();
        hb1.getChildren().add(lblInsuranceDetails);

        HBox hb2 = new HBox();
        hb2.getChildren().addAll(lblHomeValue, lblHomeValueAmount);
        hb2.setSpacing(114);

        VBox vbOptions = new VBox();
        vbOptions.getChildren().addAll(lblInsuranceOptionsChosen1, lblInsuranceOptionsChosen2, lblInsuranceOptionsChosen3);

        HBox hb3 = new HBox();
        hb3.getChildren().addAll(lblInsuranceOptions, vbOptions);
        hb3.setSpacing(20);

        HBox hb4 = new HBox();
        hb4.getChildren().addAll(lblTotalCost, lblTotalCostAmount);
        hb4.setSpacing(126);

        HBox hb5 = new HBox();
        hb5.getChildren().add(btnQuote);
        hb5.setAlignment(Pos.BOTTOM_RIGHT);

        VBox vbAll = new VBox();
        vbAll.getChildren().addAll(hb1, hb2, hb3, hb4, hb5);
        vbAll.setSpacing(15);
        vbAll.setPadding(new Insets(20));


        //Create a scene

        Scene sc = new Scene (vbAll);

        // Add the scene to the stage

        primaryStage.setScene(sc);

        //Show the stage

        primaryStage.show();




    } // end of start


    public static void main(String args[]){
        launch(args);
    }








}