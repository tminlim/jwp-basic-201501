package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class SaveController extends AbstractController {
	private static final Logger logger = LoggerFactory
			.getLogger(SaveController.class);
	private QuestionDao qDao = new QuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String btnName = ServletRequestUtils.getStringParameter(request,"button");
		logger.debug(btnName);

		if (btnName.equals("수정하기")) {
			long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
			String writer = ServletRequestUtils.getStringParameter(request, "writer");
			String title = ServletRequestUtils.getStringParameter(request, "title");
			String contents = ServletRequestUtils.getStringParameter(request, "contents");
			Question question = new Question(writer, title, contents);
			logger.debug(question.toString());
			logger.debug("showController questionId : {}", questionId);
			
			qDao.edit(questionId, question.getTitle(), question.getWriter(),
					question.getContents());
		} else {
			String writer = ServletRequestUtils.getStringParameter(request, "writer");
			String title = ServletRequestUtils.getStringParameter(request, "title");
			String contents = ServletRequestUtils.getStringParameter(request,"contents");
			Question question = new Question(writer, title, contents);
			logger.debug(question.toString());
			
			qDao.insert(question);
		}

		List<Question> questionList = qDao.findAll();
		ModelAndView mav = jstlView("redirect:/list.next");
		mav.addObject("questions", questionList);

		return mav;
	}
}
