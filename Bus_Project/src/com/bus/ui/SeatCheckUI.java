package com.bus.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bus.dao.BusCheckDAO;
import com.bus.dao.MyDataDAO;

public class SeatCheckUI extends JFrame implements ActionListener{

	private JPanel mainPan;	//메인 패널
	private JPanel textPan;	//텍스트 패널
	
	private JLabel seatLabel;		//좌석 번호 :
	
	private JTextField seatNumText;	//좌석번호 출력 라벨
	
	private JButton backBtn;		//뒤로가기 버튼
	
	private Color mainColor;
	
	MyDataDAO mydao = new MyDataDAO();
	String id = mydao.getMyId();//정보 테이블에 있는 아이디를 id에 저장
	BusCheckDAO bcdao=new BusCheckDAO();
	
	TicketingCheckUI tui;
	public SeatCheckUI(TicketingCheckUI tui){
		this.tui=tui;
		
		
		mainColor = new Color(166, 221, 248);
		mainPan=new JPanel();
		mainPan.setBackground(mainColor);
		mainPan.setLayout(null);
		
		textPan=new JPanel();
		textPan.setBackground(Color.white);
		textPan.setLayout(null);
		textPan.setBounds(10,10,370,50);
		
		seatLabel=new JLabel("좌석 번호 :");
		seatLabel.setBounds(30,15,80,20);
		
		
		int row=tui.jt.getSelectedRow();//선택된 행의 번호 구함	
		seatNumText=new JTextField(10);
		seatNumText.setText(tui.jt.getValueAt(row, 4).toString());
		seatNumText.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //텍스트필드 테두리 제거
		seatNumText.setBounds(105,5,300,40);
		seatNumText.setBackground(Color.white);
		seatNumText.setEditable(false);//텍스트 수정 불가
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image img=toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		backBtn=new JButton("뒤로가기");
		backBtn.setBounds(150,70,100,30);
		
		textPan.add(seatNumText);
		textPan.add(seatLabel);
		mainPan.add(backBtn);
		mainPan.add(textPan);
		add(mainPan);
		
		backBtn.addActionListener(this);
		
		setTitle("좌석 보기");
		setSize(400,150);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			dispose();
		}
	}
}
