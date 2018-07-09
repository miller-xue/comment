package com.miller.dto;

import com.miller.constant.ApiCodeEnum;
import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@JsonInclude(Include.NON_NULL)
public class ApiCodeDto {

	private Integer errno;

	private String msg;

	private String token;

	private String code;

	public ApiCodeDto() {

	}

	public ApiCodeDto(Integer errno, String msg) {
		this.errno = errno;
		this.msg = msg;
	}

	public ApiCodeDto(ApiCodeEnum apiCodeEnum) {
		this.errno = apiCodeEnum.getErrno();
		this.msg = apiCodeEnum.getMsg();
	}

}
