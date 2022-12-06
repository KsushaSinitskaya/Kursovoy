package controllers;

import client.sceneloader.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ActionManagerController {
    @FXML
    private Label name;

    public void initialize() {
        name.setText(LoginController.getManager());
    }

    public void addSupplierOrder() {
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addsupplierorder", "Оформление заказа поставщику");
    }

    public void getVips(){
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("vipclients", "Вип-клиенты");
    }

    public void viewSupplierOrders() {
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewsupplierorders", "Просмотр заказов поставщикам");
    }

    public void viewClientsOrders() {
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewordersmanager", "Просмотр заказов клиентов");
    }

    public void statistics() {
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("piechart", "Статистика заказов");
    }

    public void chart(){
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("linechart", "Статистика заказов");
    }

    public void addModel(){
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addmodel", "Добавление новой модели");
    }

    public void logOut() {
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("login", "Авторизация");
    }
}