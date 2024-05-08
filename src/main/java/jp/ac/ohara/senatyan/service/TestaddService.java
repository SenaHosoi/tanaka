package jp.ac.ohara.senatyan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.senatyan.model.GakuseiHyou;
import jp.ac.ohara.senatyan.model.TestModel;
import jp.ac.ohara.senatyan.repository.GakuseiRepository;
import jp.ac.ohara.senatyan.repository.TestaddRepository;

 

@Service
@Transactional
public class  TestaddService{

    @Autowired
    private TestaddRepository testaddrepository;
    @Autowired
    private GakuseiRepository repository;

    /**
     * 学生一覧の取得
     * @return List<Testadd>
     */
    public List<TestModel> getTestaddList() {
        List<TestModel> entityList = this.testaddrepository.findAll();
        return entityList;
    }

    /**
     * 詳細データの取得
     * @param @NonNull Long index

     */
    public TestModel get(@NonNull Long index) {
        TestModel test = this.testaddrepository.findById(index).orElse(new TestModel());
        return test;
    }

    public void save(@NonNull TestModel testModel) {
        this.testaddrepository.save(testModel);
    }


  //学生データの削除・編集
    public void delete(@NonNull Long index) {
    		this.repository.deleteById(index);
    	}
    	
        public List<TestModel> getTestList() {
            return testaddrepository.findAll();
        }

        public TestModel getTestById(Long id) {
            return testaddrepository.findById(id).orElse(null);
        }

        public void saveOrUpdateTest(TestModel test) {
            testaddrepository.save(test);
        }

        public void deleteTest(Long id) {
            testaddrepository.deleteById(id);
        }
        
        
    public List<GakuseiHyou> filterStudents(Integer entYear, String classNum) {
        List<GakuseiHyou> students = repository.findAll();
 
        // 入学年度で絞り込み
        if (entYear != null) {
            students = repository.findByEntYear(entYear);
        }
 
     // クラス番号で絞り込み
        if (classNum != null && !classNum.isEmpty()) {
            List<GakuseiHyou> classNumStudents = repository.findByClassNum(classNum);
            students.retainAll(classNumStudents);
        }
 
        return students;
    }

}