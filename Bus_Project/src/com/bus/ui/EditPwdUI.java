package com.bus.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bus.dao.BusMemDAO;
import com.bus.dao.MyDataDAO;
import com.bus.vo.MemberVO;

public class EditPwdUI extends JFrame implements ActionListener {
	
	private JPanel mainPan = new JPanel();
	
	private Color mainColor; //메인 색상
	
	private JLabel pwdLabel; //비밀번호 라벨
	private JLabel newPwdLabel; //새로운 비밀번호 라벨
	private JLabel checkPwdLabel; //비밀번호 확인 라벨
	
	private JButton okBtn; //확인 버튼
	
	JTextField pwdText; //비밀번호 텍스트
	JTextField newPwdText; //새로운 비밀번호 텍스트
	JTextField checkPwdText; //비밀번호 확인 텍스트
	
	
	// 수정할 비밀번호를 넣을 클래스
	//MemberVO mvo = new MemberVO();
	MyDataDAO dao = new MyDataDAO();
	BusMemDAO bdao = new BusMemDAO();
	
	MyDataDAO mydao = new MyDataDAO();
	String id = mydao.getMyId();//정보 테이블에 있는 아이디를 id에 저장
	MemberVO mvo = mydao.getMyData(id);//정보 테이블과 회원테이블을 조인해서 얻은 데이터를 mvo에 저장
	
	
	EditPwdUI() {
		
		// 기존 비밀번호 가져오기
		//dao.getData(mvo);
		
		mainColor = new Color(166,221,248);
		mainPan = new JPanel();
		mainPan.setLayout(null);
		mainPan.setBackground(mainColor);
		
		//라벨
		pwdLabel = new JLabel("기존 비밀번호");
		pwdLabel.setBounds(70,20,115,20);
		
		newPwdLabel = new JLabel("새로운 비밀번호");
		newPwdLabel.setBounds(70,75,130,20);
		
		checkPwdLabel = new JLabel("비밀번호 확인");
		checkPwdLabel.setBounds(70,130,115,20);
		
		
		//버튼
		okBtn = new JButton("확인");
		okBtn.setBounds(205,185,100,20);
		
		//텍스트
		pwdText = new JTextField();
		pwdText.setBounds(180,20,180,20);
		
		newPwdText = new JTextField();
		newPwdText.setBounds(180,75,180,20);
		
		checkPwdText = new JTextField();
		checkPwdText.setBounds(180,130,180,20);
		
		add(mainPan);
		
		mainPan.add(pwdLabel);
		mainPan.add(newPwdLabel);
		mainPan.add(checkPwdLabel);
		mainPan.add(okBtn);
		mainPan.add(pwdText);
		mainPan.add(newPwdText);
		mainPan.add(checkPwdText);
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image img=toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		setTitle("비밀번호 변경");
		setSize(500,250);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		eventSet();
	}
	
	public void eventSet() {
		okBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String pwd = pwdText.getText().trim();// 기존 비밀번호 저장
		String ednewPwd = newPwdText.getText().trim(); // 수정 비밀번호 저장
		String edcheckPwd = checkPwdText.getText().trim(); // 수정 비밀번호 저장
		
		// 기존 비밀번호
		
		if(e.getSource() == okBtn) {
			if(pwd.equals("")) { // 기존 비밀번호창이 빈칸일때
				JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요.");
				pwdText.setText("");
				pwdText.requestFocus();
			}else if(!(pwd.equals(mvo.getB_pwd()))) { // 기존 비밀번호가 일치하지 않을때
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
				pwdText.setText("");
				pwdText.requestFocus();
			}
		
			// 수정 비밀번호
			else if(ednewPwd.equals(mvo.getB_pwd())) { // 기존 비밀번호랑 일치할시
				JOptionPane.showMessageDialog(this, "기존 비밀번호랑 일치합니다.");
				newPwdText.setText("");
				newPwdText.requestFocus();
			}
		
			else if(ednewPwd.equals("")) { // 수정 비밀번호가 빈칸일 시
				JOptionPane.showMessageDialog(this, "수정할 비밀번호를 입력해주세요.");
				newPwdText.setText("");
				newPwdText.requestFocus();			
			}else if(newPwdText.getText().length()<6 || newPwdText.getText().length() > 16 
					|| !(Pattern.matches("^[a-z0-9]*$", newPwdText.getText()))) { // 수정 비밀번호 조건
				JOptionPane.showMessageDialog(this, "비번은 6~16자리 영문 소문자와 숫자로만 입력 가능합니다.");
				newPwdText.setText("");
				newPwdText.requestFocus();
				
			}
		
		// 비밀번호 확인
			
			else if(edcheckPwd.equals("")) { // 수정 확인 비밀번호가 빈칸일 시
				JOptionPane.showMessageDialog(this, "수정할 비밀번호를 입력해주세요.");
			}else if(checkPwdText.getText().length()<6 || checkPwdText.getText().length() > 16 
					|| !(Pattern.matches("^[a-z0-9]*$", checkPwdText.getText()))) { 
				// 수정 확인 비밀번호 조건
				JOptionPane.showMessageDialog(this, "비번은 6~16자리 영문 소문자와 숫자로만 입력 가능합니다.");
				checkPwdText.setText("");
				checkPwdText.requestFocus();
			}else if(!(edcheckPwd.equals(ednewPwd))) { // 수정 비밀번호랑 일치하지 않을시
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
				checkPwdText.setText("");
				checkPwdText.requestFocus();
			}
			else { // 위에 모든 조건을 일치했을때
				
				JOptionPane.showMessageDialog(null, "변경이 완료되었습니다!");
				dispose(); //창 닫기
				mvo.setB_pwd(edcheckPwd);
				bdao.editPwd(mvo, id);
			}
			// 수정한 비밀번호를 MemberVO클래스에 저장
			
			
		}
		
		
		
	}

	public static void main(String[] args) {
		
		new EditPwdUI();
	}
}

