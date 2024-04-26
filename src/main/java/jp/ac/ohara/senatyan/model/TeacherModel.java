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
@Table(name = "TEACHER")

public class TeacherModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 30, nullable = false)
	private String password;
	
	@Column(length = 10, nullable = false)
	private String name;
	
	@Column(length = 3, nullable = false)
	private String schoolCd;
	
	
}