package chatiigappliction;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
 public class ClientSide  implements ActionListener
 
 {
	 JTextField text;
	  static  JPanel a1;
	  static Box vertical = Box.createVerticalBox();
	  static JFrame f= new JFrame();
	  static DataOutputStream dout;
	 
	 ClientSide()
        {
	     JPanel p = new JPanel();
		  p.setBackground(new Color(7,94,84));
		  p.setBounds(0,0,500,60);
		  p.setLayout(null);
		  f.add(p);
		  
		  ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icone/back2.png"));
		  Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		  ImageIcon i3 = new ImageIcon(i2);
		  JLabel back = new JLabel(i3);
		  back.setBounds(5,20,25,25);
		  p.add(back);
		  
		  back.addMouseListener(new MouseAdapter () 
		    {
			  public void mouseClicked(MouseEvent e)
			  {
				  System.exit(0);
			  }
		    }
		  );
		  
		  ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icone/dp1.png"));
		  Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		  ImageIcon i6 = new ImageIcon(i5);
		  JLabel profile = new JLabel(i6);
		  profile.setBounds(50,6,50,50);
		  p.add(profile);
		  
		  ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icone/call.png"));
		  Image i8 = i7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		  ImageIcon i9 = new ImageIcon(i8);
		  JLabel call = new JLabel(i9);
		  call.setBounds(400,6,50,50);
		  p.add(call);
		  
		  ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icone/vcall.png"));
		  Image i11 = i10.getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT);
		  ImageIcon i12 = new ImageIcon(i11);
		  JLabel vcall = new JLabel(i12);
		  vcall.setBounds(350,6,50,50);
		  p.add(vcall);
		  
		  ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icone/dot.png"));
		  Image i14 = i13.getImage().getScaledInstance(35,35, Image.SCALE_DEFAULT);
		  ImageIcon i15 = new ImageIcon(i14);
		  JLabel dot = new JLabel(i15);
		  dot.setBounds(445,16,30,30);
		  p.add(dot);
		  
		  JLabel name = new JLabel ("Aamir Jawed");
		  name.setBounds(120,15,120,15);
		  name.setFont(new Font("SAN_SERIF",Font.BOLD,14));
		  name.setForeground(Color.WHITE);
		  p.add(name);
		  
		  JLabel name1 = new JLabel ("online");
		  name1.setBounds(120,37,100,9);
		  name1.setFont(new Font("SAN_SERIF",Font.BOLD,9));
		  name1.setForeground(Color.WHITE);
		  p.add(name1);
		  
		  text = new JTextField ();
		  text.setBounds(0,718,400,40);
		  text.setFont(new Font("SAN_SERIF",Font.BOLD,14));
		  f.add(text);
		  
		  JButton send = new JButton("Send");
		  send.setBounds(405,718,77,40);
		  send.setBackground(new Color(7,94,84));
		  send.setFont(new Font("SAN_SERIF",Font.BOLD,14));
		  send.setForeground(Color.WHITE);
		  send.addActionListener(this);    
		  f.add(send);
		  
		    /*JLabel background;
		    //setDefaultCloseOperation(EXIT_ON_CLOSE);
		    ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("icone/wall.jpg"));
		    background = new JLabel("",img,JLabel.CENTER);
		    background.setBounds(0,0,500,800);
		    f.add(background);*/
		    
		    f.setSize(500,800);
		    f.setLayout(null);
		    a1=new JPanel ();
		    a1.setBounds(0,80,490,639);
		    f.add(a1);
		    //f.getContentPane().setBackground(Color.RED);
		    //setUndecorated(true);
	       f. setLocation(1200,50);
    	   f. setVisible(true);
        }
	public void actionPerformed(ActionEvent ae)
	{
	  try 
		{
		
		String out = text.getText();
		
		
		JPanel p2 = formatLabel(out);
		
		a1.setLayout(new BorderLayout());
		
		JPanel right = new JPanel (new BorderLayout());
		right.add(p2,BorderLayout.LINE_END);
		vertical.add(right);
		vertical.add(Box.createVerticalStrut(15));
		
		a1.add(vertical,BorderLayout.PAGE_START);
		dout.writeUTF(out);
		
		text.setText("");
		
		f.repaint();
		f.invalidate();
		f.validate();
	}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	    public static JPanel  formatLabel(String out)
	    {
	    	JPanel panel = new JPanel();
	    	panel.setLayout(new BoxLayout (panel, BoxLayout.Y_AXIS));
	    	JLabel output = new JLabel ( out );
	    	output.setFont(new Font("Tahoma",Font.PLAIN,16));
	    	output.setBackground(new Color(37,211,102));
	    	output.setOpaque(true);
	    	output.setBorder(new EmptyBorder(15,15,15,50));
	    	
	    	panel.add(output);
	    	
	    	Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        
	        JLabel time = new JLabel();
	        time.setText(sdf.format(cal.getTime()));
	        
	        panel.add(time);
	        
	        return panel;
	    }
	    
	    public static void main(String[] args)
	    {
	        new ClientSide();
	        
	        try {
	            Socket k = new Socket("127.0.0.1", 6001);
	            DataInputStream din = new DataInputStream(k.getInputStream());
	            dout = new DataOutputStream(k.getOutputStream());
	            
	            while(true) 
	            {
	                a1.setLayout(new BorderLayout());
	                String msg = din.readUTF();
	                JPanel panel = formatLabel(msg);

	                JPanel left = new JPanel(new BorderLayout());
	                left.add(panel,BorderLayout.LINE_START);
	                vertical.add(left);
	                
	                vertical.add(Box.createVerticalStrut(15));
	                a1.add(vertical,BorderLayout.PAGE_START);
	                
	                f.validate();
	            }
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	        
	        
	    }
	}