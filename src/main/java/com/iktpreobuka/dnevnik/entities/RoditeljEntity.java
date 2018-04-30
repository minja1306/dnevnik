package com.iktpreobuka.dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.dnevnik.security.Views;
import com.iktpreobuka.dnevnik.entities.UcenikEntity;


@Entity
@JsonIgnoreProperties({"hybernateLazyInitializer","handler"})
public class RoditeljEntity {

	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@JsonView(Views.Public.class)
		private Integer id;
		@Column(nullable=false)
		@JsonView(Views.Public.class)
		private String ime;
		@Column(nullable=false)
		@JsonView(Views.Public.class)
		private String prezime;
		@Column(nullable=false)
		@JsonView(Views.Public.class)
		private String jmbg;
		
		@Version
		private Integer version;
		
		@JsonManagedReference
		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
		@JoinTable(name = "Roditelj_Ucenik", joinColumns =
		{@JoinColumn(name = "Roditelj_id", nullable = false, updatable = false) },
		inverseJoinColumns = { @JoinColumn(name = "Ucenik_id",
		nullable = false, updatable = false) })
		private List<UcenikEntity> ucenik = new ArrayList<>();
		
		
		
		
		public Integer getVersion() {
			return version;
		}
		public void setVersion(Integer version) {
			this.version = version;
		}
		public List<UcenikEntity> getUcenik() {
			return ucenik;
		}
		public void setUcenik(List<UcenikEntity> ucenik) {
			this.ucenik = ucenik;
		}
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
		public RoditeljEntity() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		

		
	}


