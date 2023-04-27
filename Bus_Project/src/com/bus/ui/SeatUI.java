package com.bus.ui;

import java.awt.Color;
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
import javax.swing.JTextField;

import com.bus.dao.BusCheckDAO;
import com.bus.dao.BusListDAO;
import com.bus.vo.BusListVO;

public class SeatUI  extends JFrame implements ActionListener{
	private ImageIcon seatOnImage; 	//선택가능좌석 이미지
	private ImageIcon seatOffImage; //선택중인좌석 이미지
	private ImageIcon seatCantImage;//선택불가좌석 이미지
	private ImageIcon driverImage; 	//운전석 이미지
	private ImageIcon enterImage; 	//버스입구 이미지

	private JPanel mainPan;	//메인 패널
	private JPanel seatPan;	//좌석 패널
	private JPanel topPan;	//상단 패널
	private JPanel infoPan;	//버스정보 패널
	private JPanel payPan;	//결제 패널

	private JLabel topLabel; 		//좌석배치도 :
	private JLabel departmentLabel; //출발지 :
	private JLabel arrivalLabel; 	//도착지 :
	private JLabel dayLabel;		//출발 날짜 :
	private JLabel timeLabel;		//출발 시간 :
	private JLabel seatNumLabel;	//좌석 선택 수 :
	private JLabel seatLabel;		//개
	private JLabel ticketLabel;		//매수 선택 :
	private JLabel adultLabel;		//일반(만 13세 이상)
	private JLabel childLabel;		//어린이(만 6세 ~ 만 12세)
	private JLabel oldLabel;		//경로(만 65세 이상)
	private JLabel count1Label;		//매(일반)
	private JLabel count2Label;		//매(어린이)
	private JLabel count3Label;		//매(경로)
	private JLabel amountLabel;		//총 결제 금액 :
	private JLabel wonLabel;		//원

	private JLabel seatOnLabel;		//선택가능좌석 이미지를 담을 라벨
	private JLabel seatOffLabel;	//선택불가좌석 이미지를 담을 라벨
	private JLabel driverLabel;		//운전석 이미지를 담을 라벨
	private JLabel enterLabel;		//입구 이미지를 담을 라벨
	private JLabel seat01Label;		//선택 가능
	private JLabel seat02Label;		//선택 불가

	private Color mainColor;	//메인 배경색

	private JButton resetBtn;	//좌석 다시 선택 버튼
	private JButton cancelBtn;	//뒤로가기 버튼
	private JButton paymentBtn;	//결제하기 버튼
	private JButton[][] btn=new JButton[25][2];//좌석버튼 배열 (1~25)

	JTextField departmentText;	//출발지 출력 필드
	JTextField arrivalText;		//도착지 출력 필드
	JTextField dayText;			//출발 날짜 출력 필드
	JTextField timeText;		//출발 시간 출력 필드
	JTextField seatText;		//좌석 선택 수 출력 필드
	JTextField seatNumText;		//좌석 번호 출력 필드
	JTextField amountText;		//총 결제 금액 출력 필드

	JComboBox adultCBox;	//어른 티켓 콤보박스
	JComboBox childCBox;	//어린이 티켓 콤보박스
	JComboBox oldCBox;		//경로 티켓 콤보박스

	BusListVO vo=new BusListVO();
	BusListDAO dao=new BusListDAO();
	TicketingMenuUI tui;
	BusCheckDAO bcdao=new BusCheckDAO();

	int sCount = 0;//좌석수를 카운트할 변수
	int mCount = 0;//표 매수를 카운트할 변수

	public SeatUI(TicketingMenuUI tui) {
		this.tui=tui;
		//메인컬러설정 및 패널 레이아웃 null지정
		mainColor=new Color(166,221,248);
		mainPan=new JPanel();
		mainPan.setLayout(null); //setBounds를 쓰기위해 null 지정

		seatPan=new JPanel();
		seatPan.setLayout(null);

		topPan=new JPanel();
		topPan.setLayout(null);

		infoPan=new JPanel();
		infoPan.setLayout(null);

		payPan=new JPanel();
		payPan.setLayout(null);

		//이미지 저장
		seatOnImage=new ImageIcon("./images/seat_1.png");
		seatOffImage=new ImageIcon("./images/seat_2.png");
		driverImage=new ImageIcon("./images/driver.png");
		enterImage=new ImageIcon("./images/entrance.png");
		seatCantImage=new ImageIcon("./images/seat_3.png");
		//seatPanel 정리
		seatPan.setBackground(Color.white);
		seatPan.setBounds(20,20,440,720);

		topLabel=new JLabel("좌석 배치도");
		topLabel.setBounds(170,10,150,30);
		topLabel.setFont(new Font("맑은고딕",Font.BOLD,18));

		driverLabel=new JLabel(driverImage);
		driverLabel.setBounds(80,20,70,70);

		enterLabel=new JLabel(enterImage);
		enterLabel.setBounds(360,20,70,70);

		for(int i=0; i<btn.length; i++){//좌석 배치
			//x좌표 설정
			int x = 50;
			if(i == 22){x = 190;}
			else if(i == 1 || i % 4 == 1) {x = 120;}
			else if(i == 2 || i == 23 || i % 4 == 2) {x = 260;}
			else if(i == 3 || i == 24 || i % 4 == 3) {x = 330;}
			//y좌표 설정
			int y = 600;
			if(i < 4){y = 100;}
			else if(i < 8){y = 200;}
			else if(i < 12){y = 300;}
			else if(i < 16){y = 400;}
			else if(i < 20){y = 500;}

			for(int a = 0; a <= 1; a++){
				if(a == 0){
					btn[i][a] = new JButton(seatOnImage);//btn[i][0]에 이미지 넣기
				}else{
					btn[i][a] = new JButton(seatOffImage);//btn[i][1]에 이미지 넣기
					btn[i][a].setVisible(false);//btn[i][1]안보이게 설정
				}
				btn[i][a].setBounds(x, y, 60, 90);
				seatPan.add(btn[i][a]);
			}//2중 for end
		}//for end

		//topPanel 정리
		seatOnLabel=new JLabel(seatOnImage);
		seatOnLabel.setBounds(20,20,60,90);
		

		seatOffLabel=new JLabel(seatCantImage);
		seatOffLabel.setBounds(90,20,60,90);

		seat01Label=new JLabel("선택 가능");
		seat01Label.setFont(new Font("맑은고딕",Font.BOLD, 12));
		seat01Label.setBounds(23,110,60,20);

		seat02Label=new JLabel("선택 불가");
		seat02Label.setFont(new Font("맑은고딕",Font.BOLD, 12));
		seat02Label.setBounds(92,110,60,20);

		resetBtn=new JButton("좌석 다시 선택");
		resetBtn.setBounds(170,20,120,40);
		resetBtn.setFont(new Font("맑은고딕",Font.BOLD,12));

		//infoPanel 정리
		departmentLabel=new JLabel("출발지 :");
		departmentLabel.setBounds(10,15,100,30);

		arrivalLabel=new JLabel("도착지 :");
		arrivalLabel.setBounds(10, 45, 100, 30);

		dayLabel=new JLabel("출발 날짜 :");
		dayLabel.setBounds(10, 75, 100, 30);

		timeLabel=new JLabel("출발 시간 :");
		timeLabel.setBounds(10,105,100,30);

		seatNumLabel=new JLabel("좌석 선택 수 :");
		seatNumLabel.setBounds(10,10,100,30);


		int row=tui.jt.getSelectedRow();//선택된 행의 번호 구함
		departmentText=new JTextField(10);
		departmentText.setText(tui.jt.getValueAt(row, 2).toString());
		departmentText.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //텍스트필드 테두리 제거
		departmentText.setBounds(120,20,100,20);
		departmentText.setBackground(Color.white);
		departmentText.setEditable(false);//텍스트 수정 불가

		arrivalText=new JTextField(10);
		arrivalText.setText(tui.jt.getValueAt(row, 3).toString());
		arrivalText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		arrivalText.setBounds(120,50,100,20);
		arrivalText.setBackground(Color.white);
		arrivalText.setEditable(false);

		dayText=new JTextField(10);
		dayText.setText(tui.jt.getValueAt(row, 0).toString());
		dayText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		dayText.setBounds(120, 80, 100, 20);
		dayText.setBackground(Color.white);
		dayText.setEditable(false);

		timeText=new JTextField(10);
		timeText.setText(tui.jt.getValueAt(row, 1).toString());
		timeText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		timeText.setBounds(120,110,100,20);
		timeText.setBackground(Color.white);
		timeText.setEditable(false);

		//payPanel 정리
		seatLabel=new JLabel("개");
		seatLabel.setBounds(160, 15, 40, 20);
		ticketLabel=new JLabel("매수 선택 :");
		ticketLabel.setBounds(10,60,100,30);

		adultLabel=new JLabel("일반(만 13세 이상)");
		adultLabel.setBounds(90,60,150,30);

		childLabel=new JLabel("어린이 (만 12세 이하)");
		childLabel.setBounds(90,120,180,30);

		oldLabel=new JLabel("경로(만 65세 이상)");
		oldLabel.setBounds(90,180,150,30);

		count1Label=new JLabel("매");
		count1Label.setBounds(160,90,40,30);

		count2Label=new JLabel("매");
		count2Label.setBounds(160,150,40,30);

		count3Label=new JLabel("매");
		count3Label.setBounds(160,210,40,30);

		amountLabel=new JLabel("총 결제 금액 :");
		amountLabel.setBounds(10,250,180,30);

		wonLabel=new JLabel("원");
		wonLabel.setBounds(160,250,40,30);

		String[] tickets= {"0","1","2","3"};;	//티켓 콤보박스 선택 배열
		adultCBox =new JComboBox(tickets);
		childCBox =new JComboBox(tickets);
		oldCBox =new JComboBox(tickets);
		adultCBox.setBounds(90,90,60,30);
		childCBox.setBounds(90,150,60,30);
		oldCBox.setBounds(90,210,60,30);

		seatText=new JTextField(10);
		seatText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		seatText.setBounds(120,15,40,20);
		seatText.setBackground(Color.white);
		seatText.setEditable(false);

		seatNumText=new JTextField(10);
		seatNumText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		seatNumText.setBounds(40,40,300,20);
		seatNumText.setBackground(Color.white);
		seatNumText.setEditable(false);


		amountText=new JTextField(10);
		amountText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		amountText.setBounds(110,255,50,20);
		amountText.setText("0");
		amountText.setBackground(Color.white);
		amountText.setEditable(false);

		cancelBtn=new JButton("뒤로가기");
		cancelBtn.setBounds(20,310,120,50);
		//	cancelBtn.setFont(new Font("맑은고딕",Font.BOLD,15));

		paymentBtn=new JButton("결제하기");
		paymentBtn.setBounds(170,310,120,50);
		//	paymentBtn.setFont(new Font("맑은고딕",Font.BOLD,15));

		//패널에 색상,위치 설정
		topPan.setBackground(Color.white);
		topPan.setBounds(480,20,310,150);
		infoPan.setBackground(Color.white);
		infoPan.setBounds(480,190,310,150);
		payPan.setBackground(Color.white);
		payPan.setBounds(480,360,310,380);

		//각 패널에 추가
		seatPan.add(topLabel);
		seatPan.add(driverLabel);
		seatPan.add(enterLabel);

		topPan.add(seat01Label);
		topPan.add(seat02Label);		
		topPan.add(seatOnLabel);
		topPan.add(seatOffLabel);
		topPan.add(resetBtn);

		infoPan.add(departmentLabel);
		infoPan.add(arrivalLabel);
		infoPan.add(dayLabel);
		infoPan.add(timeLabel);
		infoPan.add(departmentText);
		infoPan.add(arrivalText);
		infoPan.add(dayText);
		infoPan.add(timeText);


		payPan.add(seatNumLabel);
		payPan.add(seatText);
		payPan.add(seatLabel);
		payPan.add(ticketLabel);
		payPan.add(adultLabel);
		payPan.add(childLabel);
		payPan.add(oldLabel);
		payPan.add(seatNumText);
		payPan.add(count1Label);
		payPan.add(count2Label);
		payPan.add(count3Label);
		payPan.add(adultCBox);
		payPan.add(childCBox);
		payPan.add(oldCBox);
		payPan.add(amountLabel);
		payPan.add(amountText);
		payPan.add(wonLabel);
		payPan.add(cancelBtn);
		payPan.add(paymentBtn);

		//매인패널에 패널들 추가
		mainPan.add(seatPan);
		mainPan.add(topPan);
		mainPan.add(infoPan);
		mainPan.add(payPan);

		mainPan.setBackground(mainColor);
		add(mainPan);

		vo.setBus_day(dayText.getText());
		vo.setBus_time(timeText.getText());
		vo.setBus_start(departmentText.getText());
		vo.setBus_end(arrivalText.getText());

		boolean[] S = bcdao.seatCheck(vo);

		for(int a = 0; a < S.length; a++) {
			if(S[a] == true) {btn[a][0].setEnabled(false);};
		}

		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image img=toolkit.getImage("./images/titlebus.png");
		setIconImage(img);

		eventSet();

		setTitle("자바 버스 예매 시스템");
		setSize(820,800);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {

	}
	@Override
	public void actionPerformed(ActionEvent e) {

		int row=tui.jt.getSelectedRow();
		int money = Integer.parseInt((String)tui.jt.getValueAt(row, 5));//기준이 되는 가격
		if(e.getSource()==resetBtn) {
			dispose();
			new SeatUI(tui);
		}
		if(e.getSource()==cancelBtn) {
			dispose();
			new TicketingMenuUI();
		}
		if(e.getSource()==paymentBtn) {
			if(mCount == sCount && mCount != 0) {
				dispose();
				new PaymentUI(this);
			}else if(mCount == 0 && sCount == 0){
				JOptionPane.showMessageDialog(this, "좌석을 선택한후에 결제를 시도해 주세요.");
			}else {
				JOptionPane.showMessageDialog(this, "선택하신 좌석과 표 수량이 일치 하지 않습니다!");
			}
		}

		for(int i=0;i<btn.length;i++) {	//좌석 버튼 클릭 시 좌석 번호 출력
			if(e.getSource()==btn[i][0]) {
				//sCount += 1;//버튼 하나 클릭할때마다 1씩 늘어남
				
				if(sCount < 9) {

					btn[i][0].setVisible(false);//seatOnImage좌석 안보이게
					btn[i][1].setVisible(true);//seatOffImage좌석 보이게
					String seat = seatNumText.getText().toString();//좌석 텍스트 필드에 있는 내용을 가져옴
					String selseat = seat+"F0"+(i-19)+" ";//마지막열 좌석 이름 설정

					//좌석이름 설정
					if(i<4) {selseat = seat+"A0"+(i+1)+" ";}
					else if(i<8) {selseat = seat+"B0"+(i-3)+" ";}
					else if(i<12) {selseat = seat+"C0"+(i-7)+" ";}
					else if(i<16) {selseat = seat+"D0"+(i-11)+" ";}
					else if(i<20){selseat = seat+"E0"+(i-15)+" ";}

					seatNumText.setText(selseat);
					sCount += 1;//버튼 하나 클릭할때마다 1씩 늘어남
				}else {//만약 좌석버튼을 9개를 초과해서 눌렀다면
					JOptionPane.showMessageDialog(this, "선택가능한 최대 자리는 9개입니다!");
					continue;
				}
				seatText.setText(sCount+"");
			}
		}
		if(e.getSource()==adultCBox) {

			int a = Integer.parseInt((String) adultCBox.getSelectedItem());//일반 인원 수
			if(sCount == 0 && a != 0) {
				JOptionPane.showMessageDialog(this, "좌석을 먼저 선택해 주세요!");
				adultCBox.setSelectedItem("0");
			}else {
				int result = 0;
				int m = Integer.parseInt(amountText.getText().toString());//현재 설정 되어 있는 가격

				mCount += a;
				result = money * a;//선택 표 수량에 따라 일반 요금 구하기

				if(mCount > sCount){
					JOptionPane.showMessageDialog(this, "선택하신 좌석 개수보다 표 수량이 더많습니다!");
					adultCBox.setSelectedItem("0");
					mCount -= a;
				}else {
					m += result;

					amountText.setText(m+"");
					if(a != 0) {
						adultCBox.setEnabled(false);
					}
				}
			}


		}
		if(e.getSource()==childCBox) {
			int c = Integer.parseInt((String)childCBox.getSelectedItem());//어린이 인원 수
			if(sCount == 0 && c != 0) {
				JOptionPane.showMessageDialog(this, "좌석을 먼저 선택해 주세요!");
				childCBox.setSelectedItem("0");
			}else {
				int result = 0;
				int m = Integer.parseInt(amountText.getText().toString());//현재 설정 되어 있는 가격
				int childMoney = money / 2;//50%가 할인된 어린이 요금 설정

				mCount += c;
				result = childMoney * c;//선택 표 수량에 따라 어린이 요금 구하기

				if(mCount > sCount){
					JOptionPane.showMessageDialog(this, "선택하신 좌석 개수보다 표 수량이 더많습니다!");
					childCBox.setSelectedItem("0");
					mCount -= c;
				}else {
					m += result;

					amountText.setText(m+"");
					if(c != 0) {
						childCBox.setEnabled(false);
					}
				}
			}
		}
		if(e.getSource()==oldCBox) {
			int o = Integer.parseInt((String)oldCBox.getSelectedItem());//경로 인원 수
			if(sCount == 0 && o != 0) {
				JOptionPane.showMessageDialog(this, "좌석을 먼저 선택해 주세요!");
				oldCBox.setSelectedItem("0");
			}else {
				int result = 0;
				int m = Integer.parseInt(amountText.getText().toString());//현재 설정 되어 있는 가격
				int oldMoney = money - ((money / 100) * 25); //25%가 할인된 경로 요금 설정

				mCount += o;
				result = oldMoney * o;//선택 표 수량에 따라 경로 요금 구하기

				if(mCount > sCount){
					JOptionPane.showMessageDialog(this, "선택하신 좌석 개수보다 표 수량이 더많습니다!");
					oldCBox.setSelectedItem("0");
					mCount -= o;
				}else {
					m += result;

					amountText.setText(m+"");
					if(o != 0) {
						oldCBox.setEnabled(false);
					}
				}
			}
		}
	}
	public void eventSet() {
		resetBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		paymentBtn.addActionListener(this);
		adultCBox.addActionListener(this);
		childCBox.addActionListener(this);
		oldCBox.addActionListener(this);
		for(int i=0;i<=24;i++) {
			btn[i][0].addActionListener(this);
		}
	}
}
