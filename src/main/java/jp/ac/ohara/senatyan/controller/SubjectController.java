package jp.ac.ohara.senatyan.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import  org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.micrometer.common.lang.NonNull;
import jp.ac.ohara.senatyan.model.GakuseiHyou;
import jp.ac.ohara.senatyan.model.SubjectModel;
import jp.ac.ohara.senatyan.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	

	
	@GetMapping("/kamokuadd/")
	public ModelAndView add( SubjectModel subjectmodel, ModelAndView model) {
		model.addObject("subjectmodel", subjectmodel);
		model.setViewName("kamokuadd");
		return model;
	}
	
	
	
	@PostMapping("/kamokuadd/")
	public String add(@Validated @ModelAttribute @NonNull SubjectModel subjectmodel, RedirectAttributes result,ModelAndView model,
			RedirectAttributes redirectAttributes) {
		try {
			this.subjectService.save(subjectmodel);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "tourokukannryou";

	}
	
	// 削除を行う
	@GetMapping("/delete")
	public String delete(Model model, SubjectModel subjectmodel) {
			
	    // データベースのデータを削除する
	    sbjectService.delete(subjectmodel.getId());

	    // 本の一覧画面にリダイレクト
	    return "redirect:/";
	}
	
	
	// 学生情報の編集機能の追加(のちにedit_student.htmlという編集ページつくる)
	@GetMapping("/top/edit/{id}")
	public String editStudent(@PathVariable Long id, Model model) {
	    GakuseiHyou student = gakuseiService.getStudentById(id);
	    model.addAttribute("student", student);
	    
	    // 入学年度の選択肢を設定(あんま気にしないで)
	    List<Integer> years = Arrays.asList(2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033, 2034);
	    model.addAttribute("years", years);
	    
	    // クラス番号の選択肢を設定(これもあんま気にしないで)
	    List<String> classNumbers = Arrays.asList("101", "102", "103", "104", "105", "201", "202", "203", "204", "205", "301", "302", "303", "304", "305");
	    model.addAttribute("classNumbers", classNumbers);
	    
	    return "edit_student";
	}
	
	@GetMapping("/kamokukanri/")
	public String kamokukanri(Model model) {
		return "kamokukanri";
	}
	
	@GetMapping("/kamokuadd/")
	public String kamokuadd(Model model) {
		return "kamokuadd";
	}
}

	