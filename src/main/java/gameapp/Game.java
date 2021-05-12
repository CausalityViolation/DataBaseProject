package gameapp;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(schema = "gameproject", name = "game")
public class Game {


    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "companyid")
    private Developer dev;

    @OneToMany(mappedBy = "game", cascade = CascadeType.PERSIST)
    private Set<Local_release> releases = new HashSet<>();

    public Game() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRelease(Local_release release) {
        releases.add(release);
    }

    public Developer getDev() {
        return dev;
    }

    public void setDev(Developer dev) {
        this.dev = dev;
    }

    public Set<Local_release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Local_release> releases) {
        this.releases = releases;
    }


    @Override
    public String toString() {

        String dev = "\nDeveloper: ";

        if (this.dev != null) {
            dev += this.dev.getDeveloperName();
        } else {
            dev += "\nNo Developer Found/Not Yet Set";
        }
        return "\nGame ID: " + id +
                "\nName: " + name +
                "\nPrice: " + price + dev +
                "\nReleases: " + releases.size()+"\n";
    }
}