package com.testninja.pageobjects.examples.spotify.pages;

import com.testninja.pageobjects.examples.spotify.components.playlist.PlayButton;
import com.testninja.pageobjects.examples.spotify.components.playlist.SaveButton;
import com.testninja.pageobjects.examples.spotify.components.playlist.SongRow;
import com.testninja.pageobjects.wrappers.ScriptHelper;
import com.testninja.pageobjects.wrappers.annotations.Page;
import org.apache.xpath.operations.Bool;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.stream.Collectors;

@Page
public class PlaylistPage extends SpotifyBasePage {

    @FindBy(css = "[data-testid=\"action-bar-row\"] > [data-testid='play-button']")
    private PlayButton playButton;

    @FindBy(xpath = ".//*=[@data-testid='action-bar-row']/button[2]")
    private SaveButton saveButton;

    @FindBy(css = "[data-testid='tracklist-row']")
    private List<SongRow> songRows;

    @FindBy(xpath = ".//span/h1")
    private WebElement playlistName;

    public PlaylistPage(ScriptHelper scriptHelper) {
        super(scriptHelper);
    }

    public void waitForPageLoad() {
        interactions.waitHandler.waitFor(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(@NullableDecl WebDriver input) {
                return getSongs().size() > 0;
            }
        });
    }

    public String getPlaylistName() {
        return interactions.getText(playlistName);
    }

    public PlaylistPage play() {
        playButton.play();
        return this;
    }

    public PlaylistPage playAndWait() {
       play();
       interactions.waitHandler.waitFor(new ExpectedCondition<Boolean>() {
           @Override
           public Boolean apply(@NullableDecl WebDriver input) {
               return isSongPlaying();
           }
       });
       return this;
    }

    public boolean isSongPlaying() {
        return playButton.isPlaying();
    }

    public boolean isSongPaused() {
        return playButton.isPaused();
    }

    public PlaylistPage saveToFavourites() {
        saveButton.clickSave();
        return this;
    }

    public boolean isSavedToFavourites() {
        return saveButton.isSaved();
    }

    public SongRow findSongByName(String name) {
        return getSongs()
                .stream()
                .filter(song -> song.getSongName().equals(name))
                .findFirst()
                .get();

    }

    public SongRow findSongByIndex(Integer index) {
        return getSongs().get(index);
    }

    private List<SongRow> getSongs() {
        return songRows
                .stream()
                .map(song -> {
                    song.setScriptHelper(scriptHelper);
                    return  song;
                }).collect(Collectors.toList());
    }
}
