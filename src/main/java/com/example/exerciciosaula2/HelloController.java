package com.example.exerciciosaula2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    @FXML
    public Button teste;

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
        List<Computador> comps = computadores.mostrarComputadores();
        Label[] paineis = {comp1, comp2, comp3};

        for (int i = 0; i < 3; i++) {
            if (!comps.get(i).isEmUso()) {
                paineis[i].setStyle("-fx-background-color: #FF6347;");
            } else {
                paineis[i].setStyle("-fx-background-color: #008000;");
            }
        }
    }

    @FXML
    public void ocuparDesocupar(ActionEvent event) {
        int indicie = -1;
        if (checkcomp1.isSelected()){
            indicie = 0;
        } else if (checkcomp2.isSelected()) {
            indicie = 1;
        } else if (checkcomp3.isSelected()){
            indicie = 2;
        } else if (indicie == -1) {
            System.out.println("selecione 1");
            return;
        }

        computadores.setComputadorSelecionado(indicie);
        Computador comp = computadores.mostrarComputadorSelecionadoObjeto();

        if (comp == null || comp.getIp() == null || comp.getIp().isEmpty()){
            return;
        }
        ComandoRemoto.bloquearTela(comp.getIp());
        computadores.ocupar();

        listar();
    }
}
