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
@Table(name = "SUBJECT")

public class SubjectModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column(name = "CD",length = 3, nullable = false)
	private String cd;
	
	@Column(name = "NAME",length = 20, nullable = false)
	private String name;
	
	@Column(length = 3, nullable = true)
	private String schoolCd;
	
	
}