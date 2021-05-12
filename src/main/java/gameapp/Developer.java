package gameapp;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(schema = "gameproject", name = "developer")
public class Developer {

    @Id
    @GeneratedValue
    private int companyId;
    private String developerName;
    private int unitsSold;

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "dev")
    private Set<Game> games = new HashSet<>();


    public Developer(String developerName, int unitsSold, Set<Game> games) {
        this.developerName = developerName;
        this.unitsSold = unitsSold;
        this.games = games;
    }

    public Developer(String developerName, int unitsSold) {
        this.developerName = developerName;
        this.unitsSold = unitsSold;
    }

    public Developer() {
    }


    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public int getunitsSold() {
        return unitsSold;
    }

    public void calculateunitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    public void setunitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    @Override
    public String toString() {

        return "\nCompany ID: " + companyId +
                "\nDeveloper Name: " + developerName +
                "\nUnits Sold: " + unitsSold +
                "\nGames: " + games.stream().map(Game::getName).collect(Collectors.toList()) + "\n";
    }
}