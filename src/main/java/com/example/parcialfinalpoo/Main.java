package com.example.parcialfinalpoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {//00351519 declara la clase main y se extiende la clase Application para usar JavaFx
    @Override//00351519 Sobreescribe el metodo star
    public void start(Stage stage) throws IOException {//00351519 metodo start que se llama cuando se inicia la aplicacion javafx
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pantalla-principal.fxml"));//00351519 carga la pantalla principal de la aplicacion javaFx
        Scene scene = new Scene(fxmlLoader.load(), 800, 650);//00351519 crea un objeto Scene y carga el archivo FXML anterior con tamanio 800x650 pixeles
        stage.setTitle("Parcial Final POO");//00351519 establece el titulo de la ventana como Parcial Final POO
        stage.setScene(scene);//00351519 establece la escena a la ventana
        stage.show();//00351519 muestra la ventana
    }

    public static void main(String[] args) {//00351519 metodo main para entrar a la aplicacion
        launch();//00351519 metodo launch que inicia la aplicacion javafx
    }
}