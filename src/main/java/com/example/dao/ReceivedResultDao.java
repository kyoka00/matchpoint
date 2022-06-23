package com.example.dao;

import java.util.List;

import com.example.entity.ReceivedResult;

public interface ReceivedResultDao {
	public List<ReceivedResult> search(ReceivedResult receivedResult, String keyword);
	public int insert (ReceivedResult receivedResult);
	public int update (ReceivedResult receivedResult);
	public ReceivedResult findByGameInfoId(ReceivedResult receivedResult);
}