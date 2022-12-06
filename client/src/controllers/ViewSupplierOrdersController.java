package controllers;

import client.ClientInstance;
import client.Order;
import client.sceneloader.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ViewSupplierOrdersController {

    @FXML
    private TableView<Order> ordersTableView;
    @FXML
    private TableColumn<Order, Integer> idColumn;
    @FXML
    private TableColumn<Order, String> supplierColumn;
    @FXML
    private TableColumn<Order, String> makeColumn;
    @FXML
    private TableColumn<Order, String> modelColumn;
    @FXML
    private TableColumn<Order, String> colorColumn;
    @FXML
    private TableColumn<Order, Integer> quantityColumn;

    public void initialize() {
        ClientInstance.INSTANCE.getInstance().send("viewsupplierorders");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Order> orders = FXCollections.observableArrayList();
        String[] orderString;
        for (String s : list) {
            orderString = s.split(" ", 6);
            Order order = new Order();
            order.setOrderNumber(Integer.parseInt(orderString[0]));
            order.setSupplier(orderString[1]);
            order.setMake(orderString[2]);
            order.setColor(orderString[3]);
            order.setQuantity(Integer.parseInt(orderString[4]));
            order.setModel(orderString[5]);
            orders.add(order);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ordersTableView.setItems(orders);
    }

    public void goBack() {
        ordersTableView.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionmanager", "Меню менеджера");
    }
}
