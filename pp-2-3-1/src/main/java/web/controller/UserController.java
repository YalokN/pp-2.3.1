package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.config.AppConfig;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String printUsers(ModelMap model) {
/*		List<User> messages = new ArrayList<>();
		messages.add(new User("ara", "bara"));
		messages.add(new User("ara", "bara"));
		messages.add(new User("ara", "bara"));*/
		List<User> messages = userService.listUser();
		model.addAttribute("messages", messages);
		return "index";
	}

	@GetMapping(value = "/new")
	public String newUser(ModelMap model) {
		model.addAttribute("user", new User());
		return "new";
	}

	@PostMapping()
	public String createNewUser(@ModelAttribute("user") User user) {
		userService.addUser(user);
		return "redirect:/";
	}

	@GetMapping(value = "/del")
	public String delUser(ModelMap model) {
		model.addAttribute("userDel", new User());
		return "del";
	}

	@DeleteMapping("")
	public String deletedUser(@ModelAttribute("userDel") User userDel) {
		userService.delUser(userDel.getId());
		return "redirect:/";
	}

	@GetMapping(value = "/edit")
	public String editUser(ModelMap model) {
		model.addAttribute("userEdit", new User());
		return "edit";
	}

	@PatchMapping("")
	public String editedUser(@ModelAttribute("userEdit") User userEdit) {
		userService.changeUser(userEdit);
		return "redirect:/";
	}


}