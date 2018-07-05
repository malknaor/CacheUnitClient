package com.hit.model;

/**
 * This interface part of the MVC Client architecture - Model
 */
public interface Model {
    /**
     * Update the Model Data accordingly
     * @param t
     * @param <T>
     */
    <T> void updateModelData(T t);
}
