package com.iktpreobuka.dnevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PredmetDTO {
	
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("ime")
	private String ime;
	@JsonProperty("fond")
	private Integer fond;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Integer getFond() {
		return fond;
	}

	public void setFond(Integer fond) {
		this.fond = fond;
	}

	public PredmetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	


}



