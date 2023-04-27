
package com.bus.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class QuestionUI extends JFrame implements ActionListener{
	
	private JLabel mainLabel;  // Q & A
	private JLabel q1Label; // 질문1
	private JLabel q2Label; // 질문2
	private JLabel q3Label; // 질문3
	private JLabel q4Label; // 질문4
	private JLabel q5Label; // 질문5
	private JLabel q6Label; // 질문6
	private ImageIcon qui;
	
	private JButton backBtn; // 뒤로가기
	private JButton q1Btn; // 답1
	private JButton q2Btn; // 답2
	private JButton q3Btn; // 답3
	private JButton q4Btn; // 답4
	private JButton q5Btn; // 답5
	private JButton q6Btn; // 답6
	
	private JPanel mainPan;
	private JPanel topPan;
	private JPanel botPan;
	private JPanel backPan;
	private JPanel q1Pan;
	private JPanel q2Pan;
	private JPanel q3Pan;
	private JPanel q4Pan;
	private JPanel q5Pan;
	private JPanel q6Pan;
	private JPanel quesPan;
	
	
	
	private Color mainColor; // 메인 색상
	private Color btnColor; 
	Answer1 a1; // 답변1 ui
	Answer2 a2; // 답변2 ui
	Answer3 a3; // 답변3 ui
	Answer4 a4; // 답변4 ui
	Answer5 a5; // 답변5 ui
	Answer6 a6; // 답변6 ui
	MainUI main; // 메인화면
	
	public QuestionUI() {
		setTitle("자주하는 질문"); // 창 제목
		setSize(700,750); // 창 크기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫기
		setResizable(false); // 창 크기 고정
		setLocationRelativeTo(null); // 창 가운데 띄우기
		
		
		mainPan = new JPanel(new GridLayout(0,1)); // 메인패널
		topPan = new JPanel(new BorderLayout(80,80)); 
		// 이미지를 넣기 위한 패널,이미지 균형 맞추기 위한 간격 조절
	    botPan = new JPanel(new GridLayout(0,1,10,10));
		// 질문 버튼 넣기 위한 패널, 버튼 간격 조절
		backPan = new JPanel(new FlowLayout(FlowLayout.LEFT,20,10)); // 상단 뒤로가기 패널
		quesPan = new JPanel(new  BorderLayout()); // Q&A 라벨 패널
		q1Pan = new JPanel(new  GridLayout(0,1)); // 1번째 질문 패널
		q2Pan = new JPanel(new  GridLayout(0,1)); // 2번째 질문 패널
		q3Pan = new JPanel(new  GridLayout(0,1)); // 3번째 질문 패널
		q4Pan = new JPanel(new  GridLayout(0,1)); // 4번째 질문 패널
		q5Pan = new JPanel(new  GridLayout(0,1)); // 5번째 질문 패널
		q6Pan = new JPanel(new  GridLayout(0,1)); // 6번째 질문 패널	
		// 상위 패널들의 배경색 지정
		mainColor = new Color(245,245,245);
		btnColor = new Color(166, 221, 248);
		qui = new ImageIcon("./images/question.png");
		
		topPan.setBackground(mainColor);
		botPan.setBackground(mainColor);
		backPan.setBackground(mainColor);
		quesPan.setBackground(mainColor);
		
		// 질문 패널들의 배경색, 여백 지정
		mainPan.setBackground(mainColor);

		
		mainLabel = new JLabel(qui);
		mainLabel.setFont(new Font("Lucida Console",Font.BOLD,60));

		// 각 버튼들의 크기,글씨 지정
		q1Btn  = new JButton("Q1. 다른 휴대폰 번호로 결제가 가능한가요?");
		//Q1btn.setPreferredSize(new Dimension(200,5));
		q1Btn.setFont(new Font("Fixedsys",Font.BOLD,20));
		q1Btn.setBackground(btnColor);
		
		q2Btn  = new JButton("Q2. 회원 정보 변경, 삭제는 어디서 하나요?");
		//Q2btn.setPreferredSize(new Dimension(60,5));
		q2Btn.setFont(new Font("Fixedsys",Font.BOLD,20));
		q2Btn.setBackground(btnColor);
		
		q3Btn  = new JButton("Q3. 버스 요금 기준이 어떻게 되나요?");
		//Q3btn.setPreferredSize(new Dimension(60,5));
		q3Btn.setFont(new Font("Fixedsys",Font.BOLD,20));
		q3Btn.setBackground(btnColor);
		
		q4Btn  = new JButton("Q4. 반려동물과 함께 탑승 가능한가요?");
		//Q4btn.setPreferredSize(new Dimension(60,5));
		q4Btn.setFont(new Font("Fixedsys",Font.BOLD,20));
		q4Btn.setBackground(btnColor);
		
		q5Btn  = new JButton("Q5. 갑자기 버스가 취소 되었는데 왜 그런가요?");
		//Q5btn.setPreferredSize(new Dimension(60,5));
		q5Btn.setFont(new Font("Fixedsys",Font.BOLD,20));
		q5Btn.setBackground(btnColor);
		
		q6Btn  = new JButton("Q6. 장애인은 버스이용을 어떻게 하나요?");
		//Q6btn.setPreferredSize(new Dimension(60,5));
		q6Btn.setFont(new Font("Fixedsys",Font.BOLD,20));
		q6Btn.setBackground(btnColor);
		
	
		
		// 라벨 글씨 크기, 모양 설정
		mainLabel.setFont(new Font("HYGraphic",Font.BOLD,60));

		// 뒤로가기 버튼 글씨,크기 지정
		backBtn = new JButton("뒤로가기");
		backBtn.setPreferredSize(new Dimension(90,35));
		backBtn.setFont(new Font("HYGraphic",Font.BOLD,13));
		
		// 각 패널 라벨,버튼 대입
		quesPan.add(mainLabel,BorderLayout.NORTH);
		//Q1pan.add(Q1Label,BorderLayout.WEST);
		q1Pan.add(q1Btn);
		//Q2pan.add(Q2Label,BorderLayout.WEST);
		q2Pan.add(q2Btn);
		//Q3pan.add(Q3Label,BorderLayout.WEST);
		q3Pan.add(q3Btn);
		//Q4pan.add(Q4Label,BorderLayout.WEST);
		q4Pan.add(q4Btn);
		//Q5pan.add(Q5Label,BorderLayout.WEST);
		q5Pan.add(q5Btn);
		q6Pan.add(q6Btn);
		backPan.add(backBtn);
		
		// 이미지, 뒤로가기 버튼 넣기
		topPan.add(quesPan,BorderLayout.CENTER);
		topPan.add(backPan,BorderLayout.NORTH);
		
		//  질문 버튼 넣기
		botPan.add(q1Pan);
		botPan.add(q2Pan);
		botPan.add(q3Pan);
		botPan.add(q4Pan);
		botPan.add(q5Pan);
		botPan.add(q6Pan);
		
		mainPan.add(topPan);
		mainPan.add(botPan);
		

		
		eventSet(); // 버튼효과 메서드
		
		add(mainPan); // 프레임에 패널 대입
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		setVisible(true);	// 항상 보이게 하기		
	}
	
	public void eventSet() {
		q1Btn.addActionListener(this);
		q2Btn.addActionListener(this);
		q3Btn.addActionListener(this);
		q4Btn.addActionListener(this);
		q5Btn.addActionListener(this);
		q6Btn.addActionListener(this);
		backBtn.addActionListener(this);	
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == q1Btn) {
			
			a1 = new Answer1();
		}else if(e.getSource() == q2Btn) {
			
			a2 = new Answer2();
		}else if(e.getSource() == q3Btn) {
			
			a3 = new Answer3();
		}else if(e.getSource() == q4Btn) {
			
			a4 = new Answer4();
		}else if(e.getSource() == q5Btn) {
			
			a5 = new Answer5();
		}else if(e.getSource() == q6Btn) {
			
			a6 = new Answer6();
		}else if(e.getSource() == backBtn) {
			dispose();
			main = new MainUI();
		}
		
	}
	public static void main(String[] args) {
		
		new QuestionUI();

	}

}
