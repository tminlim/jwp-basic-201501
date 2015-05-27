package next.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class AddAnswerController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	private AnswerDao aDao = new AnswerDao();
	private QuestionDao qDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");			
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		String contents = ServletRequestUtils.getStringParameter(request, "contents");
		
		Answer answer = new Answer(writer,contents,questionId);
		logger.debug(answer.toString());
		
		aDao.insert(answer);

		List<Answer> answerList = aDao.findAllByQuestionId(answer.getQuestionId());
		ModelAndView mav = jsonView();
		mav.addObject("answers", answerList);
		
		qDao.addingCount(answer.getQuestionId());		
		return mav;
	}

}
