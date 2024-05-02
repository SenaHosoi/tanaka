package jp.ac.ohara.senatyan.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.ac.ohara.senatyan.model.TeacherModel;
import jp.ac.ohara.senatyan.repository.TeacherRepository;
 
 
@Service
public class StudentDetailsServiceImplt implements UserDetailsService {
 
    @Autowired
    private TeacherRepository userRepository; // ユーザモデルのRepository
 
    /**
     * ユーザの検索を行う
     */
    @Override
    
    public UserDetails loadUserByUsername(String tid) throws UsernameNotFoundException {
        System.out.println("serach tid : " + tid);
        TeacherModel teacher = this.userRepository.findBytidEquals(tid); // emailで検索するので「EmailEquals」としている
        //System.out.println(teacher.toString());
        return teacher;
    }
}