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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.dnevnik.security.Views;
import com.iktpreobuka.dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.dnevnik.entities.RazredEntity;
import com.iktpreobuka.dnevnik.entities.UcenikEntity;

@Entity
@JsonIgnoreProperties({"hybernateLazyInitializer","handler"})

public class OdeljenjeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Integer id;
	
	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private String oznaka;
	
	@Version
	private Integer version;
	
	@JsonView(Views.Public.class)
	@JsonBackReference
	@OneToMany(mappedBy = "odeljenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<UcenikEntity> ucenik = new ArrayList<>();
	
	@JsonView(Views.Public.class)
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "razred")
	private RazredEntity razred;
	
	@JsonView(Views.Public.class)
	@JsonBackReference
	@ManyToMany(mappedBy = "odeljenje")
	private List<NastavnikEntity> nastavnik = new ArrayList<>();

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public String getOznaka() {
		return oznaka;
	}
	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
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
	public RazredEntity getRazred() {
		return razred;
	}
	public void setRazred(RazredEntity razred) {
		this.razred = razred;
	}
	public List<NastavnikEntity> getNastavnik() {
		return nastavnik;
	}
	public void setNastavnik(List<NastavnikEntity> nastavnik) {
		this.nastavnik = nastavnik;
	}
	public OdeljenjeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
