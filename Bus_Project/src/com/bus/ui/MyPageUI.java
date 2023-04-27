package com.bus.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bus.dao.BusCheckDAO;
import com.bus.dao.BusMemDAO;
import com.bus.dao.MyDataDAO;
import com.bus.vo.MemberVO;

public class MyPageUI extends JFrame implements ActionListener {

	private JPanel mainPan = new JPanel();


	private Color mainColor; //메인 색상

	private ImageIcon mainImage;

	private JLabel imgLabel; //이미지 라벨
	private JLabel idLabel; //아이디 라벨
	private JLabel nameLabel; //이름 라벨
	private JLabel birthLabel; //생일 라벨
	private JLabel emailLabel; //이메일 라벨
	private JLabel phoneLabel; //휴대폰 번호 라벨


	private JButton editMemberBtn; //회원정보 수정 버튼
	private JButton editPwdBtn; //비밀번호 변경 버튼
	private JButton exitMemberBtn; //탈퇴하기 버튼
	private JButton edit1Btn; //이메일 수정 버튼
	private JButton edit2Btn; //휴대폰 번호 수정 버튼
	private JButton backBtn; //뒤로가기 버튼

	JTextField idText;
	JTextField nameText;
	JTextField birthText;
	JTextField emailText;
	JTextField phoneText;


	MyDataDAO mydao = new MyDataDAO();
	String id = mydao.getMyId();//정보 테이블에 있는 아이디를 id에 저장
	MemberVO mvo = mydao.getMyData(id);//정보 테이블과 회원테이블을 조인해서 얻은 데이터를 mvo에 저장
	MemberVO vo = new MemberVO();
	BusMemDAO bdao = new BusMemDAO();
	BusCheckDAO bcdao = new BusCheckDAO();
	LoginUI logui;

	MyPageUI() {

		mainImage=new ImageIcon("./images/mypage.png");
		imgLabel=new JLabel(mainImage);
		imgLabel.setLayout(null);
		imgLabel.setBounds(0,300,550,220);

		mainColor = new Color(166,221,248);
		mainPan = new JPanel();
		mainPan.setLayout(null);
		mainPan.setBackground(Color.white);

		//라벨
		idLabel = new JLabel("아이디");
		idLabel.setBounds(40,75,70,20);

		nameLabel = new JLabel("이름");
		nameLabel.setBounds(40,120,70,20);

		birthLabel = new JLabel("생년월일");
		birthLabel.setBounds(40,165,70,20);

		emailLabel = new JLabel("이메일");
		emailLabel.setBounds(40,210,70,20);

		phoneLabel = new JLabel("휴대폰 번호");
		phoneLabel.setBounds(40,255,70,20);

		//버튼
		editMemberBtn = new JButton("회원정보 수정");
		editMemberBtn.setBounds(350,80,130,50);

		editPwdBtn = new JButton("비밀번호 변경");
		editPwdBtn.setBounds(350,150,130,50);

		exitMemberBtn = new JButton("탈퇴하기");
		exitMemberBtn.setBounds(350,220,130,50);

		backBtn = new JButton("뒤로가기");
		backBtn.setBounds(30,15,100,30);

		//텍스트
		idText = new JTextField(10);
		idText.setBounds(130, 75, 150, 20);
		idText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		idText.setEditable(false);

		idText.setText(mvo.getB_id()); // 아이디 저장

		nameText = new JTextField(10);
		nameText.setBounds(130, 120, 150, 20);
		nameText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nameText.setEditable(false);

		nameText.setText(mvo.getB_name()); // 이름 저장

		birthText = new JTextField(10);
		birthText.setBounds(130, 165, 150, 20);
		birthText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		birthText.setEditable(false);

		birthText.setText(mvo.getB_birth());  // 생일 저장

		emailText = new JTextField(10);
		emailText.setBounds(130, 210, 150, 20);
		emailText.setText(mvo.getB_email());

		emailText.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		phoneText = new JTextField(10);
		phoneText.setBounds(130, 255, 150, 20);
		phoneText.setText(mvo.getB_phone()); 

		add(mainPan);

		mainPan.add(idLabel);
		mainPan.add(nameLabel);
		mainPan.add(birthLabel);
		mainPan.add(emailLabel);
		mainPan.add(phoneLabel);
		mainPan.add(editMemberBtn);
		mainPan.add(editPwdBtn);
		mainPan.add(exitMemberBtn);
		mainPan.add(backBtn);
		mainPan.add(idText);
		mainPan.add(nameText);
		mainPan.add(birthText);
		mainPan.add(emailText);
		mainPan.add(imgLabel);
		mainPan.add(phoneText);




		setTitle("마이 페이지");
		setSize(550,550);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image img=toolkit.getImage("./images/titlebus.png");
		setIconImage(img);

		eventSet();
	}

	public void eventSet() {
		editMemberBtn.addActionListener(this);
		exitMemberBtn.addActionListener(this);
		editPwdBtn.addActionListener(this);
		backBtn.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		String edmail = emailText.getText().trim(); // 수정한 이메일 저장
		String edphone = phoneText.getText().trim(); // 수정한 폰번호 저장
		if(e.getSource() == editMemberBtn) {  //회원정보 수정 버튼을 눌렀을때

			if(edmail.equals("")) { // 이메일 수정칸이 빈칸일시
				JOptionPane.showMessageDialog(this, "이메일을 입력하세요!");
				emailText.setText("");
				emailText.requestFocus();


			}else if(!(edmail.matches("(.*)@(.*)"))){ // 이메일 수정칸에 @ 포함여부 확인
				JOptionPane.showMessageDialog(this, "@을 포함시켜 주세요!");
				emailText.requestFocus();
			}else if(!(edmail.matches("(.*).com(.*)")) ^ (edmail.matches("(.*).net(.*)"))){ 
				// 이메일 수정칸에.com or .net 포함여부 확인
				JOptionPane.showMessageDialog(this, "정확한 이메일이 아닙니다!");
				emailText.requestFocus();
			}
			else if(phoneText.getText().length() != 11) {
				JOptionPane.showMessageDialog(this, "휴대폰 번호가 정확하지 않습니다!");
			}

			else if(edphone.equals("")) {
				JOptionPane.showMessageDialog(this, "휴대폰 번호를 입력해주세요!");
				phoneText.setText("");
				phoneText.requestFocus();
			}else {	
				vo.setB_email(edmail); // MemberVO에 수정된 이메일 저장
				vo.setB_phone(edphone); // MemberVO에 수정된 번호 저장
				bdao.myPageUpdate(vo, id);
				JOptionPane.showMessageDialog(this, "정보수정이 완료되었습니다!");
				//dispose(); //창 닫기
				//new MainUI();
			}


		}

		if(e.getSource() == editPwdBtn) {

			new EditPwdUI();

		}


		if(e.getSource() == exitMemberBtn) { //탈퇴하기 버튼을 눌렀을때
			int re = JOptionPane.showConfirmDialog(this, "정말 탈퇴하시겠습니까?", " ",
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(re == JOptionPane.YES_NO_OPTION) {// 예 를 누르면 탈퇴처리 후 로그인 창 띄움

				if(bcdao.ticketCheck(id) == true) {//예매내역이 있다면 
					JOptionPane.showMessageDialog(this, "예매 내역 삭제후 탈퇴가 가능합니다.");
				}else {
				JOptionPane.showMessageDialog(this, "탈퇴가 완료되었습니다.");
				dispose();
				new LoginUI();

				vo.setB_id(idText.getText().toString());
				bdao.delId(mvo);
				}
			}

		}

		if(e.getSource() == backBtn) { //뒤로가기 버튼을 눌렀을때
			dispose();
			new MainUI();
		}

	}


	public static void main(String[] args) {
		new MyPageUI();
	}
}

