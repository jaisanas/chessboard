package com.example.jais.chessboard;

import android.app.Application;

/**
 * Created by jais on 5/28/2016.
 */
public class ChessApplication extends Application {
    private static ChessApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized ChessApplication getInstance() {
        return mInstance;
    }


}
