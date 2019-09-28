package com.insa.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.insa.data.vo.InsaComVO;
import com.insa.data.vo.InsaVO;
import com.insa.data.vo.SearchVO;
import com.insa.service.IInsaSVC;

@Controller
public class InsaController {

	@Autowired
	private IInsaSVC isSvc;
	
	@RequestMapping(value = "/insa_form.do", method = RequestMethod.GET)
	public ModelAndView showInsaForm(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1", required = false) int pn) {
		
		ModelAndView mav = new ModelAndView();
		
		SearchVO search = new SearchVO();
		search.setPn(pn);
		int maxPn = 0;
		System.out.println("pn : " + pn);
//		System.out.println("maxPn : " + maxPn);
		List<InsaVO> insaList = null;
		
		String sabun = request.getParameter("sabun");
		String name = request.getParameter("name");
		String joinYn = request.getParameter("join_yn");
		String putYn = request.getParameter("put_yn");
		String posGbnCode = request.getParameter("pos_gbn_code");
		String joinDate = request.getParameter("join_date");
		String retireDate = request.getParameter("retire_date");
		String jobType = request.getParameter("job_type");
		
		System.out.println("sabun : " + sabun + ", name : " + name + ", joinYn : " + joinYn + ", putYn : " + putYn + ", posGbnCode : " + posGbnCode
				+ ", joinDate : " + joinDate + ", retireDate : " +retireDate + ", jobType : " + jobType);
		
		if(sabun == null || sabun.isEmpty() || sabun.equals("")) {
			sabun = "%";
		}
		if(name == null || name.isEmpty() || name.equals("")) {
			name = "%";
		}
		if(joinYn == null || joinYn.isEmpty() || joinYn.equals("") || joinYn.equals("(선택)")) {
			joinYn = "%";
		}
		if(putYn == null || putYn.isEmpty() || putYn.equals("") || putYn.equals("(선택)")) {
			putYn = "%";
		}
		if(posGbnCode == null || posGbnCode.isEmpty() || posGbnCode.equals("") || posGbnCode.equals("(선택)")) {
			posGbnCode = "%";
		}
		if(jobType == null || jobType.isEmpty() || jobType.equals("") || jobType.equals("(선택)")) {
			jobType = "%";
		}
		
		
		search.setName(name);
		search.setJoinGbnCode(joinYn);
		search.setPutYn(putYn);
		search.setPosGbnCode(posGbnCode);
		search.setJobType(jobType);
		search.setSabun(sabun);
		
		Date dt = new Date();
		SimpleDateFormat toDate = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat toString = new SimpleDateFormat("yy/mm/dd");
		
		if(joinDate == null || joinDate.isEmpty() || joinDate.equals("") || joinDate.equals(" ")) {
			joinDate = "%";
			search.setJoinDate(joinDate);
			System.out.println("null or '' : " + search.getJoinDate());
		}else {
			try {
				dt = toDate.parse(joinDate);
				joinDate = toString.format(dt);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			search.setJoinDate(joinDate);
			System.out.println("not null : " + search.getJoinDate());
		}
		
		
		
		if(retireDate == null || retireDate.isEmpty() || retireDate.equals("") || retireDate.equals(" ")) {
			retireDate = "%";
			search.setRetireDate(retireDate);
		}else {
			try {
				dt = toDate.parse(retireDate);
				retireDate = toString.format(dt);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			search.setRetireDate(retireDate);
			System.out.println("not null : " + search.getRetireDate());
		}
//		System.out.println("sabun : " + sabun + ", name : " + name + ", joinYn : " + joinYn + ", putYn : " + putYn + ", posGbnCode : " + posGbnCode
//				+ ", joinDate : " + joinDate + ", retireDate : " +retireDate + ", jobType : " + jobType);
		try {
			System.out.println("search : " + search);
//			maxPn = isSvc.computeMaxPageNumber(search);
//			insaList = isSvc.showAllInsaInfomation(search);
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
//			insaList = null;
		}catch(NullPointerException e) {
			e.printStackTrace();
//			insaList = null;
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
//			insaList = null;
		}finally {
			System.out.println("search2 : " + search);
			maxPn = isSvc.computeMaxPageNumber(search);
			insaList = isSvc.showAllInsaInfomation(search);
		}
//		System.out.println("insa_form.do의 insaList : " + insaList);
		
//		for(InsaVO insa : insaList) {
//			System.out.println(insa);
//		}
		
//		System.out.println(search);
		
		List<InsaComVO> jobList = isSvc.showCom("job_type");
		List<InsaComVO> ptList = isSvc.showCom("put_yn");
		List<InsaComVO> posList = isSvc.showCom("pos_gbn");
		List<InsaComVO> joinList = isSvc.showCom("join_yn");
		
//		for(InsaVO i : insaList) {
//			System.out.println("정보 : " + i);
//		}
		
		mav.addObject("jobList", jobList);
		mav.addObject("ptList", ptList);
		mav.addObject("posList", posList);
		mav.addObject("joinList", joinList);
		
		mav.addObject("pn", pn);
		mav.addObject("maxPn", maxPn);
		mav.addObject("insaList", insaList);	
		mav.setViewName("insa/insa_form");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/insa_detail_form.do", method = RequestMethod.GET)
	public ModelAndView showInsaDetailForm(HttpSession ses, @RequestParam (required = false) String userId) {
		
		ModelAndView mav = new ModelAndView();
	
		List<InsaComVO> emailList = isSvc.showCom("email2");
		List<InsaComVO> jobList = isSvc.showCom("job_type");
		List<InsaComVO> sexList = isSvc.showCom("sex");
		List<InsaComVO> posList = isSvc.showCom("pos_gbn");
		List<InsaComVO> deptList = isSvc.showCom("dept");
		List<InsaComVO> joinList = isSvc.showCom("join_yn");
		List<InsaComVO> gartList = isSvc.showCom("gart_level");
		List<InsaComVO> putList = isSvc.showCom("put_yn");
		List<InsaComVO> milList = isSvc.showCom("mil_yn");
		List<InsaComVO> milTypeList = isSvc.showCom("mil_type");
		List<InsaComVO> milLevelList = isSvc.showCom("mil_level");
		List<InsaComVO> kosaRegList = isSvc.showCom("kosa_yn");
		List<InsaComVO> kosaClassList = isSvc.showCom("kosa_class");
		
		InsaVO insaList = isSvc.showOneInsaDetail(userId);
		
//		System.out.println("email : " + emailList);
//		System.out.println("email.name : " + emailList.get(0).getName());
//		System.out.println("job : " + jobList);
//		System.out.println("sex : " + sexList);
//		System.out.println("pos: " + posList);
//		System.out.println("dept: " + deptList);
//		System.out.println("join : " + joinList);
//		System.out.println("gart : " + gartList);
//		System.out.println("put : " + putList);
//		System.out.println("mil : " + milList);
//		System.out.println("miltType : " + milTypeList);
//		System.out.println("milLevel : " + milLevelList);
//		System.out.println("kosaReg : " + kosaRegList);
//		System.out.println("kosaClass : " + kosaClassList);
		
		mav.addObject("emList", emailList);
		mav.addObject("jList", jobList);
		mav.addObject("sList", sexList);
		mav.addObject("psList", posList);
		mav.addObject("dtList", deptList);
		mav.addObject("jnList", joinList);		
		mav.addObject("gtList", gartList);
		mav.addObject("ptList", putList);
		mav.addObject("mList", milList);
		mav.addObject("mtList", milTypeList);
		mav.addObject("mlList", milLevelList);
		mav.addObject("krList", kosaRegList);
		mav.addObject("kcList", kosaClassList);
		mav.addObject("isList", insaList);
		mav.setViewName("insa/insa_detail_form");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/insa_insert.do", method = RequestMethod.POST)
	public String insertInfomation(HttpSession ses, HttpServletRequest request, Model model
			, MultipartFile cmp_reg, MultipartFile carrier_reg, MultipartFile img_upload) {
		
//		int sabun = Integer.parseInt(request.getParameter("sabun"));
		String korName = request.getParameter("kor_name");
		String engName = request.getParameter("eng_name");
		String userId = request.getParameter("userid");
		String pwd = request.getParameter("pwd_hidden");
		String tel = request.getParameter("tel");
		String hp = request.getParameter("hp");
		String regNo = request.getParameter("reg_no_hidden");
//		int age = Integer.parseInt(request.getParameter("age"));
		String age = request.getParameter("age");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String jobType = request.getParameter("job_type");
		String sex = request.getParameter("sex");
//		int zip = Integer.parseInt(request.getParameter("zip"));
		String zip = request.getParameter("zip");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String posGbn = request.getParameter("pos_gbn");
		String dept = request.getParameter("dept");
//		int salary = Integer.parseInt(request.getParameter("salary"));
		String salary = request.getParameter("salary");
		String joinGbn = request.getParameter("join_gbn");
		String gartLevel = request.getParameter("gart_level");
		String putYn = request.getParameter("put_yn");
		String milYn = request.getParameter("mil_yn");
		String milType = request.getParameter("mil_type");
		String milLevel = request.getParameter("mil_level");
		String milStartDate = request.getParameter("mil_start_date");
		String milEndDate = request.getParameter("mil_end_date");
		String kosaReg = request.getParameter("kosa_reg");
		String kosaClass = request.getParameter("kosa_class");
		String joinDate = request.getParameter("join_date");
		String endDate = request.getParameter("end_date");
		String cmpNo = request.getParameter("cmp_no");
		String cmpName = request.getParameter("cmp_name");
		String cmpImg = request.getParameter("cmp_img");
//		String cmpReg = request.getParameter("cmp_reg");
		String selfIntro = request.getParameter("self_intro");
		String carrier = request.getParameter("carrier");
//		String carrierReg = request.getParameter("carrier_reg");
		
		String email = request.getParameter("email");
		
		// cmpReg = cmpReg        
		// carrierReg
		// imgUpload
		
		SimpleDateFormat sdf = new SimpleDateFormat("-yyyyMMddHHmmss");
		final String CMP_FILE_PREFIX = "cmpReg-";
		final String CRR_FILE_PREFIX = "crrReg-";
		final String PRF_FILE_PREFIX = "prfReg-";
		final String FILE_UPLOAD_DEST = "/uploads/" + userId;
		String realFilePath = ses.getServletContext().getRealPath(FILE_UPLOAD_DEST);
		String oriClientName = "";
		String oriFileName = "";
		String prfPath = "";
		String crrPath = "";
		String cmpPath = "";
		
		System.out.println("insert할때 img_upload 값 : " + img_upload);
		
		if(img_upload != null && !img_upload.isEmpty()) {
			oriFileName = img_upload.getOriginalFilename();
			System.out.println("multiPart 처리 : " + oriFileName + ", " + img_upload.getSize()/1024.0 + "KB");
			int lastExt = oriFileName.lastIndexOf(".");
			String fileName = oriFileName.substring(0, lastExt);
			String ext = oriFileName.substring(lastExt + 1).toLowerCase();
			String timeName = sdf.format(new Date());
			oriClientName = PRF_FILE_PREFIX + fileName + timeName + "." + ext;
			
			try {
				File filePath = new File(realFilePath);
				if(!filePath.exists()) {
					try {
						filePath.mkdir();
						img_upload.transferTo(new File(realFilePath + "/" + oriClientName));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else{
					img_upload.transferTo(new File(realFilePath + "/" + oriClientName));
				}
				prfPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		if(carrier_reg != null && !carrier_reg.isEmpty()) {
			oriFileName = carrier_reg.getOriginalFilename();
			System.out.println("multiPart 처리 : " + oriFileName + ", " + carrier_reg.getSize()/1024.0 + "KB");
			int lastExt = oriFileName.lastIndexOf(".");
			String fileName = oriFileName.substring(0, lastExt);
			String ext = oriFileName.substring(lastExt + 1).toLowerCase();
			String timeName = sdf.format(new Date());
			oriClientName = CRR_FILE_PREFIX + fileName + timeName + "." + ext;
			
			try {
				File filePath = new File(realFilePath);
				if(!filePath.exists()) {
					try {
						filePath.mkdir();
						carrier_reg.transferTo(new File(realFilePath + "/" + oriClientName));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else{
					carrier_reg.transferTo(new File(realFilePath + "/" + oriClientName));
				}
				crrPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		if(cmp_reg != null && !cmp_reg.isEmpty()) {
			oriFileName = cmp_reg.getOriginalFilename();
			System.out.println("multiPart 처리 : " + oriFileName + ", " + cmp_reg.getSize()/1024.0 + "KB");
			int lastExt = oriFileName.lastIndexOf(".");
			String fileName = oriFileName.substring(0, lastExt);
			String ext = oriFileName.substring(lastExt + 1).toLowerCase();
			String timeName = sdf.format(new Date());
			oriClientName = CMP_FILE_PREFIX + fileName + timeName + "." + ext;
			
			try {
				File filePath = new File(realFilePath);
				if(!filePath.exists()) {
					try {
						filePath.mkdir();
						cmp_reg.transferTo(new File(realFilePath + "/" + oriClientName));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else{
					cmp_reg.transferTo(new File(realFilePath + "/" + oriClientName));
				}
				cmpPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("cmpReg : " + cmp_reg+"\n, cmpPath : " + cmpPath);
		System.out.println("carrierReg : " + carrier_reg + "\n, crrPath : " + crrPath);
		System.out.println("imgUpload : " + img_upload + "\n, prfPath : " + prfPath);
		
		//korName : 이장원, engName : leejangwon, userId : test1, pwd : test, tel : 041-834-6944
		// , hp : 010-7332-6944, regNo : 910710-1457151, email : asdf@naver.com, jobType : 개발
		// , sex : 남자, addr1 : 제주특별자치도 서귀포시 가가로 14, addr2 : 34341, posGbn : 사원, dept : 개발
		// , joinGbn : YgartLevel : 대졸, putYn : N, milYn : Y, milType : 공군, milLevel : 병장
		// , milStartDate : 2010-08-30, milEndDate : 2012-09-04, kosaReg : N, kosaClass : 초급
		// , joinDate : 2019-09-17, endDate : , cmpNo : 121-23-12345, cmpName : 피노소프트, cmpImg : null
		// , cmpReg : , selfIntro : ㅇ, carrier : null, carrierReg :
		
		System.out.println("korName : " + korName + ", engName : " + engName + ", userId : " + userId + ", pwd : " + pwd +
				", tel : " + tel + ", hp : " + hp + ", regNo : " + regNo + ", email : " + email + ", jobType : " + jobType + ", sex : " + 
				sex + ", addr1 : " + addr1 + ", addr2 : " + addr2 + ", posGbn : " + posGbn + ", dept : " + dept + ", joinGbn : " + joinGbn
				 + ", gartLevel : " + gartLevel + ", putYn : " + putYn + ", milYn : " + milYn + ", milType : " + milType + ", milLevel : " + milLevel
				 + ", milStartDate : " + milStartDate + ", milEndDate : " + milEndDate + ", kosaReg : " + kosaReg + ", kosaClass : " + kosaClass
				 + ", joinDate : " + joinDate + ", endDate : " + endDate + ", cmpNo : " + cmpNo + ", cmpName : " + cmpName + ", cmpImg : " + cmpImg
				 + ", cmpReg : " + cmp_reg + ", selfIntro : " + selfIntro + ", carrier : " + carrier + ", carrierReg : " + carrier_reg);
		int sal = 0;
		int years = 0;
		int zipNum = 0;
		if(age == null || age == "" || age.isEmpty()) {
			years = 0;
		}else {
			years = Integer.parseInt(age);
		}
		if(zip == null || zip == "" || zip.isEmpty()) {
			zipNum = 0;
		}else {
			zipNum = Integer.parseInt(zip);
		}
		if(salary == null || salary == "" || salary.isEmpty()) {
			sal = 0;
		}else {
//			System.out.println("제거 전 : " + salary);
			for(int i = 0; i < salary.length(); i++) {
				salary = salary.replace(",", "");
			}
//			System.out.println("제거 후 : " + salary);
			sal = Integer.parseInt(salary);
		}
		
		InsaVO insa = new InsaVO(0, joinDate, endDate, putYn, korName, regNo, engName, tel, hp, crrPath, posGbn, cmpNo, cmpPath, sex, years
				, email, zipNum, addr1, addr2, dept, joinGbn, userId, pwd, sal, kosaReg, kosaClass, milYn, milType, milLevel
				, milStartDate, milEndDate, jobType, gartLevel, selfIntro, cmpName, prfPath);
		
		isSvc.insertInsaData(insa);	
		int rtnKey = insa.getSabun();
		System.out.println("return key real : " + rtnKey);
		return "redirect:insa_detail_form.do";
//		return "redirect:insa_edit_form.do?sabun=" + rtnKey;
	}
	
	@RequestMapping(value = "/dup_chk.do", method = RequestMethod.POST)
	@ResponseBody
	public String dupCheck(@RequestParam(value="userId") String userId) {
//		System.out.println("중복검사 리턴값 : " + isSvc.showInsaCount(userId)+"");
			
		return isSvc.showInsaCount(userId)+"";
		
	}
	
	@RequestMapping(value = "/now_userid.do", method = RequestMethod.POST)
	@ResponseBody
	public String nowUserId(@RequestParam(value="sabun") int sabun, @RequestParam(value="userId") String userId) {
//		System.out.println("중복검사 리턴값 : " + isSvc.showInsaCount(userId)+"");
		
		String nowId = isSvc.showOneInsaDetail(sabun).getId();
		
//		System.out.println("현재 아이디 : " + nowId);
//		System.out.println("받은 아이디 : " + userId);
		
		if(userId.equals(nowId)) {
			return "0";
		}else {
			
			return this.dupCheck(userId);
		}
		
	}
	
	@RequestMapping(value = "/insa_edit_form.do", method = RequestMethod.GET)
	public ModelAndView showInsaEditForm(HttpSession ses, @RequestParam (required = false, defaultValue = "1") int sabun
			, MultipartFile cmp_reg, MultipartFile carrier_reg, MultipartFile img_upload) {
		
		ModelAndView mav = new ModelAndView();
	
		List<InsaComVO> emailList = isSvc.showCom("email2");
		List<InsaComVO> jobList = isSvc.showCom("job_type");
		List<InsaComVO> sexList = isSvc.showCom("sex");
		List<InsaComVO> posList = isSvc.showCom("pos_gbn");
		List<InsaComVO> deptList = isSvc.showCom("dept");
		List<InsaComVO> joinList = isSvc.showCom("join_yn");
		List<InsaComVO> gartList = isSvc.showCom("gart_level");
		List<InsaComVO> putList = isSvc.showCom("put_yn");
		List<InsaComVO> milList = isSvc.showCom("mil_yn");
		List<InsaComVO> milTypeList = isSvc.showCom("mil_type");
		List<InsaComVO> milLevelList = isSvc.showCom("mil_level");
		List<InsaComVO> kosaRegList = isSvc.showCom("kosa_yn");
		List<InsaComVO> kosaClassList = isSvc.showCom("kosa_class");
		
		if(sabun < 1) {
			sabun = 1;
		}
		
		InsaVO insa = isSvc.showOneInsaDetail(sabun);
		
		// cmpReg = cmpReg        
				// carrierReg
				// imgUpload
				
				SimpleDateFormat sdf = new SimpleDateFormat("-yyyyMMddHHmmss");
				final String CMP_FILE_PREFIX = "cmpReg-";
				final String CRR_FILE_PREFIX = "crrReg-";
				final String PRF_FILE_PREFIX = "prfReg-";
				final String FILE_UPLOAD_DEST = "/uploads/" + insa.getId();
				String realFilePath = ses.getServletContext().getRealPath(FILE_UPLOAD_DEST);
				String oriClientName = "";
				String oriFileName = "";
				String prfPath = "";
				String crrPath = "";
				String cmpPath = "";
				
				if(img_upload != null && !img_upload.isEmpty()) {
					oriFileName = img_upload.getOriginalFilename();
					System.out.println("multiPart 처리 : " + oriFileName + ", " + img_upload.getSize()/1024.0 + "KB");
					int lastExt = oriFileName.lastIndexOf(".");
					String fileName = oriFileName.substring(0, lastExt);
					String ext = oriFileName.substring(lastExt + 1).toLowerCase();
					String timeName = sdf.format(new Date());
					oriClientName = PRF_FILE_PREFIX + fileName + timeName + "." + ext;
					
					try {
						File filePath = new File(realFilePath);
						if(!filePath.exists()) {
							try {
								filePath.mkdir();
								img_upload.transferTo(new File(realFilePath + "/" + oriClientName));
							}catch(Exception e) {
								e.printStackTrace();
							}
						}else{
							img_upload.transferTo(new File(realFilePath + "/" + oriClientName));
						}
						prfPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
					}catch(IllegalStateException e) {
						e.printStackTrace();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
				
				if(carrier_reg != null && !carrier_reg.isEmpty()) {
					oriFileName = carrier_reg.getOriginalFilename();
					System.out.println("multiPart 처리 : " + oriFileName + ", " + carrier_reg.getSize()/1024.0 + "KB");
					int lastExt = oriFileName.lastIndexOf(".");
					String fileName = oriFileName.substring(0, lastExt);
					String ext = oriFileName.substring(lastExt + 1).toLowerCase();
					String timeName = sdf.format(new Date());
					oriClientName = CRR_FILE_PREFIX + fileName + timeName + "." + ext;
					
					try {
						File filePath = new File(realFilePath);
						if(!filePath.exists()) {
							try {
								filePath.mkdir();
								carrier_reg.transferTo(new File(realFilePath + "/" + oriClientName));
							}catch(Exception e) {
								e.printStackTrace();
							}
						}else{
							carrier_reg.transferTo(new File(realFilePath + "/" + oriClientName));
						}
						crrPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
					}catch(IllegalStateException e) {
						e.printStackTrace();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
				
				if(cmp_reg != null && !cmp_reg.isEmpty()) {
					oriFileName = cmp_reg.getOriginalFilename();
					System.out.println("multiPart 처리 : " + oriFileName + ", " + cmp_reg.getSize()/1024.0 + "KB");
					int lastExt = oriFileName.lastIndexOf(".");
					String fileName = oriFileName.substring(0, lastExt);
					String ext = oriFileName.substring(lastExt + 1).toLowerCase();
					String timeName = sdf.format(new Date());
					oriClientName = CMP_FILE_PREFIX + fileName + timeName + "." + ext;
					
					try {
						File filePath = new File(realFilePath);
						if(!filePath.exists()) {
							try {
								filePath.mkdir();
								cmp_reg.transferTo(new File(realFilePath + "/" + oriClientName));
							}catch(Exception e) {
								e.printStackTrace();
							}
						}else{
							cmp_reg.transferTo(new File(realFilePath + "/" + oriClientName));
						}
						cmpPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
					}catch(IllegalStateException e) {
						e.printStackTrace();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("cmpReg : " + cmp_reg+"\n, cmpPath : " + cmpPath);
				System.out.println("carrierReg : " + carrier_reg + "\n, crrPath : " + crrPath);
				System.out.println("imgUpload : " + img_upload + "\n, prfPath : " + prfPath);
				
		
		
//		System.out.println("email : " + emailList);
//		System.out.println("email.name : " + emailList.get(0).getName());
//		System.out.println("job : " + jobList);
//		System.out.println("sex : " + sexList);
//		System.out.println("pos: " + posList);
//		System.out.println("dept: " + deptList);
//		System.out.println("join : " + joinList);
//		System.out.println("gart : " + gartList);
//		System.out.println("put : " + putList);
//		System.out.println("mil : " + milList);
//		System.out.println("miltType : " + milTypeList);
//		System.out.println("milLevel : " + milLevelList);
//		System.out.println("kosaReg : " + kosaRegList);
//		System.out.println("kosaClass : " + kosaClassList);
		
		System.out.println("insa : " + insa);
		
		String joinDate = insa.getJoinDate();
		String retireDate = null;
		
		joinDate = joinDate.split(" ")[0];
		try {
			retireDate = insa.getRetireDate();
			if(retireDate != null) {
				retireDate = retireDate.split(" ")[0];
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
			retireDate = null;
		}
		
//		System.out.println(joinDate);
//		System.out.println(retireDate);
		
		int salary = insa.getSalary();
		DecimalFormat comma = new DecimalFormat("###,###");
		
//		System.out.println("salary : " + salary);
		
		String sal = comma.format(salary);
		
//		System.out.println("sal : " + sal);
		System.out.println("insa.email : " + insa.getEmail());
		String email1 = insa.getEmail().split("@")[0];
		String email2 = "@" + insa.getEmail().split("@")[1];
		
		mav.addObject("emList", emailList);
		mav.addObject("jList", jobList);
		mav.addObject("sList", sexList);
		mav.addObject("psList", posList);
		mav.addObject("dtList", deptList);
		mav.addObject("jnList", joinList);		
		mav.addObject("gtList", gartList);
		mav.addObject("ptList", putList);
		mav.addObject("mList", milList);
		mav.addObject("mtList", milTypeList);
		mav.addObject("mlList", milLevelList);
		mav.addObject("krList", kosaRegList);
		mav.addObject("kcList", kosaClassList);
		mav.addObject("insa", insa);
		mav.addObject("joinDate", joinDate);
		mav.addObject("retireDate", retireDate);
		mav.addObject("email1", email1);
		mav.addObject("email2", email2);
		mav.addObject("sal", sal);
		mav.setViewName("insa/insa_edit_form");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/insa_update.do", method = RequestMethod.POST)
	public String insaUpdate(HttpServletRequest request, HttpSession ses
			, MultipartFile cmp_reg, MultipartFile carrier_reg, MultipartFile img_upload) {
		
		int sabun = Integer.parseInt(request.getParameter("sabun"));
		String korName = request.getParameter("kor_name");
		String engName = request.getParameter("eng_name");
		String userId = request.getParameter("userid");
		String pwd = request.getParameter("pwd_hidden");
		String tel = request.getParameter("tel");
		String hp = request.getParameter("hp");
		String regNo = request.getParameter("reg_no_hidden");
//		int age = Integer.parseInt(request.getParameter("age"));
		String age = request.getParameter("age");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String jobType = request.getParameter("job_type");
		String sex = request.getParameter("sex");
//		int zip = Integer.parseInt(request.getParameter("zip"));
		String zip = request.getParameter("zip");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String posGbn = request.getParameter("pos_gbn");
		String dept = request.getParameter("dept");
//		int salary = Integer.parseInt(request.getParameter("salary"));
		String salary = request.getParameter("salary");
		String joinGbn = request.getParameter("join_gbn");
		String gartLevel = request.getParameter("gart_level");
		String putYn = request.getParameter("put_yn");
		String milYn = request.getParameter("mil_yn");
		String milType = request.getParameter("mil_type");
		String milLevel = request.getParameter("mil_level");
		String milStartDate = request.getParameter("mil_start_date");
		String milEndDate = request.getParameter("mil_end_date");
		String kosaReg = request.getParameter("kosa_reg");
		String kosaClass = request.getParameter("kosa_class");
		String joinDate = request.getParameter("join_date");
		String endDate = request.getParameter("end_date");
		String cmpNo = request.getParameter("cmp_no");
		String cmpName = request.getParameter("cmp_name");
		String cmpImg = request.getParameter("cmp_img");	//사업자등록증 경로
		String cmpReg = request.getParameter("cmp_reg");
		String selfIntro = request.getParameter("self_intro");
		String carrier = request.getParameter("carrier");	//이력서 경로
		String carrierReg = request.getParameter("carrier_reg");
		String email = request.getParameter("email");
		String prePrfPath = request.getParameter("img_upload_path");	//프로필사진 경로
		
//		System.out.println("수정할때 email1 : " + email1 + ", email2 : " + email2 + ", email : " + email);
		
//		if(email2 == null) {
//			email = request.getParameter(email);
//		}else {
//			email = email1 + email2;
//		}
		
		//korName : 이장원, engName : leejangwon, userId : test1, pwd : test, tel : 041-834-6944
		// , hp : 010-7332-6944, regNo : 910710-1457151, email : asdf@naver.com, jobType : 개발
		// , sex : 남자, addr1 : 제주특별자치도 서귀포시 가가로 14, addr2 : 34341, posGbn : 사원, dept : 개발
		// , joinGbn : YgartLevel : 대졸, putYn : N, milYn : Y, milType : 공군, milLevel : 병장
		// , milStartDate : 2010-08-30, milEndDate : 2012-09-04, kosaReg : N, kosaClass : 초급
		// , joinDate : 2019-09-17, endDate : , cmpNo : 121-23-12345, cmpName : 피노소프트, cmpImg : null
		// , cmpReg : , selfIntro : ㅇ, carrier : null, carrierReg :
		
		System.out.println("korName : " + korName + ", engName : " + engName + ", userId : " + userId + ", pwd : " + pwd +
				", tel : " + tel + ", hp : " + hp + ", regNo : " + regNo + ", email : " + email + ", jobType : " + jobType + ", sex : " + 
				sex + ", addr1 : " + addr1 + ", addr2 : " + addr2 + ", posGbn : " + posGbn + ", dept : " + dept + ", joinGbn : " + joinGbn
				 + ", gartLevel : " + gartLevel + ", putYn : " + putYn + ", milYn : " + milYn + ", milType : " + milType + ", milLevel : " + milLevel
				 + ", milStartDate : " + milStartDate + ", milEndDate : " + milEndDate + ", kosaReg : " + kosaReg + ", kosaClass : " + kosaClass
				 + ", joinDate : " + joinDate + ", endDate : " + endDate + ", cmpNo : " + cmpNo + ", cmpName : " + cmpName + ", cmpImg : " + cmpImg
				 + ", cmpReg : " + cmpReg + ", selfIntro : " + selfIntro + ", carrier : " + carrier + ", carrierReg : " + carrierReg + ", prePrfPath: " + prePrfPath);
		
		SimpleDateFormat sdf = new SimpleDateFormat("-yyyyMMddHHmmss");
		final String CMP_FILE_PREFIX = "cmpReg-";
		final String CRR_FILE_PREFIX = "crrReg-";
		final String PRF_FILE_PREFIX = "prfReg-";
		final String FILE_UPLOAD_DEST = "/uploads/" + userId;
		String realFilePath = ses.getServletContext().getRealPath(FILE_UPLOAD_DEST);
		String oriClientName = "";
		String oriFileName = "";
		String prfPath = "";
		String crrPath = "";
		String cmpPath = "";
		
		if(img_upload != null && !img_upload.isEmpty() && (prePrfPath.isEmpty() || prePrfPath == null)) {
			oriFileName = img_upload.getOriginalFilename();
			System.out.println("multiPart 처리 : " + oriFileName + ", " + img_upload.getSize()/1024.0 + "KB");
			int lastExt = oriFileName.lastIndexOf(".");
			String fileName = oriFileName.substring(0, lastExt);
			String ext = oriFileName.substring(lastExt + 1).toLowerCase();
			String timeName = sdf.format(new Date());
			oriClientName = PRF_FILE_PREFIX + fileName + timeName + "." + ext;
			
			try {
				File filePath = new File(realFilePath);
				if(!filePath.exists()) {
					try {
						filePath.mkdir();
						img_upload.transferTo(new File(realFilePath + "/" + oriClientName));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else{
					img_upload.transferTo(new File(realFilePath + "/" + oriClientName));
				}
				prfPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else if(img_upload != null && !img_upload.isEmpty()) {		//기존에 있던걸 수정할때
			oriFileName = img_upload.getOriginalFilename();
			System.out.println("multiPart 처리 : " + oriFileName + ", " + img_upload.getSize()/1024.0 + "KB");
			int lastExt = oriFileName.lastIndexOf(".");
			String fileName = oriFileName.substring(0, lastExt);
			String ext = oriFileName.substring(lastExt + 1).toLowerCase();
			String timeName = sdf.format(new Date());
			oriClientName = PRF_FILE_PREFIX + fileName + timeName + "." + ext;
			
			try {
				File filePath = new File(realFilePath);
				if(!filePath.exists()) {
					try {
						filePath.mkdir();
						img_upload.transferTo(new File(realFilePath + "/" + oriClientName));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else{
					img_upload.transferTo(new File(realFilePath + "/" + oriClientName));
				}
				prfPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else if((img_upload == null || img_upload.isEmpty()) && prePrfPath.length() > 0){	//변동 없을때
			prfPath = prePrfPath;
		}
		
		if(carrier_reg != null && !carrier_reg.isEmpty() && (carrier.isEmpty() || carrier == null)) {
			oriFileName = carrier_reg.getOriginalFilename();
			System.out.println("multiPart 처리 : " + oriFileName + ", " + carrier_reg.getSize()/1024.0 + "KB");
			int lastExt = oriFileName.lastIndexOf(".");
			String fileName = oriFileName.substring(0, lastExt);
			String ext = oriFileName.substring(lastExt + 1).toLowerCase();
			String timeName = sdf.format(new Date());
			oriClientName = CRR_FILE_PREFIX + fileName + timeName + "." + ext;
			
			try {
				File filePath = new File(realFilePath);
				if(!filePath.exists()) {
					try {
						filePath.mkdir();
						carrier_reg.transferTo(new File(realFilePath + "/" + oriClientName));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else{
					carrier_reg.transferTo(new File(realFilePath + "/" + oriClientName));
				}
				crrPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else if(carrier_reg != null && !carrier_reg.isEmpty()) {
			oriFileName = carrier_reg.getOriginalFilename();
			System.out.println("multiPart 처리 : " + oriFileName + ", " + carrier_reg.getSize()/1024.0 + "KB");
			int lastExt = oriFileName.lastIndexOf(".");
			String fileName = oriFileName.substring(0, lastExt);
			String ext = oriFileName.substring(lastExt + 1).toLowerCase();
			String timeName = sdf.format(new Date());
			oriClientName = CRR_FILE_PREFIX + fileName + timeName + "." + ext;
			
			try {
				File filePath = new File(realFilePath);
				if(!filePath.exists()) {
					try {
						filePath.mkdir();
						carrier_reg.transferTo(new File(realFilePath + "/" + oriClientName));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else{
					carrier_reg.transferTo(new File(realFilePath + "/" + oriClientName));
				}
				crrPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else if((carrier_reg == null || carrier_reg.isEmpty()) && carrier.length() > 0){
			crrPath = carrier;
		}
		
		if(cmp_reg != null && !cmp_reg.isEmpty() && (cmpImg.isEmpty() || cmpImg == null)) {
			oriFileName = cmp_reg.getOriginalFilename();
			System.out.println("multiPart 처리 : " + oriFileName + ", " + cmp_reg.getSize()/1024.0 + "KB");
			int lastExt = oriFileName.lastIndexOf(".");
			String fileName = oriFileName.substring(0, lastExt);
			String ext = oriFileName.substring(lastExt + 1).toLowerCase();
			String timeName = sdf.format(new Date());
			oriClientName = CMP_FILE_PREFIX + fileName + timeName + "." + ext;
			
			try {
				File filePath = new File(realFilePath);
				if(!filePath.exists()) {
					try {
						filePath.mkdir();
						cmp_reg.transferTo(new File(realFilePath + "/" + oriClientName));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else{
					cmp_reg.transferTo(new File(realFilePath + "/" + oriClientName));
				}
				cmpPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else if(cmp_reg != null && !cmp_reg.isEmpty()) {
			oriFileName = cmp_reg.getOriginalFilename();
			System.out.println("multiPart 처리 : " + oriFileName + ", " + cmp_reg.getSize()/1024.0 + "KB");
			int lastExt = oriFileName.lastIndexOf(".");
			String fileName = oriFileName.substring(0, lastExt);
			String ext = oriFileName.substring(lastExt + 1).toLowerCase();
			String timeName = sdf.format(new Date());
			oriClientName = CMP_FILE_PREFIX + fileName + timeName + "." + ext;
			
			try {
				File filePath = new File(realFilePath);
				if(!filePath.exists()) {
					try {
						filePath.mkdir();
						cmp_reg.transferTo(new File(realFilePath + "/" + oriClientName));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else{
					cmp_reg.transferTo(new File(realFilePath + "/" + oriClientName));
				}
				cmpPath = (FILE_UPLOAD_DEST + "/" + oriClientName);
			}catch(IllegalStateException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else if((cmp_reg == null || cmp_reg.isEmpty()) && cmpImg.length() > 0){
			cmpPath = cmpImg;
		}
		
		System.out.println("cmpReg : " + cmp_reg+", cmpPath : " + cmpPath);
		System.out.println("carrierReg : " + carrier_reg + ", crrPath : " + crrPath);
		System.out.println("imgUpload : " + img_upload + ", prfPath : " + prfPath);
		
		int sal = 0;
		int years = 0;
		int zipNum = 0;
		if(age == null || age == "" || age.isEmpty()) {
			years = 0;
		}else {
			years = Integer.parseInt(age);
		}
		if(zip == null || zip == "" || zip.isEmpty()) {
			zipNum = 0;
		}else {
			zipNum = Integer.parseInt(zip);
		}
		if(salary == null || salary == "" || salary.isEmpty()) {
			sal = 0;
		}else {
//			System.out.println("제거 전 : " + salary);
			for(int i = 0; i < salary.length(); i++) {
				salary = salary.replace(",", "");
			}
//			System.out.println("제거 후 : " + salary);
			sal = Integer.parseInt(salary);
		}
//		System.out.println("update sabun : " + sabun);
		InsaVO insa = new InsaVO(sabun, joinDate, endDate, putYn, korName, regNo, engName, tel, hp, crrPath, posGbn, cmpNo, cmpPath, sex, years
				, email, zipNum, addr1, addr2, dept, joinGbn, userId, pwd, sal, kosaReg, kosaClass, milYn, milType, milLevel
				, milStartDate, milEndDate, jobType, gartLevel, selfIntro, cmpName, prfPath);
		
//		isSvc.insertInsaData(insa);	
		isSvc.updateInsaData(insa);
		int rtnKey = insa.getSabun();
		System.out.println("return key real : " + rtnKey);
//		return "insa/insa_edit_form";
		return "redirect:insa_edit_form.do?sabun=" + sabun;
	}
	
	@RequestMapping(value = "/delete_insa.do", method = RequestMethod.POST)
	@ResponseBody
	public String deleteInsa(HttpServletRequest request, int sabun) {
//		String sabun = request.getParameter("sabun");
		System.out.println("sabun : " + sabun);
		
		
		if(isSvc.deleteInsaData(sabun) >= 1) {
			return "ok";
		}else {
			return "fail";
		}
		
	}
	
}
