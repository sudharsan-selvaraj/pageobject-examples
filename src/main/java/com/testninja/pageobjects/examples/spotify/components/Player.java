package com.testninja.pageobjects.examples.spotify.components;

import com.testninja.pageobjects.examples.spotify.components.playlist.PlayButton;
import com.testninja.pageobjects.wrappers.BaseWebComponent;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class Player extends BaseWebComponent {

    @FindBy(xpath = ".//button[@data-testid='control-button-play' or @data-testid='control-button-pause']")
    private PlayButton playButton;

    public String getSongName() {
        return interactions.getText(findElement(getChildByTestId("nowplaying-track-link")));
    }

    public List<String> getArtists() {
        return findElements(getChildByTestId("nowplaying-artist"))
                .stream()
                .map(interactions::getText)
                .collect(Collectors.toList());
    }

    public boolean isPlaying() {
        return playButton.isPlaying();
    }

    public boolean isPaused() {
        return playButton.isPaused();
    }

    public void play() {
        playButton.play();
    }

    public void pause() {
        playButton.pause();
    }
}
