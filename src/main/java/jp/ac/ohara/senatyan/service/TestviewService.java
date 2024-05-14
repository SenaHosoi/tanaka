package jp.ac.ohara.senatyan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.senatyan.model.TestModel;
import jp.ac.ohara.senatyan.repository.TestviewRepository;

@Service
@Transactional
public class TestviewService {
	@Autowired
	private TestviewRepository repository;
	
	/**
     * 学生一覧の取得
     * @return List<Testview>
     */
    public List<TestModel> getTestviewList() {
        List<TestModel> entityList = this.repository.findAll();
        return entityList;
    }
    
    /**
     * 詳細データの取得
     * @param @NonNull Long index

     */
    public TestModel get(@NonNull Long index) {
        TestModel test = this.repository.findById(index).orElse(new TestModel());
        return test;
    }

    public void save(@NonNull TestModel testModel) {
        this.repository.save(testModel);
    }
    
    public List<TestModel> filterStudents(String studentNo) {
        List<TestModel> students = repository.findAll();
 
        // 学生番号で絞り込み
        if (studentNo != null) {
            students = repository.findByStudentNo(studentNo);
        }
 
        return students;
    }
    
    //科目コードで科目名を持ってくる
}
