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

public class AdminBusUpUI extends JFrame implements ActionListener {
	
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
	private JLabel noLabel;				//버스번호
	
	private JTextField dayText;				//날짜 입력필드
	private JTextField timeText;			//시간 입력필드
	private JTextField startTerminalText;	//출발 터미널 입력필드
	private JTextField endTerminalText;		//도착 터미널 입력필드
	private JTextField seatText;			//남은 좌석 입력필드
	private JTextField priceText;			//가격 입력필드
	private JTextField noText;				//버스번호 입력필드
	
	private Color mainColor;
	private JButton updateBtn;	//수정 버튼
	private JButton cancelBtn;	//취소 버튼
	
	AdminDAO dao=new AdminDAO();
	BusListVO vo=new BusListVO();
	AdminBusUI au;
	
	public AdminBusUpUI(AdminBusUI au){
		this.au=au;
		
		mainColor = new Color(166, 221, 248);
		topPan=new JPanel();
		eastPan=new JPanel();
		wordPan=new JPanel(new GridLayout(7,1,20,20));
		textPan=new JPanel(new GridLayout(7,1,10,10));
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
		noLabel=new JLabel("  버스 번호");
		
		wordPan.add(dayLabel);
		wordPan.add(timeLabel);
		wordPan.add(startTerminalLabel);
		wordPan.add(endTerminalLabel);
		wordPan.add(seatLabel);
		wordPan.add(priceLabel);
		wordPan.add(noLabel);
		

		dayText=new JTextField();
		timeText=new JTextField();
		startTerminalText=new JTextField();
		endTerminalText=new JTextField();
		seatText=new JTextField();
		priceText=new JTextField();
		noText=new JTextField();
		
		int row=au.jt.getSelectedRow();//선택된 행의 번호 구함
		//입력박스에 선택된 행 첫번째 열값을 문자열로 표시
		dayText.setText(au.jt.getValueAt(row, 0).toString());
		timeText.setText(au.jt.getValueAt(row, 1).toString());
		startTerminalText.setText(au.jt.getValueAt(row, 2).toString());
		endTerminalText.setText(au.jt.getValueAt(row, 3).toString());
		seatText.setText(au.jt.getValueAt(row, 4).toString());
		priceText.setText(au.jt.getValueAt(row, 5).toString());
		noText.setText(au.jt.getValueAt(row, 6).toString());
		
		noText.setEnabled(false); //버스 번호 텍스트 비활성화
		
		textPan.add(dayText);
		textPan.add(timeText);
		textPan.add(startTerminalText);
		textPan.add(endTerminalText);
		textPan.add(seatText);
		textPan.add(priceText);
		textPan.add(noText);
		
		updateBtn=new JButton("수정");
		updateBtn.setPreferredSize(new Dimension(100,40));
		cancelBtn=new JButton("취소");
		cancelBtn.setPreferredSize(new Dimension(100,40));
		
		btnPan.add(updateBtn);
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
		
		
		setTitle("관리자 버스 수정");
		setSize(400,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

	}
	public void eventSet() {
		updateBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelBtn) {
			dispose();//창 하나만 사라짐
		}else if(e.getSource() == updateBtn) {

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
				vo.setBus_no(Integer.parseInt(noText.getText().trim()));
				
				if(dao.busListUpdate(vo)>0) {
					AdminDAO.messageBox(this,"버스 수정 완료!");

					dao.busListAll(au.dt);
					dispose();
				}
			}
		}
	}
}
