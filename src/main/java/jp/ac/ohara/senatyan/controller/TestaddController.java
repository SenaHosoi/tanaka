package jp.ac.ohara.senatyan.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.ohara.senatyan.model.GakuseiHyou;
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
    public String index(Model model) {
        List<TestModel> tests = testaddService.getTestList();
        model.addAttribute("tests", tests);
        System.out.println(tests);
        return "testadd";
    }
    @PostMapping("/testadd/")
    public String handleListActions(
    		@RequestParam(name = "entYear", required = false) Integer entYear,
    		@RequestParam(name = "classNum", required = false) String classNum,
    		@RequestParam(name = "isAttend", required = false) Boolean isAttend,
    		Model model) {
    	// 検索操作の場合
		
        List<GakuseiHyou> students = gakuseiService.filterStudents(entYear, classNum, isAttend);
        System.out.println("検索結果: " + students);
        model.addAttribute("searchedStudents", students);
        return "testadd"; // 検索結果のテンプレート名を返す
	}
    
    @GetMapping("/testaddcomplete/")
    public ModelAndView testaddcomplete(ModelAndView model) {
        model.setViewName("testaddcomplete");
        return model;
    }
	
}