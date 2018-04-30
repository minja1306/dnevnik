package com.iktpreobuka.dnevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iktpreobuka.dnevnik.entities.dto.RoditeljDTO;
import com.iktpreobuka.dnevnik.entities.dto.UcenikDTO;

public class UcenikRoditeljDTO {
	 @JsonProperty("ucenik")
	    private UcenikDTO ucenik;
	    @JsonProperty("roditelj")
	    private RoditeljDTO roditelj;

	    public UcenikRoditeljDTO() {

	    }

	    public UcenikDTO getUcenik() {
	        return ucenik;
	    }

	    public void setUcenik(UcenikDTO ucenik) {
	        this.ucenik = ucenik;
	    }

	    public RoditeljDTO getRoditelj() {
	        return roditelj;
	    }

	    public void setRoditelj(RoditeljDTO roditelj) {
	        this.roditelj = roditelj;
	    }
	

}
