package jp.ac.ohara.senatyan.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.ac.ohara.senatyan.model.GakuseiHyou;


@Repository
public interface GakuseiRepository extends JpaRepository<GakuseiHyou, Long>{
	List<GakuseiHyou> findByEntYear(Integer entYear);
    List<GakuseiHyou> findByClassNum(String classNum);
    List<GakuseiHyou> findByIsAttend(Boolean isAttend);

}