package com.insa.data.vo;

public class InsaVO {
	
	int sabun;
	String joinDate;
	String retireDate;
	String putYn;
	String name;
	String regNo;
	String engName;
	String phone;
	String hp;
	String carrier;
	String posGbnCode;
	String cmpRegNo;
	String cmpRegImage;
	String sex;
	int years;
	String email;
	int zip;
	String addr1;
	String addr2;
	String deptCode;
	String joinGbnCode;
	String id;
	String pwd;
	int salary;
	String kosaRegYn;
	String kosaClassCode;
	String milYn;
	String milType;
	String milLevel;
	String milStartDate;
	String milEndDate;
	String jobType;
	String gartLevel;
	String selfIntro;
	String crmName;
	String profile;
	public InsaVO() {
		super();
	}
	//INSERT INTO insa VALUES (     
	//auto_sabun.nextval     , ?     , ?     , ?     , ?     , ?     ,
	// ?     , ?     , ?     , ?     , ?     , ?     , 
	// ?     , ?     , ?     , ?     , ?     , ?     ,
	// ?     , ?     , ?     , ?     , ?     , ?     ,
	// ?     , ?     , ?     , ?     , ?     , ?     ,
	// ?     , ?     , ?     , ?     , ?     , ?    )
	public InsaVO(int sabun, String joinDate, String retireDate, String putYn, String name, String regNo,
			String engName, String phone, String hp, String carrier, String posGbnCode, String cmpRegNo,
			String cmpRegImage, String sex, int years, String email, int zip, String addr1, String addr2,
			String deptCode, String joinGbnCode, String id, String pwd, int salary, String kosaRegYn,
			String kosaClassCode, String milYn, String milType, String milLevel, String milStartDate, String milEndDate,
			String jobType, String gartLevel, String selfIntro, String crmName, String profile) {
		super();
		this.sabun = sabun;
		this.joinDate = joinDate;
		this.retireDate = retireDate;
		this.putYn = putYn;
		this.name = name;
		this.regNo = regNo;
		this.engName = engName;
		this.phone = phone;
		this.hp = hp;
		this.carrier = carrier;
		this.posGbnCode = posGbnCode;
		this.cmpRegNo = cmpRegNo;
		this.cmpRegImage = cmpRegImage;
		this.sex = sex;
		this.years = years;
		this.email = email;
		this.zip = zip;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.deptCode = deptCode;
		this.joinGbnCode = joinGbnCode;
		this.id = id;
		this.pwd = pwd;
		this.salary = salary;
		this.kosaRegYn = kosaRegYn;
		this.kosaClassCode = kosaClassCode;
		this.milYn = milYn;
		this.milType = milType;
		this.milLevel = milLevel;
		this.milStartDate = milStartDate;
		this.milEndDate = milEndDate;
		this.jobType = jobType;
		this.gartLevel = gartLevel;
		this.selfIntro = selfIntro;
		this.crmName = crmName;
		this.profile = profile;
	}
	public int getSabun() {
		return sabun;
	}
	public void setSabun(int sabun) {
		this.sabun = sabun;
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
	public String getPutYn() {
		return putYn;
	}
	public void setPutYn(String putYn) {
		this.putYn = putYn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getPosGbnCode() {
		return posGbnCode;
	}
	public void setPosGbnCode(String posGbnCode) {
		this.posGbnCode = posGbnCode;
	}
	public String getCmpRegNo() {
		return cmpRegNo;
	}
	public void setCmpRegNo(String cmpRegNo) {
		this.cmpRegNo = cmpRegNo;
	}
	public String getCmpRegImage() {
		return cmpRegImage;
	}
	public void setCmpRegImage(String cmpRegImage) {
		this.cmpRegImage = cmpRegImage;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getJoinGbnCode() {
		return joinGbnCode;
	}
	public void setJoinGbnCode(String joinGbnCode) {
		this.joinGbnCode = joinGbnCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getKosaRegYn() {
		return kosaRegYn;
	}
	public void setKosaRegYn(String kosaRegYn) {
		this.kosaRegYn = kosaRegYn;
	}
	public String getKosaClassCode() {
		return kosaClassCode;
	}
	public void setKosaClassCode(String kosaClassCode) {
		this.kosaClassCode = kosaClassCode;
	}
	public String getMilYn() {
		return milYn;
	}
	public void setMilYn(String milYn) {
		this.milYn = milYn;
	}
	public String getMilType() {
		return milType;
	}
	public void setMilType(String milType) {
		this.milType = milType;
	}
	public String getMilLevel() {
		return milLevel;
	}
	public void setMilLevel(String milLevel) {
		this.milLevel = milLevel;
	}
	public String getMilStartDate() {
		return milStartDate;
	}
	public void setMilStartDate(String milStartDate) {
		this.milStartDate = milStartDate;
	}
	public String getMilEndDate() {
		return milEndDate;
	}
	public void setMilEndDate(String milEndDate) {
		this.milEndDate = milEndDate;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getGartLevel() {
		return gartLevel;
	}
	public void setGartLevel(String gartLevel) {
		this.gartLevel = gartLevel;
	}
	public String getselfIntro() {
		return selfIntro;
	}
	public void setselfIntro(String selfIntro) {
		this.selfIntro = selfIntro;
	}
	public String getCrmName() {
		return crmName;
	}
	public void setCrmName(String crmName) {
		this.crmName = crmName;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	@Override
	public String toString() {
		return "InsaVO [sabun=" + sabun + ", joinDate=" + joinDate + ", retireDate=" + retireDate + ", putYn=" + putYn
				+ ", name=" + name + ", regNo=" + regNo + ", engName=" + engName + ", phone=" + phone + ", hp=" + hp
				+ ", carrier=" + carrier + ", posGbnCode=" + posGbnCode + ", cmpRegNo=" + cmpRegNo + ", cmpRegImage="
				+ cmpRegImage + ", sex=" + sex + ", years=" + years + ", email=" + email + ", zip=" + zip + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", deptCode=" + deptCode + ", joinGbnCode=" + joinGbnCode + ", id=" + id
				+ ", pwd=" + pwd + ", salary=" + salary + ", kosaRegYn=" + kosaRegYn + ", kosaClassCode="
				+ kosaClassCode + ", milYn=" + milYn + ", milType=" + milType + ", milLevel=" + milLevel
				+ ", milStartDate=" + milStartDate + ", milEndDate=" + milEndDate + ", jobType=" + jobType
				+ ", gartLevel=" + gartLevel + ", selfIntro=" + selfIntro + ", crmName=" + crmName + ", profile="
				+ profile + "]";
	}
	
}
