
package com.example.entity;

import java.util.Date;
>>>>>>> fe58be5e56c7d4eb708d1d2723d93b3c57353ebc

import lombok.Data;

@Data

public class Comp{
	private Integer compId;
	private String compName;
	private Date compDate;
	private String compPlace;
	private String compLoginId;
	private Integer tournamentCount;
	private Integer gameType;
	private String gameTypeStr;
	private String memo;
}

