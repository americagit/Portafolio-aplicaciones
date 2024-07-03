package sust.muro.controllers;

import sust.muro.daos.CommentsDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
  @Autowired
  CommentsDao commentDao;

  @PostMapping("/comments")
  public String createComment(@RequestParam Long message_id, @RequestParam Long user_id, @RequestParam String content) {
    commentDao.create(message_id, user_id, content);

    return "redirect:/";
  }
}
