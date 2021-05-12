package gameapp;

public class Menu {

    Management manage = new Management();

    public void mainMenu() {

        System.out.println("\n=================================");
        System.out.println("              Menu               ");
        System.out.println("=================================");
        System.out.println("1. Show");
        System.out.println("2. Add");
        System.out.println("3. Edit");
        System.out.println("4. Delete");
        System.out.println("0. Exit");
        System.out.println("=================================");
        System.out.print("Input: ");

        int choice = manage.scanInt();

        switch (choice) {
            case 1:
                showData();
                break;
            case 2:
                addData();
                break;
            case 3:
                editData();
                break;
            case 4:
                deleteData();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Incorrect input");
        }
    }

    private void deleteData() {

        System.out.println("\n=================================");
        System.out.println("              Delete               ");
        System.out.println("=================================");
        System.out.println("1. Delete Game");
        System.out.println("2. Delete Release");
        System.out.println("3. Delete Developer");
        System.out.println("4. Remove Game From Developer");
        System.out.println("5. Remove Game From Release");
        System.out.println("0. Return To Main Menu");
        System.out.println("=================================");
        System.out.print("Input: ");

        int choice = manage.scanInt();

        switch (choice) {
            case 1:
                manage.deleteGame();
                break;
            case 4:
                manage.removeGameFromDev();
                break;
            case 3:
                manage.deleteDev();
                break;
            case 2:
                manage.deleteRelease();
                break;
            case 0:
                return;
            default:
                System.out.println("Incorrect input");
        }
    }

    private void editData() {

        System.out.println("\n=================================");
        System.out.println("              Edit               ");
        System.out.println("=================================");
        System.out.println("1. Edit Game");
        System.out.println("2. Edit Developer");
        System.out.println("3. Edit Release");
        System.out.println("4. Connect Game To Developer");
        System.out.println("5. Connect Game To Release");
        System.out.println("0. Return To Main Menu");
        System.out.println("=================================");
        System.out.print("Input: ");

        int choice = manage.scanInt();

        switch (choice) {
            case 1:
                manage.editGame();
                break;
            case 2:
                manage.editDeveloper();
                break;
            case 3:
                manage.editRelease();
                break;
            case 4:
                manage.connectToDeveloper();
                break;
            case 5:
                manage.connectGameToRelease();
                break;
            case 0:
                return;
            default:
                System.out.println("Incorrect Input");
        }
    }

    private void addData() {

        System.out.println("\n=================================");
        System.out.println("              Add Data               ");
        System.out.println("=================================");
        System.out.println("1. Add New Game");
        System.out.println("2. Add New Developer");
        System.out.println("3. Add New Release");
        System.out.println("0. Return To Main Menu");
        System.out.println("=================================");
        System.out.print("Input: ");

        int choice = manage.scanInt();

        switch (choice) {
            case 1:
                manage.newGame();
                break;
            case 2:
                manage.newDeveloper();
                break;
            case 3:
                manage.newRelease();
            case 0:
                return;
            default:
                System.out.println("Incorrect input");
        }
    }

    private void showData() {

        System.out.println("\n=================================");
        System.out.println("            Show Data               ");
        System.out.println("=================================");
        System.out.println("1. Show All");
        System.out.println("2. Show Games");
        System.out.println("3. Show Developers");
        System.out.println("4. Show Releases");
        System.out.println("5. Show And Calculate Total Units Sold");
        System.out.println("6. Show Sales Percentage");
        System.out.println("0. Return To Main Menu");
        System.out.println("=================================");
        System.out.print("Input: ");

        int choice = manage.scanInt();

        switch (choice) {
            case 1:
                manage.showAll();
                break;
            case 2:
                manage.showGames();
                break;
            case 3:
                manage.showDevelopers();
                break;
            case 4:
                showRelease();
                break;
            case 5:
                manage.calculateUnitsSold();
                break;
            case 6:
                manage.calculatePercentage();
                break;
            case 0:
                return;
            default:
                System.out.println("Incorrect input");
        }
    }

    private void showRelease() {

        System.out.println("\n=================================");
        System.out.println("            Show Releases               ");
        System.out.println("=================================");
        System.out.println("1. Show All ");
        System.out.println("2. Show Releases Based On Game Name");
        System.out.println("3. Show Releases Based On Developer Name");
        System.out.println("0. Return To Main Menu");
        System.out.println("=================================");
        System.out.print("Input: ");

        switch (manage.scanInt()) {
            case 1:
                manage.showReleases();
                break;
            case 2:
                manage.showReleasesByGame();
                break;
            case 3:
                manage.showReleasesByDev();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid Input. Returning To Main Menu");
        }

    }
}