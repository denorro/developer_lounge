package com.denorro.model;

public class ResultingResponse {
	
	public int code;
	
	public String message;
	
	public ResultingResponse() {super();}
	
	public ResultingResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [code=" + code + ", message=" + message + "]";
	}
}
