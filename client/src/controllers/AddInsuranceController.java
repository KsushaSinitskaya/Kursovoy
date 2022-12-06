package controllers;

import client.AlertBox;
import client.ClientInstance;
import client.sceneloader.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddInsuranceController {

    @FXML
    private TextField type;
    @FXML
    private TextField price;

    public void addInsurance() {
        try {
            Integer.valueOf(price.getText());
        } catch (Exception e) {
            AlertBox.display("Стоимость введена некорректно");
            return;
        }
        if (type.getText().equals("") || price.getText().equals("")) {
            AlertBox.display("Введите все данные");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send("addinsurance " + price.getText() + " " + type.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertBox.display("Страховка добавлена успешно");
        } else {
            AlertBox.display("Ошибка добавления страховки");
        }
    }

    public void goBack() {
        type.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionadmin", "Администратор");
    }
}