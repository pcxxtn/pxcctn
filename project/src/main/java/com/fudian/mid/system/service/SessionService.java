package com.fudian.mid.system.service;

import java.util.List;

import com.fudian.mid.system.domain.UserOnline;

public interface SessionService {

	List<UserOnline> list();

	boolean forceLogout(String sessionId);
}
