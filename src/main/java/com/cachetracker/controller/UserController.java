package com.cachetracker.controller;

import com.cachetracker.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public String getUser(@PathVariable Long id) {
    System.out.println("ssddds");
    return userService.getUserById(id);
  }
}
