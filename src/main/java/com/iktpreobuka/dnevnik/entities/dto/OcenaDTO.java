package com.iktpreobuka.dnevnik.entities.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OcenaDTO {
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("vrednost")
	private Integer vrednost;
	@JsonProperty("datum")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date datum;
	@JsonProperty("polugodiste")
	private Integer polugodiste;
	@JsonProperty("zakljucna")
	private boolean zakljucna;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getVrednost() {
		return vrednost;
	}


	public void setVrednost(Integer vrednost) {
		this.vrednost = vrednost;
	}


	public Date getDatum() {
		return datum;
	}


	public void setDatum(Date datum) {
		this.datum = datum;
	}


	public boolean isZakljucna() {
		return zakljucna;
	}


	public void setZakljucna(boolean zakljucna) {
		this.zakljucna = zakljucna;
	}


	public Integer getPolugodiste() {
		return polugodiste;
	}


	public void setPolugodiste(Integer polugodiste) {
		this.polugodiste = polugodiste;
	}


	public OcenaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	
}
