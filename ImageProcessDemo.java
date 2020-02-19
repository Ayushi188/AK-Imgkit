import java.awt.*;
import java.applet.*;
import java.awt.event.*;
//import java.aJFramelet.*;
import java.awt.image.* ;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

class MenuFrame extends JFrame
{
	String msg="Welcome", imgfilename="", imgdir="";
	Image img;

	CheckboxMenuItem debug,test;
	LoadedImage lim;
	ImageIcon   imageicon;
	
	PlugInFilter pif;
	Image fimg;
	Label lab;
	boolean newimg=true;
	
	String filtername="";
	JLabel imagelabel;
	JScrollPane jsp;
    
	MenuFrame(String title)
   {

		super(title);
		setSize(500,500);
		setLayout(new FlowLayout());

		MenuBar mbar=new MenuBar();
		setMenuBar(mbar);

		Menu file=new Menu("File");
		MenuItem item1,item2,item3,item4,item5;

		file.add(item1=new MenuItem("Open..."));

		file.add(item2=new MenuItem("Save"));

		file.add(item3=new MenuItem("Close"));

		file.add(item4=new MenuItem("-"));

		file.add(item5=new MenuItem("Quit..."));

		mbar.add(file);

		Menu filter=new Menu("Filter");

		MenuItem item6,item7,item8,item9,item10,item11,item12;
		filter.add(item6=new MenuItem("Grayscale"));
		filter.add(item7=new MenuItem("Invert"));
		filter.add(item8=new MenuItem("Contrast"));
		filter.add(item9=new MenuItem("Blur"));
		filter.add(item10=new MenuItem("Sharpen"));
		filter.add(item12=new MenuItem("-"));
        filter.add(item11=new MenuItem("Reset"));
		
		mbar.add(filter);

		MyMenuHandler handler = new MyMenuHandler(this);
		item1.addActionListener(handler);
		item2.addActionListener(handler);
		item3.addActionListener(handler);
		item4.addActionListener(handler);
		item5.addActionListener(handler);
		item6.addActionListener(handler);
		item7.addActionListener(handler);
		item8.addActionListener(handler);
		item9.addActionListener(handler);
		item10.addActionListener(handler);
		item12.addActionListener(handler);
		item11.addActionListener(handler);
	
		

		MyWindowAdapter adapter=new MyWindowAdapter(this);
		addWindowListener(adapter);

		imagelabel = new JLabel();
			
		Toolkit t=Toolkit.getDefaultToolkit();
        
   
        int xSize = ((int) t.getScreenSize().getWidth());  
        int ySize = ((int) t.getScreenSize().getHeight());  
       
		
		int w = (int) (Math.round(xSize * 0.80));
		int h = (int) (Math.round(ySize * 0.80));
		 setSize(w,h);
		 jsp=new JScrollPane();
		//add(jsp, BorderLayout.NORTH);
        jsp.setPreferredSize(new Dimension(600,600));			
        //jsp.add(imagelabel);
		
		add(imagelabel);
		
		
		
	    
		
}

   public void paint(Graphics g)
   {
		//g.drawString(msg, 10, 200);
		String s="";
		s=imgdir+imgfilename;
		System.out.println(s);
		
    	if(s.length()>0)
		{
			Toolkit t=Toolkit.getDefaultToolkit();
			System.out.println(newimg);
			if(newimg)	img=t.getImage(s);
				imageicon = new ImageIcon(img);
				imagelabel.setIcon(imageicon);
				lim=new LoadedImage(img);
			
      
		}

   }
}

class MyWindowAdapter extends WindowAdapter
{

     MenuFrame menuFrame;

	public MyWindowAdapter(MenuFrame menuFrame)
	{
	this.menuFrame = menuFrame;
	}
	public void windowClosing(WindowEvent we)
	{
	menuFrame.setVisible(false);
	}
}

class MyMenuHandler implements ActionListener,ItemListener
{
    MenuFrame menuFrame;

	public MyMenuHandler(MenuFrame menuFrame)
	{
	this.menuFrame = menuFrame;
	}

    public void actionPerformed(ActionEvent ae)
    {
        String msg="You selected";
        
		String arg=ae.getActionCommand();
		
		if(arg.equals("Open..."))
		{
			FileDialog fd = new FileDialog(menuFrame, "File Dialog");
			fd.setVisible(true);
			menuFrame.imgfilename=fd.getFile();
			menuFrame.imgdir=fd.getDirectory();
			menuFrame.newimg=true;
		}

		else if(arg.equals("close"))
		msg+="Close";
		else if(arg.equals("Quit..."))
		{
		msg += "Quit.";
		System.exit(0);
	    }
		else if(arg.equals("Save"))
		{
		msg += "Save";
		
		}
		else if(arg.equals("Filter"))
		msg += "Edit.";
	    else if(arg.equals("Reset"))
		{
			 menuFrame.newimg=true;
			 menuFrame.repaint();
			
		}

       //String a=ae.getActionCommand();
      try
	  {	  
		if(arg.equals("Blur"))
		{
			  menuFrame.filtername="Blur";
				menuFrame.newimg=false;
				menuFrame.pif = (PlugInFilter)Class.forName("Blur").newInstance(); 
			  menuFrame.fimg = menuFrame.pif.filter(menuFrame, menuFrame.img); 
			  menuFrame.img = menuFrame.pif.filter(menuFrame, menuFrame.img); 
			  
			  menuFrame.lim.set(menuFrame.fimg); 
			  //menuFrame.lab.setText("Filtered: " + arg); 
			   menuFrame.repaint();
		}
		else if(arg.equals("Grayscale"))
		{
			    
			    menuFrame.filtername="Grayscale";
				menuFrame.newimg=false;
				menuFrame.pif = (PlugInFilter)Class.forName("Grayscale").newInstance(); 
			    menuFrame.fimg = menuFrame.pif.filter(menuFrame, menuFrame.img); 
			    menuFrame.img = menuFrame.pif.filter(menuFrame, menuFrame.img); 
			  
			    menuFrame.lim.set(menuFrame.fimg); 
			    //menuFrame.lab.setText("Filtered: " + arg); 
			    menuFrame.repaint();
		}
		
		else if(arg.equals("Invert"))
		{
			    
			    menuFrame.filtername="Invert";
				menuFrame.newimg=false;
				menuFrame.pif = (PlugInFilter)Class.forName("Invert").newInstance(); 
			    menuFrame.fimg = menuFrame.pif.filter(menuFrame, menuFrame.img); 
			    menuFrame.img = menuFrame.pif.filter(menuFrame, menuFrame.img); 
			    
				System.out.println(menuFrame.pif);
				
			    menuFrame.lim.set(menuFrame.fimg); 
			    //menuFrame.lab.setText("Filtered: " + arg); 
			    menuFrame.repaint();
		}
		else if(arg.equals("Contrast"))
		{
			    
			    menuFrame.filtername="Contrast";
				menuFrame.newimg=false;
				menuFrame.pif = (PlugInFilter)Class.forName("Contrast").newInstance(); 

			    menuFrame.fimg = menuFrame.pif.filter(menuFrame, menuFrame.img); 
			   
			   menuFrame.img = menuFrame.pif.filter(menuFrame, menuFrame.img); 
			  
			    menuFrame.lim.set(menuFrame.fimg); 
			    //menuFrame.lab.setText("Filtered: " + arg); 
			    menuFrame.repaint();
		}
		
		else if(arg.equals("Sharpen"))
		{
			
			  menuFrame.filtername="Sharpen";
				menuFrame.newimg=false;
				menuFrame.pif = (PlugInFilter)Class.forName("Sharpen").newInstance(); 
			    menuFrame.fimg = menuFrame.pif.filter(menuFrame, menuFrame.img); 
			    menuFrame.img = menuFrame.pif.filter(menuFrame, menuFrame.img); 
			  
			    //menuFrame.lab.setText("Filtered: " + arg); 
			    menuFrame.repaint();
		}
		
		else if(arg.equals("Save"))
		{
			 
			 Image im=new ImageIcon(menuFrame.img).getImage();
			 ImageIcon icn= new ImageIcon(menuFrame.img);
			 BufferedImage bi=new BufferedImage(icn.getIconWidth(),icn.getIconHeight(),BufferedImage.TYPE_INT_RGB);
			 bi.getGraphics().drawImage(im,0,0,null);
			 
			
			
			String str=menuFrame.imgfilename;
			int x=str.lastIndexOf(".");
			String fname=str.substring(0,x);
			String exten=str.substring(x,str.length());
		 
			String nfname=menuFrame.imgdir+fname+"-"+menuFrame.filtername+exten;
					 
			System.out.println(nfname);
			
             if(str.toLowerCase().endsWith(".png"))
			 {
				 
				 java.io.File f=new java.io.File(nfname);
				 ImageIO.write(bi,"png",f);
				      
				 
			 }
			 else if(str.toLowerCase().endsWith(".jpg"))
			 {
				 
				 java.io.File f=new java.io.File(nfname);
				 ImageIO.write(bi,"jpg",f);
				      
				 
			 }
			 else
			 {
			    java.io.File f=new java.io.File(nfname);
				 String a=exten.substring(1,exten.length());
				 ImageIO.write(bi,a,f);
					 
			
			 }
		}
		
		
		 
	  }
         catch (ClassNotFoundException e) 
         { 
			 menuFrame.lab.setText(arg + " not found"); 
			 menuFrame.lim.set(menuFrame.img); 
			 menuFrame.repaint(); 
	     }
	    
		catch (InstantiationException e) 
        {
	      menuFrame.lab.setText("couldn't new " + arg); 
        }
        
		catch (IllegalAccessException e)
        { 
		  menuFrame.lab.setText("no access: " + arg); 
        }
		catch (Exception e) 
         { 
			System.out.println("My Error"); e.printStackTrace();
	     }
   
		menuFrame.msg = msg;
		menuFrame.repaint();
    }

	public void itemStateChanged(ItemEvent ie)
	{
	  menuFrame.repaint();
	}

}

public class ImageProcessDemo
{
     public static void main(String[] args)
     {
        SwingUtilities.invokeLater(new Runnable() 
		{

           @Override
           public void run() 
		   {
            new MenuFrame("ImageProcessDemo").setVisible(true);

           }
        });

     }
}
