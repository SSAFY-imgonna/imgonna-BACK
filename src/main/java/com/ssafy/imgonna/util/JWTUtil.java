package com.ssafy.imgonna.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import com.ssafy.imgonna.exception.member.InvalidTokenException;
import com.ssafy.imgonna.exception.member.UnAuthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JWTUtil {

	@Value("${jwt.salt}")
	private String salt;

	@Value("${jwt.access-token.expiretime}")
	private long accessTokenExpireTime;

	@Value("${jwt.refresh-token.expiretime}")
	private long refreshTokenExpireTime;

	/**
	 * AccessToken 생성
	 *
	 * @param id 회원 아이디
	 * @return 생성한 AccessToken
	 */
	public String createAccessToken(String id) {
		return create(id, "access-token", accessTokenExpireTime);
	}

	/**
	 * RefreshToken 생성 (AccessToken에 비해 유효기간을 길게 설정)
	 * 
	 * @param id 회원 아이디
	 * @return 생성한 RefreshToken
	 */
	public String createRefreshToken(String id) {
		return create(id, "refresh-token", refreshTokenExpireTime);
	}

	/**
	 * Token을 생성하는 메서드
	 *
	 * jwt 토큰의 구성 : header + payload + signature
	 * 
	 * @param id 사용자아이디
	 * @param subject payload에 sub의 value로 들어갈 subject값
	 * @param expireTime 토큰 유효기간 설정을 위한 값
	 * @return 생성한 jwt 토큰
	 */
	private String create(String id, String subject, long expireTime) {
		Claims claims = Jwts.claims()
			.setSubject(subject) // 토큰 제목 설정 ex) access-token, refresh-token
			.setIssuedAt(new Date()) // 생성일 설정
			.setExpiration(new Date(System.currentTimeMillis() + expireTime)); // 만료일 설정 (유효기간)

		claims.put("id", id);

        return Jwts.builder()
			.setHeaderParam("type", "JWT").setClaims(claims) // Header 설정 : 토큰의 타입, 해쉬 알고리즘 정보 세팅.
			.signWith(SignatureAlgorithm.HS256, this.generateKey()) // Signature 설정 : secret key를 활용한 암호화.
			.compact();
	}

	/**
	 * Signature 설정에 들어갈 key 생성
	 *
	 * @return 생성한 key
	 */
	private byte[] generateKey() {
		byte[] key = null;
		try {
//			charset 설정 안하면 사용자 플랫폼의 기본 인코딩 설정으로 인코딩 됨.
			key = salt.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
//			if (log.isInfoEnabled()) {
				e.printStackTrace();
//			} else {
//				log.error("Making JWT Key Error ::: {}", e.getMessage());
//			}
		}
		return key;
	}
	
//	전달 받은 토큰이 제대로 생성된것인지 확인 하고

	/**
	 * 전달 받은 토큰이 제대로 생성된 것인지 확인
	 * 
	 * @param token 확인할 토큰
	 * @return 유효 생성 토큰 여부
	 */
	public boolean isValidToken(String token) {
		try {
//			Json Web Signature: 서버에서 인증을 근거로 인증 정보를 서버의 private key로 서명 한것을 토큰화 한 것
//			setSigningKey : JWS 서명 검증을 위한  secret key 세팅
//			parseClaimsJws : 파싱하여 원본 jws 만들기
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
//			Claims 는 Map의 구현체 형태
			return true;
		} catch (Exception e) {
			throw new InvalidTokenException();
		}
	}

	/**
	 *  문제가 있다면 UnauthorizedException을 발생
	 * @param authorization
	 * @return
	 */
	public String getUserId(String authorization) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(authorization);
		} catch (Exception e) {
			throw new UnAuthorizedException();
		}
		Map<String, Object> value = claims.getBody();
		return (String) value.get("userId");
	}

}
