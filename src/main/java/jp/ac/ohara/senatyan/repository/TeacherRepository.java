package jp.ac.ohara.senatyan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.ohara.senatyan.model.TeacherModel;

public interface TeacherRepository extends JpaRepository<TeacherModel, Long>{
	TeacherModel findBytidEquals(String tid);
}
