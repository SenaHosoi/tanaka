package jp.ac.ohara.senatyan.controller;
 
import java.util.List;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.annotation.Nonnull;
import jp.ac.ohara.senatyan.model.SubjectModel;
import jp.ac.ohara.senatyan.service.SubjectService;
 
@Controller
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @GetMapping("/subjecttop/")
    public String index(Model model) {
        List<SubjectModel> subjects = subjectService.getSubjectList();
        model.addAttribute("subjects", subjects);
        System.out.println(subjects);
        return "subjecttop";
    }
    // 科目の新規登録
    @GetMapping("/subjectadd/")
    public ModelAndView showAddForm(ModelAndView model) {
        model.addObject("subjectModel", new SubjectModel());
        model.setViewName("subjectadd");
        return model;
    }
    @PostMapping("/subjectadd/")
    public String add(@Validated @ModelAttribute @Nonnull SubjectModel subjectModel, RedirectAttributes redirectAttributes) {
        try {
        	
        	subjectService.save(subjectModel);
            redirectAttributes.addFlashAttribute("exception", "");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("exception", e.getMessage());
        }
        return "redirect:/subjectaddcomplete/";
    }
    @GetMapping("/subjectaddcomplete/")
    public ModelAndView addcomplete(ModelAndView model) {
        model.setViewName("subjectaddcomplete");
        return model;
    }
    @GetMapping("/subjectchange/{id}/")
    public String change(@PathVariable(name = "id") Long id, Model model) {
    	SubjectModel subject = subjectService.getOneBook(id);
        model.addAttribute("subject", subject);
        return "subjectchange";
    }
    @PostMapping("/subjectchange/{id}")
    public String update(@PathVariable(name = "id") Long id, @ModelAttribute @Validated SubjectModel subject, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "subjectchange/" + id + "/";
        }
        subjectService.update(subject);
        return "redirect:/subjectchangecomplete/"; // 更新完了画面にリダイレクト
    }
 
    @GetMapping("/subjectchangecomplete/")
    public ModelAndView changecomplete(ModelAndView model) {
        model.setViewName("subjectchangecomplete");
        return model;
    }
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable(name = "id")Long id, Subject subject, ModelAndView model) {
		this.subjectService.delete(id);
		model.setViewName("subjectdelete");
		return model;
	}
	
//	@GetMapping("/Subjectadd/")
//	public String Subjectadd(Model model) {
//		return "Subjectadd";
//	}
}