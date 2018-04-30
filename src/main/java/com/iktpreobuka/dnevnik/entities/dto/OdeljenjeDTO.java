package com.iktpreobuka.dnevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OdeljenjeDTO {
	@JsonProperty("id")
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("oznaka")
	private String oznaka;

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public OdeljenjeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
