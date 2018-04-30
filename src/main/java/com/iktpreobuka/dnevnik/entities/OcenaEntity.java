package com.iktpreobuka.dnevnik.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.dnevnik.security.Views;

@Entity
@JsonIgnoreProperties({"hybernateLazyInitializer","handler"})

public class OcenaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Integer id;
	
	@Column
	@JsonView(Views.Public.class)
	private Integer vrednost;
	
	@Column
	@JsonView(Views.Public.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern ="dd-MM-yyyy")
	private Date datum;
	
	@Column
	@JsonView(Views.Public.class)
	private boolean zakljucna;
	
	@Column
	@JsonView(Views.Public.class)
	private Integer polugodiste;
	
	@Version
	private Integer version;
	
	
	@JsonManagedReference
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn( name ="nastavnik")
	private NastavnikEntity nastavnik;
	
	@JsonManagedReference
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn( name ="predmet")
	private PredmetEntity predmet;
	
	@JsonManagedReference
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.LAZY)
	@JoinColumn( name ="ucenik")
	private UcenikEntity ucenik;
	
	
	
	
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
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public NastavnikEntity getNastavnik() {
		return nastavnik;
	}
	public void setNastavnik(NastavnikEntity nastavnik) {
		this.nastavnik = nastavnik;
	}
	public PredmetEntity getPredmet() {
		return predmet;
	}
	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}
	public UcenikEntity getUcenik() {
		return ucenik;
	}
	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}
	
	public OcenaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	

}
