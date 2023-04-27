package com.bus.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bus.dao.BusCheckDAO;
import com.bus.dao.BusMemDAO;
import com.bus.dao.MyDataDAO;
import com.bus.vo.BusCheckVO;
import com.bus.vo.MemberVO;

public class PaymentUI extends JFrame implements ActionListener{
	private JPanel mainPan;		//메인 패널
	private JPanel centerPan;	//중앙 패널

	private JLabel top1Label;	//휴대폰 결제를 위해
	private JLabel top2Label;	//정보를 입력해 주세요.
	private JLabel amountLabel;	//총 결제 금액 :
	private JLabel wonLabel;	//원
	private JLabel idLabel;		//아이디 :
	private JLabel pwdLabel;	//비밀번호 :
	private JLabel phoneLabel;	//핸드폰 번호 :
	private JLabel hyp1Label;	//- 라벨
	private JLabel hyp2Label;	//- 라벨

	private JTextField amountText;	//총 결제금액이 나올 텍스트 필드

	private JTextField idText;		//아이디 입력할 텍스트 필드
	private JPasswordField pwdText;		//비밀번호 입력할 텍스트 필드
	private JTextField topPNumText;	//폰 첫번호를 입력할 텍스트 필드
	private JTextField midPNumText;	//폰 중간번호를 입력할 텍스트 필드
	private JTextField endPNumText;	//폰 마지막번호를 입력할 텍스트 필드

	private JButton backBtn;	//결제취소 버튼
	private JButton payBtn;		//결제하기 버튼

	private Color mainColor;	//배경색

	SeatUI su;
	TicketingMenuUI tui;
	BusMemDAO bdao = new BusMemDAO();
	BusCheckDAO bcdao= new BusCheckDAO();
	BusCheckVO bcvo= new BusCheckVO();
	
	MyDataDAO mydao = new MyDataDAO();
	String id = mydao.getMyId();//정보 테이블에 있는 아이디를 id에 저장
	MemberVO mvo = mydao.getMyData(id);//정보 테이블과 회원테이블을 조인해서 얻은 데이터를 mvo에 저장
	PaymentUI(SeatUI su) {
		this.su=su;
		mainColor=new Color(166, 221, 248);

		//메인패널 설정
		mainPan=new JPanel();
		mainPan.setLayout(null);
		mainPan.setBackground(mainColor);
		top1Label=new JLabel("휴대폰 결제를 위해");
		top1Label.setFont(new Font("돋움체",Font.BOLD,15));
		top1Label.setBounds(125,20,170,20);
		top2Label=new JLabel("정보를 입력해 주세요.");
		top2Label.setFont(new Font("돋움체",Font.BOLD,15));
		top2Label.setBounds(120,40,180,20);

		backBtn=new JButton("결제취소");
		backBtn.setBounds(80,300,100,40);
		payBtn=new JButton("결제하기");
		payBtn.setBounds(220,300,100,40);

		mainPan.add(top1Label);
		mainPan.add(top2Label);
		mainPan.add(backBtn);
		mainPan.add(payBtn);

		//중앙패널 설정
		centerPan=new JPanel();
		centerPan.setLayout(null);
		centerPan.setBackground(Color.white);
		centerPan.setBounds(20,80,360,210);

		amountLabel =new JLabel("총 결제 금액 :");
		amountLabel.setBounds(50,40,100,20);
		wonLabel=new JLabel("원");
		wonLabel.setBounds(200,40,20,20);
		idLabel=new JLabel("아이디 :");
		idLabel.setBounds(50,80,60,20);
		pwdLabel=new JLabel("비밀번호 :");
		pwdLabel.setBounds(50,120,80,20);
		phoneLabel=new JLabel("핸드폰 번호 :");
		phoneLabel.setBounds(50,160,80,20);
		hyp1Label=new JLabel("-");
		hyp1Label.setFont(new Font("맑은고딕", Font.BOLD, 15));
		hyp1Label.setBounds(187,160,10,20);
		hyp2Label=new JLabel("-");
		hyp2Label.setFont(new Font("맑은고딕", Font.BOLD, 15));
		hyp2Label.setBounds(247,160,10,20);

		amountText=new JTextField(10);
		amountText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		amountText.setFont(new Font("맑은고딕",Font.BOLD,15));
		amountText.setBounds(150,39,50,20);
		amountText.setText(su.amountText.getText().toString());
		amountText.setBackground(Color.white);
		amountText.setEditable(false);
		
		idText=new JTextField(20);
		idText.setBounds(140,81,100,20);
		pwdText=new JPasswordField(20);
		pwdText.setBounds(140,121,100,20);
		topPNumText=new JTextField(10);
		topPNumText.setBounds(140,161,40,20);
		midPNumText=new JTextField(10);
		midPNumText.setBounds(200,161,40,20);
		endPNumText=new JTextField(10);
		endPNumText.setBounds(260,161,40,20);

		centerPan.add(amountLabel);
		centerPan.add(wonLabel);
		centerPan.add(idLabel);
		centerPan.add(pwdLabel);
		centerPan.add(phoneLabel);
		centerPan.add(hyp1Label);
		centerPan.add(hyp2Label);
		centerPan.add(amountText);
		centerPan.add(idText);
		centerPan.add(pwdText);
		centerPan.add(topPNumText);
		centerPan.add(midPNumText);
		centerPan.add(endPNumText);



		mainPan.add(centerPan);
		add(mainPan);

		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image img=toolkit.getImage("./images/titlebus.png");
		setIconImage(img);

		setTitle("자바 버스 예매 시스템 ");
		setSize(400,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		eventSet();

	}
	public void eventSet() {
		backBtn.addActionListener(this);
		payBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) { //뒤로가기 버튼 눌렀을때
			dispose();
			new TicketingMenuUI();
		}
		if(e.getSource() == payBtn) { //결제하기 버튼 눌렀을때
			
			if(idText.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요!");
				idText.requestFocus();
			}else if(pwdText.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요!");
				pwdText.requestFocus();
			}else if(topPNumText.getText().equals("") || midPNumText.getText().equals("") || endPNumText.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "휴대폰 번호를 입력하세요!");
				if(topPNumText.getText().equals("")) {
					topPNumText.requestFocus();
				}else if(midPNumText.getText().equals("")) {
					midPNumText.requestFocus();
				}else {
					endPNumText.requestFocus();
				}
			}
			else if(!(mvo.getB_phone().equals(topPNumText.getText()+midPNumText.getText()+endPNumText.getText())) 
						|| !(mvo.getB_pwd().equals(pwdText.getText())) || !(mvo.getB_id().equals(idText.getText()))){
					JOptionPane.showMessageDialog(this, "정보가 일치하지 않습니다!");
				}else {
					bcvo.setBd_id(idText.getText());
					bcvo.setBb_day(su.dayText.getText().trim());
					bcvo.setBb_time(su.timeText.getText().trim());
					bcvo.setBb_start(su.departmentText.getText().trim());
					bcvo.setBb_end(su.arrivalText.getText().trim());
					bcvo.setBb_seat(su.seatNumText.getText());
					bcvo.setBb_price(Integer.parseInt(amountText.getText().trim()));

					if(bcdao.checkInsert(bcvo)>0) {
						JOptionPane.showMessageDialog(this, "예매가 완료되었습니다!");
						dispose();
						
						int a = bcdao.seatCheck(bcvo);
						bcdao.seatUpdate(a, bcvo.getBb_seat(), bcvo);
						new MainUI();
					}
				}
		}
	}
} 

