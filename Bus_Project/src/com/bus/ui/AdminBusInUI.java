package com.bus.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bus.dao.AdminDAO;
import com.bus.vo.BusListVO;

public class AdminBusInUI extends JFrame implements ActionListener {
	
	private JPanel topPan;	//위쪽 여백을 채울 패널
	private JPanel eastPan;	//오른쪽 여백을 채울 패널
	private JPanel wordPan;	//라벨 패널
	private JPanel textPan;	//입력필드 패널
	private JPanel btnPan;	//버튼 패널
	
	private JLabel dayLabel;			//날짜 
	private JLabel timeLabel;			//시간
	private JLabel startTerminalLabel;	//출발터미널
	private JLabel endTerminalLabel;	//도착터미널
	private JLabel seatLabel;			//남은좌석
	private JLabel priceLabel;			//가격
	
	private JTextField dayText;				//날짜 입력필드
	private JTextField timeText;			//시간 입력필드
	private JTextField startTerminalText;	//출발 터미널 입력필드
	private JTextField endTerminalText;		//도착 터미널 입력필드
	private JTextField seatText;			//남은 좌석 입력필드
	private JTextField priceText;			//가격 입력필드
	
	private Color mainColor;
	private JButton insertBtn;	//등록 버튼
	private JButton cancelBtn;	//취소 버튼
	
	AdminDAO dao=new AdminDAO();
	BusListVO vo=new BusListVO();
	AdminBusUI a=new AdminBusUI();
	
	public AdminBusInUI(){
		mainColor = new Color(166, 221, 248);
		topPan=new JPanel();
		eastPan=new JPanel();
		wordPan=new JPanel(new GridLayout(6,1,20,20));
		textPan=new JPanel(new GridLayout(6,1,10,10));
		btnPan=new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
		
		topPan.setBackground(mainColor);
		eastPan.setBackground(mainColor);
		wordPan.setBackground(mainColor);
		textPan.setBackground(mainColor);
		btnPan.setBackground(mainColor);
		
		dayLabel=new JLabel("  날짜");
		timeLabel=new JLabel("  시간");
		startTerminalLabel=new JLabel("  출발 터미널");
		endTerminalLabel=new JLabel("  도착 터미널");
		seatLabel=new JLabel("  남은 좌석");
		priceLabel=new JLabel("  가격");
		
		wordPan.add(dayLabel);
		wordPan.add(timeLabel);
		wordPan.add(startTerminalLabel);
		wordPan.add(endTerminalLabel);
		wordPan.add(seatLabel);
		wordPan.add(priceLabel);
		
		dayText=new JTextField();
		timeText=new JTextField();
		startTerminalText=new JTextField();
		endTerminalText=new JTextField();
		seatText=new JTextField();
		priceText=new JTextField();
		
		textPan.add(dayText);
		textPan.add(timeText);
		textPan.add(startTerminalText);
		textPan.add(endTerminalText);
		textPan.add(seatText);
		textPan.add(priceText);
		
		insertBtn=new JButton("등록");
		insertBtn.setPreferredSize(new Dimension(100,40));
		cancelBtn=new JButton("취소");
		cancelBtn.setPreferredSize(new Dimension(100,40));
		
		btnPan.add(insertBtn);
		btnPan.add(cancelBtn);
		
		add(topPan,"North");
		add(eastPan,"East");
		add(wordPan,"West");
		add(textPan,"Center");
		add(btnPan,"South");
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image img=toolkit.getImage("./images/admin.png");
		setIconImage(img);
		
		eventSet();
		
		setTitle("관리자 버스 등록");
		setSize(400,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
	}
	public void eventSet() {
		insertBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelBtn) {//취소 버튼 클릭 시
			dispose();//창 하나만 사라짐
		}else if(e.getSource() == insertBtn) {//등록 버튼 클릭 시
			if(dayText.getText().equals("")) {
				AdminDAO.messageBox(this, "날짜를 입력해 주세요.");
				dayText.requestFocus();
				return;
			}
			if(timeText.getText().equals("")) {
				AdminDAO.messageBox(this, "시간을 입력해 주세요.");
				return;
			}
			if(startTerminalText.getText().equals("")) {
				AdminDAO.messageBox(this, "출발 터미널을 입력해 주세요.");
				return;
			}
			if(endTerminalText.getText().equals("")) {
				AdminDAO.messageBox(this, "도착 터미널을 입력해 주세요.");
				return;
			}
			if(seatText.getText().equals("")) {
				AdminDAO.messageBox(this, "남은 좌석을 입력해 주세요.");
				return;
			}
			if(priceText.getText().equals("")) {
				AdminDAO.messageBox(this, "가격을 입력해 주세요.");
				return;
			}else {//모두 입력했다면
				vo.setBus_day(dayText.getText().trim());
				vo.setBus_time(timeText.getText().trim());
				vo.setBus_start(startTerminalText.getText().trim());
				vo.setBus_end(endTerminalText.getText().trim());
				vo.setBus_seat(Integer.parseInt(seatText.getText().trim()));
				vo.setBus_price(Integer.parseInt(priceText.getText().trim()));
				
				if(dao.busListInsert(vo)>0) {
					AdminDAO.messageBox(this, "버스 등록 완료!");
					reset();
					dao.busListAll(a.dt);
				}
			}
		}
	}
	public void reset() {
		dayText.setText("");
		timeText.setText("");
		startTerminalText.setText("");
		endTerminalText.setText("");
		seatText.setText("");
		priceText.setText("");
	}
}
