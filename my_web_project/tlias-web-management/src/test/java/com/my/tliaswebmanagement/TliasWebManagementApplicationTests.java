package com.my.tliaswebmanagement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
class TliasWebManagementApplicationTests {

	@Test
	void contextLoads() {
	}

	/**
	 * uuid test
	 */
	@Test
	public void testUuid(){
		for(int i = 0; i < 1000; i++){
			String uuid = UUID.randomUUID().toString();
			System.out.println(uuid);
		}
	}

	/**
	 * generate Jwt
	 */
	@Test
	public void testGenJwt(){
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("id", 1);
		claims.put("name", "tom");

		String jwt = Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, "myjwt") //签名算法和签名
				.setClaims(claims) //自定义内容（载荷）
				.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置令牌有效期为1h
				.compact();//生成字符串类型返回值
		System.out.println(jwt);
	}

	/**
	 * analysis JWT
	 */
	@Test
	public void testParseJwt(){
		Claims claims = Jwts.parser()
				.setSigningKey("myjwt") //设置签名
				.parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY5OTc2ODI4M30.-cHf7AFuAC6GL5R_2MgkGutby7TbW0bnso_8raROwaY") //输入令牌
				.getBody(); //得到第二部分内容
		System.out.println(claims);
	}

}
