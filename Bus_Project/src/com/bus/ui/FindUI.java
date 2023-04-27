package com.bus.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bus.dao.BusMemDAO;
import com.bus.vo.MailSender;
import com.bus.vo.MemberVO;

public class FindUI extends JFrame implements ActionListener{
	
	private JPanel mainPan=new JPanel();//메인패널
	private JPanel fiPan=new JPanel(); 	//아이디찾기패널
	private JPanel fpPan=new JPanel();	//비번찾기패널
	
	private Color mainColor;	//배경색
	
	private JButton findIdBtn;	//아이디찾기
	private JButton	findPwdBtn;	//비번찾기
	private JButton backBtn;	//뒤로가기
	private JButton idBtn;	//아이디패널 안 찾기버튼
	private JButton pwdBtn;	//비번패널 안 찾기버튼
	
	private JLabel nameLabel;	//이름 :
	private JLabel name2Label;  //이름 :(비번찾기창)
	private JLabel phoneLabel;	//핸드폰 번호 :
	private JLabel hyp1Label;	//-
	private JLabel hyp2Label;	//-
	private JLabel emailLabel;	//이메일 :
	private JLabel atLabel;		//@
	private JLabel at2Label;	//@
	private JLabel idLabel; 	//아이디 :
	private JLabel email2Label;  //이메일 :
	
	private JTextField nameText;	//이름칸
	private JTextField name2Text;	//이름칸(비번찾기창)
	private JTextField pnumText;	//폰번호 1
	private JTextField mpnText;		//폰번호 2
	private JTextField epnText;		//폰번호 3
	private JTextField emailText;	//아이디 이메일칸
	private JTextField emailedText;	//도메인칸
	private JTextField idText;		//아이디칸
	private JTextField email1Text;	//비밀번호 이메일칸
	private JTextField email2Text;	//비밀번호 도메인칸
	
	
	BusMemDAO bdao = new BusMemDAO();
	MemberVO mvo = new MemberVO();
	
	FindUI(){
		mainColor = new Color(166, 221, 248);
		mainPan.setLayout(null);
		mainPan.setBackground(mainColor);
		
		findIdBtn=new JButton("아이디 찾기");
		findIdBtn.setBounds(70,40,130,40);
		findPwdBtn=new JButton("비밀번호 찾기");
		findPwdBtn.setBounds(220,40,130,40);
		backBtn=new JButton("뒤로가기");
		backBtn.setBounds(150,280,100,30);
		

		fiPan.setLayout(null);
		fiPan.setBounds(50,100,320,170);
		fiPan.setBackground(mainColor);
		nameLabel=new JLabel("이름 :");
		nameLabel.setBounds(25,25,40,30);
		nameText=new JTextField(10);
		nameText.setBounds(110,30,80,20);
		phoneLabel=new JLabel("핸드폰 번호 :");
		phoneLabel.setBounds(25,55,80,30);
		pnumText=new JTextField(10);
		pnumText.setBounds(110,60,30,20);
		mpnText = new JTextField(10);
		mpnText.setBounds(155,60,40,20);
		epnText = new JTextField(10);
		epnText.setBounds(210,60,40,20);
		hyp1Label = new JLabel("-");
		hyp1Label.setBounds(145,58,20,20);
		hyp2Label = new JLabel("-");
		hyp2Label.setBounds(200,58,20,20);
		emailLabel=new JLabel("이메일 :");
		emailLabel.setBounds(25,85,60,30);
		emailText = new JTextField(15);
		emailText.setBounds(110,90,80,20);
		atLabel = new JLabel("@");
		atLabel.setBounds(194,88,20,20);
		emailedText = new JTextField(15);
		emailedText.setBounds(210,90,80,20);
		idBtn=new JButton("찾기");
		idBtn.setBounds(210,130,80,30);
		
		 
		
		fiPan.add(nameLabel);
		fiPan.add(nameText);
		fiPan.add(phoneLabel);
		fiPan.add(pnumText);
		fiPan.add(mpnText);
		fiPan.add(epnText);
		fiPan.add(hyp1Label);
		fiPan.add(hyp2Label);
		fiPan.add(emailLabel);
		fiPan.add(atLabel);
		fiPan.add(emailText);
		fiPan.add(emailedText);
		fiPan.add(idBtn);
		

		fpPan.setLayout(null);
		fpPan.setBounds(50,100,320,170);
		fpPan.setBackground(mainColor);
		idLabel=new JLabel("아이디 :");
		idLabel.setBounds(25,25,80,30);
		idText=new JTextField(20);
		idText.setBounds(110,30,80,20);
		name2Label=new JLabel("이름 :");
		name2Label.setBounds(25,55,80,30);
		name2Text=new JTextField(10);
		name2Text.setBounds(110,60,80,20);
		email2Label=new JLabel("이메일 :");
		email2Label.setBounds(25,85,60,30);
		email1Text = new JTextField(15);
		email1Text.setBounds(110,90,80,20);
		at2Label = new JLabel("@");
		at2Label.setBounds(194,88,20,20);
		email2Text =new JTextField(15);
		email2Text.setBounds(210,90,80,20);
		pwdBtn=new JButton("찾기");
		pwdBtn.setBounds(210,130,80,30);
		
		fpPan.add(idLabel);
		fpPan.add(idText);
		fpPan.add(name2Label);
		fpPan.add(name2Text);
		fpPan.add(email2Label);
		fpPan.add(at2Label);
		fpPan.add(email1Text);
		fpPan.add(email2Text);
		fpPan.add(pwdBtn);
		
		eventSet();
		
		mainPan.add(findIdBtn);
		mainPan.add(findPwdBtn);
		mainPan.add(backBtn);
		add(mainPan);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		setTitle("자바 버스 예매 시스템 메인");
		setSize(420, 360);
		setLocationRelativeTo(null);//창이 가운데 나오게 설정
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	/* public static void main(String[] args) {
		new FindUI();
	} */
	
	public void eventSet() {
		findIdBtn.addActionListener(this);
		findPwdBtn.addActionListener(this);
		backBtn.addActionListener(this);
		pwdBtn.addActionListener(this);
		idBtn.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == backBtn) {
			dispose();
		}
		if(e.getSource() == findPwdBtn) {
			
			fiPan.setVisible(false);	//fiPan 안보이게하기
			fpPan.setVisible(true);		//fpPan 보이게하기
			mainPan.add(fpPan);			//버튼클릭 시 메인패널에 fiPan추가
			repaint();					//다시그리기
		}else if(e.getSource() == pwdBtn) {
			
			
			//String pwd = pwdText.getText().trim();
			//BusMemDAO bdao = new BusMemDAO();
		 	
			if(idText.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요!");
			}else if(name2Text.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "이름을 입력하세요!");
			}else if(email1Text.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "이메일을 입력하세요!");
			}else {
				String id = idText.getText().trim();
				String name = name2Text.getText().trim();
				String email1 = email1Text.getText().trim();
				String emailsum = email1+"@"+email2Text.getText().trim();
				String pwd = bdao.pwdFind(id,name,emailsum);
				
				if(pwd == null) {
					JOptionPane.showMessageDialog(this, "해당 정보가 없습니다!");
				}else {
					JOptionPane.showMessageDialog(this, "가입하신 이메일로 비밀번호를 발송했습니다.");
					MailSender mail = new MailSender(name, email1, pwd);
				}
			}
		}
			if(e.getSource() == findIdBtn) {
				fpPan.setVisible(false);	//fpPan 안보이게하기
				fiPan.setVisible(true);		//fiPan 보이게하기
				mainPan.add(fiPan);			//버튼클릭 시 메인패널에 fiPan추가
				repaint();					//다시그리기
		}else if(e.getSource() == idBtn) {//아이디 찾기를 눌러서 아이디를 가져옴
			
			if(nameText.getText().trim().equals("")) {//이름 입력필드가 공백이면
				JOptionPane.showMessageDialog(this, "이름을 입력해주세요");
			}else if(pnumText.getText().trim().equals("")) {//휴대폰 번호 입력필드가 공백이면
				JOptionPane.showMessageDialog(this, "휴대폰 번호를 입력해주세요");
			}else if(emailText.getText().trim().equals("")) {//이메일 입력필드가 공백이면
				JOptionPane.showMessageDialog(this, "이메일을 입력해주세요");
			}else {
				
				String name = nameText.getText().trim();
				String pnumber = pnumText.getText().trim()+mpnText.getText().trim()+epnText.getText().trim();
				String email = emailText.getText().trim()+"@"+emailedText.getText().trim();
				
				String id = bdao.idFind(name, pnumber, email);//검색해서 있는 레코드에 있는 아이디를 리턴 받음
				
				if(id == null) {//아이디에 값이 안들어갔으면
					JOptionPane.showMessageDialog(this, "당신의 아이디를 찾을 수 없습니다");
				}else {//아이디에 값이 들어갔다면
					JOptionPane.showMessageDialog(this, "당신의 아이디는 "+id+"입니다.");
				}
			}
		 }
		
			}
		 public static void main(String[] args) {
				new FindUI();
			} 
		}
	




	

