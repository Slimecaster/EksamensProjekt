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

    public MyUser createUpdateUser (MyUser myUser){
        try {
            if(myUser.getUserId()==null){
                String sql="INSERT INTO user(userid,fname,sname,password,email,phoneNumber,weight,height,age,gender,activityLevel,goal,role) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                jdbcTemplate.update(sql, myUser.getUserId(), myUser.getFname(), myUser.getSname(), myUser.getPassword(), myUser.getEmail(), myUser.getPhoneNumber(), myUser.getWeight(), myUser.getHeight(), myUser.getAge(), myUser.getGender(), myUser.getActivityLevel(), myUser.getGoal(), myUser.getRole());
            }else{
                String sql = "UPDATE user SET fname=?,sname=?,password=?,email=?,phoneNumber=?,weight=?,height=?,age=?,gender=?,activityLevel=?,goal=?,role=? WHERE userId="+String.valueOf(myUser.getUserId());
                jdbcTemplate.update(sql, myUser.getUserId(), myUser.getFname(), myUser.getSname(), myUser.getPassword(), myUser.getEmail(), myUser.getPhoneNumber(), myUser.getWeight(), myUser.getHeight(), myUser.getAge(), myUser.getGender(), myUser.getActivityLevel(), myUser.getGoal(), myUser.getRole());
            }return myUser;
        } catch(DataAccessException e){
            throw new RuntimeException("Error creating user", e);
        }
    }
    public void deleteUserById(Long userId){
        String sql="DELETE FROM user where userId =?";
        jdbcTemplate.update(sql,userId);
    }

    public Optional<MyUser> findUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM user WHERE email = ?";
            MyUser user = jdbcTemplate.queryForObject(sql, new Object[]{email}, userRowMapper());
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // Returner tomt Optional hvis ingen bruger er fundet
        }
    }
    private RowMapper<MyUser> userRowMapper() {
        return (rs, rowNum) -> {
            MyUser user = new MyUser();
            user.setUserId(rs.getLong("userId"));
            user.setFname(rs.getString("fname"));
            user.setSname(rs.getString("sname"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNumber(rs.getString("phoneNumber"));
            user.setWeight(rs.getDouble("weight"));
            user.setHeight(rs.getInt("height"));
            user.setAge(rs.getInt("age"));
            user.setGender(rs.getInt("gender"));
            user.setActivityLevel(rs.getInt("activityLevel"));
            user.setGoal(rs.getInt("goal"));
            user.setRole(rs.getString("role"));

            return user;
        };
    }
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
    public void deleteIngredientById(Long ingredientId){
        String sql="DELETE FROM ingredient where ingredientId =?";
        jdbcTemplate.update(sql,ingredientId);
    }

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

    public void deleteDishById(Long DishId){
        String sql="DELETE FROM dish where dishId =?";
        jdbcTemplate.update(sql,DishId);
    }
    public List<Dish> findAllDishes(){
        try {
            String sql="SELECT * FROM dish";
            return jdbcTemplate.query(sql,dishRowmapper());
        }catch(DataAccessException e){
            throw new RuntimeException("Error finding all dishes", e);
        }
    }

    public RowMapper<Dish> dishRowmapper(){
        return (rs, rowNum) ->{
            Dish dish = new Dish();
            dish.setName(rs.getString("name"));
            dish.setType(rs.getString("type"));
            return dish;
        };
    }


    public Recipe createUpdateRecipe(Recipe recipe){
        try {
            if (recipe.getRecipeId()==null){
                String sql="INSERT INTO recipe(calories,protein,fat,carbs,description) VALUES (?,?,?,?,?)";
                jdbcTemplate.update(sql,recipe.getCalories(),recipe.getProtein(),recipe.getFat(),recipe.getCarbs(),recipe.getDescription());
            } else {
                String sql="UPDATE recipe set calories=?,protein=?,fat=?,carbs=? where recipeId="+String.valueOf(recipe.getRecipeId());
                jdbcTemplate.update(sql,recipe.getCalories(),recipe.getProtein(),recipe.getFat(),recipe.getCarbs(),recipe.getDescription());
            } return recipe;
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteRecipeById(Long recipeId){
        String sql="DELETE FROM recipe where recipeId =?";
        jdbcTemplate.update(sql,recipeId);
    }

    public List<Recipe> findAllRecipes(){
        try {
            String sql="SELECT * FROM recipe";
            return jdbcTemplate.query(sql,recipeRowmapper());
        }catch(DataAccessException e){
            throw new RuntimeException("Error while finding all recipes",e);
        }
    }

    public RowMapper<Recipe> recipeRowmapper(){
        return(rs, rowNum) ->{
            Recipe recipe = new Recipe();
            recipe.setCalories(rs.getInt("calories"));
            recipe.setProtein(rs.getInt("protein"));
            recipe.setFat(rs.getInt("fat"));
            recipe.setCarbs(rs.getInt("carbs"));
            recipe.setDescription(rs.getString("description"));
            return recipe;
        };
    }
}
