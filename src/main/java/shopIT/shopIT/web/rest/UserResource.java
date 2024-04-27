package shopIT.shopIT.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shopIT.shopIT.dtos.UserProfileDTO;
import shopIT.shopIT.dtos.UserRegisterDTO;
import shopIT.shopIT.services.UserService;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserResource {

    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";

    private final UserService userService;

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @PostMapping("/login-error")
//    public String login(
//        @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
//        RedirectAttributes redirectAttributes
//    ) {
//        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
//        redirectAttributes.addFlashAttribute("bad_credentials", true);
//
//        return "redirect:/users/login";
//    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
        @Valid @RequestBody UserRegisterDTO userRegisterDTO,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
//        if (bindingResult.hasErrors() || !userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
//            redirectAttributes
//                .addFlashAttribute("userRegisterDTO", userRegisterDTO)
//                .addFlashAttribute(BINDING_RESULT_PATH + "userRegisterDTO", bindingResult);
//
//            return "redirect:/users/register";
//        }

        userService.register(userRegisterDTO);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(
        Model model,
        Principal principal
    ) {
        UserProfileDTO user = userService.getUserProfile(principal.getName());

        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile/{username}/edit")
    public String editProfile(
        @PathVariable("username") String username,
        Model model
    ) {
        model.addAttribute("user", userService.findByUsername(username));
        return "user-ed";
    }

    @PostMapping("/profile/{username}/edit")
    public String editProfile(
        @Valid UserProfileDTO user,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PATH + "user", bindingResult);

            return "redirect:/users/profile/edit";
        }

        userService.update(user);

        return "redirect:/users/profile";
    }

    @ModelAttribute(name = "userRegisterDTO")
    public UserRegisterDTO initUserRegisterDTO() {
        return new UserRegisterDTO();
    }
}
