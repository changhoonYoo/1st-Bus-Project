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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bus.dao.AdminDAO;
import com.bus.dao.BusCheckDAO;
import com.bus.dao.BusMemDAO;
import com.bus.dao.MyDataDAO;
import com.bus.vo.BusCheckVO;
import com.bus.vo.MemberVO;

public class TicketingCheckUI extends JFrame implements ActionListener {
	// 패널숫자 1 = 동쪽, 2 = 서쪽, 3 = 남쪽, 4 = 북쪽, 5 = 센터를 뜻함
	private JPanel mainPan = new JPanel(new BorderLayout());// 메인패널
	private JPanel colorPan1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
	// mainPan 동쪽 배경색을 입힐 패널
	private JPanel colorPan2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
	// mainPan 서쪽 배경색을 입힐 패널
	private JPanel colorPan3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 30));
	// mainPan 남쪽 배경색을 입힐 패널
	private JPanel colorPan4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 140, 20));
	// mainPan 북쪽 배경색을 입힐 패널 뒤로가기 이름라벨 로그아웃버튼이 들어간다.
	private JPanel centerPan5 = new JPanel(new BorderLayout());
	// mainPan 센터에 배치될 패널
	private JPanel topPan = new JPanel(new FlowLayout(FlowLayout.LEFT, 25,5));
	// centerPan5 북쪽에 위치함, 날짜, 시간 , 출발지, 도착지를 넣을 라벨
	private JPanel centerPan55 = new JPanel(new BorderLayout());
	// centerPan5 센터에 배치될 패널
	private JPanel colorPan51 = new JPanel();
	// centerPan5 동쪽 배경색을 입힐패널
	private JPanel colorPan52 = new JPanel();
	// centerPan5 서쪽 배경색을 입힐패널
	private JPanel colorPan53 = new JPanel();
	// centerPan5 남쪽 배경색을 입힐패널
	private JPanel midPan = new JPanel();
	// topPan아래에 위치한 패널
	private JPanel botPan = new JPanel();
	// midPan아래에 위치 검색조건에 맞는 버스 목록을 불러옴


	private Color mainColor;// 기본 메인색상
	private Color outColor;// 창틀 모양을 만들어줄 색상

	private JLabel nameLabel;// 상단에 이름이 들어가는 라벨

	private JButton backBtn;// 뒤로가기 버튼
	private JButton logoutBtn;// 로그아웃 버튼
	private JButton cancelBtn;// 예매취소 버튼
	private JButton sLookBtn;//좌석 확인

	String[] name= {"날짜","시간","출발터미널","도착터미널","좌석번호","결제금액"};
	DefaultTableModel dt=new DefaultTableModel(name,0) {
		public boolean isCellEditable(int rowIndex, int mColindex) {
	         return false;
	      }
	};//셀 값 수정 못하게;
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);

	BusCheckDAO bcdao=new BusCheckDAO();
	BusMemDAO bdao = new BusMemDAO();
	BusCheckVO bcvo = new BusCheckVO();

	
	MyDataDAO mydao = new MyDataDAO();
	String id = mydao.getMyId();//정보 테이블에 있는 아이디를 id에 저장
	MemberVO mvo = mydao.getMyData(id);//정보 테이블과 회원테이블을 조인해서 얻은 데이터를 mvo에 저장
	TicketingCheckUI(){
				
		jsp.setPreferredSize(new Dimension(755,470));
		midPan.add(jsp);
		mainColor = new Color(166, 221, 248);
		
		mainPan.setBackground(mainColor);
		colorPan1.setBackground(mainColor);
		colorPan2.setBackground(mainColor);
		colorPan3.setBackground(mainColor);
		colorPan4.setBackground(mainColor);
		
		backBtn = new JButton("뒤로가기");
		
		nameLabel = new JLabel(mvo.getB_name()+"님의 예매 내역입니다.");
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		logoutBtn = new JButton("로그아웃");
		colorPan4.add(backBtn);
		colorPan4.add(nameLabel);
		colorPan4.add(logoutBtn);
		
		botPan.add(jsp);
		cancelBtn = new JButton("예매 취소하기");
		cancelBtn.setPreferredSize(new Dimension(150, 50));
		sLookBtn=new JButton("좌석 보기");
		sLookBtn.setPreferredSize(new Dimension(150,50));
		colorPan3.add(sLookBtn);
		colorPan3.add(cancelBtn);
		
		
		mainPan.add(colorPan1, BorderLayout.EAST);
		mainPan.add(colorPan2, BorderLayout.WEST);
		mainPan.add(colorPan3, BorderLayout.SOUTH);
		mainPan.add(colorPan4, BorderLayout.NORTH);
		mainPan.add(centerPan5, BorderLayout.CENTER);

		outColor = new Color(213, 229, 243);
		
		
		topPan.setBackground(outColor);
		
		colorPan51.setBackground(outColor);
		colorPan52.setBackground(outColor);
		colorPan53.setBackground(outColor);
	
		centerPan5.add(colorPan51, BorderLayout.EAST);
		centerPan5.add(colorPan52, BorderLayout.WEST);
		centerPan5.add(colorPan53, BorderLayout.SOUTH);
		centerPan5.add(topPan, BorderLayout.NORTH);
		centerPan5.add(centerPan55, BorderLayout.CENTER);
		
		midPan.setBackground(outColor);
		botPan.setBackground(outColor);
		
		centerPan55.add(midPan, BorderLayout.NORTH);
		centerPan55.add(botPan, BorderLayout.CENTER);
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image img=toolkit.getImage("./images/titlebus.png");
		setIconImage(img);
		
		bcdao.getCheckId(dt,id);
		if(dt.getRowCount()>0) {
			jt.setRowSelectionInterval(0, 0);
		}
		
		eventSet();
		
		add(mainPan);
		setTitle("자바 버스 예매 시스템");
		setSize(800,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TicketingCheckUI();
	}
	public void eventSet() {
		backBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		sLookBtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == backBtn) {
			dispose();
			new MainUI();
		}
		if(e.getSource() == logoutBtn) {
			dispose();
			new LoginUI();
		}
		if(e.getSource() == sLookBtn) {
			if(dt.getRowCount()>0) {
				new SeatCheckUI(this);				
			}else {
				AdminDAO.messageBox(this, "예매된 내역이 없습니다!");
			}
		}
		if(e.getSource() == cancelBtn) {
			int row=jt.getSelectedRow();//JTable 선택된 행번호 구해 row에 저장
			
			bcvo.setBd_id(this.id);
			bcvo.setBb_day(dt.getValueAt(row, 0).toString());
			bcvo.setBb_time(dt.getValueAt(row, 1).toString());
			bcvo.setBb_start(dt.getValueAt(row, 2).toString());
			bcvo.setBb_end(dt.getValueAt(row, 3).toString());
			bcvo.setBb_seat(dt.getValueAt(row, 4).toString());
			bcvo.setBb_price(Integer.parseInt(dt.getValueAt(row, 5).toString()));
			if(bcdao.checkDelete(bcvo)>0) {//버스번호 문자열을 정수 숫자로 변경
				AdminDAO.messageBox(this, "취소가 완료되었습니다!");
				bcdao.getCheckId(dt, id);
				
				int a= bcdao.seatCheck(bcvo);
				bcdao.seatUpdatePlus(a,bcvo.getBb_seat(),bcvo);
			}
		}
	}
}
