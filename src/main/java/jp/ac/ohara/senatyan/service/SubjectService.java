package jp.ac.ohara.senatyan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ac.ohara.senatyan.model.GakuseiHyou;
import jp.ac.ohara.senatyan.model.SubjectModel;
import jp.ac.ohara.senatyan.repository.SubjectRepository;

 

@Service
@Transactional
public class  SubjectService{

    @Autowired
    private SubjectRepository repository;

    /**
     * 学生一覧の取得
     * @return List<Subject>
     */
    public List<SubjectModel> getSubjectList() {
        List<SubjectModel> entityList = this.repository.findAll();
        return entityList;
    }

    /**
     * 詳細データの取得
     * @param @NonNull Long index

     */
    public SubjectModel get(@NonNull Long index) {
        SubjectModel gakusei = this.repository.findById(index).orElse(new SubjectModel());
        return gakusei;
    }

    public void save(@NonNull SubjectModel subjectmodel) {
        this.repository.save(subjectmodel);
    }


  //科目データの削除・編集
    public void delete(@NonNull Long index) {
    		this.repository.deleteById(index);
    	}
    	
        public List<SubjectModel> getSubjectList1() {
            return repository.findAll();
        }

        public SubjectModel getSubjectBycd(Long cd) {
            return repository.findById(cd).orElse(null);
        }

        public void saveOrUpdateStudent(SubjectModel subject) {
            repository.save(subject);
        }

        public void deleteStudent(Long id) {
            repository.deleteById(id);
        }
        
    public List<SubjectModel> filter(Integer entYear, String classNum, Boolean isAttend) {
        List<SubjectModel> subjects = repository.findAll();
 
        // 入学年度で絞り込み
        if (entYear != null) {
        	subjects = repository.findByEntYear(entYear);
        }
 
        // クラス番号で絞り込み
        if (classNum != null && !classNum.isEmpty()) {
            List<GakuseiHyou> classNumStudents = repository.findByClassNum(classNum);
            students.retainAll(classNumStudents);
        }
 
        // 在学状況で絞り込み
        if (isAttend != null) {
            List<GakuseiHyou> isAttendStudents = repository.findByIsAttend(isAttend);
            students.retainAll(isAttendStudents);
        }
 
        return students;
    }

}