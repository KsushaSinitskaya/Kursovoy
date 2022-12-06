package controllers;

import client.AlertBox;
import client.ClientInstance;
import client.Order;
import client.sceneloader.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ViewOrdersClientController {

    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> makeColumn;
    @FXML
    private TableColumn<Order, String> modelColumn;
    @FXML
    private TableColumn<Order, String> yearColumn;
    @FXML
    private TableColumn<Order, Integer> priceColumn;
    @FXML
    private TableColumn<Order, String> colorColumn;
    @FXML
    private TableColumn<Order, String> countryColumn;
    @FXML
    private TableColumn<Order, String> insuranceColumn;
    @FXML
    private TableColumn<Order, Boolean> acceptedColumn;
    @FXML
    private TableColumn<Order, Boolean> completedColumn;

    public void initialize() {
        fillTableView();
    }

    public void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("vieworderclient " + LoginController.getClient());
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Order> orders = FXCollections.observableArrayList();
        String[] orderString;
        for (String s : list) {
            orderString = s.split(" ", 11);
            Order order = new Order();
            order.setMake(orderString[0]);
            order.setYear(orderString[1]);
            order.setPrice(Integer.parseInt(orderString[2]));
            order.setOrderAccepted(Boolean.parseBoolean(orderString[3]));
            order.setOrderCompleted(Boolean.parseBoolean(orderString[4]));
            order.setColor(orderString[5]);
            order.setCountry(orderString[6]);
            order.setInsurance(orderString[7] + " " + orderString[8] + " " + orderString[9]);
            order.setModel(orderString[10]);
            orders.add(order);
        }
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        insuranceColumn.setCellValueFactory(new PropertyValueFactory<>("insurance"));
        acceptedColumn.setCellValueFactory(new PropertyValueFactory<>("orderAccepted"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<>("orderCompleted"));
        ordersTable.setItems(orders);
    }

    public void saveToFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранение заказов в файл");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
                for (Order order : ordersTable.getItems()) {
                    outWriter.write(order.toString());
                    outWriter.newLine();
                }
                outWriter.close();
            } catch (IOException e) {
                AlertBox.display("Ошибка записи");
            }
        }
    }

    public void goBack() {
        ordersTable.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionclient", "Меню клиента");
    }
}
