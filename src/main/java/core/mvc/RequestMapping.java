package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.MAddAnswerController;
import next.controller.MDeleteAnswerController;
import next.controller.DeleteQuestionController;
import next.controller.EditController;
import next.controller.ListController;
import next.controller.MDeleteQuestionController;
import next.controller.SaveController;
import next.controller.ShowController;
import next.controller.MListController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	private Map<String, Controller> mappings = new HashMap<String, Controller>();
	
	public void initMapping() {
		mappings.put("/list.next", new ListController());
		mappings.put("/show.next", new ShowController());
		mappings.put("/form.next", new ForwardController("form.jsp"));
		mappings.put("/editForm.next", new EditController());
		mappings.put("/save.next", new SaveController());
		mappings.put("/deleteForm.next", new DeleteQuestionController());
		
		mappings.put("/api/deleteQuestion.next", new MDeleteQuestionController());
		mappings.put("/api/addAnswer.next", new MAddAnswerController());
		mappings.put("/api/deleteAnswer.next", new MDeleteAnswerController());
		mappings.put("/api/list.next", new MListController());
	
		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}

}
