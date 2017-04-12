package cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener{
	JPanel p_north, p_center;
	JButton bt_prev, bt_next;
	JLabel la_title;
	DateBox[] box=new DateBox[7*6];
	Calendar cal=Calendar.getInstance();
	
	//현재 날짜를 계산하기 위한 변수
	int yy;
	int mm;
	int dd;
	
	public MainFrame() {
		p_north=new JPanel();
		p_center=new JPanel();
		bt_prev=new JButton("◀");
		bt_next=new JButton("▶");
		la_title=new JLabel("2017년 4월");
		la_title.setFont(new Font("돋움", Font.BOLD, 28));
		
		
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		//public int get(int field)-> int 붙은 경우 상수 의심
		
		yy=cal.get(Calendar.YEAR);
		mm=cal.get(Calendar.MONTH);
		dd=cal.get(Calendar.DATE);
		
		System.out.println(yy+"-"+mm+"-"+dd);
		
		System.out.println(cal);
		printDate();
		
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		
		setSize((120*7)+100, (120*6)+130);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	//날짜 출력
	public void printDate(){
		//현재 날짜를 라벨에 출력
		la_title.setText(yy+"-"+(mm+1)); //사람 눈에 보일때는 mm+1
		
		//사각형 모두 날리기
		p_center.removeAll();
		
		//각 월이 무슨 요일부터 1일인지(시작하는지)
		//해당 월을 1일로 맞추고, 그 요일이 무슨 요일인지 물어보면 됨..
		cal.set(yy, mm, 1);
		int firstDay=cal.get(Calendar.DAY_OF_WEEK);
		//int LastDay=cal.get(Calendar.DAY_OF_MONTH);
		
		//다음 달의 1일보다 이전날짜 ->현재 달의 마지막 날짜를 알 수 있음
		//각 월의 마지막 날짜 알아맞추기->반복문을 언제까지 돌려야 하는지 알기 위해
		cal.set(yy, mm+1,0);
		int lastDay=cal.get(Calendar.DATE);
		
		
		System.out.println((mm+1)+"의 시작 요일은 "+firstDay);  //4의 시작 요일은 7: 일월화수목금토->토요일부터 시작
		//System.out.println((mm+1)+"의 마지막 요일은 "+LastDay);
		int num=0; //실제 찍힐 날짜용 변수
		
		for(int i=0; i<box.length; i++){
			box[i]=new DateBox(this);
			p_center.add(box[i]);

				if(i>=firstDay-1){
					num++;
				}else{
					num=0;
				}
				
				if(num!=0){		
					if(num<=lastDay){
					box[i].la.setText(Integer.toString(num));
					}
				}else{
					box[i].la.setText("");
				}
		}
		}
	
	
	
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
			//이전 달
		
		if(obj==bt_prev){
			mm--;
			
			if(mm<0){
				mm=11;
				yy--;
			}
			printDate();
		}else if(obj==bt_next){
			mm++;
			
			if(mm>11){
				mm=0;
				yy++;
			}
			printDate();
		}	
	}
	
	
	
	public static void main(String[] args) {
		new MainFrame();

	}


}
