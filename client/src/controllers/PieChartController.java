package controllers;

import client.ClientInstance;
import client.sceneloader.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;

public class PieChartController {

    @FXML
    private PieChart pieChart;

    public void initialize() {
        ClientInstance.INSTANCE.getInstance().send("getmakesstatistics");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList(
                new PieChart.Data("Mercedes", Double.parseDouble(list.get(0))),
                new PieChart.Data("Audi", Double.parseDouble(list.get(1))),
                new PieChart.Data("BMW", Double.parseDouble(list.get(2)))
        );
        pieChart.setTitle("Распределение заказов клиентов по маркам");
        pieChart.setData(pieChartList);
    }

    public void goBack() {
        pieChart.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionmanager", "Меню менеджера");
    }
}
