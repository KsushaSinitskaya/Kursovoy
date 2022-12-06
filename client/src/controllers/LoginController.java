package controllers;

import client.AlertBox;
import client.Client;
import client.ClientInstance;
import client.sceneloader.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField psswrd;
    @FXML
    private TextField usr;

    private static String clientName;
    private static String managerName;

    public void sendAcc() {

        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("login " + usr.getText() + " " + psswrd.getText());
        String resultString = ClientInstance.INSTANCE.getInstance().receiveResultString();
        if (resultString.equals("admin")) {
            psswrd.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionadmin", "Меню администратора");
        } else if (resultString.equals("manager")) {
            psswrd.getScene().getWindow().hide();
            client.send("getmanagerdata " + usr.getText() + " " + psswrd.getText());
            managerName = client.receiveResultString();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionmanager", "Меню менеджера");
        } else if (resultString.equals("client")) {
            psswrd.getScene().getWindow().hide();
            client.send("getclientdata " + usr.getText() + " " + psswrd.getText());
            clientName = client.receiveResultString();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionclient", "Меню клиента");
        } else {
            AlertBox.display("Неверный ввод имени или пароля");
        }
    }

    public void registerClient() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        usr.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("registerclient", "Регистрация клиента");
    }

    public static String getClient() {
        return clientName;
    }

    public static String getManager() {
        return managerName;
    }
}