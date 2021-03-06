package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class MDeleteAnswerController extends AbstractController {
//	private static final Logger logger = LoggerFactory.getLogger(MDeleteAnswerController.class);
	private AnswerDao aDao = new AnswerDao();
	private QuestionDao qDao = new QuestionDao();
	

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		long answerId = ServletRequestUtils.getLongParameter(request, "answerId");
		Answer answer= aDao.findByAnswerID(answerId);
		long questionId = answer.getQuestionId();
		qDao.downCount(questionId);		
		aDao.deleteByAnswerID(answerId);

		List<Answer> answerList = aDao.findAllByQuestionId(questionId);
		ModelAndView mav = jsonView();
		mav.addObject("answers", answerList);
		
		return mav;
	}
}
