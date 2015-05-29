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

public class MDeleteQuestionController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(MDeleteQuestionController.class);
	private QuestionDao qDao = new QuestionDao();
	private AnswerDao aDao = new AnswerDao();		

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		logger.debug("at mobileDeleteQuestionController questionId is " + questionId);
		
		qDao.delete(questionId);
		aDao.deleteAllByQuestionID(questionId);
		String resultMsg = "questionId: " + questionId + " is deleted successfully";

		List<Question> questionList = qDao.findAll();
		ModelAndView mav = jsonView();
		mav.addObject("questions", questionList);
		mav.addObject("resultMsg", resultMsg);
		
		return mav;
	}
}
