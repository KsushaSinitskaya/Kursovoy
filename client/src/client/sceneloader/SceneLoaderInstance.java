package client.sceneloader;

public enum SceneLoaderInstance {

    INSTANCE;

    private SceneLoader instance;

    SceneLoaderInstance() {
        instance = new SceneLoader();
    }

    public SceneLoader getInstance() {
        return instance;
    }
}