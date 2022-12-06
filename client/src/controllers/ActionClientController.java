package controllers;

import client.sceneloader.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ActionClientController {

    @FXML
    private Button add;
    @FXML
    private Label name;

    public void statistics() {
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("piechartForClient", "Популярные модели авто");
    }

    public void models(){
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("modelsinsalon", "Модели в наличии");
    }

    public void newcars(){
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("newcars", "Новые модели авто");
    }

    public void initialize() {
        name.setText(LoginController.getClient());
    }

    public void addOrder() {
        add.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addorder", "Оформление заказа");
    }

    public void viewOrders() {
        add.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewordersclient", "Просмотр заказов");
    }

    public void logOut() {
        add.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("login", "Авторизация");
    }
}