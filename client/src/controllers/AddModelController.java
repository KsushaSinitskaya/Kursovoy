package controllers;

import client.AlertBox;
import client.ClientInstance;
import client.sceneloader.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AddModelController {
    @FXML
    public TextField price;
    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<String> make;

    public void initialize() {
        ClientInstance.INSTANCE.getInstance().send("getmakes");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<String> makes = FXCollections.observableArrayList(list);
        make.setItems(makes);
    }

    public void addModel() {
        try {
            Integer.valueOf(price.getText());
        } catch (Exception e) {
            AlertBox.display("Стоимость введена некорректно");
            return;
        }
        if (name.getText().equals("") || price.getText().equals("") || make.getValue() == null) {
            AlertBox.display("Введите все данные!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send("addmodel" + " " + make.getValue() + " " + name.getText() + " " + price.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertBox.display("Новая модель зарегистрирована");
        } else {
            AlertBox.display("Ошибка регистрации");
        }
    }

    public void goBack() {
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionmanager", "Меню менеджера");
    }
}
