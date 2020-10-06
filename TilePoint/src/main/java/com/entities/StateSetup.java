package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(schema = "tilespoint", name = "state_setup")
public class StateSetup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "state_name")
	private String stateName;
	
	@Column(name = "state_code_numeric")
	private String stateCodeNumeric;
	
	@Column(name = "state_code_alpha")
	private String stateCodeAlpha;

}
