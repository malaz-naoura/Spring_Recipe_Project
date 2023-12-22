package com.mezo.recipe.bootstrap;

import com.mezo.recipe.domains.*;
import com.mezo.recipe.repositories.CategoryRepo;
import com.mezo.recipe.repositories.RecipeRepo;
import com.mezo.recipe.repositories.UnitOfMeasureRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class dataloader implements CommandLineRunner {

    UnitOfMeasureRepo unitOfMeasureRepo;
    CategoryRepo categoryRepo;
    RecipeRepo recipeRepo;

    public dataloader(UnitOfMeasureRepo unitOfMeasureRepo, CategoryRepo categoryRepo, RecipeRepo recipeRepo) {
        this.unitOfMeasureRepo = unitOfMeasureRepo;
        this.categoryRepo = categoryRepo;
        this.recipeRepo = recipeRepo;
    }

    @Override
    public void run(String... args) throws Exception {

       UnitOfMeasure teaspoon= unitOfMeasureRepo.findByDescription("Teaspoon").get();

        Recipe spicyGrilledChickenTacos=new Recipe();
        spicyGrilledChickenTacos.setCookTime(15);
        spicyGrilledChickenTacos.setPrepTime(20);
        spicyGrilledChickenTacos.setDifficulty(Difficulty.EASY);
        spicyGrilledChickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        spicyGrilledChickenTacos.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");
        spicyGrilledChickenTacos.setDirections("Prepare the grill:\n" +
                "Prepare either a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.");

        // Ingredients
        Ingredient cumin=new Ingredient();
        cumin.setDescription("cumin");
        cumin.setAmount(BigDecimal.valueOf(1));
        cumin.setUnitOfMeasure(teaspoon);
        cumin.setRecipe(spicyGrilledChickenTacos);

        // Notes
        Note note=new Note();
        note.setRecipeNotes("NUTRITION FACTS (PER SERVING) 655 CALORIES 44g FAT 32g CARBS 41g PROTEINs");
        note.setRecipe(spicyGrilledChickenTacos);

        spicyGrilledChickenTacos.getIngredients().add(cumin);
        spicyGrilledChickenTacos.setNotes(note);

        recipeRepo.save(spicyGrilledChickenTacos);

    }
}
