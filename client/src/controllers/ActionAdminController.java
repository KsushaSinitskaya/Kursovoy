package controllers;

import client.sceneloader.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ActionAdminController {

    @FXML
    private Button addManager;

    public void addManager() {
        addManager.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addmanager", "Регистрация менеджера");
    }
    public void getVips(){
        addManager.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("vipclientsforadmin", "Вип-клиенты");
    }
    public void addSupplier() {
        addManager.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addsupplier", "Регистрация поставщика");
    }

    public void addInsurance() {
        addManager.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addinsurance", "Регистрация страховки");
    }

    public void logOut() {
        addManager.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("login", "Авторизация");
    }
}