package com.hit.controller;

import com.hit.model.Model;
import com.hit.view.View;

import java.util.Observable;
import java.util.Observer;

/**
 *
 */
public class CacheUnitController implements Controller, Observer {
    private View viewComponent;
    private Model modelComponent;

    /**
     * Ctor
     * @param view
     * @param model
     */
    public CacheUnitController(View view, Model model) {
        this.viewComponent = view;
        this.modelComponent = model;
    }

    /**
     * Update The Relevant View|Model instance depends on the Observer type
     * @param o - ViewImpl instance OR ModelImpl Instance
     * @param arg - Request|Response
     */
    @Override
    public void update(Observable o, Object arg) {
        checkAndUpdateModel(o, arg);
        checkAndUpdateView(o, arg);
    }

    /**
     * Update the Model Component if the observer is ViewImpl
     * @param o
     * @param arg - Client Request
     */
    private void checkAndUpdateModel(Observable o, Object arg) {
        if (o instanceof View) {
            modelComponent.updateModelData(arg);
        }
    }

    /**
     * OR Update the Model Component if the observer is ViewImpl
     * @param o -
     * @param arg - Service Response
     */
    private void checkAndUpdateView(Observable o, Object arg) {
        if (o instanceof Model){
            viewComponent.updateUIData(arg);
        }
    }
}
