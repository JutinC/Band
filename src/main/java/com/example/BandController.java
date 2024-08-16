/*
Justin Choi
CSP Period 6
Schenk
11 April 2024
Band peoople
 */
package com.example.band;
//Import external libraries
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

//Controller class ewith initializing
public class BandController implements Initializable {

    //TextFields
    @FXML
    public TextField entryId;
    @FXML
    public TextField entryFirstName;
    @FXML
    public TextField entryLastName;
    @FXML
    public TextField entryInstrument;
    @FXML
    public TextField entryStateChair;
    @FXML
    public TextField entryDistrictChair;
    @FXML TextField entrySearch;

    //ChoiceBoxes as the ENUMS
    @FXML
    public ChoiceBox<String> choiceGrade;
    @FXML
    public ChoiceBox<String> choiceBand;

    //CheckBoxes as the BOOL
    @FXML
    public CheckBox checkMarchingBand;
    @FXML
    public CheckBox checkJazzBand;
    @FXML
    public CheckBox checkFullOrchestra;



    //Buttons for the methods
    @FXML
    public Button btnSearch;
    @FXML
    public Button btnCreate;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnLeftest;
    @FXML
    public Button btnLefter;
    @FXML
    public Button btnLeft;
    @FXML
    public Button btnRight;
    @FXML
    public Button btnRighter;
    @FXML
    public Button btnRightest;

    @FXML
    public ListView<String> listView;
    String currentPerson;

    //Initializes variables
    static int currentId = 0;
    static boolean midInsert = false;
    //Creates Object in time of the band database
    static BandDatabase bandDatabase;
    //Creates a band
    static Band band;
    //Band Controller
    public BandController() throws SQLException, IOException {
        //Initializes the widgets
        entryId = new TextField();
        entryFirstName = new TextField();
        entryLastName = new TextField();
        choiceGrade = new ChoiceBox<String>();
        entryInstrument = new TextField();
        choiceBand = new ChoiceBox<String>();
        entryStateChair = new TextField();
        entryDistrictChair = new TextField();
        checkMarchingBand = new CheckBox();
        checkJazzBand = new CheckBox();
        checkFullOrchestra = new CheckBox();

        //initialize database
        bandDatabase = new BandDatabase();
        //Connects to the database
        BandDatabase.connect();
        //display first Band
        displayBand();
    }

    //method to display last Band
    @FXML
    protected void onBtnLeftClick() {
        //handle shifting
        currentId -= 1;
        if (currentId < 0) {
            currentId += BandDatabase.bandList.size();
        }

        //display new Band
        displayBand();
    }
    //method to display last by two Band
    @FXML
    protected void onBtnLefterClick() {
        //handle shifting
        currentId -= 2;
        if (currentId < 0) {
            currentId += BandDatabase.bandList.size();
        }

        //display new Band
        displayBand();
    }
    //method to display very first Band
    @FXML
    protected void onBtnLeftestClick() {
        //handle shifting
        currentId = 0;

        //display new Band
        displayBand();
    }
    //method to display next Band
    @FXML
    protected void onBtnRightClick() {
        //handle shifting
        currentId += 1;
        if (currentId >= BandDatabase.bandList.size()) {
            currentId -= BandDatabase.bandList.size();
        }

        //display new Band
        displayBand();
    }
    //method to display next by two Band
    @FXML
    protected void onBtnRighterClick() {
        //handle shifting
        currentId += 2;
        if (currentId >= BandDatabase.bandList.size()) {
            currentId -= BandDatabase.bandList.size();
        }

        //display new Band
        displayBand();
    }
    //method to display very last Band
    @FXML
    protected void onBtnRightestClick() {
        //handle shifting
        currentId = BandDatabase.bandList.size() - 1;

        //display new Band
        displayBand();
    }

    //method to display current Band
    public void displayBand() {
        //Fetch the database
        BandDatabase.readBand();
        //Gets the current band from the id
        Band currentBand = BandDatabase.bandList.get(currentId);
        //Sets all the widgets based off the current band
        entryId.setText(String.valueOf(currentBand.getId()));
        entryFirstName.setText(currentBand.getFirstName());
        entryLastName.setText(currentBand.getLastName());
        choiceGrade.getSelectionModel().select(currentBand.getGrade());
        entryInstrument.setText(currentBand.getInstrument());
        choiceBand.getSelectionModel().select(currentBand.getBand());
        entryStateChair.setText(String.valueOf(currentBand.getStateChair()));
        entryDistrictChair.setText(String.valueOf(currentBand.getDistrictChair()));
        checkMarchingBand.setSelected(currentBand.getMarchingBand());
        checkJazzBand.setSelected(currentBand.getJazzBand());
        checkFullOrchestra.setSelected(currentBand.getFullOrchestra());
    }

    //Method to get the Band
    public void getBand() {
        //Set the id + 1 b/c MYSQL starts at 1 LIST starts at 0
        int idd = currentId + 1;
        String first = entryFirstName.getText();
        String last = entryLastName.getText();
        String grading = choiceGrade.getValue();
        String clarinet = entryInstrument.getText();
        String type = choiceBand.getValue();
        int state = Integer.parseInt(entryStateChair.getText());
        int district = Integer.parseInt(entryDistrictChair.getText());
        boolean marching = checkMarchingBand.isSelected();
        boolean jazz = checkJazzBand.isSelected();
        boolean full = checkFullOrchestra.isSelected();
        //Creates new band
        band = new Band(idd, first, last, grading, clarinet, type, state, district, marching, jazz, full);
    }
    @FXML
    protected void onBtnSearchClick(){
        String search = entrySearch.getText();
        String name = "";
        ArrayList<String> helpList = new ArrayList<String>();
        if(Objects.equals(search, "")) {
            listView.getItems().setAll(BandDatabase.person);
        } else {

            for(int i = 0; i < BandDatabase.bandList.size(); i++) {
                if((Objects.equals(search, BandDatabase.bandList.get(i).firstName)) || (Objects.equals(search, BandDatabase.bandList.get(i).lastName)) || (Objects.equals(search, BandDatabase.bandList.get(i).grade)) || (Objects.equals(search, BandDatabase.bandList.get(i).instrument)) || (Objects.equals(search, BandDatabase.bandList.get(i).band)) || (Objects.equals(search, String.valueOf(BandDatabase.bandList.get(i).stateChair))) || (Objects.equals(search, String.valueOf(BandDatabase.bandList.get(i).districtChair))) || (Objects.equals(search, "Marching")) || (Objects.equals(search, "Jazz")) || (Objects.equals(search, "Orchestra"))) {
                    name = BandDatabase.bandList.get(i).firstName + " " + BandDatabase.bandList.get(i).lastName;
                    helpList.add(name);
                }
            }
            listView.getItems().setAll(name);
        }
    }
    //method to load create form
    @FXML
    protected void onBtnCreateClick() {
        //Checks if it is clicked first or second
        if(midInsert) {
            //Creates band then
            getBand();
            BandDatabase.createBand(band);
            btnCreate.setText("Create");
            btnDelete.setText("Delete");
            midInsert = false;
            currentId += 1;
            displayBand();
            listView.getItems().setAll(BandDatabase.person);
        } else {
            //Then clears fields and gets submit and cancel
            clear();
            btnCreate.setText("Submit");
            btnDelete.setText("Cancel");
            midInsert = true;
        }
    }
    //Saving the data
    @FXML
    protected void onBtnSaveClick() {
        //Fetches data
        getBand();
        //Updates
        BandDatabase.updateBand(band);
        listView.getItems().setAll(BandDatabase.person);
    }
    //delete method
    @FXML
    protected void onBtnDeleteClick() {
        //Checks if inserting
        if(midInsert) {
            currentId = 1;
            displayBand();
            btnCreate.setText("Create");
            btnDelete.setText("Delete");
            midInsert = false;
        } else {
            Band currentBand = BandDatabase.bandList.get(currentId);
            //If not thn displays dialog box to confirm delete
            String confirmText = "Do you want to Delete:\n" + bandDatabase.bandList.get(currentId).firstName + " " + bandDatabase.bandList.get(currentId).lastName;

            Alert confirmDelete = new Alert(Alert.AlertType.INFORMATION);
            confirmDelete.setTitle("Confirm Delete");
            confirmDelete.setHeaderText(null);
            confirmDelete.setContentText(confirmText);
            Optional <ButtonType> finalDelete = confirmDelete.showAndWait();

            if(finalDelete.get() == ButtonType.OK) {
                BandDatabase.deleteBand(currentBand);
                BandDatabase.updateBand(currentBand);
                listView.getItems().setAll(BandDatabase.person);
            }
        }
    }
    //clears the texts
    public void clear() {
        //Set all of them to default
        entryId.setText("");
        entryFirstName.setText("");
        entryLastName.setText("");
        choiceGrade.getSelectionModel().clearSelection();
        entryInstrument.setText("");
        choiceBand.getSelectionModel().clearSelection();
        entryDistrictChair.setText("");
        entryStateChair.setText("");
        checkMarchingBand.setSelected(false);
        checkJazzBand.setSelected(false);
        checkFullOrchestra.setSelected(false);
    }
    //Overrieds for initialization
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Adds items to the ENUM
        choiceGrade.getItems().addAll("FRESHMAN", "SOPHOMORE", "JUNIOR", "SENIOR");
        choiceBand.getItems().addAll("SYMPHONIC", "WIND");
        listView.getItems().addAll(BandDatabase.person);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentPerson = listView.getSelectionModel().getSelectedItem();

                for(int i = 0; i < BandDatabase.bandList.size(); i++) {
                    String fullName = bandDatabase.bandList.get(i).firstName + " " + bandDatabase.bandList.get(i).lastName;
                    if(Objects.equals(currentPerson, fullName)) {
                        currentId = i;
                        displayBand();
                        break;
                    }
                }
            }
        });
        //Displays the data
        displayBand();
    }

    @FXML
    protected void help() {
        String text = "There is a list of data on the right, data on the left. Press create to fill in data and submit or cancel depending on your discretion. Save to save the work. Delete will bring forth a confirmation.";
        Alert help = new Alert(Alert.AlertType.INFORMATION);
        help.setTitle("Help");
        help.setHeaderText(null);
        help.setContentText(text);
        help.show();
    }
    @FXML
    protected void aboutUs() {
        String text = "This Database was used to buld the precious people and friends I have made and I want to track all of them to see how they are doing. I hope that this will be able to allow them to see each other and what they do beacuse it is oh so important to me.";
        Alert help = new Alert(Alert.AlertType.INFORMATION);
        help.setTitle("About Us");
        help.setHeaderText(null);
        help.setContentText(text);
        help.show();
    }
	@FXML
	protected void quit() {
		System.exit(0);
	}

}
