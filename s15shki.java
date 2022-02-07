import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class s15shki extends JFrame implements MouseListener {
  static s15shki win = new s15shki();
  
  static String[] num = new String[] { 
      " 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", "10", 
      "11", "12", "13", "14", "15", "  " };
  
  static Font font = new Font("Serif", 1, 24);
  
  public static void renum() {
    do {
      for (byte b = 0; b < 100; b++) {
        int i = Math.round((float)Math.random() * 15.0F);
        int j = Math.round((float)Math.random() * 15.0F);
        String str = num[i];
        num[i] = num[j];
        num[j] = str;
      } 
    } while (provrk());
  }
  
  public static byte s2i(String paramString) {
    byte b = (byte)(paramString.charAt(0) - 48);
    if (b < 0)
      b = 0; 
    b = (byte)(b << 4);
    b = (byte)(b + (byte)(paramString.charAt(1) - 48));
    if (b > 0)
      return b; 
    return 0;
  }
  
  public static boolean provrk() {
    byte b1 = 0;
    byte[] arrayOfByte = new byte[16];
    byte b2;
    for (b2 = 0; b2 < 16; ) {
      arrayOfByte[b2] = s2i(num[b2]);
      b2++;
    } 
    byte b = arrayOfByte[4];
    arrayOfByte[4] = arrayOfByte[7];
    arrayOfByte[7] = b;
    b = arrayOfByte[6];
    arrayOfByte[6] = arrayOfByte[5];
    arrayOfByte[5] = b;
    b = arrayOfByte[14];
    arrayOfByte[14] = arrayOfByte[13];
    arrayOfByte[13] = b;
    b = arrayOfByte[15];
    arrayOfByte[15] = arrayOfByte[12];
    arrayOfByte[12] = b;
    for (b2 = 0; b2 < 15; b2++) {
      if (arrayOfByte[b2] != 0)
        for (int i = b2 + 1; i < 16; i++) {
          if ((((arrayOfByte[i] != 0) ? 1 : 0) & ((arrayOfByte[b2] > arrayOfByte[i]) ? 1 : 0)) != 0)
            b1++; 
        }  
    } 
    if ((b1 & 0x1) == 0)
      return true; 
    return false;
  }
  
  public static void main(String[] paramArrayOfString) {
    renum();
    win.setTitle("Pyatnashki");
    win.setSize(282, 310);
    win.setDefaultCloseOperation(3);
    win.setResizable(false);
    win.addMouseListener(win);
    win.setVisible(true);
  }
  
  public void paint(Graphics paramGraphics) {
    paramGraphics.setFont(font);
    for (byte b = 0; b < 16; b++) {
      int i = ((b & 0x3) << 6) + 13;
      int j = (b >> 2 << 6) + 40;
      paramGraphics.setColor(new Color(240, 240, 240, 255));
      paramGraphics.fillRoundRect(i, j, 62, 62, 12, 12);
      paramGraphics.setColor(Color.BLACK);
      paramGraphics.drawRoundRect(i, j, 62, 62, 12, 12);
      paramGraphics.setColor(new Color(60, 60, 60, 200));
      paramGraphics.drawString(num[b], i + 18, j + 40);
     // 
    } 
  }
  
  public void mouseClicked(MouseEvent paramMouseEvent) {}
  
  public void mouseEntered(MouseEvent paramMouseEvent) {
    repaint();
  }
  
  public void mouseExited(MouseEvent paramMouseEvent) {}
  
  public void mousePressed(MouseEvent paramMouseEvent) {
    int i = paramMouseEvent.getX() - 13 >> 6;
    int j = paramMouseEvent.getY() - 40 >> 6;
    int k = i + (j << 2);
    if (i > 0 && num[k - 1] == "  ") {
      num[k - 1] = num[k];
      num[k] = "  ";
      win.repaint();
    } 
    if (i < 3 && num[k + 1] == "  ") {
      num[k + 1] = num[k];
      num[k] = "  ";
      win.repaint();
    } 
    if (j > 0 && num[k - 4] == "  ") {
      num[k - 4] = num[k];
      num[k] = "  ";
      win.repaint();
    } 
    if (j < 3 && num[k + 4] == "  ") {
      num[k + 4] = num[k];
      num[k] = "  ";
      win.repaint();
    } 
    if (num[15] == "  ") {
      k = 0;
      for (byte b = 1; b < 14; b++) {
        if (s2i(num[b - 1]) < s2i(num[b]))
          k++; 
      } 
      if (k >= 13) {
        JOptionPane.showMessageDialog(null, "YOU WIN!!!");
        renum();
      } 
    } 
  }
  
  public void mouseReleased(MouseEvent paramMouseEvent) {}
}
