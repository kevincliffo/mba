package com.android.mba;

import android.graphics.Bitmap;
import android.media.Image;

public class Fixture {
    private int id;
    private String teamA;
    private String teamB;
    private String gameDate;
    private String gameTime;

    public Fixture(String teamA, String teamB, String gameDate, String gameTime) {
        this.id = id;
        this.teamA = teamA;
        this.teamB = teamB;
        this.gameDate = gameDate;
        this.gameTime = gameTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public void setgameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public int getId() {
        return id;
    }

    public String getTeamA() {
        return teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public String getGameDate() {
        return gameDate;
    }

    public String getGameTime() {
        return gameTime;
    }
}
