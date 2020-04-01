
package recipesearch;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
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
    private AnchorPane searchPane;    //<---
    @FXML
    private ComboBox headingrediensCB;
    @FXML
    private ComboBox cuisineCB;
    @FXML
    private RadioButton allRB;
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
    }


}