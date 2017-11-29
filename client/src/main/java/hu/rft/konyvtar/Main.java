package hu.rft.konyvtar;

import java.io.IOException;

import hu.rft.adminController.AdminAddNewBookController;
import hu.rft.adminController.AdminEditEventController;
import hu.rft.adminController.AdminEditUserController;
import hu.rft.adminController.AdminHomePageController;
import hu.rft.adminController.AdminRootLayoutController;
import hu.rft.adminController.BorrowsOverviewController;
import hu.rft.controller.EventListController;
import hu.rft.controller.HomeController;
import hu.rft.controller.RegistrationController;
import hu.rft.controller.RootLayoutController;
import hu.rft.controller.SearchBooksController;
import hu.rft.controller.HomePageController;
import hu.rft.controller.LoanOverviewController;
import hu.rft.controller.PersonalDataController;
import hu.rft.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A program main függvényét tartalmazó osztály.
 *
 * @author bedonorbert
 *
 */
public class Main extends Application {

    private static Stage primaryStage;
    private BorderPane rootLayout2;
    private AnchorPane rootLayout;

    /**
     * A start metódus elkészíti az induláskor megjelenítendő ablakot.
     *
     */

    @Override
    public void start(Stage primaryStage) {

        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("RFT Könyvtár");

        Main.primaryStage.getIcons().add(new Image("/images/icon.png"));

        initLogin();
    }

    /**
     * Elkészíti a bejelentkezéshez sz�kséges felületet.
     *
     */
    public void initLogin() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/Login.fxml"));
            rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            HomeController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void registration() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/Registration.fxml"));
            rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            RegistrationController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initRootLayout(User current) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/RootLayout.fxml"));
            rootLayout2 = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout2);
            primaryStage.setScene(scene);
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this, current);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UserMainPage(User user) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/HomePage.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

            rootLayout2.setCenter(Overview);

            HomePageController controller = loader.getController();
            controller.setMainApp(this, user);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void PersonalData(User current) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/PersonalData.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

            rootLayout2.setCenter(Overview);

            PersonalDataController controller = loader.getController();
            controller.setMainApp(this, current);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void LoanOverview(User current) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/LoanOverview.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

            rootLayout2.setCenter(Overview);

            LoanOverviewController controller = loader.getController();
            controller.setMainApp(this, current);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void SearchBooks(User current) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/SearchBooks.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

            rootLayout2.setCenter(Overview);

            SearchBooksController controller = loader.getController();
            controller.setMainApp(this, current);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void EventList() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/EventList.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

            rootLayout2.setCenter(Overview);

            EventListController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

// admin surface
    public void initAdminRootLayout(User current) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/AdminRootLayout.fxml"));
            rootLayout2 = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout2);
            primaryStage.setScene(scene);
            AdminRootLayoutController controller = loader.getController();
            controller.setMainApp(this, current);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AdminHomePage(User current) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/AdminHomePage.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

            rootLayout2.setCenter(Overview);

            AdminHomePageController controller = loader.getController();
            controller.setMainApp(this, current);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void AdminAddNewBook(User current) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/AdminAddNewBook.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

            rootLayout2.setCenter(Overview);

            AdminAddNewBookController controller = loader.getController();
            controller.setMainApp(this, current);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void AdminEditEvent(User current) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/AdminEditEvent.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

            rootLayout2.setCenter(Overview);

            AdminEditEventController controller = loader.getController();
            controller.setMainApp(this, current);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void AdminEditUser(User current) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/AdminEditUsers.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

            rootLayout2.setCenter(Overview);

            AdminEditUserController controller = loader.getController();
            controller.setMainApp(this, current);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void BorrowsOverview(User current) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/BorrowsOverview.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

            rootLayout2.setCenter(Overview);

            BorrowsOverviewController controller = loader.getController();
            controller.setMainApp(this, current);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Visszaadja az ablakhoz tartozó Stage objektumot.
     *
     * @return az ablakhoz tartozó Stage objektum.
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Beállítja az ablakhoz tartozó Stage objektumot.
     *
     * @param primaryStage az ablakhoz beállítani kívánt Stage objektum.
     */
    public void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }

    /**
     * A program main függvénye.
     *
     * @param args az estleges parancssori argomentumok.
     */
    public static void main(String[] args) {

        launch(args);
    }
}
