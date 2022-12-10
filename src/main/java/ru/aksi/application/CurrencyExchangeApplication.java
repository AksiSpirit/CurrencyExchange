package ru.aksi.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CurrencyExchangeApplication extends Application {
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CurrencyExchangeApplication.class.getResource("currency-exchange.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Currency Exchange");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static Window getWindow() {
        return scene.getWindow();
    }
}