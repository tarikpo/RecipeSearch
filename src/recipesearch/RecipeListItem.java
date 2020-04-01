package recipesearch;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.lab2.Recipe;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class RecipeListItem extends AnchorPane {

    @FXML private AnchorPane image4recipie;
    @FXML private ImageView foodItem;
    @FXML private Text foodName;

    private RecipeSearchController parentController;
    private Recipe recipe;

    public RecipeListItem(Recipe recipe, RecipeSearchController recipeSearchController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recipe_listitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.recipe = recipe;
        foodItem.setImage(recipe.getFXImage());
        foodName.setText(recipe.getName());
        this.parentController = recipeSearchController;
    }
}

