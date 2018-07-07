package com.hit.model;

import java.util.Observable;

/**
 *
 */
public class CacheUnitModel extends Observable implements Model {
    private CacheUnitClient cacheUnitClient;

    /**
     * Ctor -
     */
    public CacheUnitModel() {
        cacheUnitClient = new CacheUnitClient();
    }

    @Override
    public <T> void updateModelData(T t) {
        String serverResponse = cacheUnitClient.send(t.toString());
        setChanged();
        notifyObservers(serverResponse);
    }
}
