package jp.ac.ohara.senatyan.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.annotation.Nonnull;
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
    		Model model) {
    	// 検索操作の場合
        System.out.println(testaddService.filterStudents(entYear, classNum));
        model.addAttribute("searchedStudents", testaddService.filterStudents(entYear, classNum));
        return "testadd"; // 検索結果のテンプレート名を返す
	}
    @GetMapping("/testaddcomplete/")
    public ModelAndView testaddcomplete(ModelAndView model) {
        model.setViewName("testaddcomplete");
        return model;
    }
    @PostMapping("/testaddcomplete/")
    public String testadd(@Validated @ModelAttribute @Nonnull TestModel testModel, RedirectAttributes redirectAttributes) {
    	try {
    		testaddService.save(testModel);
    		redirectAttributes.addFlashAttribute("exception", "");
    	}catch (Exception e) {
    		redirectAttributes.addFlashAttribute("exception", e.getMessage());
    	}
    	return "redirect:/testaddcomplete/";
    }
}