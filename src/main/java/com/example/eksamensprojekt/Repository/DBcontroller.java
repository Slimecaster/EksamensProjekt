package com.example.eksamensprojekt.Repository;

import com.example.eksamensprojekt.Model.Dish;
import com.example.eksamensprojekt.Model.Ingredient;
import com.example.eksamensprojekt.Model.MyUser;
import com.example.eksamensprojekt.Model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class DBcontroller {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBcontroller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a user or updates their information depending on whether the userId already exists
     * @param myUser the myUser object of the MyUser class that will be created or updated
     * @return the myUser object created and stored in the database
     * @throws DataAccessException if the myUser is not stored or found correctly in the database
     */
    public MyUser createUpdateUser (MyUser myUser){
        try {
            if(myUser.getUserId()==null){
                String sql="INSERT INTO user(userid,fname,sname,password,email,phoneNumber,weight,height,age,gender,activityLevel,goal,role) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,'USER')";
                jdbcTemplate.update(sql, myUser.getUserId(), myUser.getFname(), myUser.getSname(), myUser.getPassword(), myUser.getEmail(), myUser.getPhoneNumber(), myUser.getWeight(), myUser.getHeight(), myUser.getAge(), myUser.getGender(), myUser.getActivityLevel(), myUser.getGoal());
            }else{
                String sql = "UPDATE user SET fname=?,sname=?,password=?,email=?,phoneNumber=?,weight=?,height=?,age=?,gender=?,activityLevel=?,goal=?,role=? WHERE userId="+String.valueOf(myUser.getUserId());
                jdbcTemplate.update(sql, myUser.getUserId(), myUser.getFname(), myUser.getSname(), myUser.getPassword(), myUser.getEmail(), myUser.getPhoneNumber(), myUser.getWeight(), myUser.getHeight(), myUser.getAge(), myUser.getGender(), myUser.getActivityLevel(), myUser.getGoal(), myUser.getRole());
            }return myUser;
        } catch(DataAccessException e){
            throw new RuntimeException("Error creating user", e);
        }
    }

    /**
     * Deletes a myUser by their email
     * @param email the email of the myUser that will be deleted
     */
    public void deleteUserByEmail(String email){
        String sql="DELETE FROM user where email =?";
        jdbcTemplate.update(sql,email);
    }

    /**
     * Finds all information about a myUser by their email
     * @param email the email of the myUser that will be found
     * @return the myUser with all their information
     * @throws EmptyResultDataAccessException if the myUser is not found
     */
    public Optional<MyUser> findUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM user WHERE email = ?";
            MyUser myUser = jdbcTemplate.queryForObject(sql, new Object[]{email}, userRowMapper());
            return Optional.ofNullable(myUser);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // Returner tomt Optional hvis ingen bruger er fundet
        }
    }

    /**
     * This is a RowMapper for the MyUser class that sets all information about a myUser
     * @return myUser object with all their information
     */
    private RowMapper<MyUser> userRowMapper() {
        return (rs, rowNum) -> {
            MyUser myUser = new MyUser();
            myUser.setUserId(rs.getLong("userId"));
            myUser.setFname(rs.getString("fname"));
            myUser.setSname(rs.getString("sname"));
            myUser.setPassword(rs.getString("password"));
            myUser.setEmail(rs.getString("email"));
            myUser.setPhoneNumber(rs.getString("phoneNumber"));
            myUser.setWeight(rs.getDouble("weight"));
            myUser.setHeight(rs.getInt("height"));
            myUser.setAge(rs.getInt("age"));
            myUser.setGender(rs.getInt("gender"));
            myUser.setActivityLevel(rs.getInt("activityLevel"));
            myUser.setGoal(rs.getInt("goal"));
            myUser.setRole(rs.getString("role"));

            return myUser;
        };
    }

    /**
     * Creates an ingredient or updates its information depending on whether the ingredientId already exists
     * @param ingredient the ingredient object that will be created or updated
     * @return the ingredient object created and stored in the database
     * @throws DataAccessException if the ingredient is not stored or found correctly in the database
     */
    public Ingredient createUpdateIngredient(Ingredient ingredient){
        try{
            if (ingredient.getIngredientId()==null){
                String sql="INSERT INTO ingredient(name,calories,protein,fat,carbs) VALUES (?,?,?,?,?)";
                jdbcTemplate.update(sql,ingredient.getName(),ingredient.getCalories(),ingredient.getProtein(),ingredient.getFat(),ingredient.getCarbs());
            }else{
                String sql="update ingredient set name=?,calories=?,protein=?,fat=?,carbs=? where ingredientId="+String.valueOf(ingredient.getIngredientId());
                jdbcTemplate.update(sql,ingredient.getName(),ingredient.getCalories(),ingredient.getProtein(),ingredient.getFat(),ingredient.getCarbs());
            }
            return ingredient;
        }catch(DataAccessException e){
            throw new RuntimeException("Error creating ingredient", e);
        }
    }

    /**
     * Deletes an ingredient by its ingredientId
     * @param ingredientId the ingredientId of the ingredient that will be deleted
     */
    public void deleteIngredientById(Long ingredientId){
        String sql="DELETE FROM ingredient where ingredientId =?";
        jdbcTemplate.update(sql,ingredientId);
    }

    /**
     * Creates a dish or updates its information depending on whether the dishId already exists
     * @param dish the dish object of the dish class that will be created or updated
     * @return the dish object created and stored in the database
     * @throws DataAccessException if the dish is not stored or found correctly in the database
     */
    public Dish createUpdateDish(Dish dish){
        try{
            if (dish.getDishId()==null){
                String sql="INSERT INTO dish(name,type) VALUES (?,?)";
                jdbcTemplate.update(sql,dish.getName(),dish.getType());
            }else{
                String sql="update dish set name=?,type=? where dishId="+String.valueOf(dish.getDishId());
                jdbcTemplate.update(sql,dish.getName(),dish.getType());
            }
            return dish;
        }catch(DataAccessException e){
            throw new RuntimeException("Error creating ingredient", e);
        }
    }

    /**
     * Deletes a dish by its dishId
     * @param dishId the dishId of the dish that will be deleted
     */
    public void deleteDishById(Long dishId){
        String sql="DELETE FROM dish where dishId =?";
        jdbcTemplate.update(sql,dishId);
    }

    public Optional<Recipe> findRecipeById(Long recipeId) {
        try {
            String sql = "SELECT * FROM recipe WHERE recipeId = ?";
            Recipe recipe = jdbcTemplate.queryForObject(sql, new Object[]{recipeId}, recipeRowmapper());
            return Optional.ofNullable(recipe);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // Return empty Optional if no recipe is found
        }
    }

    /**
     * Finds a list of all dishes and their information in the database
     * @return a list of all information about all dishes in the database
     * @throws DataAccessException if the dishes are not found
     */
    public List<Dish> findAllDishes(){
        try {
            String sql="SELECT * FROM dish";
            return jdbcTemplate.query(sql,dishRowmapper());
        }catch(DataAccessException e){
            throw new RuntimeException("Error finding all dishes", e);
        }
    }

    /**
     * Finds a list of all recipes and their information in the database
     * @return a list of all information about all recipes in the database
     */
    public List<Recipe> show21Recipes(){
        String sql="SELECT * FROM recipe LIMIT 3";
        return jdbcTemplate.query(sql,recipeRowmapper());
    }

    /**
     * This is a RowMapper for the Dish class that sets all information about a dish
     * @return dish object with all its information
     */
    public RowMapper<Dish> dishRowmapper(){
        return (rs, rowNum) ->{
            Dish dish = new Dish();
            dish.setName(rs.getString("name"));
            dish.setType(rs.getString("type"));
            return dish;
        };
    }

    /**
     * Creates a recipe or updates their information depending on whether the recipeId already exists
     * @param recipe the recipe object of the Recipe class that will be created or updated
     * @return the recipe object created and stored in the database
     * @throws DataAccessException if the recipe is not stored or found correctly in the database
     */
    public Recipe createUpdateRecipe(Recipe recipe){
        try {
            if (recipe.getRecipeId()==null){
                String sql="INSERT INTO recipe(name,calories,protein,fat,carbs,description,timeOfDay) VALUES (?,?,?,?,?,?,?)";
                jdbcTemplate.update(sql,recipe.getName(),recipe.getCalories(),recipe.getProtein(),recipe.getFat(),recipe.getCarbs(),recipe.getDescription(), recipe.getTimeOfDayMeal());
            } else {
                String sql="UPDATE recipe set calories=?,protein=?,fat=?,carbs=? ,timeOfDay=? where recipeId="+String.valueOf(recipe.getRecipeId());
                jdbcTemplate.update(sql,recipe.getName(),recipe.getCalories(),recipe.getProtein(),recipe.getFat(),recipe.getCarbs(),recipe.getDescription(),recipe.getTimeOfDayMeal());
            } return recipe;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a recipe by its recipeId
     * @param recipeId the recipeId of the dish that will be deleted
     */
    public void deleteRecipeById(Long recipeId){
        String sql="DELETE FROM recipe where recipeId =?";
        jdbcTemplate.update(sql,recipeId);
    }

    /**
     * Finds a list of all recipes and their information in the database
     * @return a list of all information about all recipes in the database
     * @throws DataAccessException if the recipes are not found
     */
    public List<Recipe> findAllRecipes(){
        try {
            String sql="SELECT * FROM recipe";
            return jdbcTemplate.query(sql,recipeRowmapper());
        }catch(DataAccessException e){
            throw new RuntimeException("Error while finding all recipes",e);
        }
    }

    /**
     * This is a RowMapper for the Recipe class that sets all information about a recipe
     * @return the recipe object with all its information
     */
    public RowMapper<Recipe> recipeRowmapper(){
        return(rs, rowNum) ->{
            Recipe recipe = new Recipe();
            recipe.setName(rs.getString("name"));
            recipe.setCalories(rs.getInt("calories"));
            recipe.setProtein(rs.getInt("protein"));
            recipe.setFat(rs.getInt("fat"));
            recipe.setCarbs(rs.getInt("carbs"));
            recipe.setDescription(rs.getString("description"));
            recipe.setTimeOfDayMeal(rs.getString("timeOfDay"));
            return recipe;
        };
    }

    /**
     * Finds all the recipes that have the "favorite variable" set as 1 a.k.a. true
     * @return a list of all the recipes with the "favorite variable set as 1 a.k.a. true"
     * @throws Exception if the recipes are not found
     */
    public List<Recipe> findFavoriteRecipes(){
        try {
            //finder alle opskrifter hvor favorit variblen i databasen er =1, aka true
            String sql="SELECT * FROM recipe where favorite=1";
            return jdbcTemplate.query(sql,recipeRowmapper());
        } catch (Exception e) {
            throw new RuntimeException("Error unable to find favorites :'(",e);
        }
    }

}
