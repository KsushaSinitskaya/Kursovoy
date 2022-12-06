package controllers;

import client.Client;
import client.ClientInstance;
import client.sceneloader.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.*;

public class VipClientsForManagerController {

    @FXML
    private ListView listView;

    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.send("viewordersmanager");
        ArrayList<String> orders = client.receiveResultList();
        Map<String,Integer> clients = new HashMap<>();
        for (String item:orders) {
            String key = item.split(" ")[1] + " " + item.split(" ")[2];
            Integer price = Integer.valueOf(item.split(" ")[5]);
            if(clients.get(key) != null){
                clients.replace(key,clients.get(key) + price);
            }
            else {
                clients.put(key, price);
            }
        }
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : clients.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer str, Integer str1) {
                return (str1).compareTo(str);
            }
        });
        for (Integer str : list) {
            for (Map.Entry<String, Integer> entry : clients.entrySet()) {
                if (entry.getValue().equals(str)) {
                    sortedMap.put(entry.getKey(), str);
                }
            }
        }
        ArrayList<String> vipPeople = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            vipPeople.add(entry.getKey());
        }
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i<vipPeople.size()/2;i++){
            result.add(vipPeople.get(i));
        }
        ObservableList<String> models = FXCollections.observableArrayList(result);
        listView.setItems(models);
    }

    public void goBack() {
        listView.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionmanager", "Меню менеджера");
    }
}
