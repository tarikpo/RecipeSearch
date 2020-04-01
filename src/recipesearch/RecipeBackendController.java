package recipesearch;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.lab2.Recipe;
import se.chalmers.ait.dat215.lab2.RecipeDatabase;
import se.chalmers.ait.dat215.lab2.SearchFilter;

import java.util.List;

public class RecipeBackendController {

    private String cuisine = null;
    private String mainIngredient = null;
    private String difficulty = null;
    private int maxPrice = 0;
    private int maxtime = 0;
    List<Recipe> recipes;
    RecipeDatabase db = RecipeDatabase.getSharedInstance();


    public List<Recipe> getRecipes() {
        List<Recipe> recipes = db.search(new SearchFilter(difficulty, maxtime, cuisine, maxPrice, mainIngredient));
        return recipes;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setMainIngredient(String mainIngredient) {
        this.mainIngredient = mainIngredient;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMaxTime(int maxTime) {
        this.maxtime = maxTime;
    }


}
