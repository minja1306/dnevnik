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
import com.iktpreobuka.dnevnik.security.Views;
import com.iktpreobuka.dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.dnevnik.entities.PredmetEntity;

@Entity
@JsonIgnoreProperties({"hybernateLazyInitializer","handler"})

public class RazredEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Integer id;
	
	@Column(nullable=false)
	@JsonView(Views.Public.class)
	private Integer godina;
	
	@Version
	private Integer version;
	
	@JsonBackReference
	@OneToMany(mappedBy = "razred", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<OdeljenjeEntity> odeljenje = new ArrayList<>();
	
	@JsonManagedReference
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Razred_Predmet", joinColumns =
	{@JoinColumn(name = "Razred_id", nullable = false, updatable = false) },
	inverseJoinColumns = { @JoinColumn(name = "Predmet_id",
	nullable = false, updatable = false) })
	private List<PredmetEntity> predmet = new ArrayList<>();
	
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGodina() {
		return godina;
	}
	public void setGodina(Integer godina) {
		this.godina = godina;
	}
	public List<OdeljenjeEntity> getOdeljenje() {
		return odeljenje;
	}
	public void setOdeljenje(List<OdeljenjeEntity> odeljenje) {
		this.odeljenje = odeljenje;
	}
	public List<PredmetEntity> getPredmet() {
		return predmet;
	}
	public void setPredmet(List<PredmetEntity> predmet) {
		this.predmet = predmet;
	}
	public RazredEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
