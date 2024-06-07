package com.example.demo.Entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Data
@SessionScope
@Component
public class Account implements Serializable {
	//フィールド
	private String name;

	//コンストラクタ
	public Account() {

	}

	public Account(String name) {
		super();
		this.name = name;
	}

}
