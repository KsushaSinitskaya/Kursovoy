package controllers;

import client.AlertBox;
import client.ClientInstance;
import client.sceneloader.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterClientController {

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField login;
    @FXML
    private TextField password;

    public void register() {
        if (name.getText().equals("") || surname.getText().equals("") || login.getText().equals("")
                || password.getText().equals("")) {
            AlertBox.display("Введите все данные!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addclient " + name.getText() + " " + surname.getText() + " " + login.getText() + " " + password.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertBox.display("Регистрация успешна");
            name.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("login", "Авторизация");
        } else {
            AlertBox.display("Ошибка регистрации");
        }
    }

    public void goBack() {
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("login", "Авторизация");
    }
}
