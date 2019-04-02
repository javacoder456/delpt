//将form中的值转换为键值对 传入jqeury对象
function fngetFormJson(frm){
	var queryArray = frm.serializeArray();  
	var jsonString= '{';  
	for (var i = 0; i < queryArray.length; i++) {  
	    jsonString+= JSON.stringify(queryArray[i].name) + ':' + JSON.stringify(queryArray[i].value) + ',';  
	}  
	jsonString = jsonString.substring(0, (jsonString.length - 1));  
	jsonString += '}';
	return JSON.parse(jsonString); 
};


