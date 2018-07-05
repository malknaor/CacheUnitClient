package com.hit.controller;

import java.util.Observable;
import java.util.Observer;

/**
 * This interface part of the MVC Client architecture - Controller
 * The Controller decouples View and Model enable to change the view Component without
 * changing the Model component
 */
public interface Controller extends Observer {
    /**
     * Update The Relevant object
     * @param o
     * @param arg - Request|Response
     */
    @Override
    void update(Observable o, Object arg);
}
