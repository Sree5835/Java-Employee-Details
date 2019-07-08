import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class MainPage extends JFrame
{
	
    static JButton b1;
	static JButton b2;
	static JTextArea area;
	//JLabel[] details = new JLabel[18];
	//static JTextField[] fields = new JTextField[18];
	private static ArrayList<JLabel> details;
    private static ArrayList<Object> fields;
    private static String[] bloodGroups;
    private static String[] designations;
    private static int diffObjs;
    private static int itemDiv;
    private static ArrayList<JButton> dtBtns;
  		
    MainPage()
    {	
    	area=new JTextArea();
    	details = new ArrayList<JLabel>();
    	fields = new ArrayList<Object>();
    	dtBtns = new ArrayList<JButton>();
    	itemDiv= 9;
    	diffObjs =3;
    	designations = new String[]{"Select","Software Engineer","Senior Software Engineer","Consultant","Senior Consultant","Manager","Senior Manager"};
    	bloodGroups = new String[]{"Select","A+","O+","B+","AB+","A-","O-","B-","AB-"};
        setLayout(new FlowLayout());
        this.setLayout(null);
        JLabel title = new JLabel("Details",JLabel.CENTER);
        this.add(title);
        title.setBounds(70, 20, 600, 100);
        title.setFont(new Font("Lucida",Font.PLAIN,60));
        details.add(new JLabel("Employee ID:",JLabel.LEFT));
        details.add(new JLabel("Resource Name:",JLabel.LEFT));
        details.add(new JLabel("Client Name:",JLabel.LEFT));
        details.add(new JLabel("Reporting Manager:",JLabel.LEFT));
        details.add(new JLabel("Mobile 1:",JLabel.LEFT));
        details.add(new JLabel("Mobile 2:",JLabel.LEFT));
        details.add(new JLabel("Off. Email ID:",JLabel.LEFT));
        details.add(new JLabel("Pers. Email ID:",JLabel.LEFT));
        details.add(new JLabel("Cli. Email ID:",JLabel.LEFT));
        details.add(new JLabel("Work Location:",JLabel.LEFT));
        details.add(new JLabel("Status:",JLabel.LEFT));
        details.add(new JLabel("D.O. Birth:",JLabel.LEFT));
        details.add(new JLabel("D.O. Joining:",JLabel.LEFT));
        details.add(new JLabel("D.O. Report To Cli.:",JLabel.LEFT));
        details.add(new JLabel("D.O. Relieving:",JLabel.LEFT));
        details.add(new JLabel("Designation:",JLabel.LEFT));
        details.add(new JLabel("Blood Group:",JLabel.LEFT));
        details.add(new JLabel("Remarks:",JLabel.LEFT));
        
        b1=new JButton("Submit");
        b2=new JButton("Cancel");
        this.add(b1);
        this.add(b2);
        b1.setBounds(300,570,70,40);
        b2.setBounds(380,570,70,40);
        //details only
        for(int i=0;i<details.size();i++){
        	this.add(details.get(i));
            if(i<=itemDiv) {
            details.get(i).setBounds(70,120+(40*i),135,60);
            }else {
            details.get(i).setBounds(420,120+(40*(i-10)),135,60);
            }
        }
        this.add(details.get(17));
        //fields only 
        for(int i=0;i<(details.size()-diffObjs);i++) {
        	fields.add(new JTextField(20));
        	this.add((JTextField)(fields.get(i)));
        	if(i<=itemDiv) {
                ((JTextField)fields.get(i)).setBounds(250,140+(40*i),90,20);
                }else {
                	((JTextField)fields.get(i)).setBounds(600,140+(40*(i-10)),90,20);
                	if(i>=11&&i<=14) {
                		dtBtns.add(new JButton("..."));
                  		dtBtns.get(i-11).setBounds(700,140+(40*(i-10)),50,25);
                  		this.add(dtBtns.get(i-11));
                	}
                }
        }

        
        fields.add(new JComboBox(designations));
        ((JComboBox)fields.get(15)).setBounds(600,340,90,20);
        this.add(((JComboBox)fields.get(15)));
        fields.add(new JComboBox(bloodGroups));
        ((JComboBox)fields.get(16)).setBounds(600,380,90,20);
        this.add(((JComboBox)fields.get(16)));
        this.add(area); 	
        details.get(17).setBounds(420,440,75,60);
        area.setBounds(510,460,180,100);
    }
    
    public static void main(String args[])
    {
    	
        MainPage mp=new MainPage();
        mp.setVisible(true);
        mp.setSize(750,1500);
        mp.setTitle("Employee Details Form");
        mp.addWindowListener(new WindowAdapter(){
        	  public void windowClosing(WindowEvent e){
        	  System.exit(0);
        	  }
        });
        b2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
            	System.exit(0);
                }  
        });
        b1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
            	for(int i=0;i<17;i++) {
            		validate(i);
            		if(fields.get(i) instanceof JTextField) {
            			
            			//uncomment if all fields need to mandatory
//            			if ( ((JTextField)fields.get(i)).getText().trim().length() == 0 ) {
//            			JOptionPane.showMessageDialog(new JFrame(), "Make sure all inputs are complete!",
//                                "Incorrect Submission", JOptionPane.ERROR_MESSAGE);
//            				break;
//            			}
            				System.out.println(((JTextField)fields.get(i)).getText());
            		}
            		if(fields.get(i) instanceof JComboBox) {
            			if(i%2==1) {
            				System.out.println(designations[((JComboBox)fields.get(i)).getSelectedIndex()]);
            			}else {
            				System.out.println(bloodGroups[((JComboBox)fields.get(i)).getSelectedIndex()]);	
            			}
            		}
            	}
            	System.out.println(area.getText());
            }  
        }); 
        dtBtns.get(0).addActionListener(new ActionListener() 
		{	
			//performed action
			public void actionPerformed(ActionEvent e) 
			{
				dtAction(11);
			}
		});
        dtBtns.get(1).addActionListener(new ActionListener() 
		{	
			//performed action
			public void actionPerformed(ActionEvent e) 
			{
				dtAction(12);
			}
		});
        dtBtns.get(2).addActionListener(new ActionListener() 
		{	
			//performed action
			public void actionPerformed(ActionEvent e) 
			{
				dtAction(13);
			}
		});
        dtBtns.get(3).addActionListener(new ActionListener() 
		{	
			//performed action
			public void actionPerformed(ActionEvent e) 
			{
				dtAction(14);
			}
		});
        
    }
    
    public static void dtAction(int i) {
		final JFrame f = new JFrame();
		((JTextField) fields.get(i)).setText(new DatePicker(f).setPickedDate());
    }
    
    public static void validate(int i) {
    	if((i==0||i==4||i==5)&&((JTextField)fields.get(i)).getText().trim().length() != 0) {
    		try {
    			//DO NOT REMOVE THIS int J portion!!!!!
                int j = Integer.parseInt(((JTextField)fields.get(i)).getText());   //This was a string coming from a result that I changed into and Int
                } catch (Exception z) { 
                    JOptionPane.showMessageDialog(new JFrame(), "Please input only numbers in "+(details.get(i).getText().substring(0,details.get(i).getText().length()-1))+"!",
                       "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                    ((JTextField)fields.get(i)).setText("");
                    ((JTextField)fields.get(i)).setText("");
                    ((JTextField)fields.get(i)).requestFocusInWindow();
                    return;
           }
    	}
    	if((i==6||i==7||i==8)&&(((JTextField)fields.get(i)).getText().trim().length() != 0)) {
    		if(!((JTextField)fields.get(i)).getText().trim().contains("@")||!((JTextField)fields.get(i)).getText().trim().contains(".")) {
    			JOptionPane.showMessageDialog(new JFrame(), "Please input a proper email address in " +(details.get(i).getText().substring(0,details.get(i).getText().length()-1))+"!",
                        "Incorrect Input", JOptionPane.ERROR_MESSAGE);
    			((JTextField)fields.get(i)).setText("");
                ((JTextField)fields.get(i)).requestFocusInWindow();
                return;
    		}
    	}
    }
}