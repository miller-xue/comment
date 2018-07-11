package com.miller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class OrderForBuyDto {
	
	// 商户主键
	private Long id;
	// 登录成功后的token
	private String token;
	// 消费人数
	private Integer num;
	// 消费金额
	private Double price;
	// 会员手机号
	private Long username;
}