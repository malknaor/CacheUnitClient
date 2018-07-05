package com.hit.view;

/**
 * This interface part of the MVC Client architecture - View
 */
public interface View {
    /**
     * Start the view component
     */
    void start();

    /**
     * Update the UI Data accordingly
     * @param t
     * @param <T>
     */
    <T> void updateUIData(T t);
}
