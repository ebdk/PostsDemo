package com.Posts.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")

public class User {

	//ATTIRBUTES
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	private String password;
	private String imgUrl;
	
	//@OneToMany(cascade=CascadeType.ALL, mappedBy = "user")
	@OneToMany(mappedBy = "user")
	private List<Comentario> comentarios;
	

	//CONSTRUCTORS
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		//comentarios = new ArrayList<Comentario>();
	}
	public User() {
		super();
		//comentarios = new ArrayList<Comentario>();
	}


	//GETTERS AND SETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	//METHODS
	public void addComentario(Comentario comentario){
		this.getComentarios().add(comentario);
	}
}
