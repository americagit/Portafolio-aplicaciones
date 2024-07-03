package sust.tv_shows.controllers;

import sust.tv_shows.models.Network;
import sust.tv_shows.models.NetworkRepository;
import sust.tv_shows.models.Show;
import sust.tv_shows.models.ShowRepository;
import sust.tv_shows.services.ShowsDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShowsController {

  @Autowired
  ShowsDao showDao;

  @Autowired
  ShowRepository repo;

  @Autowired
  NetworkRepository netRepo;

  // showScreen
  @GetMapping("/shows")
  public ModelAndView showsScreen() {
    ModelAndView vista = new ModelAndView("shows.html");
    List<Show> shows = showDao.findAll();
    // vista.addObject("shows", shows);
    vista.addObject("shows", shows);
    return vista;
  }

  @GetMapping("/shows/new")
  public ModelAndView createShowsScreen() {
    ModelAndView vista = new ModelAndView("new_show.html"); //
    // me tarigo las cadenas que tengo en la base de dato
    List<Network> networks = netRepo.findAll();
    // lo devuelvo a la vista
    vista.addObject("networks", networks);
    return vista;
  }

  // createShow
  // FLAT PARA DAR MENSAJE AL USUARIO
  @PostMapping(value = "/shows/create")
  public String createShow(@RequestParam String title, @RequestParam String release_date,
      @RequestParam Long network_id, @RequestParam String description, RedirectAttributes redAt) {

    showDao.create(title, description, release_date, network_id);
    redAt.addFlashAttribute("bien", "El show ha sido creado correctamente");
    return "redirect:/shows";
  }

  @GetMapping("/shows/{id}/destroy")
  public String deleteById(@PathVariable long id) {
    // 1. Borramos el show por id
    repo.deleteById(id);
    // 2. Redirigimos a la pantalla de SHOWS
    return "redirect:/shows";
  }

  // al apretar el lapiz funcion
  @GetMapping("/shows/{id}/edit")
  public ModelAndView editShowScreen(@PathVariable long id) {
    // 1.creamos la vista
    ModelAndView view = new ModelAndView("edit.html");
    // 2. Recuperamos el show por id
    Show s = repo.findById(id).get();
    // 3. añadimos el show a la vista
    view.addObject("show", s);
    return view;
  }
  // al html a crear el comlpletado del for opciones del chanel

  @PostMapping("/shows/{id}/edit")
  public String editShow(@PathVariable Long id, @RequestParam String title, @RequestParam String description,
      @RequestParam int network_id, @RequestParam String release_date, RedirectAttributes redAt) {
    // 1. Recupero el Show a partir de su ID
    Show s = repo.findById(id).get();
    // 2. Lo modifico
    s.setTitle(title);
    s.setDescription(description);
    s.setRelease_date(release_date);
    // s.setNetwork(network);
    // 3. Lo guardo
    repo.save(s);
    // 5. Redirijo a /shows
    return "redirect:/shows";
    // 4. Agregamos mensaje de FEEDBACK
    // redAt.addFlashAttribute("bien", "Show actualizado correctamente");
    // 5. Redirijo a /shows
  }

}

// @PostMapping("/shows/{id}/edit")
// public String editShow(@PathVariable Long id, @RequestParam String title,
// @RequestParam String description,
// @RequestParam String network, @RequestParam String release_date,
// RedirectAttributes redAt) {
// // 1. Recupero el Show a partir de su ID
// Show s = repo.findById(id).get();
// // 2. Lo modifico
// s.setTitle(title);
// s.setDescription(description);
// s.setRelease_date(release_date);
// s.setNetwork(network);
// // 3. Lo guardo
// repo.save(s);
// // 4. Agregamos mensaje de FEEDBACK
// redAt.addFlashAttribute("bien", "Show actualizado correctamente");
// // 5. Redirijo a /shows
// return "redirect:/shows";
// }

// deleteById(ID id)
// existsById(ID id)

// funcion de eliminar
// @DeleteMapping("/shows/{id}")
// public String deleteShow(@PathVariable Long id) {
// // Busca la serie que se va a eliminar por su id
// Show showToDelete = repo.findById(id).orElse(null);
// // Si se encontró la serie, se elimina
// if (showToDelete != null) {
// repo.delete(showToDelete);
// // Redirige al usuario a la página de listado de series
// return "redirect:/shows";
// } else {
// // Si no se encuentra la serie, se devuelve un mensaje de error
// return "error: la serie no se pudo encontrar";
// }

// package sust.tv_shows.controllers;

// import java.util.Date;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import sust.tv_shows.models.Show;
// import sust.tv_shows.models.ShowRepository;

// @Controller
// public class ShowsController {

// @Autowired
// ShowRepository repo;

// @GetMapping("/shows")
// public ModelAndView showsScreen() {
// ModelAndView vista = new ModelAndView("shows.html");
// List<Show> shows = repo.findAll();
// vista.addObject("shows", shows);
// return vista;
// }

// @GetMapping("/shows/new")
// public ModelAndView createShowsScreen() {
// ModelAndView vista = new ModelAndView("new_show.html");
// return vista;
// }

// @PostMapping(value = "/shows/create")
// public String createShow(@RequestParam String title, @RequestParam String
// release_date,
// @RequestParam String network, @RequestParam String description,
// RedirectAttributes redAt) {
// Show s = new Show();
// s.setTitle(title);
// s.setRelease_date(release_date);
// s.setNetwork(network);
// s.setDescription(description);
// repo.save(s);
// redAt.addFlashAttribute("bien", "El show ha sido creado correctamente");
// return "redirect:/shows";
// }

// @GetMapping("/shows/{id}/destroy")
// public String deletebyId(@PathVariable Long id) {
// // 1. Borramos el Show con ID
// repo.deleteById(id);
// // 2. Redirigimos a la pantalla de SHOWS
// return "redirect:/shows";
// }

// @GetMapping("/shows/{id}/edit")
// public ModelAndView editShowScreen(@PathVariable Long id) {
// // 1. Creamos la vista
// ModelAndView view = new ModelAndView("edit.html");
// // 2. Recuperamos el show a partir de su ID
// Show s = repo.findById(id).get();
// // 3. Añadimos el show a la vista
// view.addObject("show", s);
// return view;
// }

// @PostMapping("/shows/{id}/edit")
// public String editShow(@PathVariable Long id, @RequestParam String title,
// @RequestParam String description,
// @RequestParam String network, @RequestParam String release_date,
// RedirectAttributes redAt) {
// // 1. Recupero el Show a partir de su ID
// Show s = repo.findById(id).get();
// // 2. Lo modifico
// s.setTitle(title);
// s.setDescription(description);
// s.setRelease_date(release_date);
// s.setNetwork(network);
// // 3. Lo guardo
// repo.save(s);
// // 4. Agregamos mensaje de FEEDBACK
// redAt.addFlashAttribute("bien", "Show actualizado correctamente");
// // 5. Redirijo a /shows
// return "redirect:/shows";
// }
// }
