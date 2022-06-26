//package com.example.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.dao.ReceivedResultDao;
//import com.example.dao.TeamDao;
//import com.example.entity.ReceivedResult;
//import com.example.entity.Team;
//
//@RestController
//public class GameModeRestController{
//	@Autowired
//	HttpSession session;
//	@Autowired
//	ReceivedResultDao receivedResultDao;
//	@Autowired
//	TeamDao teamDao;
//
//	@RequestMapping("searchMember")
//	public List<Team> searchMember(@RequestParam("gameNo") Integer gameNo) {
//		ReceivedResult receivedResult = new ReceivedResult();
//		//あとから変えるやつ
//		//compIDはセッションに保存されているやつを使う
//		receivedResult.setCompId(1);
//		receivedResult.setGameNo(gameNo);
//		List<ReceivedResult> list = receivedResultDao.searchMatch(receivedResult);
//		if(list == null) {
//			return null;
//		}
//		List<Team> resultList = teamDao.gameTeam(list.get(0).getTeamIdA(), list.get(0).getTeamIdB());
//		return resultList;
//	}
//}