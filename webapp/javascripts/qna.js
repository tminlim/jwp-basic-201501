var formList = document.querySelectorAll('.answerWrite input[type=submit]');
for ( var j=0 ; j < formList.length ; j++) {
	formList[j].addEventListener('click', writeAnswers, false);
}
var btnDels = document.querySelectorAll(".comments a");
for (var d = 0; d < btnDels.length; d++) {
	btnDels[d].addEventListener('click', deleteAnswer, false);
	console.log("click on " + btnDels[d]);
}

function writeAnswers(e) {
	 e.preventDefault();
	 
	 var answerForm = e.currentTarget.form;
	 var url = "/api/addanswer.next";
	 var params = "questionId=" + answerForm[0].value + "&writer=" + answerForm[1].value + "&contents=" + answerForm[2].value;

	 var request = new XMLHttpRequest();
	 request.open("POST", url, true);
	 request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 
	 request.onreadystatechange = function() {
		 if(request.readyState == 4 && request.status == 200) {
			 location.reload(true);//?
		 }
	 }
	 
	 request.send(params);
}

function deleteAnswer(e){
	 e.preventDefault();
	 
	 var url = "/api/delete.next";
	 var answerId = e.target.getAttribute("name");
	 url = url+"?answerId="+answerId;
	 console.log(url);
	 
	 var request = new XMLHttpRequest();
	 request.open("GET", url, true);
	 
	 request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 
	 request.onreadystatechange = function() {
		 if(request.readyState == 4 && request.status == 200) {
			 location.reload(true);
		 }
	 }
	 
	 request.send();	
	
}