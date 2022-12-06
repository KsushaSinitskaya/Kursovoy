package controllers;

import client.Client;
import client.ClientInstance;
import client.sceneloader.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class ModelsInSalonController {

    @FXML
    private ListView listView;

    public void initialize() {
        ArrayList<String> modelsList = new ArrayList<>();
        Client client = ClientInstance.INSTANCE.getInstance();
        client.send("getmakes");
        ArrayList<String> makesList = client.receiveResultList();
        for (String item:makesList) {
            ClientInstance.INSTANCE.getInstance().send("getmodels " + item);
            ArrayList<String> temp = ClientInstance.INSTANCE.getInstance().receiveResultList();
            for (String model: temp) {
                modelsList.add(model);
            }
        }
        ObservableList<String> models = FXCollections.observableArrayList(modelsList);
        listView.setItems(models);
    }

    public void goBack() {
        listView.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionclient", "Меню клиента");;
    }
}
