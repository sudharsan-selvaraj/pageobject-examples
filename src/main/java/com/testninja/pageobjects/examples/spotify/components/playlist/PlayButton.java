package com.testninja.pageobjects.examples.spotify.components.playlist;

import com.testninja.pageobjects.wrappers.BaseWebComponent;
import com.testninja.pageobjects.wrappers.ScriptHelper;

public class PlayButton extends BaseWebComponent {

    public void play() {
        if (!isPlaying()) {
            interactions.click(this);
        }
    }

    public void pause() {
        if (isPlaying()) {
            interactions.click(this);
        }
    }

    public boolean isPlaying() {
        return checkState("Pause");
    }

    public boolean isPaused() {
        return checkState("Play");
    }

    private boolean checkState(String state) {
        return getAttribute("aria-label").equals(state);
    }
}
