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
import com.iktpreobuka.dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.dnevnik.entities.OcenaEntity;
import com.iktpreobuka.dnevnik.entities.RoditeljEntity;

@Entity
@JsonIgnoreProperties({"hybernateLazyInitializer","handler"})
public class UcenikEntity {
	
	
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
	
	@JsonView(Views.Public.class)
	@JsonBackReference
	@ManyToMany(mappedBy = "ucenik", cascade = { CascadeType.ALL })
	private List<RoditeljEntity> roditelj = new ArrayList<>();
	
	@JsonView(Views.Public.class)
	@JsonBackReference
	@OneToMany(mappedBy = "ucenik", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<OcenaEntity> ocena = new ArrayList<>();
	
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "odeljenje")
	private OdeljenjeEntity odeljenje;
	
	
	
	
	
	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}
	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
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
	public List<RoditeljEntity> getRoditelj() {
		return roditelj;
	}
	public void setRoditelj(List<RoditeljEntity> roditelj) {
		this.roditelj = roditelj;
	}
	public List<OcenaEntity> getOcena() {
		return ocena;
	}
	public void setOcena(List<OcenaEntity> ocena) {
		this.ocena = ocena;
	}

	public UcenikEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	 
	
}
