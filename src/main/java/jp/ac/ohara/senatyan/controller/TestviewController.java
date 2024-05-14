package jp.ac.ohara.senatyan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.ac.ohara.senatyan.model.TestModel;
import jp.ac.ohara.senatyan.service.TestviewService;

@Controller
public class TestviewController {
	@Autowired
	private TestviewService testviewService;
	
	@GetMapping("/testview/")
	public String view(Model model, TestModel testModel) {
		List<TestModel> tests = testviewService.getTestviewList();
		model.addAttribute("tests", tests);
		model.addAttribute("data",testModel);
		return "testview";
	}
	@PostMapping("/testview/")
    public String handleListActions(
    		@RequestParam(name = "studentNo") String studentNo,
    		Model model) {
    	// 検索操作の場合
		
		// StudentNoを使った絞り込み
		
		// 絞り込んだ成績情報からSubjectCdを取得
		
		// SubjectCdに一致するSubjectNameを取得してマッピング
		
        System.out.println(testviewService.filterStudents(studentNo));
        System.out.println(studentNo);
        model.addAttribute("searchedStudents", testviewService.filterStudents(studentNo));
        model.addAttribute("selectstudentNo",studentNo);
        return "testview"; // 検索結果のテンプレート名を返す
	}
}
