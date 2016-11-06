package com.Elixer.net;

import com.Elixer.net.Util.Input;

import java.util.ArrayList;

/**
 * Created by aweso on 10/31/2016.
 */
public class DummyGame {

    private static ElixerGame game;

    public static void main(String[] args) {
        game = new ElixerGame("Test") {
            @Override
            public void onInit() {
                Input.cursorLock(true);
            }

            @Override
            public void onEnd() {

            }
        };

        game.start();
    }
}
