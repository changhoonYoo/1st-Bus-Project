package com.bus.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bus.dao.BusMemDAO;
import com.bus.dao.MyDataDAO;
import com.bus.vo.MemberVO;

public class LoginUI extends JFrame implements ActionListener {

	private JPanel mainPan = new JPanel(new BorderLayout(10, 10));//다른 패널을 넣을 메인 패널
	private JPanel idPwdLabelPan = new JPanel(new GridLayout(2, 1));//아이디와 비밀번호 라벨을 넣을 패널
	private JPanel idPwdTextPan = new JPanel(new GridLayout(2, 1));//아이디와 비밀번호 텍스트필드를 넣을 패널
	private JPanel newMemFindBtnPan = new JPanel(new FlowLayout());//회원가입 아이디 비밀번호찾기 버튼을 넣을 패널
	private JPanel idPwdLoginPan = new JPanel(new BorderLayout(10, 10));
	//아이디, 비밀번호 관련 라벨, 텍스트, 로그인 버튼이 들어갈 패널
	private JPanel botPan = new JPanel(new GridLayout(3, 2));//메인 패널아래쪽(사우스)에 들어갈 패널

	private ImageIcon backGroundImage;//배경이미지를 가져올 아이콘

	private Color mainColor;//메인 색상

	private JLabel backGroundImageLabel;//배경이미지 저장 라벨
	private JLabel topLabel;//상단에 들어갈 라벨
	private JLabel idLabel;//아이디 라벨
	private JLabel pwdLabel;//비밀번호 라벨

	private JButton loginBtn;//로그인 버튼
	private JButton newUserBtn;//회원가입 버튼
	private JButton idPwdFindBtn;//아이디 비밀번호 찾기 버튼

	private JTextField idText;//아이디를 입력할 텍스트필드

	private JPasswordField pwdText;//비밀번호를 입력할 텍스트필드

	MainUI mui;
	NewMemUI nmemui;
	FindUI findui;

	MyDataDAO mydao = new MyDataDAO();
	BusMemDAO bdao = new BusMemDAO();
	MemberVO mvo = new MemberVO();


	public String id;

	LoginUI(){
		backGroundImage = new ImageIcon("./images/bus01.png");//배경이미지 가져옴
		backGroundImageLabel = new JLabel(backGroundImage);//가져온 배경이미지를 라벨로 저장

		mainColor = new Color(166, 221, 248);//가져온 이미지 색상과 같은 rgb컬러를 maincolor로 가져옴

		idPwdLabelPan.setBackground(mainColor);
		newMemFindBtnPan.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		//mainColor를 각패널에 넣으면서 색상을 입힌다.

		topLabel = new JLabel("버스 예매 프로그램");//상단 라벨 설정
		topLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		topLabel.setBounds(160, 10, 300, 30);//x좌표로 160 y좌표로는 위에공간 여백을 위해서 10만큼을 설정하고
		//라벨의 길이를 300으로 폭은 30으로 설정함

		idLabel = new JLabel("아이디");//아이디 라벨 설정
		idLabel.setFont(new Font("바탕", Font.BOLD, 15));
		pwdLabel = new JLabel("비밀번호");//비밀번호 라벨 설정
		pwdLabel.setFont(new Font("바탕", Font.BOLD, 15));

		idPwdLabelPan.add(idLabel);
		idPwdLabelPan.add(pwdLabel);

		idText = new JTextField();
		pwdText = new JPasswordField();
		idPwdTextPan.add(idText);
		idPwdTextPan.add(pwdText);

		newUserBtn = new JButton("회원가입");
		idPwdFindBtn = new JButton("아이디/비밀번호 찾기");

		newMemFindBtnPan.add(newUserBtn);
		newMemFindBtnPan.add(idPwdFindBtn);
		newMemFindBtnPan.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		loginBtn = new JButton("로그인");

		idPwdLoginPan.setBackground(mainColor);
		idPwdLoginPan.add(idPwdLabelPan, BorderLayout.WEST);
		idPwdLoginPan.add(idPwdTextPan, BorderLayout.CENTER);
		idPwdLoginPan.add(loginBtn, BorderLayout.EAST);

		botPan.setBackground(mainColor);
		botPan.add(idPwdLoginPan);
		botPan.add(newMemFindBtnPan);
		//위에서 botPan에 아래(사우스)에 배치할 패널들을 넣어주고 아래에서 메인패널의 사우스로 위치를 잡아준다

		mainPan.add(botPan, BorderLayout.SOUTH);

		botPan.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));//여백을 설정하는 기능
		//setBorder를 이용해서 왼쪽에서 100만큼 띄우고 오른쪽에서 100만큼 띄운다.
		//왼쪽 오른쪽 여백을 동일하게 설정해서 가운데 공간이 400으로 되면서 중앙에 위치시킴
		//값은 위, 왼쪽, 아래, 오른쪽 순서대로 넣음

		mainPan.add(topLabel);
		mainPan.add(backGroundImageLabel);

		add(mainPan);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		//위에 3줄은 타이틀바 아이콘을 변경하는 역할을 한다.

		eventSet();

		setTitle("자바 버스 예매 시스템");
		setSize(600, 600);
		setLocationRelativeTo(null);//창이 가운데 나오게 설정
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void eventSet() {
		loginBtn.addActionListener(this);
		newUserBtn.addActionListener(this);
		idPwdFindBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == loginBtn) {
			id = idText.getText().trim();//id
			String pwd = pwdText.getText().trim();//pwd 
			if(id.equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요!");
				idText.setText("");
				idText.requestFocus();
			}else if(pwd.equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요!");
				pwdText.setText("");
				pwdText.requestFocus();
			}else if(bdao.memCheck(id) == null) {//해당 회원 정보가 없을때
				JOptionPane.showMessageDialog(this, "회원 정보에 없는 아이디입니다!");
				idText.requestFocus();
				idText.setText("");
				pwdText.setText("");
			}else if(bdao.memCheck(id) != null) {//해당 회원 정보가 있을때
				BusMemDAO Bdao = new BusMemDAO();
				MemberVO m = bdao.memCheck(id);

				if(!m.getB_pwd().equals(pwd)) {
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다!!");
					pwdText.setText("");
					pwdText.requestFocus();
				}else {
					if(id.equals("admin01")) {
						JOptionPane.showMessageDialog(this, "관리자 로그인 성공!");
						dispose();
						new AdminUI();
					}else {
						JOptionPane.showMessageDialog(this, id+"님 로그인이 되셨습니다!");
						mvo.setB_id(id);//저장된 회원 목록을 가져온다.

						if(mydao.checkData() == true) {//회원 정보 테이블에 값이 있다면
							mydao.delData();//회원 정보 테이블 값을 지운다
						}
						mydao.setMyId(mvo);//회원정보 테이블에 값을 넣는다.
						new MainUI();
						dispose();
					}
				}//if else
			}//if else if
		}//if
		if(e.getSource() == newUserBtn) {
			new NewMemUI();
		}//if
		if(e.getSource() == idPwdFindBtn) {
			new FindUI();
		}//if
	}

	public static void main(String[] args) {
		new LoginUI();
	}
}
