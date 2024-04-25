package jp.ac.ohara.senatyan.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.senatyan.model.SubjectModel;


@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel, Long>{
	List<SubjectModel> findBycd(String cd);
    List<SubjectModel> findByname(String name);

}