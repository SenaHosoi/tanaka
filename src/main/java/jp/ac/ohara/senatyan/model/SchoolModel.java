package jp.ac.ohara.senatyan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "SCHOOL")

public class SchoolModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 3, nullable = false)
	private String schoolCd;
	
	@Column(length = 3, nullable = false)
	private String cd;
	
	@Column(length = 20, nullable = false)
	private String name;
	
}