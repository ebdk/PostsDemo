package com.Posts.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.Posts.demo.models.Comentario;
import com.Posts.demo.models.DaoComentario;
import com.Posts.demo.models.DaoPost;
import com.Posts.demo.models.DaoUser;
import com.Posts.demo.models.Post;
import com.Posts.demo.models.User;

@Controller
@SessionAttributes("userSession")
public class MainController {
	
	@Autowired
	DaoUser daoUser;
	
	@Autowired
	DaoPost daoPost;
	
	@Autowired
	DaoComentario daoComentario;

	public MainController() {
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
	User user = new User();
	String str = "";
	model.addAttribute("user", user);
	model.addAttribute("mensaje", str);
		return "homepage";
	}
	
	@RequestMapping(value = "/loggeo", method = RequestMethod.POST)
	public String loggeo(Model model, @ModelAttribute User user) {

	String str;
	String userN = user.getUsername();
	String passW = user.getPassword();
	
	List<User> busquedaNombre = daoUser.findByusername(userN);
	if(busquedaNombre.size()==0){
		str = "Usuario no existia y fue creado existosamente";
		User user2 = new User(userN,passW);
		daoUser.save(user2);
	} else {
		User userExistente = busquedaNombre.get(0);
		if(userExistente.getPassword().equals(passW)){
			model.addAttribute("mensaje", "Bienvenido " + userExistente.getUsername());
			model.addAttribute("avatar", userExistente.getImgUrl());
			model.addAttribute("todosLosPosts", daoPost.findAll());
			model.addAttribute("userSession", userExistente);
			return "listaPosts";
		} else {
			str = "Contraseña incorrecta";
		}
	}
	User user1 = new User();
	model.addAttribute("user", user1);
	model.addAttribute("mensaje", str);
	
		return "homepage";
	}
	
	@RequestMapping(value = "/editaravatar", method = RequestMethod.POST)
	public String editarAvatar(Model model, @ModelAttribute("userSession") User user) {

		daoUser.save(user);
		
		
		model.addAttribute("mensaje", "Bienvenido " + user.getUsername());
		model.addAttribute("avatar", user.getImgUrl());
		model.addAttribute("todosLosPosts", daoPost.findAll());
		model.addAttribute("user", user);

		return "listaPosts";
	}
	
	@RequestMapping(value = "/crearpost", method = RequestMethod.POST)
	public String crearPost(Model model, @ModelAttribute("userSession") User user,
			@RequestParam String titulo,
			@RequestParam String url,
			@RequestParam String contenido) {

		java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		Post pos = new Post(titulo,sqlDate,url);
		Comentario com = new Comentario(contenido,sqlDate, pos,user);
		pos.addComentario(com);
		daoPost.save(pos);
		daoComentario.save(com);
		//user.addComentario(com);
		daoUser.save(user);
		
		model.addAttribute("mensaje", "Bienvenido " + user.getUsername());
		model.addAttribute("avatar", user.getImgUrl());
		model.addAttribute("todosLosPosts", daoPost.findAll());
		model.addAttribute("user", user);
		
		return "listaPosts";
	}
	
	@RequestMapping(value = "/accederpost", method = RequestMethod.GET)
	public String accederPost(Model model, @ModelAttribute("userSession") User user, @RequestParam Long id) {

		model.addAttribute("pos", daoPost.findOne(id));
		return "listaComentarios";
	}
	
	@RequestMapping(value = "/crearcomentario", method = RequestMethod.POST)
	public String crearComentario(Model model, @ModelAttribute("userSession") User user,
			@ModelAttribute Post post, @RequestParam String contenido) {

		System.out.println("Que pasa aca");
		java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		System.out.println(user.getUsername());
		Comentario com = new Comentario(contenido,sqlDate, post,user);
		post.addComentario(com);
		//daoPost.save(post);
		daoComentario.save(com);
		//user.addComentario(com);
		//daoUser.save(user);
		
		
		model.addAttribute("pos", daoPost.findOne(post.getId()));
		return "listaComentarios";
	}
}