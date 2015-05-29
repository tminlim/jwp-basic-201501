package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

public class MListController extends AbstractController {
	private QuestionDao qDao = new QuestionDao();
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Question> listQuestion = qDao.findAll();
		ModelAndView mav = jsonView();
		mav.addObject("listQuestion", listQuestion);
		return mav;
	}

}
