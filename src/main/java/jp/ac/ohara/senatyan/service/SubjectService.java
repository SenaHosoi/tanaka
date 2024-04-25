package jp.ac.ohara.senatyan.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.senatyan.model.SubjectModel;
import jp.ac.ohara.senatyan.repository.SubjectRepository;
 
@Service
@Transactional
public class SubjectService {
 
	@Autowired
	private SubjectRepository subjectRepository;
 
 
	/**
	 * ユーザー一覧の取得
	 * @return List<SubjectModel>
	 */
	public List<SubjectModel> getSubjectList() {
	    List<SubjectModel> entityList = subjectRepository.findAll();
	    return entityList;
	}
 
	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	 * @return  SubjectModel
	 */
	public SubjectModel get(@NonNull Long index) {
		SubjectModel subject = subjectRepository.findById(index).orElse(new SubjectModel());
		return subject;
	}
 
	/**
	 * データの保存
	 * @param SubjectModel subjectModel
	 */
	public void save(@NonNull SubjectModel subjectModel) {
		System.out.println(subjectModel);
		subjectRepository.save(subjectModel);
	}
 
	/**
	 * データの削除
	 * @param @NonNull Long index
	 */
	public void delete(@NonNull Long index) {
		subjectRepository.deleteById(index);
	}
	 // 受け取ったidからデータを取得して、Formを返却する
    public SubjectModel getOneBook(Long index) {
        // idを指定して本の情報を取得する
    	SubjectModel subject = subjectRepository.findById(index).orElseThrow();
        // 画面返却用のFormに値を設定する
    	/*
        Student editstudent = new Student();
        editstudent.setNAME(student.getNAME());
        editstudent.setCLASS_NUM(student.getCLASS_NUM());
		*/
        return subject;
    }
// 本を更新する
    public void update(SubjectModel editsubject) {
        // データベースに登録する値を保持するインスタンスの作成
        //Student student = new Student();
        // 画面から受け取った値を設定する
    	/*
        student.setId(editstudent.getId());
        student.setNAME(editstudent.getNAME());
        student.setCLASS_NUM(editstudent.getCLASS_NUM());
        */
        // データベースを更新する
    	subjectRepository.save(editsubject);
    }
}