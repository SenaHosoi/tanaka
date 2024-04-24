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
@Table(name = "STUDENT")
	
public class GakuseiHyou {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 10, nullable = false)
	private String no;
 
	@Column(length = 10, nullable = true)
	private String name;
	
	@Column(length = 10, nullable = true)
	private Integer entYear;
	
	@Column(length = 3, nullable = true)
	private String classNum;
	
	@Column(nullable = true)
	private Boolean isAttend;
	
	@Column(length = 3, nullable = true)
	private String schoolCd;
}
