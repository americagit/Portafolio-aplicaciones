package sust.el_muro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import sust.el_muro.models.User;
import sust.el_muro.models.UserRepository;

@Component
public class UserDao {

  @Autowired
  JdbcTemplate tpl;

  @Autowired
  UserRepository userRepo;

  public User create(String username, String name, String password) {
    User u = new User();
    u.setUsername(username);
    u.setName(name);
    u.setPassword(password);
    userRepo.save(u);

    return u;
  }
}
