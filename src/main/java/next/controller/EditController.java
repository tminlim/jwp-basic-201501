package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class EditController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	private QuestionDao qDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		logger.debug("editController questionId : {}", questionId);

		Question selectedQusetion = qDao.findById(questionId);
		ModelAndView mav = jstlView("form.jsp");
		mav.addObject("selectedQusetion", selectedQusetion);
		
		return mav;
	}

}
