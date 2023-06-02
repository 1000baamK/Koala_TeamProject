package com.hoju.koala.admin.model.service;

import java.util.ArrayList;

import com.hoju.koala.admin.model.vo.AllCount;
import com.hoju.koala.admin.model.vo.CreateSetting;
import com.hoju.koala.admin.model.vo.Supporters;
import com.hoju.koala.board.model.vo.ErrorBoard;

public interface AdminService {
	// �����ڰ� �����ؾ� �ϴ� �Խ��� �� ȸ�� ���� ��ȸ
	AllCount selectAllCount();

	// �������� ȸ�� ������ ���� ��ü ��ȸ
	ArrayList<Supporters> selectSupporters();

	// ���̺귯�� �� ���� ���� �� ��� ������ �Խñ� 
	ArrayList<CreateSetting> selectCreateSetting();

	// ���� �Խ��� �� �ذ���� ���� ���� �Խñ�
	ArrayList<ErrorBoard> selectErrorBoard();
}
