package com.bus.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bus.dao.BusMemDAO;
import com.bus.dao.MyDataDAO;
import com.bus.vo.MemberVO;

public class MainUI extends JFrame implements ActionListener {

	private JPanel mainPan = new JPanel(new BorderLayout());//메인 패널
	private JPanel topPan = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
	//메인패널 북쪽에 배치될 패널 이름라벨, 로그아웃 버튼이 들어감
	private JPanel btnPan = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 50));
	//메인패널 센터에 배치될 패널 메뉴선택 버튼이 들어감
	
	private ImageIcon busImage;//화면 밑 버스 이미지
	private ImageIcon cloud1Image;//예매하기 버튼 이미지
	private ImageIcon cloud2Image;//예매확인 및 취소 버튼 이미지
	private ImageIcon cloud3Image;//마이페이지 버튼 이미지
	private ImageIcon cloud4Image;//자주 물어보는 질문 버튼 이미지
	
	private Color mainColor;//메인 색상
	
	private JLabel nameLabel;//상단에 이름이 들어가는 라벨
	
	private JButton logoutBtn;//로그아웃 버튼
	private JButton cloud1Btn;//예매하기 버튼
	private JButton cloud2Btn;//예매확인 및 취소 버튼
	private JButton cloud3Btn;//마이페이지 버튼
	private JButton cloud4Btn;//자주물어보는 질문 버튼
	
	LoginUI logui;
	TicketingMenuUI tui;
	MyPageUI myui;
	MyDataDAO mydao = new MyDataDAO();
	String id = mydao.getMyId();//정보 테이블에 있는 아이디를 id에 저장
	MemberVO mvo = mydao.getMyData(id);//정보 테이블과 회원테이블을 조인해서 얻은 데이터를 mvo에 저장
	
	MainUI(){
		
		busImage = new ImageIcon("./images/bus02.png");
		JLabel mainimage = new JLabel(busImage);
		
		mainColor = new Color(166, 221, 248);
		btnPan.setBackground(mainColor);
		topPan.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		
		nameLabel = new JLabel(mvo.getB_name()+"님 환영합니다.");
		nameLabel.setFont(new Font("맑은고딕", Font.BOLD, 15));
		
		logoutBtn = new JButton("로그아웃");
		topPan.add(nameLabel);
		topPan.add(logoutBtn);
		
		cloud1Image = new ImageIcon("./images/cloud1.png");
		cloud1Btn =new JButton(cloud1Image);
		cloud1Btn.setBackground(mainColor);
		cloud1Btn.setBorderPainted(false); //선택되지 않은 상태에서 외곽선 없앰
		//bt1.setContentAreaFilled(false); //내용영역채우기 없앰
		//bt1.setFocusPainted(false);//버튼 선택시 얆은 점선 테두리 없앰
		cloud2Image = new ImageIcon("./images/cloud2.png");
		cloud2Btn =new JButton(cloud2Image);
		cloud2Btn.setBackground(mainColor);
		cloud2Btn.setBorderPainted(false);
		//bt2.setContentAreaFilled(false);
		//bt2.setFocusPainted(false);
		cloud3Image = new ImageIcon("./images/cloud3.png");
		cloud3Btn =new JButton(cloud3Image);
		cloud3Btn.setBackground(mainColor);
		cloud3Btn.setBorderPainted(false);
		//bt3.setContentAreaFilled(false);
		//bt3.setFocusPainted(false);
		cloud4Image = new ImageIcon("./images/cloud4.png");
		cloud4Btn = new JButton(cloud4Image);
		cloud4Btn.setBorderPainted(false);
		cloud4Btn.setBackground(mainColor);
		btnPan.add(cloud1Btn);
		btnPan.add(cloud2Btn);
		btnPan.add(cloud3Btn);
		btnPan.add(cloud4Btn);
		
		mainPan.add(mainimage, BorderLayout.SOUTH);
		mainPan.add(topPan, BorderLayout.NORTH);
		mainPan.add(btnPan, BorderLayout.CENTER);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		eventSet();
		
		add(mainPan);
		setTitle("자바 버스 예매 시스템 메인");
		setSize(900, 600);
		setLocationRelativeTo(null);//창이 가운데 나오게 설정
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void eventSet() {
		logoutBtn.addActionListener(this);
		cloud1Btn.addActionListener(this);
		cloud2Btn.addActionListener(this);
		cloud3Btn.addActionListener(this);
		cloud4Btn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logoutBtn) {
			dispose();//창 하나만 사라짐
			logui = new LoginUI();
		}else if(e.getSource() == cloud1Btn) {
			dispose();
			tui = new TicketingMenuUI();
		}else if(e.getSource() == cloud2Btn) {
			dispose();
			new TicketingCheckUI();
			
		}else if(e.getSource() == cloud3Btn) {
			dispose();
			myui = new MyPageUI();
		}else if(e.getSource() == cloud4Btn) {
			dispose();
			new QuestionUI();
		}
	}
	
	public void disp(int a) {
		if(a == 1) {
			this.dispose();
		}
	}

	public static void main(String[] args) {

		new MainUI();
	}

}
