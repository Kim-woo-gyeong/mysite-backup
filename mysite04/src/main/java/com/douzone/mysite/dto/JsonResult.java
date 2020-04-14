package com.douzone.mysite.dto;

public class JsonResult {
	private String result; // 통신성공 : "success" or "fail"
	private Object data;   // if success, Data set
	private String message; // if fail, error set
	
	
	public JsonResult(){
		
	}
	
	public JsonResult(Object data){
		result = "success";
		this.data = data;
	}
	
	public JsonResult(String message){
		result = "fail";
		this.message = message;
	}
	public static JsonResult success(Object data) {
//		jsonResult.setResult("success");
//		jsonResult.setData(data);
		return new JsonResult(data);
	}
	
	public static JsonResult fail(String message) {
//		jsonResult.setResult("fail");
//		jsonResult.setMessage(message);
		return new JsonResult(message);
	}
	
	public String getResult() {
		return result;
	}
	public Object getData() {
		return data;
	}
	public String getMessage() {
		return message;
	}
	
}
