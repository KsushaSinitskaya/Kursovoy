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

public class AddSupplierOrderController {

    @FXML
    private ChoiceBox<String> makeChoiceBox;
    @FXML
    private ChoiceBox<String> modelChoiceBox;
    @FXML
    private ChoiceBox<String> colorChoiceBox;
    @FXML
    private ChoiceBox<String> supplierChoiceBox;
    @FXML
    private TextField quantity;

    public void initialize() {
        ClientInstance.INSTANCE.getInstance().send("getmakes");
        ArrayList<String> makesList = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<String> makes = FXCollections.observableArrayList(makesList);
        ObservableList<String> colors = FXCollections.observableArrayList("Red", "Black", "White", "Silver",
                "Yellow", "Blue", "Green");
        makeChoiceBox.setItems(makes);
        colorChoiceBox.setItems(colors);
    }

    public void setModelsAndSupplier() {
        ObservableList<String> models = FXCollections.observableArrayList(getModels());
        ObservableList<String> suppliers = FXCollections.observableArrayList(getSupplier());
        modelChoiceBox.setItems(models);
        supplierChoiceBox.setItems(suppliers);
    }

    public ArrayList<String> getModels() {
        ClientInstance.INSTANCE.getInstance().send("getmodels " + makeChoiceBox.getValue());
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ArrayList<String> modelsList = new ArrayList<>(list.size());
        for (String s : list) {
            String[] temp = s.split(" ", 2);
            modelsList.add(temp[1]);
        }
        return modelsList;
    }

    public ArrayList<String> getSupplier() {
        ArrayList<String> list;
        ClientInstance.INSTANCE.getInstance().send("getsupplier " + makeChoiceBox.getValue());
        list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        return list;
    }

    public void addOrder() {
        ClientInstance.INSTANCE.getInstance().send("addsupplierorder " + makeChoiceBox.getValue() + " "
                + colorChoiceBox.getValue() + " " + supplierChoiceBox.getValue() + " " + quantity.getText()
                + " " + modelChoiceBox.getValue());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertBox.display("Заказ оформлен успешно");
        } else {
            AlertBox.display("Ошибка оформления заказа");
        }
    }

    public void goBack() {
        makeChoiceBox.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionmanager", "Менеджер");
    }
}
