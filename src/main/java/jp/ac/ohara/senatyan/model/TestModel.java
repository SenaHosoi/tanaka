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
@Table(name = "TEST")

public class TestModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 10, nullable = false)
	private String studentNo;
	
	@Column(length = 3, nullable = false)
	private String subjectCd;
	
	@Column(length = 10, nullable = false)
	private String schoolCd;
	
	@Column(length = 3, nullable = false)
	private Integer no;
	
	@Column(length = 10, nullable = false)
	private Integer point;
	
	@Column(length = 5, nullable = false)
	private String classNum;
	
	
}