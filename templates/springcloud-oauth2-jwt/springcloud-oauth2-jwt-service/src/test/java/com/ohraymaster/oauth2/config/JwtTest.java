package com.ohraymaster.oauth2.config;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

public class JwtTest {
	@Test
	public void test() {
		String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MzIxNTAyMTEsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiOGMzM2RmMjItMzIyNC00OGY4LTllMjItYWYyYWIxMjhhZmQwIiwiY2xpZW50X2lkIjoiY2xpZW50Iiwic2NvcGUiOlsiYXBwIl19.WKL2d3J9NXXbvehkkYlH0WB_yIKGX70ZRmDJEXVyhUy8HM8TGg7E6Zmgd15iIUMRtXTYcV-3tI9NmT5ryLfaDs1o98iV1VP-L45eA6Kur8-IavoJM7gZ4-bigTo-US3oWLw4Pu8ph2RcHmZ5Yz5DpWf9qHvJQQEq3bfIos-VqqKKnlMxgMb5mTs1NxMX_22rqqTliSagD6RnyP498-xRuiYscE8Od5WKRXhtDhofeo3uWKMnbeLLy6gBUQ9btxzD-NOeDEtmrd7e_Xn6ixZx47CB9_jHsGtASrVG9FAiNaqdN1seIuRIIVy8GsW3TXWQw0k4VQDtoWrAzbx_ojwVWQ";
		Jwt jwt = JwtHelper.decode(token);
		System.out.println(jwt.toString());
	}

}
