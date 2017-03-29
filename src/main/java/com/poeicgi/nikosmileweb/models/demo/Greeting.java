package com.poeicgi.nikosmileweb.models.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="demo_greeeting")
public class Greeting {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@JsonInclude
    private Long id=null;

	@JsonProperty(value="mysuper")
    private String content;

	public Greeting(){

	}

	public Greeting(String content)
	{
		this.content = content;
	}

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Greeting setContent (String content){
    	this.content = content;
    	return this;
    }

}
