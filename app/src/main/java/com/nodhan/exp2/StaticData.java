package com.nodhan.exp2;

import android.app.Application;

/**
 * Created by Nodhan on 03-08-2016.
 */
public class StaticData extends Application {

    private int count;

    /**
     * Incrementing count
     */
    void incCount() {
        count++;
    }

    /**
     * Getting the count
     *
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

}
