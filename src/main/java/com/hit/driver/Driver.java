package com.hit.driver;

import com.hit.controller.CacheUnitController;
import com.hit.controller.Controller;
import com.hit.model.CacheUnitModel;
import com.hit.model.Model;
import com.hit.view.CacheUnitView;
import com.hit.view.View;

public class Driver {
    public Driver() {
    }

    /**
     * Entry Point - start the Client Service And connect to the Service(Server)
     * @param args
     */
    public static void main(String[] args) {
        Model model = new CacheUnitModel();
        View view = new CacheUnitView();
        Controller controller = new CacheUnitController(view, model);
        ((CacheUnitModel) model).addObserver(controller);
        ((CacheUnitView) view).addObserver(controller);
        view.start();
/*

        ///Test GUI
        View view = new CacheUnitView();
        view.start();

        ///Test Connection between the server side and the client side
        *//*CacheUnitClient cli = new CacheUnitClient();
        String x = cli.send("{\"headers\":{\"action\":\"GET\"},\"body\":[{\"id\":111900,\"content\":\"Some Thing 1\"},{\"id\":111011,\"content\":\"Some Thing 2\"},{\"id\":111013,\"content\":\"Some Thing 3\"}]}");
        System.out.println(x);*/
    }
}
