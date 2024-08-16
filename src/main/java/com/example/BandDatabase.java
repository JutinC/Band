/*
Justin Choi
CSP Period 6
Schenk
11 April 2024
Band peoople
 */
package com.example.band;

//External libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//This is the databse connected to mysql
public class BandDatabase {

    //Creates an arrayList to store every record in the database
    public static ArrayList<Band> bandList = new ArrayList<Band>();
    public static ArrayList<String> person = new ArrayList<String>();
    static String fullName = "";
    //Connection to the databsae
    public static Connection conn = null;

    //Connection method
    public static void connect() {

        //Tries to connect
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Gets the connection
            conn = DriverManager.
                    getConnection("jdbc:mysql://127.0.0.1:3306/class?user=root&password=K3wlpass!");
        //Catches the exception
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //Fetches all the bands
    public static void readBand() {
        //Sql statement to read everything
        String sql = "SELECT * FROM class";
        //Try statement for error
        try {
            //Creates the statement
            Statement statement	= conn.createStatement();
            //Executes as a query
            ResultSet resultSet = statement.executeQuery(sql);
            //Repeats for every single result to add into the band list
            while (resultSet.next()) {
                //Instantiates the band data
                Band nextBand = new Band(resultSet.getInt("id"), resultSet.getString("firstName"),resultSet.getString("lastName"), resultSet.getString("grade"), resultSet.getString("instrument"), resultSet.getString("band"), resultSet.getInt("stateChair"), resultSet.getInt("districtChair"), resultSet.getBoolean("marchingBand"), resultSet.getBoolean("jazzBand"), resultSet.getBoolean("fullOrchestra"));
                //case to check if it is a new band in the list or has already repeated and if not then add it in

                boolean failed = false;
                if(bandList.isEmpty()) {
                    fullName = nextBand.firstName + " " + nextBand.lastName;
                    bandList.add(nextBand);
                    person.add(fullName);
                }
//                int maxId = bandList.get(bandList.size()).id;
                //Checks for every case that if a band got updated to fix it
                for(int i = 0; i < bandList.size(); i++) {
                    if(bandList.get(i).id == nextBand.id) {
                        failed = true;
                        if(bandList.get(i) != nextBand) {
                            fullName = nextBand.firstName + " " + nextBand.lastName;
                            bandList.set(i,nextBand);
                            person.set(i,fullName);
                        }
                    }
//                    if(bandList.size() < nextBand.id) {
//                        if(bandList.get(i).id == nextBand.id) {
//                            failed = true;
//                        }
//                    }
                }
                if(!failed) {
                    fullName = nextBand.firstName + " " + nextBand.lastName;
                    bandList.add(nextBand);
                    person.add(fullName);
                } else {
                    failed = false;
                }
            }
            //Catches any errors
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
    //Creates a new band person
    public static void createBand(Band band) {
        //Sql statement
        String sql = "INSERT INTO class (firstName,lastName,grade,instrument,band,stateChair,districtChair,marchingBand,jazzBand,fullOrchestra)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?)";
        //try the prepared statement
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            //Sets every one of the values to the band parameter
            preparedStatement.setString(1, band.getFirstName());
            preparedStatement.setString(2, band.getLastName());
            preparedStatement.setString(3, band.getGrade());
            preparedStatement.setString(4, band.getInstrument());
            preparedStatement.setString(5, band.getBand());
            preparedStatement.setInt(6, band.getStateChair());
            preparedStatement.setInt(7, band.getDistrictChair());
            preparedStatement.setBoolean(8, band.getMarchingBand());
            preparedStatement.setBoolean(9, band.getJazzBand());
            preparedStatement.setBoolean(10, band.getFullOrchestra());
            //Executes the query
            preparedStatement.executeUpdate();
            //fetches the bands
            readBand();
            //In case of error
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    //Updates the band
    public static void updateBand(Band band) {
        //Sql statement
        String sql = "UPDATE class SET firstName = ?, lastName = ?, grade = ?, "
                + "instrument = ?, band = ?, stateChair = ?, districtChair = ?, marchingBand = ?, jazzBand = ?,"
                + "fullOrchestra = ? WHERE id = ?";
        //Trys the prepared statement
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            //Inserts into the values of the variables missing
            preparedStatement.setString(1, band.getFirstName());
            preparedStatement.setString(2, band.getLastName());
            preparedStatement.setString(3, band.getGrade());
            preparedStatement.setString(4, band.getInstrument());
            preparedStatement.setString(5, band.getBand());
            preparedStatement.setInt(6, band.getStateChair());
            preparedStatement.setInt(7, band.getDistrictChair());
            preparedStatement.setBoolean(8, band.getMarchingBand());
            preparedStatement.setBoolean(9, band.getJazzBand());
            preparedStatement.setBoolean(10, band.getFullOrchestra());
            //Id last b/c of the"Where Id = "
            preparedStatement.setInt(11, band.getId());
            //Executes
            preparedStatement.executeUpdate();
            //Fetches the records
            readBand();
        //Catches them
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //Delete the band
    public static void deleteBand(Band band) {
        //Creates sql statement to get rid of it
        String sql = "DELETE FROM class WHERE id = ?";
        //Tries prepared statement
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            //Gets the id
            preparedStatement.setInt(1, band.getId());
            //Executes update
            preparedStatement.executeUpdate();
            //Catches error
            bandList.remove(band);
            fullName = band.firstName + " " + band.lastName;
            person.remove(fullName);
            readBand();
            //Catches the error
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
