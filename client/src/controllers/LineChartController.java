package controllers;

import client.ClientInstance;
import client.sceneloader.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class LineChartController {
    @FXML
    private LineChart lineChart;

    public void initialize() {
        ClientInstance.INSTANCE.getInstance().send("getyearstats");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
//        final NumberAxis xAxis=new NumberAxis();
//        xAxis.setLabel("Year");
        XYChart.Series series = new XYChart.Series();
        series.setName("Все модели");
        series.getData().add(new XYChart.Data("2016", Integer.parseInt(list.get(0))));
        series.getData().add(new XYChart.Data("2017", Integer.parseInt(list.get(1))));
        series.getData().add(new XYChart.Data("2018", Integer.parseInt(list.get(2))));
        series.getData().add(new XYChart.Data("2019", Integer.parseInt(list.get(3))));
        lineChart.getXAxis().setLabel("Год");
        lineChart.setTitle("Распределение заказов по годам сборки");
        lineChart.getData().addAll(series);
    }

    public void goBack() {
        lineChart.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionmanager", "Меню менеджера");
    }
}

