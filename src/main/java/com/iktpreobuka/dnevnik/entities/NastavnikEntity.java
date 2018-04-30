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
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.dnevnik.entities.OcenaEntity;
import com.iktpreobuka.dnevnik.entities.PredmetEntity;
import com.iktpreobuka.dnevnik.security.Views;
import com.iktpreobuka.dnevnik.entities.OdeljenjeEntity;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class NastavnikEntity {

	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@JsonView(Views.Public.class)
		@Column(nullable=false)
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
		
		
		@JsonBackReference
		@OneToMany(mappedBy = "nastavnik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
		private List<OcenaEntity> ocena = new ArrayList<>();
		
		@JsonView(Views.Public.class)
		@JsonManagedReference
		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
		@JoinTable(name = "Nastavnik_Predmet", joinColumns =
		{@JoinColumn(name = "Nastavnik_id", nullable = false, updatable = false) },
		inverseJoinColumns = { @JoinColumn(name = "Predmet_id",
		nullable = false, updatable = false) })
		private List<PredmetEntity> predmet = new ArrayList<>();
		


		@JsonView(Views.Public.class)
		@JsonManagedReference
		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
		@JoinTable(name = "Nastavnik_Odeljenje", joinColumns =
		{@JoinColumn(name = "Nastavnik_id", nullable = false, updatable = false) },
		inverseJoinColumns = { @JoinColumn(name = "Odeljenje_id",
		nullable = false, updatable = false) })
		private List<OdeljenjeEntity> odeljenje = new ArrayList<>();
		
		public NastavnikEntity() {
			super();
			// TODO Auto-generated constructor stub
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


		public Integer getVersion() {
			return version;
		}
		public void setVersion(Integer version) {
			this.version = version;
		}
		public List<OcenaEntity> getOcena() {
			return ocena;
		}
		public void setOcena(List<OcenaEntity> ocena) {
			this.ocena = ocena;
		}
		public List<PredmetEntity> getPredmet() {
			return predmet;
		}
		public void setPredmet(List<PredmetEntity> predmet) {
			this.predmet = predmet;
		}
		public List<OdeljenjeEntity> getOdeljenje() {
			return odeljenje;
		}


		public void setOdeljenje(List<OdeljenjeEntity> odeljenje) {
			this.odeljenje = odeljenje;
		}


		
		

}
