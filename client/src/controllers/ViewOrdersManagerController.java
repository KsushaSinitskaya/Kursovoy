package controllers;

import client.AlertBox;
import client.ClientInstance;
import client.Order;
import client.sceneloader.SceneLoaderInstance;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.print.attribute.standard.OrientationRequested;
import java.util.ArrayList;

public class ViewOrdersManagerController {

    @FXML
    private TextField search;
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
    private TableColumn<Order, Integer> numberColumn;
    @FXML
    private TableColumn<Order, String> colorColumn;
    @FXML
    private TableColumn<Order, String> countryColumn;
    @FXML
    private TableColumn<Order, String> insuranceColumn;
    @FXML
    private TableColumn<Order, String> nameColumn;
    @FXML
    private TableColumn<Order, String> surnameColumn;
    @FXML
    private TableColumn<Order, Boolean> acceptedColumn;
    @FXML
    private TableColumn<Order, Boolean> completedColumn;

    public void initialize() {
        fillTableView();
        search.textProperty().addListener(( observable, oldValue,  newValue)->
                filterPostavshikList(oldValue, newValue));
    }

    public void filterPostavshikList(String oldValue, String newValue) {
        ObservableList<Order> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (Order order : ordersTable.getItems()) {
                String filterPostavshik = order.getModel();
                if (filterPostavshik.toUpperCase().contains(newValue)) {
                    filteredList.add(order);
                }
            }
            ordersTable.setItems(filteredList);
        }
    }

    private void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("viewordersmanager " + LoginController.getManager());
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Order> orders = FXCollections.observableArrayList();
        String[] orderString;
        for (String s : list) {
            orderString = s.split(" ", 14);
            Order order = new Order();
            order.setOrderNumber(Integer.parseInt(orderString[0]));
            order.setName(orderString[1]);
            order.setSurname(orderString[2]);
            order.setMake(orderString[3]);
            order.setYear(orderString[4]);
            order.setPrice(Integer.parseInt(orderString[5]));
            order.setOrderAccepted(Boolean.parseBoolean(orderString[6]));
            order.setOrderCompleted(Boolean.parseBoolean(orderString[7]));
            order.setColor(orderString[8]);
            order.setCountry(orderString[9]);
            order.setInsurance(orderString[10] + " " + orderString[11] + " " + orderString[12]);
            order.setModel(orderString[13]);
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
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        ordersTable.setItems(orders);
    }

    public void markAccepted() {
        if (ordersTable.getSelectionModel().getSelectedItem() == null) {
            AlertBox.display("Выберите заказ");
        } else {
            ClientInstance.INSTANCE.getInstance().send("markaccepted " + ordersTable.getSelectionModel().
                    getSelectedItem().getOrderNumber());
        }
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            fillTableView();
            AlertBox.display("Заказ помечен как принятый");
        } else {
            AlertBox.display("Ошибка изменения статуса заказа");
        }
    }

    public void markCompleted() {
        if (ordersTable.getSelectionModel().getSelectedItem() == null) {
            AlertBox.display("Выберите заказ");
            return;
        }
        if (!ordersTable.getSelectionModel().getSelectedItem().isOrderAccepted()) {
            AlertBox.display("Сначала примите заказ");
            return;
        } else {
            ClientInstance.INSTANCE.getInstance().send("markcompleted " + ordersTable.getSelectionModel().
                    getSelectedItem().getOrderNumber());
        }
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            fillTableView();
            AlertBox.display("Заказ помечен как выполненный");
        } else {
            AlertBox.display("Ошибка изменения статуса заказа");
        }
    }

    public void deleteOrder() {
        if (ordersTable.getSelectionModel().getSelectedItem() == null) {
            AlertBox.display("Выберите заказ");
        } else {
            ClientInstance.INSTANCE.getInstance().send("removeorder " +
                    ordersTable.getSelectionModel().getSelectedItem().getOrderNumber());
            if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
                fillTableView();
                AlertBox.display("Заказ удалён");
            } else {
                AlertBox.display("Ошибка удаления заказа");
            }
        }
    }

    public void goBack() {
        ordersTable.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("actionmanager", "Меню менеджера");
    }
}
