package sust.el_muro.controllers;

import jakarta.servlet.http.HttpSession;
import sust.el_muro.models.User;
import sust.el_muro.services.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

  @Autowired
  UserDao userDao;

  @GetMapping("/login")
  public String loginPage() {
    return "login.html";
  }

  @GetMapping("/register")
  public String registerPage() {
    return "register.html";
  }

  @PostMapping("/register")
  public String register(@RequestParam String username, @RequestParam String name, @RequestParam String password,
      @RequestParam String passConfirm, RedirectAttributes redAt, HttpSession session) {
    // 1. Si las contraseñas no coinciden, mandamos una alerta y redirigimos de
    // vuelta al formulario
    if (!password.equals(passConfirm)) {
      redAt.addFlashAttribute("mal", "Las contraseñas no coinciden");
      return "redirect:/register";
    }
    // 2. Si todo esta 'OK', creamos el usuario
    User u = userDao.create(username, name, password);

    // 3. Guardamos el usuario en sesión
    session.setAttribute("user", u);

    // 4. Mandamos al usuario al HOME
    redAt.addFlashAttribute("bien", "Registro exitoso");

    return "redirect:/";
  }

}

// @GetMapping("/login")
// public String loginPage() {
// return "login.html";
// }

// @GetMapping("/register")
// public String registerPage() {
// return "register.html";
// }