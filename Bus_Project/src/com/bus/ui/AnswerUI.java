package com.bus.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

class Answer1 extends JFrame implements ActionListener{ // 1번째 답변
	
	private JLabel aLabel1; // 질문 라벨
	private JTextArea aT1; // 답변 텍스트
	private Color mainColor; // 메인 색상
	private JButton backBtn; // 자주하는 질문으로 돌아가는 버튼
	private JPanel mainPan,aPan1,aPan11,mainTop,
	mainWest,mainEast,mainSouth,mainIn,aLabel1Pan,backPan; 
	// 라벨, 텍스트 그리고 패널들의 균형들을 맞추기 위한 패널
	QuestionUI qui; // 자주하는 질문 ui
	
	
	
	public Answer1(){
		
		setTitle("a1");
		setSize(805,280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		
		mainPan = new JPanel(new BorderLayout());
		mainTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainWest = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainEast = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainIn = new JPanel(new BorderLayout());
		aPan1 = new JPanel(new BorderLayout(10,10)); // ap11,텍스트 넣기 위한 패널
		aPan11 = new JPanel(new BorderLayout(0,0)); //  라벨,버튼 넣기 위한 패널
		
		aLabel1 = new JLabel("Q1. 다른  휴대폰 번호로 결제가 가능한가요?"); // 질문
		aLabel1.setFont(new Font("HYGraphic",Font.BOLD,18));
		aT1 = new JTextArea("\n"
				+ " 가입하실 때 입력하신 휴대폰 번호를 기준으로 결제가 진행되기 때문에\n 가입 당시 핸드폰 번호가 아닌 다른 핸드폰 번호로 결제를 원하는 경우에는\n"
				+ " 마이 페이지에서 핸드폰 번호를 변경하신 후에 결제를 진행해 주시기 바랍니다.",19,37); // 답변 텍스트
		
		
		
		aT1.setForeground(Color.BLACK);
		aT1.setFont(new Font("HYGraphic",Font.BOLD,20));
		mainColor = new Color(166, 221, 248);
		backBtn = new JButton("뒤로가기"); 
		aPan1.setBackground(mainColor);
		aPan11.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		mainTop.setBackground(mainColor);
		mainWest.setBackground(mainColor);
		mainEast.setBackground(mainColor);
		mainSouth.setBackground(mainColor);
		
		aLabel1Pan = new JPanel();
		backPan = new JPanel();
		aPan11.add(aLabel1Pan,BorderLayout.WEST);
		aPan11.add(backPan,BorderLayout.EAST);
		mainIn.add(aPan11,BorderLayout.NORTH);
		mainIn.add(aT1,BorderLayout.CENTER);
		
		mainPan.add(mainTop, BorderLayout.NORTH);
		mainPan.add(mainEast, BorderLayout.EAST);
		mainPan.add(mainWest, BorderLayout.WEST);
		mainPan.add(mainSouth, BorderLayout.SOUTH);
		mainPan.add(mainIn, BorderLayout.CENTER);
		aPan11.add(aLabel1,BorderLayout.WEST);
		aPan11.add(backBtn,BorderLayout.EAST);
		aPan1.add(aPan11,BorderLayout.NORTH);
		aPan1.add(aT1,BorderLayout.CENTER);
		mainPan.add(aPan1);
		add(mainPan);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		aT1.setLineWrap(true); // 텍스트area 자동 줄바꿈
		aT1.setEnabled(true); // 텍스트area 비활성화
		


		
		eventSet();
		
		setVisible(true);
	}
	
	public void eventSet() {
		backBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			dispose();
			
		}
	}
}

class Answer2 extends JFrame implements ActionListener{ // 2번째 답변
	
	private JLabel aLabel2; // 질문 라벨
	private JTextArea aT2; // 답변 텍스트
	private Color mainColor; // 메인 색상
	private JButton backBtn; // 자주하는 질문으로 돌아가는 버튼
	private JPanel mainPan,aPan2,aPan22,mainTop,mainWest,mainEast,
	mainSouth,mainIn,aLabel2Pan,backPan;
	// 라벨, 텍스트 그리고 패널들의 균형들을 맞추기 위한 패널
	QuestionUI qui; // 자주하는 질문 ui
	
	public Answer2(){
		
		setTitle("a2");
		setSize(805,280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
			
		aLabel2Pan = new JPanel();
		backPan = new JPanel();
		mainPan = new JPanel(new BorderLayout());
		mainTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainWest = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainEast = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainIn = new JPanel(new BorderLayout());
		aPan2 = new JPanel(new BorderLayout(10,10)); // aP22,텍스트 넣기 위한 패널
		aPan22 = new JPanel(new  BorderLayout(0,0)); //  라벨,버튼 넣기 위한 패널
		
		aLabel2 = new JLabel("Q2. 회원 정보 변경, 삭제는 어디서 하나요?"); // 질문
		aLabel2.setFont(new Font("HYGraphic",Font.BOLD,18));
		aT2 = new JTextArea("\n 회원 정보 변경은 메인 페이지 -> 마이페이지로 이동 후"
				+ "정보 변경이 가능합니다.\n 회원 탈퇴 역시 마이페이지에서 탈퇴 버튼을 통해 하실 수 있습니다",19,37); // 답변 텍스트
		
		
		aT2.setFont(new Font("HYGraphic",Font.BOLD,20));
		mainColor = new Color(166, 221, 248);
		backBtn = new JButton("뒤로가기");
		aPan2.setBackground(mainColor);
		aPan22.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		aPan2.setBackground(mainColor);
		aPan22.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		mainTop.setBackground(mainColor);
		mainWest.setBackground(mainColor);
		mainEast.setBackground(mainColor);
		mainSouth.setBackground(mainColor);
		mainPan.add(mainTop, BorderLayout.NORTH);
		mainPan.add(mainEast, BorderLayout.EAST);
		mainPan.add(mainWest, BorderLayout.WEST);
		mainPan.add(mainSouth, BorderLayout.SOUTH);
		mainPan.add(mainIn, BorderLayout.CENTER);
		aPan22.add(aLabel2,BorderLayout.WEST);
		aPan22.add(backBtn,BorderLayout.EAST);
		aPan2.add(aPan22,BorderLayout.NORTH);
		aPan2.add(aT2,BorderLayout.CENTER);
		mainPan.add(aPan2);
		add(mainPan);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		aT2.setLineWrap(true); // 텍스트area 자동 줄바꿈
		aT2.setEnabled(true); // 텍스트area 비활성화
		
		eventSet();
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == backBtn) {
				dispose();
			
			}
	}

	public void eventSet() {
		backBtn.addActionListener(this);
	}
}

class Answer3 extends JFrame implements ActionListener{ // 3번째 답변
	
	private JLabel aLabel3; // 질문 라벨
	private JTextArea aT3; // 답변 텍스트
	private Color mainColor; // 메인 색상
	private JButton backBtn; // 자주하는 질문으로 돌아가는 버튼
	private JPanel mainPan,aPan3,aPan33,mainTop,
	mainWest,mainEast,mainSouth,mainIn,aLabel3Pan,backPan; 
	// 라벨, 텍스트 그리고 패널들의 균형들을 맞추기 위한 패널
	QuestionUI qui; // 자주하는 질문 ui
	
	public Answer3(){
		
		setTitle("a3");
		setSize(805,280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
				
		
		aLabel3Pan = new JPanel();
		backPan = new JPanel();
		mainPan = new JPanel(new BorderLayout());
		mainTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainWest = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainEast = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainIn = new JPanel(new BorderLayout());
		aPan3 = new JPanel(new BorderLayout(10,10)); // aP22,텍스트 넣기 위한 패널
		aPan33 = new JPanel(new  BorderLayout(0,0)); //  라벨
		
		aLabel3 = new JLabel("Q3. 버스 요금 기준이 어떻게 되나요?"); // 질문
		aLabel3.setFont(new Font("HYGraphic",Font.BOLD,18));
		aT3 = new JTextArea("\n 일반 요금 기준으로 어린이일 경우 일반 요금의 50%\n 어르신일 경우 일반 요금의"
				+ "25% 할인이 적용됩니다.\n\n 예) 일반 요금 기준 10000원인 경우 어린이 5000원, 어르신 7500원으로 금액 적용",19,37); // 답변 텍스트
		
		
		aT3.setFont(new Font("HYGraphic",Font.BOLD,20));
		mainColor = new Color(166, 221, 248);
		backBtn = new JButton("뒤로가기");
		aPan3.setBackground(mainColor);
		aPan33.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		aPan3.setBackground(mainColor);
		aPan33.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		mainTop.setBackground(mainColor);
		mainWest.setBackground(mainColor);
		mainEast.setBackground(mainColor);
		mainSouth.setBackground(mainColor);
		mainPan.add(mainTop, BorderLayout.NORTH);
		mainPan.add(mainEast, BorderLayout.EAST);
		mainPan.add(mainWest, BorderLayout.WEST);
		mainPan.add(mainSouth, BorderLayout.SOUTH);
		mainPan.add(mainIn, BorderLayout.CENTER);
		aPan33.add(aLabel3,BorderLayout.WEST);
		aPan33.add(backBtn,BorderLayout.EAST);
		aPan3.add(aPan33,BorderLayout.NORTH);
		aPan3.add(aT3,BorderLayout.CENTER);
		mainPan.add(aPan3);
		add(mainPan);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		eventSet();
		
		aT3.setLineWrap(true); // 텍스트area 자동 줄바꿈
		aT3.setEnabled(true); // 텍스트area 비활성화
		
		setVisible(true);
	}
	
	public void eventSet() {
		backBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			dispose();
			
		}	
	}
}

class Answer4 extends JFrame implements ActionListener{ // 4번째 답변
	
	private JLabel aLabel4; // 질문 라벨
	private JTextArea aT4; // 답변 텍스트
	private Color mainColor; // 메인 색상
	private JButton backBtn; // 자주하는 질문으로 돌아가는 버튼
	private JPanel mainPan,aPan4,aPan44,mainTop,mainWest,mainEast,
	mainSouth,mainIn,aLabel4Pan,backPan;
	// 라벨, 텍스트 그리고 패널들의 균형들을 맞추기 위한 패널
	QuestionUI qui; // 자주하는 질문 ui
	
	public Answer4(){
		
		setTitle("a4");
		setSize(805,280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
			
		aLabel4Pan = new JPanel();
		backPan = new JPanel();
		mainPan = new JPanel(new BorderLayout());
		mainTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainWest = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainEast = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainIn = new JPanel(new BorderLayout());
		aPan4 = new JPanel(new BorderLayout(10,10)); // aP22,텍스트 넣기 위한 패널
		aPan44 = new JPanel(new  BorderLayout(0,0)); //  
		
		aLabel4 = new JLabel("Q4. 반려동물과 함께 탑승 가능한가요?"); // 질문
		aLabel4.setFont(new Font("HYGraphic",Font.BOLD,18));
		aT4 = new JTextArea("\n 다른 승객분들에게 위해를 끼치거나\n 불쾌감을 줄 우려가 있는"
				+ " 동물은 탑승이 불가하며\n 장애인 보조견 및 애완동물 동반 탑승 시"
				+ " 전용 캐리어를 이용해야 합니다.",19,37); // 답변 텍스트
		
		
		aT4.setFont(new Font("HYGraphic",Font.BOLD,20));
		mainColor = new Color(166, 221, 248);
		backBtn = new JButton("뒤로가기");
		aPan4.setBackground(mainColor);
		aPan44.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		aPan4.setBackground(mainColor);
		aPan44.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		mainTop.setBackground(mainColor);
		mainWest.setBackground(mainColor);
		mainEast.setBackground(mainColor);
		mainSouth.setBackground(mainColor);
		mainPan.add(mainTop, BorderLayout.NORTH);
		mainPan.add(mainEast, BorderLayout.EAST);
		mainPan.add(mainWest, BorderLayout.WEST);
		mainPan.add(mainSouth, BorderLayout.SOUTH);
		mainPan.add(mainIn, BorderLayout.CENTER);
		aPan44.add(aLabel4,BorderLayout.WEST);
		aPan44.add(backBtn,BorderLayout.EAST);
		aPan4.add(aPan44,BorderLayout.NORTH);
		aPan4.add(aT4,BorderLayout.CENTER);
		mainPan.add(aPan4);
		add(mainPan);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		aT4.setLineWrap(true); // 텍스트area 자동 줄바꿈
		aT4.setEnabled(true); // 텍스트area 비활성화
		
		eventSet();
		
		setVisible(true);
	}
	
	public void eventSet() {
		backBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			dispose();
			
		}
	}
}

class Answer5 extends JFrame implements ActionListener{ // 5번째 답변
	
	private JLabel aLabel5; // 질문 라벨
	private JTextArea aT5; // 답변 텍스트
	private Color mainColor; // 메인 색상
	private JButton backBtn; // 자주하는 질문으로 돌아가는 버튼
	private JPanel mainPan,aPan5,aPan55,mainTop,mainWest,mainEast,mainSouth,
	mainIn,aLabel5Pan,backPan;
	// 라벨, 텍스트 그리고 패널들의 균형들을 맞추기 위한 패널
	QuestionUI qui; // 자주하는 질문 ui
	
	public Answer5(){
		
		setTitle("a5");
		setSize(805,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		aLabel5Pan = new JPanel();
		backPan = new JPanel();
		mainPan = new JPanel(new BorderLayout());
		mainTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainWest = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainEast = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainIn = new JPanel(new BorderLayout());
		aPan5 = new JPanel(new BorderLayout(10,10)); // aP22,텍스트 넣기 위한 패널
		aPan55 = new JPanel(new  BorderLayout(0,0)); //  
		
		aLabel5 = new JLabel("Q5. 갑자기 버스가 취소 되었는데 왜 그런가요?"); // 질문
		aLabel5.setFont(new Font("HYGraphic",Font.BOLD,18));
		aT5 = new JTextArea("\n 버스회사 사정으로 인해 배차가 취소될 수 있습니다.\n 이런 경우에는 별도의 안내를 해드린 후에 표 예매를 취소하고\n"
				+ "전액 환불을 해드리고 있습니다.\n\n 더 궁금한 사항이 있으시다면 관리자에게 문의를 부탁드립니다."
				+ "\n 문의 이메일 : busAdmin@gmail.com",19,37); // 답변 텍스트
		
		
		aT5.setFont(new Font("HYGraphic",Font.BOLD,20));
		mainColor = new Color(166, 221, 248);
		backBtn = new JButton("뒤로가기");
		aPan5.setBackground(mainColor);
		aPan55.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		aPan5.setBackground(mainColor);
		aPan55.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		mainTop.setBackground(mainColor);
		mainWest.setBackground(mainColor);
		mainEast.setBackground(mainColor);
		mainSouth.setBackground(mainColor);
		mainPan.add(mainTop, BorderLayout.NORTH);
		mainPan.add(mainEast, BorderLayout.EAST);
		mainPan.add(mainWest, BorderLayout.WEST);
		mainPan.add(mainSouth, BorderLayout.SOUTH);
		mainPan.add(mainIn, BorderLayout.CENTER);
		aPan55.add(aLabel5,BorderLayout.WEST);
		aPan55.add(backBtn,BorderLayout.EAST);
		aPan5.add(aPan55,BorderLayout.NORTH);
		aPan5.add(aT5,BorderLayout.CENTER);
		mainPan.add(aPan5);
		add(mainPan);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		aT5.setLineWrap(true); // 텍스트area 자동 줄바꿈
		aT5.setEnabled(true); // 텍스트area 비활성화
		
		eventSet();
		
		setVisible(true);
	}
	
	public void eventSet() {
		backBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			dispose();
		
		}
	}
}

class Answer6 extends JFrame implements ActionListener{ // 5번째 답변
	
	private JLabel aLabel6; // 질문 라벨
	private JTextArea aT6; // 답변 텍스트
	private Color mainColor; // 메인 색상
	private JButton backBtn; // 자주하는 질문으로 돌아가는 버튼
	private JPanel mainPan,aPan6,aPan66,mainTop,mainWest,mainEast,
	mainSouth,mainIn,aLabel6Pan,backPan;
	// 라벨, 텍스트 그리고 패널들의 균형들을 맞추기 위한 패널
	QuestionUI qui; // 자주하는 질문 ui
	
	public Answer6(){
		
		setTitle("a5");
		setSize(805,280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		aLabel6Pan = new JPanel();
		backPan = new JPanel();
		mainPan = new JPanel(new BorderLayout());
		mainTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainWest = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainEast = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		mainSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainIn = new JPanel(new BorderLayout());
		aPan6 = new JPanel(new BorderLayout(10,10)); // aP22,텍스트 넣기 위한 패널
		aPan66= new JPanel(new  BorderLayout(0,0)); // 
		
		aLabel6= new JLabel("Q6. 장애인인 경우 버스 이용은 어떻게 하나요?"); // 질문
		aLabel6.setFont(new Font("HYGraphic",Font.BOLD,18));
		aT6 = new JTextArea("\n 저희 회사와 계약된 버스들은 모두 장애인분들이 탑승하실 수 있도록\n"
				+ " 준비가 된 버스로만 이루어져 있습니다.\n\n 혹시라도 이용 중 불편한 사항이 있으시다면 관리자에게 문의를 부탁드립니다"
				+ "\n 문의 이메일 : busAdmin@gmail.com",19,37); // 답변 텍스트
		
		
		
		aT6.setFont(new Font("HYGraphic",Font.BOLD,20));
		mainColor = new Color(166, 221, 248);
		backBtn = new JButton("뒤로가기");
		aPan6.setBackground(mainColor);
		aPan66.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		aPan6.setBackground(mainColor);
		aPan66.setBackground(mainColor);
		mainPan.setBackground(mainColor);
		mainTop.setBackground(mainColor);
		mainWest.setBackground(mainColor);
		mainEast.setBackground(mainColor);
		mainSouth.setBackground(mainColor);
		mainPan.add(mainTop, BorderLayout.NORTH);
		mainPan.add(mainEast, BorderLayout.EAST);
		mainPan.add(mainWest, BorderLayout.WEST);
		mainPan.add(mainSouth, BorderLayout.SOUTH);
		mainPan.add(mainIn, BorderLayout.CENTER);
		aPan66.add(aLabel6,BorderLayout.WEST);
		aPan66.add(backBtn,BorderLayout.EAST);
		aPan6.add(aPan66,BorderLayout.NORTH);
		aPan6.add(aT6,BorderLayout.CENTER);
		mainPan.add(aPan6);
		add(mainPan);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		aT6.setLineWrap(true); // 텍스트area 자동 줄바꿈
		aT6.setEnabled(true); // 텍스트area 비활성화
		
		eventSet();
		
		setVisible(true);
	}
	
	public void eventSet() {
		backBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			dispose();			
		}
	}

}

public class AnswerUI {

}
