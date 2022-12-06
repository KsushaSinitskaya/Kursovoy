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

public class AddSupplierController {

    @FXML
    private TextField name;
    @FXML
    private TextField country;
    @FXML
    private ChoiceBox<String> make;

    public void initialize() {
        ClientInstance.INSTANCE.getInstance().send("getmakes");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<String> makes = FXCollections.observableArrayList(list);
        make.setItems(makes);
    }

    public void addSupplier() {
        if (name.getText().equals("") || country.getText().equals("") || make.getValue() == null) {
            AlertBox.display("Введите все данные!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send("addsupplier " + name.getText() + " " + country.getText()
                + " " + make.getValue());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertBox.display("Поставщик успешно добавлен");
        } else {
            AlertBox.display("Ошибка добавления поставщика");
        }
    }

    public void goBack() {
        name.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionadmin", "Меню администратора");
    }
}