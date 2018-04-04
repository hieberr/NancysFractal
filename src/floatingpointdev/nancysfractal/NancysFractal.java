package floatingpointdev.nancysfractal;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;


public class NancysFractal extends JFrame implements ActionListener{
	private NancysFractalApplet nancysFractalApplet;
    JButton btnReset = new JButton("Reset");
    public NancysFractal() {
        super("Embedded PApplet");
        nancysFractalApplet = new NancysFractalApplet();
        JPanel content = new JPanel();
        JPanel content2 = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JPanel appletFrame = new JPanel();
        appletFrame.add(nancysFractalApplet);
        JButton btnSaveScreenShot = new JButton();
        btnSaveScreenShot.setText("Save Image");
        btnSaveScreenShot.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		//When the Save Screenshot button gets pressed
        		//Open the file Save dialog.
        		JFileChooser fc = new JFileChooser();
        		fc.setDialogType(JFileChooser.SAVE_DIALOG);
        		FileFilter filter = new FileNameExtensionFilter(".tif",".tif");

        		fc.addChoosableFileFilter(filter);
        		//fc.setFileView(new ImageFileView());
     	   
        		int ret = fc.showSaveDialog((Container)e.getSource());
        		if(ret == JFileChooser.APPROVE_OPTION){
        			//Save the file.
        			String filePath = fc.getSelectedFile().getAbsolutePath();
        			nancysFractalApplet.saveScreenshot(filePath);
        		}
        	}
         });
        JButton btnHelp = new JButton();
        btnHelp.setText("?");
        btnHelp.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
            	JFrame f = new JFrame();
            	JPanel content = new JPanel();
            	f.setTitle("Help");
            	content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            	content.add(new JLabel("Zoom In:"+"   "+"Left Click"));
            	content.add(new JLabel("Zoom Out:"+"  "+"Right Click"));
            	content.add(new JLabel("Panning:"+"   "+"Arrow Keys"));
            	content.add(new JLabel("+ Theta:"+"   "+"'q'"));          
            	content.add(new JLabel("- Theta:"+"   "+"'a'"));
            	content.add(new JLabel("+ Beta:"+"   "+"'w'"));          
            	content.add(new JLabel("- Beta:"+"   "+"'s'"));
              content.add(new JLabel("+ Function:"+"   "+"'e'"));          
              content.add(new JLabel("- Function:"+"   "+"'d'"));   
              content.add(new JLabel("+ Gradient:"+"   "+"'r'")); 
              content.add(new JLabel("- Gradient:"+"   "+"'f'")); 
                
              content.add(new JLabel("+ Red Amp:"+"   "+"'u'"));                 
              content.add(new JLabel("- Red Amp:"+"   "+"'j'")); 
              content.add(new JLabel("+ Green Amp:"+"   "+"'i'")); 
              content.add(new JLabel("- Green Amp:"+"   "+"'k'")); 
              content.add(new JLabel("+ Blue Amp:"+"   "+"'o'")); 
              content.add(new JLabel("- Blue Amp:"+"   "+"'l'")); 
                
            	f.setContentPane(content);
             	f.pack();
             	Point p = ((Component)e.getSource()).getLocationOnScreen();
             	f.setLocation(p.x, p.y + 60);
             	f.setAlwaysOnTop(true);
            	f.setVisible(true);
        	}
        });

        btnReset.addActionListener(this); 
        setBackground(new Color(10,0,0));
        
        //------------
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        //buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        //buttonsPanel.add(btnSaveScreenShot);
        //buttonsPanel.add(btnHelp);
        content.add(Box.createHorizontalGlue());  
        //buttonsPanel.setMinimumSize(buttonsPanel.getSize());
        //content.add(buttonsPanel);
        content.add(appletFrame);

        content.add(Box.createHorizontalGlue());      
        
        content2.setLayout(new BoxLayout(content2,BoxLayout.Y_AXIS));
        content2.add(Box.createVerticalGlue());   

        content2.add(content);
        
        //buttons panel
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(btnReset);
        buttonsPanel.add(btnSaveScreenShot);
        buttonsPanel.add(btnHelp);
        
        btnReset.setFocusable(false);
        btnSaveScreenShot.setFocusable(false);
        btnHelp.setFocusable(false);

        //content.add(Box.createHorizontalGlue());  
        //buttonsPanel.setMinimumSize(buttonsPanel.getSize());
        content2.add(buttonsPanel);
        content2.add(Box.createVerticalGlue()); 
        
        //getContentPane().setLayout( new FlowLayout());      
        getContentPane().add(content2);


        //------------
        // important to call this whenever embedding a PApplet.
        // It ensures that the animation thread is started and
        // that other internal variables are properly set.
        nancysFractalApplet.init();
        pack();
        
        //appletFrame.requestFocusInWindow();
        nancysFractalApplet.requestFocusInWindow();
        
        //Setup the event handler for closing the window.
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              System.exit(0);
            }
          });
        setTitle ("~~~~~~~~~~");
        setSize (1280,768); 
    }
    
     public void actionPerformed(ActionEvent e){ 
       if(e.getSource() == this.btnReset){
         nancysFractalApplet.reset();
         nancysFractalApplet.requestFocus();
       }
    }
    
    static public void main(String args[]) {
    	Frame f = new NancysFractal();
    	f.setVisible(true);
  	}
}
