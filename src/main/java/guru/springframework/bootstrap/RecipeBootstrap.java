package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        // get UOMs
        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");

        if(!ounceUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");

        if(!pinchUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tablespoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaspoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        // get UOM Optionals
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure ounceUom = ounceUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure tbspUom = tablespoonUomOptional.get();
        UnitOfMeasure tspUom = teaspoonUomOptional.get();

        // get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }

        // get Category Optionals
        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        // Guac recipe
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1, Cut the avocados:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl.\n" +
                "\n" +
                "2. Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.\n" +
                "\n" +
                "3. Add the remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4. Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.\n" +
                "\n" +
                "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/"
        );

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Don't have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It still tastes great.\n"
        );

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal("0.25"), tspUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime or lemon juice", new BigDecimal(1), tbspUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tbspUom));
        guacRecipe.addIngredient(new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced", new BigDecimal(1), eachUom));
        guacRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tbspUom));
        guacRecipe.addIngredient(new Ingredient("freshly ground black pepper", new BigDecimal(1), pinchUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, chopped (optional)", new BigDecimal("0.5"), eachUom));
        guacRecipe.addIngredient(new Ingredient("Red radish or jicama slices for garnish (optional)", new BigDecimal(1), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        // add to return list
        recipes.add(guacRecipe);

        // Tacos recipe
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setCookTime(9);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setDirections("1. Prepare the grill:\n" +
                "Prepare either a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2. Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3. Grill the chicken:\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165°F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4. Thin the sour cream with milk:\n" +
                "Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle.\n" +
                "\n" +
                "5. Assemble the tacos:\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "6. Warm the tortillas:\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/"
        );

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today's tacos are more purposeful a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n");

        tacoRecipe.setNotes(tacoNotes);

        tacoRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tbspUom));
        tacoRecipe.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), tspUom));
        tacoRecipe.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), tspUom));
        tacoRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), tspUom));
        tacoRecipe.addIngredient(new Ingredient("kosher salt", new BigDecimal("0.5"), tspUom));
        tacoRecipe.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), eachUom));
        tacoRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tbspUom));
        tacoRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tbspUom));
        tacoRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tbspUom));
        tacoRecipe.addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", new BigDecimal("4"), eachUom));
        tacoRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom));
        tacoRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom));
        tacoRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), eachUom));
        tacoRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal("0.5"), pintUom));
        tacoRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal("0.25"), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(1), eachUom));
        tacoRecipe.addIngredient(new Ingredient("sour cream", new BigDecimal("0.5"), cupUom));
        tacoRecipe.addIngredient(new Ingredient("milk", new BigDecimal("0.25"), cupUom));
        tacoRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(1), eachUom));

        tacoRecipe.getCategories().add(americanCategory);
        tacoRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacoRecipe);

        return recipes;
    }
}
