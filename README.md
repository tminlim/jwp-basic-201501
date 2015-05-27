#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
web.xml의 주석처리된 부분이 어노테이션로 이루어진다.

1)
@WebListener 통해서 next.support.context.ContextLoaderListender
에서  contextInitialized메소드로 서블릿의 가장 큰 영역인 servletcontext
 생성된다(sql문이 addScript실행)

2)
@WebServlet 통해서 
>>dispatcher이름으로 DispatcherServlet 이 실행(먼저 init() Map<String, Controller> url과 컨트롤러 해시로 맵핑되어 초기화 initMapping)

service()로 *.next의 url요청되면 해시로 맵핑된 해당 controller가 찾고,
>>mav = controller.execute(req, resp);
각각의 controller의 execute(요청, 응답)내부의 메소드인 jstlView(url)/jsonView()의 반환값으로 새로운ModelAndView가 생긴다. 
각각 컨트롤러에서는 앞으로 사용될 모델값에 addObject(키, 밸류)를 저장하고 ModelAndView를 mav에 저장하고 반환된다.

>>mav안에 있는 view를 찾고, 
view.render(mav.getModel(), req, resp);

>>View 클래스로부터 상속받은 render(model, req, res) 메소드를 통해 인자값 모델(위의 addObject )로부터 요청객체를 설정하고 RequestDispatcher를 활용해 forward(req, res)로 해당 뷰로 연결이 된다.

요약하자면, 특정 url요청에 따라 매핑된 컨트롤러 excute()통해서 데이터를 감싼 모델과 함께 해당되는 view로 render()된다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
xml파일에서  welcome설정이 없으면 index로 연결되지만, response.sendRedirect("/list.next");로 url이 /list.next로 변경된다
초기에  RequestMapping클래스의 initMapping()로 ListController로 맵핑되었기 때문에 ListController의 execute() 실행될때, 
jstlView("list.jsp”)통해 ModelAndView가 생성된다.

DispatcherServlet을 통하여 render()메소드 안의list.jsp로 forward(request, response)를 통해서 화면이 보여진다.

#### 8. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 

