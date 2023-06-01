package com.hoju.koala.admin.model.vo;

import java.sql.Date;

import com.hoju.koala.member.model.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supporters extends Member{
	/*
	 * Member ��� ���� ����
	 * Supporters�� ������ ���̽����� Member�� �����ϰ� �ִ�.
	 * view�� ������� �ϴ� ������ USER_NO(= refUNO), NICKNAME, GITHUB_ID, CREATE_DATE
	 * NICKNAME�� ���� �� ���� �� �ִ� ������ �ʿ������� �̷������� ����� ���� VO������ ��谡 ��ȣ������ ����.
	 * �׷��� ������ "���"�� �����߰� Mybatis�� resultMap ���� ����� ������ ������ �����߰� ������ �¾Ҵ�.*/
	private int refUno;
	private String githubId;
	private Date createDate;
}