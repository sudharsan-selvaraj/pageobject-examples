package com.testning.pageobjects.examples.spotify;

import com.testning.pageobjects.wrappers.BaseTest;
import com.testninja.pageobjects.examples.spotify.components.Player;
import com.testninja.pageobjects.examples.spotify.components.playlist.SongRow;
import com.testninja.pageobjects.examples.spotify.pages.PlaylistPage;
import com.testninja.pageobjects.examples.spotify.pages.SpotifyHomePage;
import com.testninja.pageobjects.wrappers.annotations.PageObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class SpotifyPlaylistTest extends BaseTest {

    private static final String playlistUrl, email, password;

    static {
        playlistUrl = "https://open.spotify.com/playlist/69Th2qi7qhSDySe6D6XfCl";
        email = "fawogi9556@zcai55.com"; //Test user credentials 😋
        password = "fawogi9556";
    }

    @PageObject
    SpotifyHomePage spotifyHomePage;

    @PageObject
    PlaylistPage playlistPage;

    @BeforeClass
    public void loginToSpotify() {
        driver.get("https://open.spotify.com");
        spotifyHomePage.loginWithCredentials(email, password);
        driver.get(playlistUrl);
        playlistPage.waitForPageLoad();
        playlistPage.findSongByIndex(0).playAndPause();
    }

    @BeforeMethod
    public void resetPage() {
        driver.get(playlistUrl);
        playlistPage.waitForPageLoad();
    }

    @Test
    public void PlayListPageSongDetailsTest() {

        assertEquals(playlistPage.getPlaylistName(), "Best of The Chainsmokers");
        assertFalse(playlistPage.isSongPlaying());

        SongRow firstSong = playlistPage.findSongByIndex(0);

        assertEquals(firstSong.getSongName(), "Closer");
        assertEquals(firstSong.getArtists(), Arrays.asList("The Chainsmokers", "Halsey"));
        assertEquals(firstSong.getAlbum(), "Closer");
        assertEquals(firstSong.getDateAdded(), "Jan 9, 2017");
        assertEquals(firstSong.getDuration(), "4:04");
    }

    @Test
    public void PlayListPageSongPlayTest() {
        Player player = spotifyHomePage.getPlayer();
        /*
        * Play the song with global playlist play button
        * */
        playlistPage.playAndWait();
        assertEquals(player.getSongName(), playlistPage.findSongByIndex(0).getSongName());
        assertTrue(playlistPage.isSongPlaying());

        /*
         * Pause the song using pause button in track player
         * */
        player.pause();
        interactions.waitHandler.sleep(1000);
        assertTrue(player.isPaused());
        assertFalse(playlistPage.isSongPlaying());

        /*
         * Play the song using play button near the track list.
         * */
        playlistPage.findSongByIndex(0).play();
        assertTrue(player.isPlaying());
        assertTrue(playlistPage.isSongPlaying());
    }
}
