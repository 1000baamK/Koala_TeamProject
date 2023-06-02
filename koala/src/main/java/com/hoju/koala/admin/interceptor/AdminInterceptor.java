package com.hoju.koala.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hoju.koala.admin.model.service.AdminService;
import com.hoju.koala.admin.model.vo.BlockIp;
import com.hoju.koala.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	GetClientIp getClientIp;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginUser");
		
		if(member == null) { // �α��� ȸ���� �ƴϰ� �����ڵ� �ƴϴ�. (�������� �����)
			
			String ip = getClientIp.getClientIp(request);
			BlockIp result = adminService.selectBlockIpUser(ip);
			if(result == null) {
				int insertBlockIp = adminService.insertBlockIpUser(ip);
				if(insertBlockIp > 0) {					
					log.info("{} >> ���ο� ������ ����ڷ� ����", ip);
				}
			}else {
				if(result.getCount() >= 5) {
					if(result.getStatus().equals('N')) {
						int block = adminService.blockBlockIpUser(ip);											
					}
					session.setAttribute("BlockUser", "--");
				}else {					
					int updateBlockIp = adminService.updateBlockIpUser(ip);
					log.info("{} >> ������ ����� ���� Ƚ�� ����", ip);
					System.out.println(result.getCount());
				}
			}
			session.setAttribute("alertMsg", "�������� �����ڷ� ���еǾ����ϴ�.\n���� ���Ƚ�� : " + 1 + "ȸ �Դϴ�.");
//			return false;
			return true; // �׽�Ʈ
			
		}
		if(member.getType() != 2) { // �α��� ȸ�������� �����ڰ� �ƴϴ�. (���)
			session.setAttribute("alertMsg", "�����ڰ� �ƴϹǷ� ������ �Ұ����մϴ�.");
			response.sendRedirect("/koala");
//			return false; 
			return true; // �׽�ư
		}else { // ������
			return true;
		}
	}
	
}
