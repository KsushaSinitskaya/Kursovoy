package controllers;

import client.AlertBox;
import client.Client;
import client.ClientInstance;
import client.sceneloader.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class AddOrderController {

    @FXML
    private ChoiceBox<String> makesChoiceBox;
    @FXML
    private ChoiceBox<String> modelsChoiceBox;
    @FXML
    private ChoiceBox<String> yearChoiceBox;
    @FXML
    private ChoiceBox<String> colorChoiceBox;
    @FXML
    private ChoiceBox<String> countryChoiceBox;
    @FXML
    private ChoiceBox<String> insuranceChoiceBox;
    @FXML
    private Label price;

    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.send("getmakes");
        ArrayList<String> makesList = client.receiveResultList();
        ArrayList<String> insuranceList = getInsuranceList();
        ObservableList<String> makes = FXCollections.observableArrayList(makesList);
        ObservableList<String> insurance = FXCollections.observableArrayList(insuranceList);
        ObservableList<String> years = FXCollections.observableArrayList("2016", "2017", "2018", "2019");
        ObservableList<String> colors = FXCollections.observableArrayList("Red", "Black", "White", "Silver",
                "Yellow", "Blue", "Green");
        makesChoiceBox.setItems(makes);
        insuranceChoiceBox.setItems(insurance);
        yearChoiceBox.setItems(years);
        colorChoiceBox.setItems(colors);
    }

    private ArrayList<String> getModels() {
        ClientInstance.INSTANCE.getInstance().send("getmodels " + makesChoiceBox.getValue());
        return ClientInstance.INSTANCE.getInstance().receiveResultList();
    }

    private ArrayList<String> getInsuranceList() {
        ClientInstance.INSTANCE.getInstance().send("getinsurance");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ArrayList<String> insuranceList = new ArrayList<>(0);
        for (String s : list) {
            String[] temp = s.split(" ", 2);
            insuranceList.add(temp[1]);
        }
        return insuranceList;
    }

    public ArrayList<String> getCountryList() {
        ClientInstance.INSTANCE.getInstance().send("getcountry " + makesChoiceBox.getValue());
        return ClientInstance.INSTANCE.getInstance().receiveResultList();
    }

    public void setModelsAndCountry() {
        ArrayList<String> listM = getModels();
        ArrayList<String> listC = getCountryList();
        ObservableList<String> countries = FXCollections.observableArrayList(listC);
        ArrayList<String> modelsList = new ArrayList<>(0);
        for (String s : listM) {
            String[] temp = s.split(" ", 2);
            modelsList.add(temp[1]);
        }
        ObservableList<String> models = FXCollections.observableArrayList(modelsList);
        modelsChoiceBox.setItems(models);
        countryChoiceBox.setItems(countries);
    }

    private int getInsuranceCost() {
        int insuranceCost = 0;
        ClientInstance.INSTANCE.getInstance().send("getinsurance");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        try {
            for (String s : list) {
                String[] temp = s.split(" ", 2);
                if (temp[1].equals(insuranceChoiceBox.getValue())) {
                    insuranceCost = Integer.parseInt(temp[0]);
                }
            }
            return insuranceCost;
        } catch (Exception e) {
            return 0;
        }
    }

    private int getCarCost() {
        int carCost = 0;
        ArrayList<String> list = getModels();
        for (int i = 0; i < list.size(); i++) {
            String[] temp = list.get(i).split(" ", 2);
            if (temp[1].equals(modelsChoiceBox.getValue())) {
                carCost = Integer.parseInt(temp[0]);
            }
        }
        return carCost;
    }

    public void setPrice() {
        price.setText(String.valueOf(getCarCost() + getInsuranceCost()));
    }

    public void addOrder() {
        StringBuilder order = new StringBuilder();
        if (makesChoiceBox.getValue().equals("") || modelsChoiceBox.getValue().equals("")
                || yearChoiceBox.getValue().equals("") || colorChoiceBox.getValue().equals("")
                || insuranceChoiceBox.getValue().equals("") || countryChoiceBox.getValue().equals("")) {
            AlertBox.display("Выберите все опции");
            return;
        }
        order.append("addorderclient ").append(makesChoiceBox.getValue()).append(" ").
                append(yearChoiceBox.getValue()).append(" ").
                append(LoginController.getClient()).append(" ").
                append(price.getText()).append(" ").
                append(colorChoiceBox.getValue()).append(" ").
                append(countryChoiceBox.getValue()).append(" ").
                append(insuranceChoiceBox.getValue()).append(" ").
                append(modelsChoiceBox.getValue());
        ClientInstance.INSTANCE.getInstance().send(order.toString());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertBox.display("Заказ оформлен");
        } else {
            AlertBox.display("Ошибка оформления заказа");
        }
    }

    public void goBack() {
        makesChoiceBox.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionclient", "Меню клиента");
    }
}
