package jp.ac.ohara.senatyan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.senatyan.model.TestModel;

@Repository
public interface TestaddRepository extends JpaRepository<TestModel, Long> {
	List<TestModel> findBySubjectCd(String subjectCd);
	List<TestModel> findByClassNum(String classNum);
	List<TestModel> findByNo(Integer no);
	
}
