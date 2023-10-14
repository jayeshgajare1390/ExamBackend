package net.examsection.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="strengthsTable")
public class BlocksAndStrengths {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private long id;
	@Column(name="blocks")
	private int blocks;
	@Column(name="strengths")
	private int strengths;
	@Column(name="Building")
	private String Building;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BlocksAndStrengths() {
		
	}

	public BlocksAndStrengths(long id, int blocks, int strengths) {
		super();
		this.id = id;
		this.blocks = blocks;
		this.strengths = strengths;
	}

	public int getBlocks() {
		return blocks;
	}

	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}

	public int getStrengths() {
		return strengths;
	}

	public void setStrengths(int strengths) {
		this.strengths = strengths;
	}

	public String getBuilding() {
		return Building;
	}

	public void setBuilding(String building) {
		Building = building;
	}
	
		
		
		

}
