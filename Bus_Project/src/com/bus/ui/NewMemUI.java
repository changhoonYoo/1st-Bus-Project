package com.bus.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.bus.dao.BusMemDAO;
import com.bus.vo.MemberVO;



public class NewMemUI extends JFrame implements ActionListener {

	private JPanel mainPan = new JPanel();//메인 패널
	
	private Color mainColor;//메인 색상
	
	private JLabel idLabel;//아이디 라벨
	private JLabel pwdLabel;//비밀번호 라벨
	private JLabel pwdCheckLabel;//비밀번호 확인 라벨
	private JLabel nameLabel;//이름 라벨
	private JLabel birthLabel;//생년월일 라벨
	private JLabel yearLabel;//년 라벨
	private JLabel monthLabel;//월 라벨
	private JLabel dayLabel;//일 라벨
	private JLabel pNumLabel;//핸드폰번호 라벨
	private JLabel hyp1Label;//- 라벨
	private JLabel hyp2Label;//- 라벨
	private JLabel addressLabel;//주소 라벨
	private JLabel emailLabel;//이메일 라벨
	private JLabel atLabel;//@라벨
	
	private JButton DuplicateCheckBtn;//중복확인 버튼
	private JButton joinBtn;//가입하기 버튼
	private JButton cancelBtn;//취소 버튼
	
	private JTextField idText;//아이디를 입력할 텍스트 필드
	private JTextField pwdText;//비밀번호를 입력할 텍스트 필드
	private JTextField pwdCheckText;//비밀번호를 확인할 텍스트 필드
	private JTextField nameText;//이름을 입력할 텍스트 필드
	private JTextField midPNumText;//폰 중간번호를 입력할 텍스트 필드
	private JTextField endPNumText;//폰 마지막번호를 입력할 텍스트 필드
	private JTextField addressText;//주소를 입력할 텍스트 필드
	private JTextField emailText;//이메일을 앞부분을 입력할 텍스트 필드
	private JTextField emailEndText;//이메일 뒷부분을 입력할 텍스트 필드
	
	private JComboBox yearCBox;//년도 콤보박스
	private JComboBox monthCBox;//월 콤보박스
	private JComboBox dayCBox;//일 콤보박스
	private JComboBox pNumCBox;//폰번호 앞자리 콤보박스
	private JComboBox emailEndCBox;//이메일 뒷부분 콤보박스
	
	private String[] year = new String[102];//년도를 저장할 배열 생성
	private String[] month = new String[13];//월을 저장할 배열 생성
	private String[] day = new String[32];//일을 저장할 배열 생성
	
	
	
	MemberVO mvo = new MemberVO();
	
	BusMemDAO bdao = new BusMemDAO();
	

	
	
	NewMemUI(){
		valueCreate(year, 2024, 1923);
		valueCreate(month, 0, 12);
		valueCreate(day, 0, 31);
		
		mainColor = new Color(166, 221, 248);
		mainPan.setBackground(mainColor);
		//JLabel label = new JLabel();
		
		idLabel = new JLabel("아이디");
		idLabel.setBounds(20,20,60,20);
		idText = new JTextField(15);
		idText.setBounds(110,20,160,20);
		DuplicateCheckBtn = new JButton("중복확인");
		DuplicateCheckBtn.setBounds(280,20,90,20);
		mainPan.add(idLabel);
		mainPan.add(idText);
		mainPan.add(DuplicateCheckBtn);
		
		pwdLabel = new JLabel("비밀번호");
		pwdLabel.setBounds(20, 50, 60, 20);
		pwdText = new JTextField(15);
		pwdText.setBounds(110,50,160,20);
		pwdCheckLabel = new JLabel("비밀번호 확인");
		pwdCheckLabel.setBounds(20,80,150,20);
		pwdCheckText = new JTextField(15);
		pwdCheckText.setBounds(110,80,160,20);
		mainPan.add(pwdLabel);
		mainPan.add(pwdText);
		//mainPan.add(label);
		mainPan.add(pwdCheckLabel);
		mainPan.add(pwdCheckText);
		
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(20,110,60,20);
		nameText = new JTextField(15);
		nameText.setBounds(110,110,80,20);
		mainPan.add(nameLabel);
		mainPan.add(nameText);
		
		birthLabel = new JLabel("생년월일");
		birthLabel.setBounds(20,140,60,20);
		yearCBox = new JComboBox(year);
		yearCBox.setBounds(110,140,60,20);
		yearLabel = new JLabel("년");
		yearLabel.setBounds(175,140,60,20);
		monthCBox = new JComboBox(month);
		monthCBox.setBounds(200,140,60,20);
		monthLabel = new JLabel("월");
		monthLabel.setBounds(265,140,60,20);
		dayCBox = new JComboBox(day);
		dayCBox.setBounds(290,140,60,20);
		dayLabel = new JLabel("일");
		dayLabel.setBounds(355,140,60,20);
		
		mainPan.add(birthLabel);
		mainPan.add(yearCBox);
		mainPan.add(yearLabel);
		mainPan.add(monthCBox);
		mainPan.add(monthLabel);
		mainPan.add(dayCBox);
		mainPan.add(dayLabel);
		
		pNumLabel = new JLabel("핸드폰 번호");
		pNumLabel.setBounds(20,170,80,20);
		String[] pNum = {"010", "011", "016", "017"};
		pNumCBox = new JComboBox(pNum);
		pNumCBox.setBounds(110,170,50,20);
		hyp1Label = new JLabel(" - ");
		hyp1Label.setBounds(165,170,20,20);
		hyp2Label = new JLabel(" - ");
		hyp2Label.setBounds(225,170,20,20);
		//하이픈 기호를 하나만 생성해서 메인패널에 두번넣으면 마지막에 넣은 하이픈 기호만 나오는 현상이 보여서 두개를 생성
		hyp1Label.setFont(new Font("맑은고딕", Font.BOLD, 15));
		hyp2Label.setFont(new Font("맑은고딕", Font.BOLD, 15));
		midPNumText = new JTextField(15);
		midPNumText.setBounds(180,170,40,20);
		endPNumText = new JTextField(15);
		endPNumText.setBounds(240,170,40,20);
		mainPan.add(pNumLabel);
		mainPan.add(pNumCBox);
		mainPan.add(hyp1Label);
		mainPan.add(midPNumText);
		mainPan.add(hyp2Label);
		mainPan.add(endPNumText);
		
		addressLabel = new JLabel("주소");
		addressLabel.setBounds(20,200,60,20);
		addressText = new JTextField(15);
		addressText.setBounds(110,200,160,20);
		mainPan.add(addressLabel);
		mainPan.add(addressText);
		
		emailLabel = new JLabel("이메일");
		emailLabel.setBounds(20,230,60,20);
		emailText = new JTextField(15);
		emailText.setBounds(110,230,100,20);
		atLabel = new JLabel(" @ ");
		atLabel.setBounds(215,230,20,20);
		emailEndText = new JTextField(15);
		emailEndText.setBounds(240,230,80,20);
		String[] emailEnd = {"선택", "naver.com", "daum.net", "nate.com", "gmail.com"};
		emailEndCBox = new JComboBox(emailEnd);
		emailEndCBox.setBounds(325,230,90,20);
		mainPan.add(emailLabel);
		mainPan.add(emailText);
		mainPan.add(atLabel);
		mainPan.add(emailEndText);
		mainPan.add(emailEndCBox);
		
		joinBtn = new JButton("가입하기");
		joinBtn.setBounds(110,280,100,40);
		cancelBtn = new JButton("취소");
		cancelBtn.setBounds(230,280,100,40);
		mainPan.add(joinBtn);
		mainPan.add(cancelBtn);
				
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image img=toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		mainPan.setLayout(null);
		add(mainPan);
		
		setTitle("자바 버스 예매 시스템 회원가입");
		setSize(440,380);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		eventSet();
	}
	
	public void valueCreate(String[] name, int stnum, int ednum) {
	      //년도, 월, 일자 배열에 들어갈 값을 생성할 메서드
	      name[0] = "선택";
	      for(int a = 1; a < name.length; a++) {
	         if(stnum > ednum) { stnum--; } //시작값이 끝나는 값보다 크다면 내림차순으로 저장
	         else { stnum++; }//시작값이 끝나는 값보다 작다면 오름차순으로 저장
	         
	         if(stnum < 10) {
	            name[a] = ("0"+stnum);
	         }else {
	         name[a] = (""+stnum);
	         }
	      }
	      if(name == year) { this.year = name; }
	      else if(name == month) { this.month = name; }
	      else { this.day = name; }
	   }
	public void eventSet() {
		DuplicateCheckBtn.addActionListener(this);
		joinBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		emailEndCBox.addActionListener(this);
	}
	
	
	
	
	 @Override
     public void actionPerformed(ActionEvent e) {
		 int i=0; //중복확인 버튼을 눌렀는지 확인할 변수
		 if(e.getSource() == joinBtn) {
			 if(DuplicateCheckBtn.isEnabled() == true) {//중복확인 버튼이 사용가능하면 true
				 JOptionPane.showMessageDialog(this, "아이디 중복 확인을 해주세요.");
				 return;//해당 이벤트 종료
			 }
			 
			 
			 if(pwdText.getText().equals("")) {
				 JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요!");
				 pwdText.requestFocus();//해당 텍스트 포커스로 이동
				 return;
			 }
			 if((pwdText.getText().length() < 6 || pwdText.getText().length() > 16)
					 || !(Pattern.matches("^[a-z0-9]*$", pwdText.getText()))) {
				// 정규표현식 ->  ^[a-z0-9]*$ 은 비번을 영문 소문자와 숫자 조합으로만 입력가능하게 한다.
				 JOptionPane.showMessageDialog(this, "비번은 6~16자리 영문 소문자와 숫자로만 입력 가능합니다.");
				 pwdText.requestFocus();
				 return;
			 }
			 if(pwdCheckText.getText().equals("")) {
				 JOptionPane.showMessageDialog(this, "비밀번호 확인을 입력하세요.");
				 pwdCheckText.requestFocus();
				 return;
			 }
			 if(!pwdText.getText().equals(pwdCheckText.getText())) {
				 JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다!");
					//pwdText.setText("");
					pwdCheckText.setText("");//입력된 비밀번호 확인 텍스트 지우기
					pwdCheckText.requestFocus();
					return;
				}
			 if(nameText.getText().equals("")) {
				 JOptionPane.showMessageDialog(this, "이름을 입력하세요!");
					nameText.requestFocus();
					return;
			 }
			 if(yearCBox.getSelectedItem().equals("선택") || monthCBox.getSelectedItem().equals("선택") 
						|| dayCBox.getSelectedItem().equals("선택")) {
				 JOptionPane.showMessageDialog(this,"생년월일을 선택해주세요.");
					return;
				}
			 if(pNumCBox.getSelectedItem().equals("선택")) {
				 JOptionPane.showMessageDialog(this,"번호 앞자리를 선택해주세요.");
					return;
			 }
			 if(midPNumText.getText().equals("") || endPNumText.getText().equals("")) {
				 JOptionPane.showMessageDialog(this, "핸드폰 번호를 입력하세요.");
					midPNumText.requestFocus();
					return;
				}
			 if(addressText.getText().equals("")) {
				 JOptionPane.showMessageDialog(this, "주소를 입력하세요.");
					addressText.requestFocus();
					return;
				}
			 if(emailText.getText().equals("")) {
				 JOptionPane.showMessageDialog(this, "이메일을 입력하세요.");
					emailText.requestFocus();
					return;
				}
			 if(emailEndText.getText().equals("") && emailEndCBox.getSelectedItem().equals("선택")) {
				 JOptionPane.showMessageDialog(this, "이메일 주소를 입력 혹은 선택 하세요.");
					emailEndText.requestFocus();
					return;
			 }
			 else {
				 
				 String year = (String)yearCBox.getSelectedItem();//선택된 콤보박스 가져오기
				 String month = (String)monthCBox.getSelectedItem();
				 String day = (String)dayCBox.getSelectedItem();
				 String pNum = (String)pNumCBox.getSelectedItem();
				 
				 mvo.setB_id(idText.getText().trim());
				 mvo.setB_pwd(pwdText.getText().trim());
				 mvo.setB_name(nameText.getText().trim());
				 mvo.setB_birth(year + month + day);
				 mvo.setB_phone(pNum + midPNumText.getText().trim() + endPNumText.getText().trim());
				 mvo.setB_addr(addressText.getText().trim());
				 mvo.setB_email(emailText.getText().trim() +"@"+emailEndText.getText().trim());;
				 
				 if(bdao.newUserInsert(mvo) > 0) {//가입이 성공했다면
					 JOptionPane.showMessageDialog(this, idText.getText()+"님 가입이 완료되었습니다");
						dispose();
				 }
			 }
		 }
		 else if(e.getSource() == cancelBtn) {
			 //DuplicateCheckBtn.setEnabled(true);
			 dispose();
		 }
		 else if(e.getSource() == DuplicateCheckBtn) {

			 if((idText.getText().length() < 6 || idText.getText().length() > 16
					 || !(Pattern.matches("^[a-z]+[a-z0-9]*$", idText.getText())))) {
				 JOptionPane.showMessageDialog(this, "아이디는 6~16자리 영문 소문자와 숫자로만 입력 가능합니다.");
				 idText.requestFocus();
				 return;
			 }
			 if(bdao.checkId(idText.getText().trim()) == true) {
				 JOptionPane.showMessageDialog(this, "사용 가능한 아이디입니다!");

				 DuplicateCheckBtn.setEnabled(false);//중복버튼 비활성화
				 pwdText.requestFocus(); 
			 }else {
				 JOptionPane.showMessageDialog(this, "사용 불가능한 아이디입니다!");
				 idText.setText("");
				 idText.requestFocus();
			 }//if else
		 } 
		 if(e.getSource() == emailEndCBox) {
			 
			 String s = emailEndCBox.getSelectedItem().toString();
			 emailEndText.setText(s);
	         emailEndText.setEnabled(false); 

		 } 
} 
	 
	public static void main(String[] args) {

		new NewMemUI();
	}

}
