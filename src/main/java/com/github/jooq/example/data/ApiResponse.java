package com.github.jooq.example.data;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 包装API的返回值
 * @param <T> - 任意类型
 */
@Getter
@Setter
public class ApiResponse<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 成功返回码
	 */
	private static final int SUCCESS_CODE = 200;

	/**
	 * 默认失败错误码
	 */
	private static final int FAIL_DEFAULT_CODE =202;
	
	/**
	 * 成功消息
	 */
	private static final String SUCCESS_MSG = "操作成功";

	/**
	 * 成功或失败的具体信息
	 */
	private String message;
	
	/**
	 * 返回码
	 */
	private int code;
	
	/**
	 * 返回数据
	 */
	private T body;

	/**
	 * 空构造函数
	 */
	public ApiResponse() {
	}

	/**
	 * 构造函数
	 * @param code - 返回码；200成功，其它为失败
	 * @param message - 成功或失败的具体信息
	 * @param body - 返回的数据，如果没有数据则为null
	 */
	public ApiResponse(int code, String message, T body) {
		this();
		this.message = message;
		this.body = body;
		this.code = code;
	}

	/**
	 * 返回成功消息API
	 * @param <T> 任意类型
	 * @param body - 要返回的数据
	 * @return
	 */
	public static <T> ApiResponse<T> success(T body) {
		return new ApiResponse<T>(SUCCESS_CODE, SUCCESS_MSG, body);
	}

	/**
	 * 无返回body成功API
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> success(){
		return new ApiResponse<>(SUCCESS_CODE, SUCCESS_MSG, null);
	}

	/**
	 * 返回成功消息API，指定消息
	 * @param <T> 任意类型
	 * @param message - 成功或失败的具体信息
	 * @param body - 要返回的数据
	 * @return
	 */
	public static <T> ApiResponse<T> success(String message, T body) {
		return new ApiResponse<T>(SUCCESS_CODE, message, body);
	}

	/**
	 * 返回失败消息API，有返回数据
	 * @param <T> - 任意类型
	 * @param code - 返回码
	 * @param message - 返回的消息
	 * @param body - 返回的数据
	 * @return - ApiResponse
	 */
	public static <T> ApiResponse<T> fail(int code, String message, T body) {
		return new ApiResponse<T>(code, message, body);
	}

	/**
	 *
	 * @param apiRetCode
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> fail(ApiRetCode apiRetCode) {
		return new ApiResponse<T>(apiRetCode.getCode(), apiRetCode.getMessage(), null);
	}

	/**
	 * 默认失败返回
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> fail(String message){
		return new ApiResponse<>(FAIL_DEFAULT_CODE, message, null);
	}
	
	/**
	 * 返回失败消息API，无返回数据
	 * @param <T> - 任意类型
	 * @param code - 返回码
	 * @param message - 返回的消息
	 * @return - ApiResponse
	 */
	public static <T> ApiResponse<T> fail(int code, String message) {
		return new ApiResponse<T>(code, message, null);
	}
	
	/**
	 * API调用是否成功
	 * @return - 是否成功
	 */
	public boolean isSuccess() {
		return this.code == SUCCESS_CODE;
	}
}
