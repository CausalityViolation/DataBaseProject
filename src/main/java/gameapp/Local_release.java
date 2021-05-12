package gameapp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "gameproject", name = "local_release")

public class Local_release {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int releaseID;

    @Column(name = "release_date")
    private Date release;
    private String country;

    @Column(name = "units_sold")
    private int unitsSold;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "gameID")
    Game game;


    public Local_release(Date release, String country, int unitsSold) {
        this.release = release;
        this.country = country;
        this.unitsSold = unitsSold;
    }

    public Local_release() {

    }

    public void Game(Game spel) {
        game = spel;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public String getCountry() {
        return country;
    }

    public int getReleaseID() {
        return releaseID;
    }

    public void setReleaseID(int releaseID) {
        this.releaseID = releaseID;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {

        return "<Release Information>" +
                "\nRelease ID: " + releaseID +
                "\nRelease Date: " + release +
                "\nCountry: " + country +
                "\nUnits Sold: " + unitsSold;
    }
}