package com.iktpreobuka.dnevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminDTO {


	
		@JsonProperty("id")
		private Integer id;
		@JsonProperty("ime")
		private String ime;
		@JsonProperty("prezime")
		private String prezime;
		@JsonProperty("jmbg")
		private String jmbg;
		
		
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
		public String getPrezime() {
			return prezime;
		}
		public void setPrezime(String prezime) {
			this.prezime = prezime;
		}
		public String getJmbg() {
			return jmbg;
		}
		public void setJmbg(String jmbg) {
			this.jmbg = jmbg;
		}
		public AdminDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

	
		
	

	}

