import java.awt.*;
import javax.swing.*;
import java.lang.Math.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class test extends JFrame implements MouseListener {
static test win = new test();
static String [] num = {" 1"," 2"," 3"," 4"," 5"," 6"," 7"," 8"," 9","10","11","12","13","14","15","  "};
static Font font = new Font("Serif", Font.BOLD,24); // "Courier"

public static void renum(){
int n1;
int n2;
String S;
do{
for(int i=0;i<256;i++){
n1=Math.round((float)Math.random()*15);
n2=Math.round((float)Math.random()*15);
S=num[n1];
num[n1]=num[n2];
num[n2]=S;
}}
while(provrk());
}

public static byte s2i(String s) {
byte i;
i=(byte)(s.charAt(0)-48);
if(i<0)i=0;
i<<=4;
i+=(byte)(s.charAt(1)-48);
if(i>0)return i;
return 0;	
}

public static boolean provrk(){
int z=0;
byte m[]=new byte[16]; 
byte q;	
for(int i=0;i<16;i++)m[i]=s2i(num[i]);
q=m[4];m[4]=m[7];m[7]=q;
q=m[6];m[6]=m[5];m[5]=q;
q=m[14];m[14]=m[13];m[13]=q;
q=m[15];m[15]=m[12];m[12]=q;
   for(int x=0;x<15;x++)
   if(m[x]!=0)
   for(int y=x+1;y<16;y++)
   if((m[y]!=0)&(m[x]>m[y]))z++;   
if ((z&1)==0)return true;
return false;
}	

public static void main (String [] args){
renum();
win.setTitle("Pyatnashki");
win.setSize(64*4+9,64*4+33);
win.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
win.setResizable(false);
win.addMouseListener(win);
win.setVisible(true);
}

public void paint(Graphics g) {

int x,y;
super.paint(g);
g.setFont(font);
for(int i=0;i<16;i++){
x=((i & 3)<<6)+5;
y=((i >> 2)<<6)+28;
g.setColor(Color.BLACK);
g.drawRoundRect(x,y,62,62,12,12);
g.drawString(num[i],x+18,y+40);
g.setColor(new Color(128,128,200,100));
g.drawString(num[i],x+16,y+41);

}
}
 
public void mouseClicked(MouseEvent e) {
}

public void mouseEntered(MouseEvent e) {
repaint();
}

public void mouseExited(MouseEvent e) {
}

public void mousePressed(MouseEvent e) {
int X=((e.getX()-5)>>6);
int Y=((e.getY()-28)>>6);
int B=(X+(Y<<2));

if(X>0)if(num[B-1]=="  "){num[B-1]=num[B];num[B]="  ";win.repaint();}
if(X<3)if(num[B+1]=="  "){num[B+1]=num[B];num[B]="  ";win.repaint();}
if(Y>0)if(num[B-4]=="  "){num[B-4]=num[B];num[B]="  ";win.repaint();}
if(Y<3)if(num[B+4]=="  "){num[B+4]=num[B];num[B]="  ";win.repaint();}
if(num[15]=="  "){
	B=0;
	for(int i=1;i<14;i++)
		if(s2i(num[i-1])<s2i(num[i]))B++;
		if(B>=13){JOptionPane.showMessageDialog(null, "YOU WIN!!!");renum();}
	}
}

public void mouseReleased(MouseEvent e) {
//	JOptionPane.showMessageDialog(null, "message", "title",1 );

}

}
