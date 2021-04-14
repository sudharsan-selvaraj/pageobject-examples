package com.testninja.pageobjects.examples.spotify.components.playlist;

import com.testninja.pageobjects.wrappers.BaseWebComponent;
import com.testninja.pageobjects.wrappers.ScriptHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SongRow extends BaseWebComponent {

    public String getRowNumber() {
        return interactions.getText(getColumn(1));
    }

    public String getSongName() {
        return interactions.getText(getColumn(2).findElement(By.cssSelector("[as=\"div\"]")));
    }

    public List<String> getArtists() {
        return getColumn(2)
                .findElements(By.xpath(".//span/a"))
                .stream()
                .map(interactions::getText)
                .collect(Collectors.toList());
    }

    public String getAlbum() {
        return interactions.getText(getColumn(3));
    }

    public String getDateAdded() {
        return interactions.getText(getColumn(4));
    }

    public String getDuration() {
        return interactions.getText(getColumn(5));
    }

    public void playAndPause() {
        play();
        play();
    }

    public void play() {
        WebElement playIcon = getColumn(1);
        interactions.mouseHover(playIcon).click(playIcon);
    }

    private WebElement getColumn(Integer index) {
        return findElement(By.xpath(".//div[" + index + "]"));
    }
}
