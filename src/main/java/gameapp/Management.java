package gameapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Management {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    Scanner input = new Scanner(System.in);

    public void showAll() {

        showGames();
        showDevelopers();

    }

    public void showGames() {

        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT g FROM Game g");

        @SuppressWarnings("unchecked")
        List<Game> content = query.getResultList();

        System.out.println("<Displaying all games>");

        for (Game g : content) {
            System.out.println(g);
        }
        System.out.println("<End of list>\n");
    }

    public void showDevelopers() {

        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT d FROM Developer d");

        @SuppressWarnings("unchecked")
        List<Developer> content = query.getResultList();

        System.out.println("<Displaying all developers>");

        for (Developer d : content) {
            System.out.println(d);
        }
        System.out.println("<End of list>\n");

    }

    public void newGame() {

        EntityManager em = emf.createEntityManager();

        Game game = new Game();

        em.getTransaction().begin();

        System.out.print("Name of game: ");
        game.setName(input.nextLine());

        System.out.print("Price of game: ");
        game.setPrice(scanInt());

        em.persist(game);
        em.getTransaction().commit();

    }


    public void newDeveloper() {

        EntityManager em = emf.createEntityManager();

        System.out.print("Developer: ");
        String name = input.nextLine();

        Developer dev = new Developer(name, 0);
        em.getTransaction().begin();
        em.persist(dev);
        em.getTransaction().commit();

    }


    public void editGame() {

        EntityManager em = emf.createEntityManager();

        showGames();

        System.out.print("Enter ID: ");
        int id = scanInt();

        Game game = em.find(Game.class, id);
        System.out.println(game);
        System.out.println("=================================");
        System.out.println("\n<Edit Options>");
        System.out.println("1. Name");
        System.out.println("2. Price");
        System.out.println("0. Return to main menu");
        System.out.println("=================================");
        System.out.print("Input: ");

        int choice = scanInt();

        if (choice == 1) {
            System.out.print("\nEnter new name: ");
            String name = input.nextLine();
            game.setName(name);

        } else if (choice == 2) {
            System.out.print("Enter new price: ");
            int price = scanInt();
            game.setPrice(price);

        } else {
            return;
        }
        em.getTransaction().begin();
        em.persist(game);
        em.getTransaction().commit();

    }

    public void editDeveloper() {

        EntityManager em = emf.createEntityManager();

        showDevelopers();

        System.out.println("Enter ID Of Developer To Edit: ");
        int id = scanInt();

        Developer dev = em.find(Developer.class, id);
        System.out.println(dev);

        System.out.println("=================================");
        System.out.println("\n<Edit Options>");
        System.out.println("1. Company name");
        System.out.println("0. Return to main");
        System.out.println("=================================");
        System.out.print("Input: ");

        int choice = scanInt();

        if (choice == 1) {

            System.out.print("New name: ");
            String name = input.nextLine();
            dev.setDeveloperName(name);

        } else {
            return;
        }
        em.getTransaction().begin();
        em.persist(dev);
        em.getTransaction().commit();
    }

    public void editRelease() {

        EntityManager em = emf.createEntityManager();

        showReleases();

        System.out.print("Enter ID Of Release To Edit: ");
        int id = scanInt();

        Local_release lr = em.find(Local_release.class, id);

        System.out.println("=================================");
        System.out.println("\n<Edit Options>");
        System.out.println("1. Country");
        System.out.println("2. Release Date");
        System.out.println("3. Units Sold");
        System.out.println("0. Return To Main Menu");
        System.out.println("=================================");
        System.out.print("Input: ");

        int choice = scanInt();

        if (choice == 1) {

            System.out.print("New Country: ");
            String name = input.nextLine();
            lr.setCountry(name);

            System.out.println("<Country successfully updated to " + name + ">");

        } else if (choice == 2) {

            System.out.println("<Input Release Date>");
            System.out.print("Year of release: ");
            int year = scanInt();

            System.out.print("Month of release: ");
            int month = scanInt();

            System.out.print("Day of release: ");
            int day = scanInt();

            Date date = Date.valueOf(LocalDate.of(year, month, day));
            lr.setRelease(date);

            System.out.println("<Date successfully updated to " + date + ">");


        } else if (choice == 3) {

            System.out.print("Input New Value For Units Sold: ");
            int unitsSold = scanInt();
            lr.setUnitsSold(unitsSold);

            System.out.println("<Units Sold Successfully Updated To " + unitsSold + ">");

        } else {
            return;
        }
        em.getTransaction().begin();
        em.persist(lr);
        em.getTransaction().commit();


    }

    public void connectToDeveloper() {

        EntityManager em = emf.createEntityManager();

        showGames();

        System.out.print("\nID of game: ");
        int game = scanInt();

        showDevelopers();

        System.out.print("\nID of developer: ");
        int id = scanInt();

        Game g1 = em.find(Game.class, game);

        if (g1 == null) {
            System.out.println("Developer or game not found. Returning to main");
            return;
        }

        Developer dev = em.find(Developer.class, id);
        em.getTransaction().begin();
        g1.setDev(dev);
        dev.getGames().add(g1);
        em.getTransaction().commit();

    }

    public void connectGameToRelease() {

        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();

        showGames();

        System.out.print("\nID of game: ");
        int gameID = scanInt();

        showReleases();

        System.out.print("\nID of Release: ");
        int releaseID = scanInt();

        Game game = em2.find(Game.class, gameID);

        Local_release lr = em2.find(Local_release.class, releaseID);

        lr.setGame(game);

        em2.persist(lr);
        em2.getTransaction().commit();

        System.out.println("<Release Date Successfully Added To " + game.getName() + ">");

    }


    public void deleteGame() {

        EntityManager em = emf.createEntityManager();

        showGames();

        System.out.println("Input ID Of Game To Delete: ");
        int id = scanInt();
        Game game = em.find(Game.class, id);

        game.setDev(null);

        em.getTransaction().begin();
        em.remove(game);
        em.getTransaction().commit();

    }

    public void deleteRelease() {

        EntityManager em = emf.createEntityManager();

        showReleases();

        System.out.print("Enter ID Of The Release You Would Like To Delete: ");
        int gameID = scanInt();

        Local_release lr = em.find(Local_release.class, gameID);

        lr.setGame(null);

        em.getTransaction().begin();
        em.remove(lr);
        em.getTransaction().commit();

        System.out.println("<Release successfully removed>");

    }

    public void deleteDev() {

        EntityManager em = emf.createEntityManager();

        showDevelopers();

        System.out.print("Enter ID Of The Developer You Would Like To Delete: ");
        int devID = scanInt();

        Developer dev = em.find(Developer.class, devID);

        for (Game game : dev.getGames()) {
            game.setDev(null);
        }

        em.getTransaction().begin();
        em.remove(dev);
        em.getTransaction().commit();

        System.out.println("<Release successfully removed>");

    }

    public void removeGameFromDev() {

        EntityManager em = emf.createEntityManager();

        showDevelopers();
        System.out.print("Enter Developer ID: ");
        int devID = scanInt();

        Developer dev = em.find(Developer.class, devID);

        System.out.println(dev.getGames());
        System.out.print("Enter Game To Remove From Developer: ");

        int gameID = scanInt();

        Game game = em.find(Game.class, gameID);

        game.setDev(null);

        em.getTransaction().begin();
        em.persist(game);
        em.getTransaction().commit();

        System.out.println("<" + game.getName() + "> successfully disconnected from " + dev.getDeveloperName() + ">");

    }


    public void newRelease() {

        EntityManager em = emf.createEntityManager();

        System.out.println("<Input Release Date>");
        System.out.print("Year of release: ");
        int year = scanInt();

        System.out.print("Month of release: ");
        int month = scanInt();

        System.out.print("Day of release: ");
        int day = scanInt();

        Date date = Date.valueOf(LocalDate.of(year, month, day));
        System.out.print("Country: ");
        String country = input.nextLine();

        System.out.print("Units sold: ");
        int sold = scanInt();

        Local_release release = new Local_release(date, country, sold);

        em.getTransaction().begin();
        em.persist(release);
        em.getTransaction().commit();

        System.out.println("<Release Successfully Added!>");


    }

    public void showReleases() {

        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT l FROM Local_release l");

        @SuppressWarnings("unchecked")
        List<Local_release> content = query.getResultList();

        System.out.println("<Displaying All Releases>\n");
        for (Local_release l : content) {
            System.out.println(l + "\n");
        }
        System.out.println("<End of List>\n");
        em.close();
    }

    public void showReleasesByGame() {

        EntityManager em = emf.createEntityManager();

        System.out.print("Input Game Name: ");
        String name = input.nextLine();

        Query query = em.createQuery("SELECT l FROM Local_release l WHERE l.game.name =:name");
        query.setParameter("name", name);

        @SuppressWarnings("unchecked")
        List<Local_release> content = query.getResultList();

        System.out.println("\n<Displaying All Releases For " + name + ">");
        for (Local_release l : content) {
            System.out.println(l + "\n");
        }
        System.out.println("<End of List>\n");
        em.close();

    }

    public void showReleasesByDev() {

        EntityManager em = emf.createEntityManager();

        System.out.print("Input Developer Name: ");
        String name = input.nextLine();

        Query query = em.createQuery("SELECT l FROM Local_release l WHERE l.game.dev.developerName =:name");
        query.setParameter("name", name);

        @SuppressWarnings("unchecked")
        List<Local_release> content = query.getResultList();

        System.out.println("\n<Displaying All Releases For Developer " + name + ">");
        for (Local_release l : content) {
            System.out.println(l + "\n");
        }
        System.out.println("<End of List>\n");
        em.close();


    }

    public void calculateUnitsSold() {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        showDevelopers();

        System.out.print("Enter ID Of The Developer To Calculate Total Amounts Sold: ");
        int devID = scanInt();

        Developer dev = em.find(Developer.class, devID);

        int totalUnitsSold = 0;

        for (Game game : dev.getGames()) {

            List<Integer> profit = game.getReleases().stream().map(Local_release::getUnitsSold).collect(Collectors.toList());

            for (Integer index : profit) {
                totalUnitsSold += index;
            }
        }

        System.out.println("<Displaying Developer " + dev.getDeveloperName() + " Total Units Sold>\n");
        System.out.println(totalUnitsSold);

        dev.setunitsSold(totalUnitsSold);
        em.persist(dev);
        em.getTransaction().commit();

    }

    public void calculatePercentage() {

        EntityManager em = emf.createEntityManager();

        System.out.print("Input Country To Display Statistics About: ");
        String country = input.nextLine();

        em.getTransaction().begin();

        Query query = em.createQuery("SELECT lr FROM Local_release lr WHERE lr.country =:country");
        query.setParameter("country", country);

        @SuppressWarnings("unchecked")
        List<Local_release> countries = query.getResultList();

        double unitsSold = 0;

        for (Local_release c : countries) {

            unitsSold += c.getUnitsSold();

        }

        Query query2 = em.createQuery("SELECT lr FROM Local_release lr");

        @SuppressWarnings("unchecked")
        List<Local_release> total = query2.getResultList();

        double totalunitsSold = 0;

        for (Local_release d : total) {

            totalunitsSold += d.getUnitsSold();

        }

        var result = (unitsSold / totalunitsSold) * 100;

        System.out.println("<Total Number Of Games Sold In " + country + ">");
        System.out.println(String.format("%.1f", result) + "% Of Total World Sales");
    }

    public int scanInt() {
        int input;
        while (true) {
            try {
                input = this.input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please input numerical data");
                this.input.nextLine();
            }
        }
        this.input.nextLine();
        return input;
    }

}