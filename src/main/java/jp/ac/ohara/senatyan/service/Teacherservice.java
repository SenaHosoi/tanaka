package jp.ac.ohara.senatyan.service;



	 
	 
	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import jp.ac.ohara.senatyan.model.TeacherModel;
import jp.ac.ohara.senatyan.repository.TeacherRepository;
	 
	@Service
	@Transactional
	 
	 
	public class Teacherservice implements UserDetailsService {
	 
	 
		
	 
			@Autowired
			private TeacherRepository repository;
			
			@Autowired
			private PasswordEncoder passwordencoder;
	 
			/**
			 * アドレス帳一覧の取得
			 * @return List<oharabank>
			 */
			public List<TeacherModel> getStudentList() {
			    List<TeacherModel> entityList = this.repository.findAll();
			    return entityList;
			}
	 
			/**
			 * 詳細データの取得
			 * @param @NonNull Long index
			 * @return  AddressBook
			 */
			public TeacherModel get(@NonNull Long index) {
				TeacherModel teacher = this.repository.findById(index).orElse(new TeacherModel());
				return teacher;
			}
			
			/**
			 * アドレス帳一覧の取得
			 * @param AddressBook addressBook
			 */
			public void save(@Nonnull TeacherModel teacher) {
				teacher.setPassword(this.passwordencoder.encode(teacher.getPassword()));
				this.repository.save(teacher);
				System.out.println(teacher);
			}
			/**
			 * アドレス帳データの削除
			 * @param @NonNull Long index
			 */
			public void delete(@NonNull Long id) {
				this.repository.deleteById(id);
			}
			public TeacherModel getOneBook(Long id) {
		        // idを指定して本の情報を取得する
				TeacherModel teacher = this.repository.findById(id).orElseThrow();
		        // 画面返却用のFormに値を設定する
		        return teacher;
		    }
	 
		    // 本を更新する
		    public void update(TeacherModel editteacher) {
		        // データベースに登録する値を保持するインスタンスの作成
		        System.out.println(editteacher);
		       
		        
		        // データベースを更新する
		        this.repository.save(editteacher);
		        System.out.println("aaa");
		    }
		   
	 
	 
		    public TeacherModel getStudent(Long id) {
		    	TeacherModel teacher = this.repository.findById(id).orElse(null);
		    	return teacher;
		    	}
		    @Override
	 		public UserDetails loadUserByUsername(String tid)throws UsernameNotFoundException {
	 			System.out.println("serach ID:"+tid);
	 			TeacherModel user = this.repository.findBytidEquals(tid);
	 			System.out.println(user.toString());
	 			return user;
	 		}
		    }
	 		
	 