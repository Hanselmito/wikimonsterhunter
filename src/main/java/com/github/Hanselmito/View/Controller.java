package com.github.Hanselmito.View;

import com.github.Hanselmito.App;

public abstract class Controller {
    App app;
    public void setApp(App app){
        this.app=app;
    }

    public abstract void onOpen(Object input) throws Exception;
    public abstract void onClose(Object output);
}
