package com.zettamine.isa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsaSearchCriteria implements SearchCriteria {
	private String recrName;
	private String recrEmail;
	private String recrPassword;
	private String interviewerId;
	private Integer skillId;
	

	

}
