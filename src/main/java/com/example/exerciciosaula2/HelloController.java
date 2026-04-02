package com.example.exerciciosaula2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HelloController implements Initializable {
    @FXML
    private Label relogio;
    @FXML
    private Label comp1;
    @FXML
    private Label comp2;
    @FXML
    private Label comp3;
    @FXML
    public CheckBox checkcomp1;
    @FXML
    public CheckBox checkcomp2;
    @FXML
    public CheckBox checkcomp3;

    Computadores computadores = new Computadores();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        relogioTime();
        listar();
    }

    public void relogioTime(){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(e ->{
            Thread thread = new Thread(e);
            thread.setDaemon(true);
            return thread;
        });

        scheduler.scheduleAtFixedRate(() ->{
        LocalTime data = LocalTime.now();
        String horaFormatada = data.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            Platform.runLater(() ->{
                relogio.setText(horaFormatada);
            });
        },0, 1, TimeUnit.SECONDS);

    }

    @FXML
    public void listar(){
        boolean[] comps = computadores.mostrarComputadores();

        if (!comps[0]){
            comp1.setStyle("-fx-background-color: #FF6347");
        } else {
            comp1.setStyle("-fx-background-color: #008000");
        }
        if (!comps[1]){
            comp2.setStyle("-fx-background-color: #FF6347");
        } else {
            comp2.setStyle("-fx-background-color: #008000");
        }
        if (!comps[2]){
            comp3.setStyle("-fx-background-color: #FF6347");
        } else {
            comp3.setStyle("-fx-background-color: #008000");
        }
    }

    @FXML
    public void ocuparDesocupar(ActionEvent event) {
        boolean[] comps = computadores.mostrarComputadores();

        if (checkcomp1.isSelected()){
            computadores.Computadores(1);
            if (!comps[0]){
                computadores.ocupar();
            } else{
                computadores.desocupar();
            }
        }
        if (checkcomp2.isSelected()){
            computadores.Computadores(2);
            if (!comps[1]){
                computadores.ocupar();
            } else{
                computadores.desocupar();
            }
        }
        if (checkcomp3.isSelected()){
            computadores.Computadores(3);
            if (!comps[2]){
                computadores.ocupar();
            } else{
                computadores.desocupar();
            }
        }
        listar();
    }
}
