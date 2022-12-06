package client;

import javafx.application.Application;

public abstract class AbstractApp extends Application implements AppInterface {
    @Override
    public abstract String getTitle();
}
