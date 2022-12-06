package controllers;

import client.sceneloader.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class NewCarsController {

    @FXML
    private ListView listView;

    public void initialize() {
        ObservableList<String> cars = FXCollections.observableArrayList(
                "Audi E-tron I кроссовер", "BMW M8 I (F91/F92/F93) кабриолет",
                "Kia Mohave II рестайлинг внедорожник", "Mercedes-Benz AMG GT С190 рестайлинг купе",
                "Mercedes-Benz AMG GT С190 рестайлинг хэтчбек","Infiniti Q50 I-рестайлинг седан",
                "Porsche 911 Carrera 992 купе","Rolls-Royce Ghost II поколение седан"
        );
        listView.setItems(cars);
    }

    public void goBack() {
        listView.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionclient", "Меню клиента");;
    }
}
