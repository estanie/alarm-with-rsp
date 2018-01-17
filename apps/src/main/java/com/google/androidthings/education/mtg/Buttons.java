package com.google.androidthings.education.mtg;

import android.util.Log;
import android.view.KeyEvent;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.contrib.driver.button.ButtonInputDriver;
import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

/**
 * Created by estanie on 2018-01-18.
 */

public class Buttons {
    public static final String TAG = Buttons.class.getSimpleName();
    public static final String A = "GPIO6_IO14";
    public static final String B = "GPIO6_IO15";
    public static final String C = "GPIO2_IO05";

    ButtonInputDriver aButtonInput;
    ButtonInputDriver bButtonInput;
    ButtonInputDriver cButtonInput;

    public boolean open() {
        try {
            aButtonInput = new ButtonInputDriver(A, Button.LogicState.PRESSED_WHEN_LOW, KeyEvent.KEYCODE_A);
            aButtonInput.register();
            bButtonInput = new ButtonInputDriver(B, Button.LogicState.PRESSED_WHEN_LOW, KeyEvent.KEYCODE_B);
            bButtonInput.register();
            cButtonInput = new ButtonInputDriver(C, Button.LogicState.PRESSED_WHEN_LOW, KeyEvent.KEYCODE_C);
            cButtonInput.register();

            Log.d(TAG, "Initialized GPIO Button that generates a keypress with KEYCODE_A");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error on Button", e);
        }
        return false;
    }

    public void close() {
        if (aButtonInput != null) {
            try {
                aButtonInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (bButtonInput != null) {
            try {
                bButtonInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (cButtonInput != null) {
            try {
                cButtonInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        aButtonInput = null;
        bButtonInput = null;
        cButtonInput = null;
    }
}