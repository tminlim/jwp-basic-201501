package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DeleteQuestionController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(DeleteQuestionController.class);
	private QuestionDao qDao = new QuestionDao();
	private AnswerDao aDao = new AnswerDao();	

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		logger.debug("at deleteQuestionController questionId is " + questionId);
		
		qDao.delete(questionId);
		aDao.deleteAllByQuestionID(questionId);
		
		List<Question> questionList = qDao.findAll();
		ModelAndView mav = jstlView("redirect:/list.next");
		mav.addObject("questions", questionList);
		
		return mav;
	}

}
