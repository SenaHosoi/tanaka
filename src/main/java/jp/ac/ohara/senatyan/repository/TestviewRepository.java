package jp.ac.ohara.senatyan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.ohara.senatyan.model.TestModel;

public interface TestviewRepository extends JpaRepository<TestModel, Long> {
	List<TestModel> findByStudentNo(String studentNo);
}
