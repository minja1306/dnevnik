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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.dnevnik.security.Views;
import com.iktpreobuka.dnevnik.entities.RazredEntity;
import com.iktpreobuka.dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.dnevnik.entities.OcenaEntity;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class PredmetEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Integer id;
	
	@Column(nullable=false)
	@JsonView(Views.Public.class)
	private String ime;
	
	@Column(nullable=false)
	@JsonView(Views.Public.class)
	private Integer fond;
	
	@Version
	private Integer version;
	
	
	
	@JsonBackReference
	@ManyToMany(mappedBy = "predmet")
	private List<NastavnikEntity> nastavnik = new ArrayList<>();
	
	@JsonBackReference
	@ManyToMany(mappedBy = "predmet")
	private List<RazredEntity> razred = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "predmet", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<OcenaEntity> ocena = new ArrayList<>();
	
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
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public List<NastavnikEntity> getNastavnik() {
		return nastavnik;
	}
	public List<RazredEntity> getRazred() {
		return razred;
	}
	public void setRazred(List<RazredEntity> razred) {
		this.razred = razred;
	}
	public void setNastavnik(List<NastavnikEntity> nastavnik) {
		this.nastavnik = nastavnik;
	}
	public List<OcenaEntity> getOcena() {
		return ocena;
	}
	public void setOcena(List<OcenaEntity> ocena) {
		this.ocena = ocena;
	}
	
	

	public PredmetEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
