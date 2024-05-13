package jp.ac.ohara.senatyan.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.ohara.senatyan.model.GakuseiHyou;
import jp.ac.ohara.senatyan.model.TeacherModel;
import jp.ac.ohara.senatyan.model.TestModel;
import jp.ac.ohara.senatyan.service.GakuseiService;
import jp.ac.ohara.senatyan.service.TestaddService;
 
@Controller
public class TestaddController {
	
    @Autowired
    private TestaddService testaddService;
    @Autowired
	private GakuseiService gakuseiService;
    
    @GetMapping("/testadd/")
    public String index(Model model,GakuseiHyou gakuseihyou) {
        List<TestModel> tests = testaddService.getTestList();
        model.addAttribute("tests", tests);
        model.addAttribute("data",gakuseihyou);
        System.out.println(tests);
        return "testadd";
    }
    @PostMapping("/testadd/")
    public String handleListActions(
    		@RequestParam(name = "entYear") Integer entYear,
    		@RequestParam(name = "classNum") String classNum,
    		@RequestParam(name = "subjectCd") String subjectCd,
    		@RequestParam(name = "no") Integer no,
    		@AuthenticationPrincipal TeacherModel teachermodel,
    		
    		Model model) {
    	// 検索操作の場合
        System.out.println(testaddService.filterStudents(entYear, classNum));
        System.out.println(no);
        System.out.println(subjectCd);
        model.addAttribute("searchedStudents", testaddService.filterStudents(entYear, classNum));
        model.addAttribute("selectno",no);
        model.addAttribute("selectsubjectCd",subjectCd);
        model.addAttribute("schoolcd",teachermodel.getSchoolCd());
        System.out.print(teachermodel.getSchoolCd());
        return "testadd"; // 検索結果のテンプレート名を返す
	}
    @PostMapping("/testaddcomplete/")
    public String save(Model model, @ModelAttribute("TestModel") TestModel testModel, HttpServletRequest request) {
    	//フォームで受け取った値を受け取りカンマ区切りで配列にする
    	System.out.println("ここからすぷりっと");
    	System.out.println(testModel);
    	String[]studentNos = testModel.getStudentNo().split(",");
    	String[]schoolCds = testModel.getSchoolCd().split(",");
    	String[]subjectCds = testModel.getSubjectCd().split(",");
    	String[]classNums = testModel.getClassNum().split(",");
    	String[]nos = request.getParameterValues("no");
    	String[]points = request.getParameterValues("point");
    	
    	for(int i = 0; studentNos.length > i; i++) {
    		System.out.println(i);
    		TestModel newTestModel = new TestModel();
    		newTestModel.setStudentNo(studentNos[i]);
    		newTestModel.setSchoolCd(schoolCds[i]);
    		newTestModel.setSubjectCd(subjectCds[i]);
    		newTestModel.setClassNum(classNums[i]);
    		newTestModel.setNo(Integer.parseInt(nos[i]));
    		newTestModel.setPoint(Integer.parseInt(points[i]));//intに変換
    		// 保存されるnewTestModelの確認
    		System.out.println(newTestModel);
    		testaddService.save(newTestModel);
    	}
    	return "redirect:/testaddcomplete/";
    }
    @GetMapping("/testaddcomplete/")
    public ModelAndView testaddcomplete(ModelAndView model) {
        model.setViewName("testaddcomplete");
        return model;
    }
   
}