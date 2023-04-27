package com.bus.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bus.dao.BusListDAO;
import com.bus.dao.MyDataDAO;
import com.bus.vo.BusListVO;
import com.bus.vo.MemberVO;

public class TicketingMenuUI extends JFrame implements ActionListener {

	//패널숫자 1 = 동쪽, 2 = 서쪽, 3 = 남쪽, 4 = 북쪽, 5 = 센터를 뜻함
	private JPanel mainPan = new JPanel(new BorderLayout());//메인패널
	private JPanel colorPan1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
	//mainPan 동쪽 배경색을 입힐 패널
	private JPanel colorPan2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
	//mainPan 서쪽 배경색을 입힐 패널
	private JPanel colorPan3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
	//mainPan 남쪽 배경색을 입힐 패널
	private JPanel colorPan4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 140, 20));
	//mainPan 북쪽 배경색을 입힐 패널 뒤로가기 이름라벨 로그아웃버튼이 들어간다.
	private JPanel centerPan5 = new JPanel(new BorderLayout());
	//mainPan 센터에 배치될 패널
	private JPanel topPan = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
	//centerPan5 북쪽에 위치함, 콤보박스 조회하기 등이 들어갈 패널
	private JPanel centerPan55 = new JPanel();
	//centerPan5 센터에 배치될 패널
	private JPanel colorPan51 = new JPanel();
	//centerPan5 동쪽 배경색을 입힐패널
	private JPanel colorPan52 = new JPanel();
	//centerPan5 서쪽 배경색을 입힐패널
	private JPanel colorPan53 = new JPanel();
	//centerPan5 남쪽 배경색을 입힐패널
	private JPanel midPan = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));//버스 조회 테이블

	private ImageIcon reverseImage;//출발터미널 도착터미널 반전시키는 버튼 이미지

	private Color mainColor;//기본 메인색상
	private Color outColor;//창틀 모양을 만들어줄 색상
	private Color inColor;//창틀 안쪽 색상

	private JLabel nameLabel;//상단에 이름이 들어가는 라벨
	private JLabel dayLabel;//날짜 라벨
	private JLabel timeLabel;//시간 라벨
	private JLabel startTerminalLabel;//출발터미널 라벨
	private JLabel endTerminalLabel;//도착터미널 라벨
	private JLabel mid1Label;//midPan 날짜
	private JLabel mid2Label;//midPan 시간
	private JLabel mid3Label;//midPan 출발터미널
	private JLabel mid4Label;//midPan ->
	private JLabel mid5Label;//midPan 도착터미널
	private JLabel mid6Label;//midPan 남은좌석

	private JButton backBtn;//뒤로가기 버튼
	private JButton logoutBtn;//로그아웃 버튼
	private JButton ticketingBtn;//예매하기 버튼
	private JButton reverseBtn;//출발터미널 도착터미널 반전시키는 버튼
	private JButton lookUpBtn;//조회하기 버튼

	private JComboBox dayCBox;//날짜 콤보박스
	private JComboBox timeCBox;//시간 콤보박스
	private JComboBox startTerminalCBox;//출발터미널 콤보박스
	private JComboBox endTerminalCBox;//도착터미널 콤보박스

	String[] name = {"날짜", "시간", "출발터미널", "도착터미널", "남은좌석", "가격"};
	String[] daycom = {"전체", "2월 22일", "2월 23일", "2월 24일", "2월 25일", "2월 26일", "2월 27일", "2월 28일"};
	String[] timecom = {"전체", "6 : 00", "6 : 30", "7 : 00", "7 : 30", "8 : 00", "8 : 30", "9 : 00", "9 : 30", "10 : 00",
				"10 : 30", "11 : 00", "11 : 30", "12 : 30", "13 : 00", "13 : 30", "14 : 00", "14 : 30", "15 : 00", "15 : 30",
				"16 : 00", "16 : 30", "17 : 00", "17 : 30", "18 : 00"};
	String[] stcom = {"전체", "서울", "인천", "대전", "부산", "대구"};
	String[] edcom = {"전체", "서울", "인천", "대전", "부산", "대구"};
	DefaultTableModel dt = new DefaultTableModel(name, 0) {
		public boolean isCellEditable(int rowIndex, int mColindex) {
			return false;
		}
	}; 
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);

	//SeatUI seat;
	SeatUI seat;
	TicketingMenuUI tm;
	BusListDAO dao = new BusListDAO();
	BusListVO bvo = new BusListVO();

	MyDataDAO mydao = new MyDataDAO();
	String id = mydao.getMyId();//정보 테이블에 있는 아이디를 id에 저장
	MemberVO mvo = mydao.getMyData(id);//정보 테이블과 회원테이블을 조인해서 얻은 데이터를 mvo에 저장
	TicketingMenuUI tui;


	public TicketingMenuUI(){

		jsp.setPreferredSize(new Dimension(755,470));
		mainColor = new Color(166, 221, 248);

		mainPan.setBackground(mainColor);
		colorPan1.setBackground(mainColor);
		colorPan2.setBackground(mainColor);
		colorPan3.setBackground(mainColor);
		colorPan4.setBackground(mainColor);

		backBtn = new JButton("뒤로가기");
		nameLabel = new JLabel(mvo.getB_name()+"님 예매하시겠습니까?");
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		logoutBtn = new JButton("로그아웃");
		colorPan4.add(backBtn);
		colorPan4.add(nameLabel);
		colorPan4.add(logoutBtn);

		ticketingBtn = new JButton("예매하기");
		ticketingBtn.setPreferredSize(new Dimension(150, 30));
		colorPan3.add(ticketingBtn);

		mainPan.add(colorPan1, BorderLayout.EAST);
		mainPan.add(colorPan2, BorderLayout.WEST);
		mainPan.add(colorPan3, BorderLayout.SOUTH);
		mainPan.add(colorPan4, BorderLayout.NORTH);
		mainPan.add(centerPan5, BorderLayout.CENTER);

		outColor = new Color(213, 229, 243);

		topPan.setBackground(outColor);
		dayLabel = new JLabel("날짜");
		dayCBox = new JComboBox(daycom);
		timeLabel = new JLabel("시간");
		timeCBox = new JComboBox(timecom);
		startTerminalLabel = new JLabel("출발터미널");
		startTerminalCBox = new JComboBox(stcom);

		reverseImage = new ImageIcon("./images/reverse.png");
		reverseBtn = new JButton(reverseImage);
		reverseBtn.setBorderPainted(false); //선택되지 않은 상태에서 외곽선 없앰
		reverseBtn.setContentAreaFilled(false); //내용영역채우기 없앰


		endTerminalLabel = new JLabel("도착터미널");
		endTerminalCBox = new JComboBox(edcom);
		lookUpBtn = new JButton("조회하기");

		topPan.add(dayLabel);
		topPan.add(dayCBox);
		topPan.add(timeLabel);
		topPan.add(timeCBox);
		topPan.add(startTerminalLabel);
		topPan.add(startTerminalCBox);
		topPan.add(reverseBtn);
		topPan.add(endTerminalLabel);
		topPan.add(endTerminalCBox);
		topPan.add(lookUpBtn);

		colorPan51.setBackground(outColor);
		colorPan52.setBackground(outColor);
		colorPan53.setBackground(outColor);

		centerPan5.add(colorPan51, BorderLayout.EAST);
		centerPan5.add(colorPan52, BorderLayout.WEST);
		centerPan5.add(colorPan53, BorderLayout.SOUTH);
		centerPan5.add(topPan, BorderLayout.NORTH);
		centerPan5.add(centerPan55, BorderLayout.CENTER);

		inColor = new Color(232, 238, 248);
		midPan.setBackground(inColor);

		mid1Label = new JLabel("날짜");
		mid1Label.setFont(new Font("맑은고딕", Font.BOLD, 15));
		mid2Label = new JLabel("시간");
		mid2Label.setFont(new Font("맑은고딕", Font.BOLD, 15));
		mid3Label = new JLabel("출발터미널");
		mid3Label.setFont(new Font("맑은고딕", Font.BOLD, 15));
		mid4Label = new JLabel("→");
		mid4Label.setFont(new Font("맑은고딕", Font.BOLD, 25));
		mid5Label = new JLabel("도착터미널");
		mid5Label.setFont(new Font("맑은고딕", Font.BOLD, 15));
		mid6Label = new JLabel("남은좌석");
		mid6Label.setFont(new Font("맑은고딕", Font.BOLD, 15));

		midPan.add(jsp);
		midPan.setVisible(true);

		dao.busListAll(dt);
		if(dt.getRowCount() > 0) {
			midPan.setVisible(true);
			jt.setRowSelectionInterval(0, 0);
		}

		centerPan55.add(midPan);

		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image img=toolkit.getImage("./images/titlebus.png");
		setIconImage(img);

		eventSet();

		add(mainPan);
		setTitle("자바 버스 예매 시스템");
		setSize(800,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void eventSet() {
		lookUpBtn.addActionListener(this);
		ticketingBtn.addActionListener(this);
		reverseBtn.addActionListener(this);
		backBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String label = e.getActionCommand();//버튼위 문자열 구함
		
		if(e.getSource() == logoutBtn) {
			dispose();
			new LoginUI();
		}
		if(e.getSource() == lookUpBtn) {
			String day = "bus_day";
			String time = "bus_time";
			String st = "bus_start";
			String ed = "bus_end";
			String v1 = null;
			String v2 = null;
			String v3 = null;
			String v4 = null;
			
			if(!(dayCBox.getSelectedItem().toString().equals("전체"))) {//날짜 콤보박스 선택이 되었다면
				if(!(timeCBox.getSelectedItem().toString().equals("전체"))) {//시간 콤보박스 선택이 되었다면
					if(!(startTerminalCBox.getSelectedItem().toString().equals("전체"))) {
						if(!(endTerminalCBox.getSelectedItem().toString().equals("전체"))) {

							v1 = dayCBox.getSelectedItem().toString();
							v2 = timeCBox.getSelectedItem().toString();
							v3 = startTerminalCBox.getSelectedItem().toString();
							v4 = endTerminalCBox.getSelectedItem().toString();
							dao.busListAll(v1, v2, v3, v4, dt);

							if(dt.getRowCount() > 0) {
								jt.setRowSelectionInterval(0, 0);
							}else {
								messageBox(this, "조회가능한 목록이 없습니다!");
							}

						}else {
							v1 = dayCBox.getSelectedItem().toString();
							v2 = timeCBox.getSelectedItem().toString();
							v3 = startTerminalCBox.getSelectedItem().toString();
							dao.busListAll(dt, day, v1, time, v2, st, v3);

							if(dt.getRowCount() > 0) {
								jt.setRowSelectionInterval(0, 0);
							}else {
								messageBox(this, "조회가능한 목록이 없습니다!");
							}
						}
					}else if(!(endTerminalCBox.getSelectedItem().toString().equals("전체"))){
						v1 = dayCBox.getSelectedItem().toString();
						v2 = timeCBox.getSelectedItem().toString();
						v3 = endTerminalCBox.getSelectedItem().toString();
						dao.busListAll(dt, day, v1, time, v2, st, v3);

						if(dt.getRowCount() > 0) {
							jt.setRowSelectionInterval(0, 0);
						}else {
							messageBox(this, "조회가능한 목록이 없습니다!");
						}
					}else {
						v1 = dayCBox.getSelectedItem().toString();
						v2 = timeCBox.getSelectedItem().toString();
						dao.busListAll(dt, day, v1, time, v2);

						if(dt.getRowCount() > 0) {
							jt.setRowSelectionInterval(0, 0);
						}else {
							messageBox(this, "조회가능한 목록이 없습니다!");
						}
					}
				}else if(!(startTerminalCBox.getSelectedItem().toString().equals("전체"))){
					if(!(endTerminalCBox.getSelectedItem().toString().equals("전체"))) {
						v1 = dayCBox.getSelectedItem().toString();
						v2 = startTerminalCBox.getSelectedItem().toString();
						v3 = endTerminalCBox.getSelectedItem().toString();
						dao.busListAll(dt, day, v1, st, v2, ed, v3);

						if(dt.getRowCount() > 0) {
							jt.setRowSelectionInterval(0, 0);
						}else {
							messageBox(this, "조회가능한 목록이 없습니다!");
						}
					}else {
						v1 = dayCBox.getSelectedItem().toString();
						v2 = startTerminalCBox.getSelectedItem().toString();
						dao.busListAll(dt, day, v1, st, v2);

						if(dt.getRowCount() > 0) {
							jt.setRowSelectionInterval(0, 0);
						}else {
							messageBox(this, "조회가능한 목록이 없습니다!");
						}
					}
				}else if(!(endTerminalCBox.getSelectedItem().toString().equals("전체"))) {                                        
					v1 = dayCBox.getSelectedItem().toString();
					v2 = endTerminalCBox.getSelectedItem().toString();
					dao.busListAll(dt, day, v1, ed, v2);

					if(dt.getRowCount() > 0) {
						jt.setRowSelectionInterval(0, 0);
					}else {
						messageBox(this, "조회가능한 목록이 없습니다!");
					}
				}else {//날짜만 선택시
					v1 = dayCBox.getSelectedItem().toString();
					dao.busListAll(dt, day, v1);

					if(dt.getRowCount() > 0) {
						jt.setRowSelectionInterval(0, 0);
					}else {
						messageBox(this, "조회가능한 목록이 없습니다!");
					}
				}
			}else if(!(timeCBox.getSelectedItem().toString().equals("전체"))){
				if(!(startTerminalCBox.getSelectedItem().toString().equals("전체"))) {
					if(!(endTerminalCBox.getSelectedItem().toString().equals("전체"))) {

						v1 = timeCBox.getSelectedItem().toString();
						v2 = startTerminalCBox.getSelectedItem().toString();
						v3 = endTerminalCBox.getSelectedItem().toString();
						dao.busListAll(dt, time, v1, st, v2, ed, v3);

						if(dt.getRowCount() > 0) {
							jt.setRowSelectionInterval(0, 0);
						}else {
							messageBox(this, "조회가능한 목록이 없습니다!");
						}
					}else {
						v1 = timeCBox.getSelectedItem().toString();
						v2 = startTerminalCBox.getSelectedItem().toString();
						dao.busListAll(dt, time, v1, st, v2);

						if(dt.getRowCount() > 0) {
							jt.setRowSelectionInterval(0, 0);
						}else {
							messageBox(this, "조회가능한 목록이 없습니다!");
						}
					}
				}else if(!(endTerminalCBox.getSelectedItem().toString().equals("전체"))) {
					v1 = timeCBox.getSelectedItem().toString();
					v2 = endTerminalCBox.getSelectedItem().toString();
					dao.busListAll(dt, time, v1, ed, v2);

					if(dt.getRowCount() > 0) {
						jt.setRowSelectionInterval(0, 0);
					}else {
						messageBox(this, "조회가능한 목록이 없습니다!");
					}
				}else {
					v1 = timeCBox.getSelectedItem().toString();
					dao.busListAll(dt, time, v1);

					if(dt.getRowCount() > 0) {
						jt.setRowSelectionInterval(0, 0);
					}else {
						messageBox(this, "조회가능한 목록이 없습니다!");
					}
				}
			}else if(!(startTerminalCBox.getSelectedItem().toString().equals("전체"))) {
				if(!(endTerminalCBox.getSelectedItem().toString().equals("전체"))) {
					v1 = startTerminalCBox.getSelectedItem().toString();
					v2 = endTerminalCBox.getSelectedItem().toString();
					dao.busListAll(dt, st, v1, ed, v2);

					if(dt.getRowCount() > 0) {
						jt.setRowSelectionInterval(0, 0);
					}else {
						messageBox(this, "조회가능한 목록이 없습니다!");
					}
				}else {
					v1 = startTerminalCBox.getSelectedItem().toString();
					dao.busListAll(dt, st, v1);

					if(dt.getRowCount() > 0) {
						jt.setRowSelectionInterval(0, 0);
					}else {
						messageBox(this, "조회가능한 목록이 없습니다!");
					}
				}
			}else if(!(endTerminalCBox.getSelectedItem().toString().equals("전체"))) {
				v1 = endTerminalCBox.getSelectedItem().toString();
				dao.busListAll(dt, ed, v1);

				if(dt.getRowCount() > 0) {
					jt.setRowSelectionInterval(0, 0);
				}else {
					messageBox(this, "조회가능한 목록이 없습니다!");
				}
			}else {

				dao.busListAll(dt);
				if(dt.getRowCount() > 0) {
					jt.setRowSelectionInterval(0, 0);
				}
			}
		}else if(e.getSource() == ticketingBtn) {
			
			dispose();
			new SeatUI(this);

		}else if(e.getSource() == reverseBtn) {
			String a = startTerminalCBox.getSelectedItem().toString();
			String b = endTerminalCBox.getSelectedItem().toString();
			startTerminalCBox.setSelectedItem(b);
			endTerminalCBox.setSelectedItem(a);
		}else if(e.getSource() == backBtn) {
			dispose();
			new MainUI();
		}
		
	}

	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component)obj, message);
	}


	public static void main(String[] args) {

		new TicketingMenuUI();
		
	}

}
