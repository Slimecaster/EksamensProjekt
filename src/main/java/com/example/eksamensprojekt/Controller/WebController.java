package com.example.eksamensprojekt.Controller;

import com.example.eksamensprojekt.Model.Dish;
import com.example.eksamensprojekt.Model.MyUser;
import com.example.eksamensprojekt.Model.Recipe;
import com.example.eksamensprojekt.Repository.DBcontroller;
import com.example.eksamensprojekt.Service.CustomUserDetailsService;
import com.example.eksamensprojekt.Service.Usecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class WebController {
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final Usecase usecase;

    @Autowired
    public WebController(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder, Usecase usecase) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.usecase = usecase;
    }
    @GetMapping("/login")
    public String login() {
        return "loginPage";
    }

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }
    @GetMapping("/admin/homepage")
    public String adminHome() {
        return "homepage_admin";
    }

    @GetMapping("/user/homepage")
    public String userHomepage(Model model) {
        model.addAttribute("recipe", usecase.show21Recipes());
        return "homepage_user";
    }

    @GetMapping("/login/register/user")
    public String registerUser(Model model){
        model.addAttribute("MyUser", new MyUser());
        return "registerUser";
    }

    @PostMapping("/login/register/user")
    public String registerUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usecase.createUpdateUser(user);
        return "redirect:/login/register/user/userCreatedSuccess";

    }
    @GetMapping("/login/register/user/userCreatedSuccess")
    public String userCreatedSuccess() {
        return "userCreatedSuccess";
    }

    @GetMapping("/admin/homepage/createRecipe")
    public String createRecipe(){
        return "createRecipe";
    }

    @PostMapping("/admin/homepage/createRecipe")
    public String createRecipe(Recipe recipe) {
        usecase.createUpdateRecipe(recipe);
        return "recipeCreatedSuccess";
    }

    @GetMapping("/user/profile")
    public String getUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // The logged-in user's email
        Optional<MyUser> userOptional = usecase.findUserByEmail(userEmail);

        if (userOptional.isPresent()) {
            MyUser user = userOptional.get(); // Extract the user object from the optional
            double bmr = usecase.calculateBMR(userEmail);
            double activityLevelCalories = usecase.calculateActivityLevel(userEmail);
            double dailyCalories = usecase.calculateDailyCalories(userEmail);

            model.addAttribute("user", user);
            model.addAttribute("bmr", bmr);
            model.addAttribute("activityLevelCalories", activityLevelCalories);
            model.addAttribute("dailyCalories", dailyCalories);

            return "userProfile";
        } else {
            // Handle user not found
            return "error";
        }
    }

    @PostMapping("/user/delete")
    public String deleteUserProfile(@RequestParam("email") String email, Model model) {
        // Call the repository method to delete the user by email
        usecase.deleteUserByEmail(email);
        // Redirect to a confirmation page or another appropriate page
        return "redirect:/logout";
    }

    @GetMapping("/user/recipe/{recipeId}")
    public String ShowRecipe(@PathVariable Long recipeId, Model model){
        usecase.findRecipeById(recipeId).ifPresent(recipe -> model.addAttribute("recipe", recipe));
        return "specificRecipe";
    }
    @GetMapping("/user/edit/{email}")
    public String showEditForm(@PathVariable String email, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // The logged-in user's email
        Optional<MyUser> userOptional = usecase.findUserByEmail(userEmail);
        userOptional.ifPresent(user -> model.addAttribute("user", user));
        return "editUser";
    }

    @PostMapping("/user/edit/{email}")
    public String updateUser(@PathVariable String email, MyUser user) {
        // Update the user details
        // Assuming you have a method in your use case to update the user
        usecase.createUpdateUser(user);

        // Redirect to the user profile page or any other appropriate page
        return "redirect:/user/profile";
    }
}



