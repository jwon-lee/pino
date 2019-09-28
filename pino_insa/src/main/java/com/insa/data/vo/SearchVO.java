package com.insa.data.vo;

public class SearchVO {

	String sabun;
	String name;
	String joinGbnCode;
	String putYn;
	String posGbnCode;
	String joinDate;
	String retireDate;
	String jobType;
	int pn;
	int offset;
	int maxRow;
	int lastPn;
	public SearchVO() {
		super();
	}
	public SearchVO(String sabun, String name, String joinGbnCode, String putYn, String posGbnCode, String joinDate,
			String retireDate, String jobType, int pn, int offset, int maxRow, int lastPn) {
		super();
		this.sabun = sabun;
		this.name = name;
		this.joinGbnCode = joinGbnCode;
		this.putYn = putYn;
		this.posGbnCode = posGbnCode;
		this.joinDate = joinDate;
		this.retireDate = retireDate;
		this.jobType = jobType;
		this.pn = pn;
		this.offset = offset;
		this.maxRow = maxRow;
		this.lastPn = lastPn;
	}
	public String getSabun() {
		return sabun;
	}
	public void setSabun(String sabun) {
		this.sabun = sabun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJoinGbnCode() {
		return joinGbnCode;
	}
	public void setJoinGbnCode(String joinGbnCode) {
		this.joinGbnCode = joinGbnCode;
	}
	public String getPutYn() {
		return putYn;
	}
	public void setPutYn(String putYn) {
		this.putYn = putYn;
	}
	public String getPosGbnCode() {
		return posGbnCode;
	}
	public void setPosGbnCode(String posGbnCode) {
		this.posGbnCode = posGbnCode;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getRetireDate() {
		return retireDate;
	}
	public void setRetireDate(String retireDate) {
		this.retireDate = retireDate;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public int getPn() {
		return pn;
	}
	public void setPn(int pn) {
		this.pn = pn;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getMaxRow() {
		return maxRow;
	}
	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}
	public int getLastPn() {
		return lastPn;
	}
	public void setLastPn(int lastPn) {
		this.lastPn = lastPn;
	}
	@Override
	public String toString() {
		return "SearchVO [sabun=" + sabun + ", name=" + name + ", joinGbnCode=" + joinGbnCode + ", putYn=" + putYn
				+ ", posGbnCode=" + posGbnCode + ", joinDate=" + joinDate + ", retireDate=" + retireDate + ", jobType="
				+ jobType + ", pn=" + pn + ", offset=" + offset + ", maxRow=" + maxRow + ", lastPn=" + lastPn + "]";
	}
	
}
