package com.poeicgi.nikosmileweb.models.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="demo_greeting")
public class Greeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;
	
	@JsonProperty(value = "my_super_content")
    private String content;

    public Greeting(Long id, String content) {
        this.id = id;
        this.content = content;
    }
    
    public Greeting(String content) {
        this.content = content;
    }
    
    public Greeting(){
    	
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
