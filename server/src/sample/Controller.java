package sample;

import db.DB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import server.ServerInstance;

public class Controller {

    @FXML
    private TextField db;
    @FXML
    private TextField login;
    @FXML
    private TextField psswrd;
    @FXML
    private TextField prt;
    @FXML
    private Button srvOn;
    @FXML
    private Button srvOff;


    public void connectDB() {
        DB.connect(db.getText(), login.getText(), psswrd.getText(), prt.getText());
    }

    public void startServer() {
        new Thread(ServerInstance.INSTANCE.getInstance()).start();
        srvOn.setVisible(false);
        srvOff.setVisible(true);
    }

    public void stopServer() {
        ServerInstance.INSTANCE.getInstance().stop();
        srvOn.setVisible(true);
        srvOff.setVisible(false);
    }
}