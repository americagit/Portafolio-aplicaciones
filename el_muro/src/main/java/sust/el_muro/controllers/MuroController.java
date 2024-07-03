package sust.el_muro.controllers;

import jakarta.servlet.http.HttpSession;
import sust.el_muro.models.MessageRepository;
import sust.el_muro.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MuroController {

  // @GetMapping("/")
  // public ModelAndView muroScreen() {
  // ModelAndView vista = new ModelAndView("muro.html");

  // return vista;
  // }

  @Autowired
  MessageRepository messageRepo;

  @GetMapping("/logout")
  public String logout(@RequestParam String param) {
      return new String();
  }
  



  @GetMapping("/")
  public Object muroScreen(HttpSession session) {
    ModelAndView vista = new ModelAndView("muro.html");
    // 1. Nos traemos el usuario de la sesión
    User u = (User) session.getAttribute("user");
    if (u == null) {
      // si el usuario es null, significa que no está logueado
      return "redirect:/login";
    }

    return vista;

  }
}
