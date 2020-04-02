
package recipesearch;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import com.sun.jdi.ObjectReference;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.lab2.Recipe;
import se.chalmers.ait.dat215.lab2.RecipeDatabase;




public class RecipeSearchController implements Initializable {


    RecipeDatabase db = RecipeDatabase.getSharedInstance();
    RecipeBackendController bc = new RecipeBackendController();

    @FXML
    private AnchorPane recipeDetailPane;  //<---
    @FXML
    private SplitPane searchPane;   //<---
    @FXML
    private ComboBox headingrediensCB;
    @FXML
    private ComboBox cuisineCB;
    @FXML
    private RadioButton allRB;
    @FXML
    private RadioButton easyRB;
    @FXML
    private RadioButton mediumRB;
    @FXML
    private RadioButton difficulRB;
    @FXML
    private Spinner maxpriceSpin;
    @FXML
    private Slider maxtimeSlide;
    @FXML
    private FlowPane recipiFP;
    @FXML
    private ImageView detailedFoodImage;
    @FXML
    private Text detailedFoodName;


    public void openRecipeView(Recipe recipe){
        populateRecipeDetailView(recipe);
        recipeDetailPane.toFront();

    }
    //TODO fixa denna i Scene builder steg 10
    @FXML
    public void closeRecipeView(){
       // recipeDetailPane.toBack();
        searchPane.toFront();
    }

    private void populateRecipeDetailView(Recipe r){
        detailedFoodName.setText(r.getName());
        detailedFoodImage.setImage(r.getFXImage());

    }

    private void updateRecipeList() {
        recipiFP.getChildren().clear();
        List<Recipe> recipes = bc.getRecipes();

        for (Recipe r : recipes) {
            RecipeListItem recipeListItem = new RecipeListItem(r, this);
            recipiFP.getChildren().add(recipeListItem);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateRecipeList();

        headingrediensCB.getItems().addAll("Visa alla", "Kött", "Fisk", "Kyckling", "Vegetarisk");
        headingrediensCB.getSelectionModel().select("Visa alla");
        headingrediensCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                bc.setMainIngredient(newValue);
                updateRecipeList();
            }
        });

        cuisineCB.getItems().addAll("Visa alla", "Sverige", "Grekland", "Indien", "Asien","Afrika","Frankrike");
        cuisineCB.getSelectionModel().select("Visa alla");
        cuisineCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                bc.setCuisine(newValue);
                updateRecipeList();
            }
        });

        ToggleGroup difficultyToggleGroup = new ToggleGroup();
        allRB.setToggleGroup(difficultyToggleGroup);
        easyRB.setToggleGroup(difficultyToggleGroup);
        mediumRB.setToggleGroup(difficultyToggleGroup);
        difficulRB.setToggleGroup(difficultyToggleGroup);
        allRB.setSelected(true);

        difficultyToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (difficultyToggleGroup.getSelectedToggle() != null) {
                    RadioButton selected = (RadioButton) difficultyToggleGroup.getSelectedToggle();
                    bc.setDifficulty(selected.getText());
                    updateRecipeList();
                }
            }
        });

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0, 10);
        maxpriceSpin.setValueFactory(valueFactory);
        maxpriceSpin.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    bc.setMaxPrice(newValue);
                    updateRecipeList();
            }
        });
        maxpriceSpin.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if(newValue){
                    //focusgained - do nothing
                }
                else{
                    Integer value = Integer.valueOf(maxpriceSpin.getEditor().getText());
                    bc.setMaxPrice(value);
                    updateRecipeList();
                }

            }
        });

        maxtimeSlide.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue != null && !newValue.equals(oldValue) && !maxtimeSlide.isValueChanging()) {
                    bc.setMaxTime(newValue.intValue());
                    updateRecipeList();
                }


            }
        });


    }


}