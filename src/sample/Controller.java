package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.w3c.dom.Text;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;

import static javafx.scene.paint.Color.*;

public class Controller implements Initializable
{
    @FXML
    private AnchorPane homepage;
    @FXML
    private AnchorPane aboutUsPage;
    @FXML
    private AnchorPane repetoirePage;
    @FXML
    private AnchorPane priceListPage;
    @FXML
    private AnchorPane moviepage;
    @FXML
    private AnchorPane paymentPage;
    @FXML
    private AnchorPane bookingpage;
    @FXML
    private ImageView moviepageSplashart;
    @FXML
    private Label movieName;
    @FXML
    private Label movieDesc;
    @FXML
    private Label selectedSeats;
    @FXML
    private Label bookingMovieName;
    @FXML
    private Label billLabel_1;
    @FXML
    private Label billLabel_2;
    @FXML
    private Label billLabel_3;
    @FXML
    private Label billLabel_4;
    @FXML
    private Label totalCost;

    private int ticketsInBill;
    private ArrayList<ImageView> images = new ArrayList<ImageView>();
    @FXML
    private ImageView movieImage_1;
    @FXML
    private ImageView movieImage_2;
    @FXML
    private ImageView movieImage_3;
    @FXML
    private ImageView movieImage_4;
    @FXML
    private ImageView movieImage_5;
    @FXML
    private ImageView movieImage_6;
    @FXML
    private ImageView movieImage_7;
    @FXML
    private ImageView movieImage_8;
    @FXML
    private ImageView movieImage_9;
    @FXML
    private ImageView movieImage_10;

    private boolean[] seatsTaken = new boolean[20];
    private boolean[] seatsBool = new boolean[20];
    private int[] seatsSelected = new int[4];
    private ArrayList<Button> seats = new ArrayList<Button>();
    @FXML
    private Button seat_1;
    @FXML
    private Button seat_2;
    @FXML
    private Button seat_3;
    @FXML
    private Button seat_4;
    @FXML
    private Button seat_5;
    @FXML
    private Button seat_6;
    @FXML
    private Button seat_7;
    @FXML
    private Button seat_8;
    @FXML
    private Button seat_9;
    @FXML
    private Button seat_10;
    @FXML
    private Button seat_11;
    @FXML
    private Button seat_12;
    @FXML
    private Button seat_13;
    @FXML
    private Button seat_14;
    @FXML
    private Button seat_15;
    @FXML
    private Button seat_16;
    @FXML
    private Button seat_17;
    @FXML
    private Button seat_18;
    @FXML
    private Button seat_19;
    @FXML
    private Button seat_20;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        clickHomepage();
        images.add(movieImage_1);

        seats.add(seat_1);
        seats.add(seat_2);
        seats.add(seat_3);
        seats.add(seat_4);
        seats.add(seat_5);
        seats.add(seat_6);
        seats.add(seat_7);
        seats.add(seat_8);
        seats.add(seat_9);
        seats.add(seat_10);
        seats.add(seat_11);
        seats.add(seat_12);
        seats.add(seat_13);
        seats.add(seat_14);
        seats.add(seat_15);
        seats.add(seat_16);
        seats.add(seat_17);
        seats.add(seat_18);
        seats.add(seat_19);
        seats.add(seat_20);

        for (int i = 1; i <= 10; i++)
        {
            File imageFile = new File("Images/"+ i +".jpg");
            Image img = new Image(imageFile.toURI().toString());
            switch (i)
            {
                case 1:
                    movieImage_1.setImage(img);
                case 2:
                    movieImage_2.setImage(img);
                case 3:
                    movieImage_3.setImage(img);
                case 4:
                    movieImage_4.setImage(img);
                case 5:
                    movieImage_5.setImage(img);
                case 6:
                    movieImage_6.setImage(img);
                case 7:
                    movieImage_7.setImage(img);
                case 8:
                    movieImage_8.setImage(img);
                case 9:
                    movieImage_9.setImage(img);
                case 10:
                    movieImage_10.setImage(img);
            }
        }
    }

    public void openBookingPage(ActionEvent e)
    {
        for (int i = 0; i < seatsSelected.length; i++)
            seatsSelected[i] = 0;
        for (int i = 0; i < seatsBool.length; i++)
            seatsBool[i] = false;

        updateSeatList();
        moviepage.setVisible(false);
        bookingpage.setVisible(true);

        DbConnection newConnection = new DbConnection();
        Connection DBconnection = newConnection.getConnection();
        try {
            Statement statement = DBconnection.createStatement();
            ResultSet rs = statement.executeQuery("select * from rooms where room_id = 1");
            while(rs.next())
            {
                for(int i = 0; i < seatsTaken.length; i++)
                {
                    if(rs.getBoolean(i+3) == true)
                    {
                        seatsTaken[i] = true;
                        seats.get(i).setStyle("-fx-background-color: #404040");
                        seats.get(i).setText("");
                    }
                    else {
                        seatsTaken[i] = false;
                        seats.get(i).setText("");
                        seats.get(i).setStyle("-fx-background-color: #fcba03");
                    }
                }
            }
            ResultSet rs2 = statement.executeQuery("select * from movie where movie_id = 1");
            while (rs2.next())
                bookingMovieName.setText(rs2.getString(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void openPaymentPage()
    {
        billLabel_1.setText("");
        billLabel_2.setText("");
        billLabel_3.setText("");
        billLabel_4.setText("");
        paymentPage.setVisible(true);
        bookingpage.setVisible(false);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < seatsSelected.length; i++)
        {
            if(seatsSelected[i] != 0)
                list.add(seatsSelected[i]);
        }

        float price = (float)list.size() * 22.90f;
        totalCost.setText(price + "0 PLN");
        switch (list.size())
        {
            case 0:
                ticketsInBill=0;
                break;
            case 1:
                billLabel_1.setText("Price: 22.90 PLN        Seat: " + list.get(0));
                ticketsInBill=1;
                break;
            case 2:
                billLabel_1.setText("Price: 22.90 PLN        Seat: " + list.get(0));
                billLabel_2.setText("Price: 22.90 PLN        Seat: " + list.get(1));
                ticketsInBill=2;
                break;
            case 3:
                billLabel_1.setText("Price: 22.90 PLN        Seat: " + list.get(0));
                billLabel_2.setText("Price: 22.90 PLN        Seat: " + list.get(1));
                billLabel_3.setText("Price: 22.90 PLN        Seat: " + list.get(2));
                ticketsInBill=3;
                break;
            case 4:
                billLabel_1.setText("Price: 22.90 PLN        Seat: " + list.get(0));
                billLabel_2.setText("Price: 22.90 PLN        Seat: " + list.get(1));
                billLabel_3.setText("Price: 22.90 PLN        Seat: " + list.get(2));
                billLabel_4.setText("Price: 22.90 PLN        Seat: " + list.get(3));
                ticketsInBill=4;
                break;
        }
    }
    public void payForBooking()
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < seatsSelected.length; i++)
        {
            if(seatsSelected[i] != 0)
                list.add(seatsSelected[i]);
        }
        
        DbConnection newConnection = new DbConnection();
        Connection DBconnection = newConnection.getConnection();
        try
        {
            String query = null;
            switch (ticketsInBill)
            {
                case 0:
                    break;
                case 1:
                    query = "update rooms set seat_" + (list.get(0)) + " = true where room_id = 1";
                    break;
                case 2:
                    query = "update rooms set seat_" + (list.get(0)) + " = true, seat_" + (list.get(1)) + " = true where room_id = 1";
                    break;
                case 3:
                    query = "update rooms set seat_" + (list.get(0)) + " = true, seat_" + (list.get(1)) + " = true, seat_" + (list.get(2)) + " = true where room_id = 1";
                    break;
                case 4:
                    query = "update rooms set seat_" + (list.get(0)) + " = true, seat_" + (list.get(1)) + " = true, seat_" + (list.get(2)) + " = true, seat_" + (list.get(3)) + " = true where room_id = 1";
                    break;
            }
            PreparedStatement preparedStmt = DBconnection.prepareStatement(query);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        clickHomepage();
    }
    public void openMoviePage()
    {
        homepage.setVisible(false);
        moviepage.setVisible(true);

        DbConnection newConnection = new DbConnection();
        Connection DBconnection = newConnection.getConnection();
        try {
            Statement statement = DBconnection.createStatement();
            ResultSet rs = statement.executeQuery("select * from movie where movie_id = 1");
            while(rs.next())
            {
                File imageFile = new File("Images/"+ rs.getString(1) +".jpg");
                Image img = new Image(imageFile.toURI().toString());
                moviepageSplashart.setImage(img);
                movieName.setText(rs.getString(2));
                movieDesc.setText(rs.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateSeatList()
    {
        String[] select = new String[4];
        select[0] = (seatsSelected[0] == 0)?"":seatsSelected[0] + "  ";
        select[1] = (seatsSelected[1] == 0)?"":seatsSelected[1] + "  ";
        select[2] = (seatsSelected[2] == 0)?"":seatsSelected[2] + "  ";
        select[3] = (seatsSelected[3] == 0)?"":seatsSelected[3] + "  ";
        selectedSeats.setText("SELECTED SEATS: " + select[0] + select[1] + select[2] + select[3]);
    }

    public void selectSeat(ActionEvent event)
    {
        for (int i = 0; i < seats.size();i++)
        {
            if(event.getSource() == seats.get(i))
            {
                if(!seatsTaken[i]) {
                    if (!seatsBool[i]) {
                        for (int b = 0; b < seatsSelected.length; b++) {
                            if (seatsSelected[b] == 0) {
                                seatsSelected[b] = i + 1;
                                seatsBool[i] = true;
                                seats.get(i).setStyle("-fx-background-color: #fc7303");
                                int f = i+1;
                                seats.get(i).setText(""+f);
                                updateSeatList();
                                break;
                            }
                        }
                    } else {
                        seatsBool[i] = false;
                        seats.get(i).setStyle("-fx-background-color: #fcba03");
                        seats.get(i).setText("");
                        for (int b = 0; b < seatsSelected.length; b++) {
                            if (seatsSelected[b] == i + 1) {
                                seatsSelected[b] = 0;
                                break;
                            }
                        }
                        updateSeatList();
                    }
                }
            }
        }
    }
    public void clickHomepage()
    {
        paymentPage.setVisible(false);
        homepage.setVisible(true);
        moviepage.setVisible(false);
        bookingpage.setVisible(false);
        repetoirePage.setVisible(false);
        priceListPage.setVisible(false);
        aboutUsPage.setVisible(false);
    }
    public void clickRepertoire()
    {
        paymentPage.setVisible(false);
        homepage.setVisible(false);
        moviepage.setVisible(false);
        bookingpage.setVisible(false);
        repetoirePage.setVisible(true);
        priceListPage.setVisible(false);
        aboutUsPage.setVisible(false);
    }
    public void clickPricelist()
    {
        paymentPage.setVisible(false);
        homepage.setVisible(false);
        moviepage.setVisible(false);
        bookingpage.setVisible(false);
        repetoirePage.setVisible(false);
        priceListPage.setVisible(true);
        aboutUsPage.setVisible(false);
    }
    public void clickAboutus()
    {
        repetoirePage.setVisible(false);
        priceListPage.setVisible(false);
        aboutUsPage.setVisible(true);
        paymentPage.setVisible(false);
        homepage.setVisible(false);
        moviepage.setVisible(false);
        bookingpage.setVisible(false);
    }
}