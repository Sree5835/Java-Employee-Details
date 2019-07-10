import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class MainPage extends JFrame
{
	
    static JButton b1;
	static JButton b2;
	static JTextArea area;
	private static ArrayList<JLabel> details;
    private static ArrayList<Object> fields;
    private static String[] bloodGroups;
    private static String[] designations;
    private static int diffObjs;
    private static int itemDiv;
    private static ArrayList<JButton> dtBtns;
    private static ArrayList<String> columns;
    private static boolean neverLoaded;
    private static JLabel submissionStatus;
    private static boolean alright;
  		
    MainPage()
    {	submissionStatus = new JLabel("Submission was successful!");
    	submissionStatus.setBounds(280,630,300,50);
    	submissionStatus.setFont(new Font("TimesRoman",Font.TRUETYPE_FONT,20));
    	this.add(submissionStatus);
    	submissionStatus.setVisible(false);
    	area=new JTextArea();
    	details = new ArrayList<JLabel>();
    	fields = new ArrayList<Object>();
    	dtBtns = new ArrayList<JButton>();
    	columns = new ArrayList<String>();
    	itemDiv= 9;
    	diffObjs =3;
    	neverLoaded=true;
    	alright=false;
    	designations = new String[]{"Select","Software Engineer","Senior Software Engineer","Consultant","Senior Consultant","Manager","Senior Manager"};
    	bloodGroups = new String[]{"Select","A+","O+","B+","AB+","A-","O-","B-","AB-"};
        setLayout(new FlowLayout());
        this.setLayout(null);
        JLabel title = new JLabel("Employee Info.",JLabel.CENTER);
        this.add(title);
        title.setBounds(120, 20, 600, 100);
        title.setFont(new Font("TimesRoman",Font.TRUETYPE_FONT,60));
        details.add(new JLabel("Employee ID*:",JLabel.LEFT));
        details.add(new JLabel("Resource Name*:",JLabel.LEFT));
        details.add(new JLabel("Client Name:",JLabel.LEFT));
        details.add(new JLabel("Reporting Manager:",JLabel.LEFT));
        details.add(new JLabel("Mobile 1*:",JLabel.LEFT));
        details.add(new JLabel("Mobile 2:",JLabel.LEFT));
        details.add(new JLabel("Off. Email ID*:",JLabel.LEFT));
        details.add(new JLabel("Pers. Email ID:",JLabel.LEFT));
        details.add(new JLabel("Cli. Email ID:",JLabel.LEFT));
        details.add(new JLabel("Work Location:",JLabel.LEFT));
        details.add(new JLabel("Status:",JLabel.LEFT));
        details.add(new JLabel("D.O. Birth*:",JLabel.LEFT));
        details.add(new JLabel("D.O. Joining*:",JLabel.LEFT));
        details.add(new JLabel("D.O. Report To Cli.:",JLabel.LEFT));
        details.add(new JLabel("D.O. Relieving:",JLabel.LEFT));
        details.add(new JLabel("Designation:",JLabel.LEFT));
        details.add(new JLabel("Blood Group:",JLabel.LEFT));
        details.add(new JLabel("Remarks:",JLabel.LEFT));
        
        b1=new JButton("Submit");
        b2=new JButton("Cancel");
        this.add(b1);
        this.add(b2);
        b1.setBounds(300,570,90,40);
        b2.setBounds(400,570,90,40);
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
        mp.setSize(800,1500);
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
            	//if(neverLoaded) {
	            	for(int i=0;i<fields.size();i++) {
	            		if(validate(i)==false) {
	            			break;
	            		}
	            		if(fields.get(i) instanceof JTextField) {
	            				//System.out.println(((JTextField)fields.get(i)).getText());
	            				columns.add(((JTextField)fields.get(i)).getText());
	            				//((JTextField)fields.get(i)).setText("");
	            		}
	            		if(fields.get(i) instanceof JComboBox) {
	            			if(i%2==1) {
	            				//System.out.println(designations[((JComboBox)fields.get(i)).getSelectedIndex()]);
	            				columns.add(designations[((JComboBox)fields.get(i)).getSelectedIndex()]);
	            			}else {
	            				//System.out.println(bloodGroups[((JComboBox)fields.get(i)).getSelectedIndex()]);	
	            				columns.add(bloodGroups[((JComboBox)fields.get(i)).getSelectedIndex()]);
	            				//((JComboBox)fields.get(i)).setSelectedIndex(0);
	            			}
	            			((JComboBox)fields.get(i)).setSelectedIndex(0);
	            		}
	            	
	            	}
	            	//System.out.println(area.getText());
	            	columns.add(area.getText());
	            	//neverLoaded=false;
            	//}
            	if(alright) {
            	try {
					updateDB();
					showStatus("Submission was successful!");
	            	clearAllData();
				} catch (InvalidFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
            	}
            	columns.removeAll(columns);
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
    
    public static boolean validate(int i) {
    	if((i==0||i==1||i==4||i==6||i==11||i==12)&&((JTextField)fields.get(i)).getText().trim().length() == 0) {
    		//System.out.println(i);
    		//showStatus("Please enter proper input in " +(details.get(i).getText().substring(0,details.get(i).getText().length()-1))+"!");
    		JOptionPane.showMessageDialog(new JFrame(), "Please enter proper input in " +(details.get(i).getText().substring(0,details.get(i).getText().length()-1))+"!",
                    "Incorrect Input", JOptionPane.ERROR_MESSAGE);
            ((JTextField)fields.get(i)).requestFocusInWindow();
            return false;
    	}
    	if((i==0||i==4||i==5)&&((JTextField)fields.get(i)).getText().trim().length() != 0) {
    		try {
    			//DO NOT REMOVE THIS double J portion!!!!!
                Double j = Double.parseDouble(((JTextField)fields.get(i)).getText());   //This was a string coming from a result that I changed into an Double
                } catch (Exception z) { 
                	//showStatus("Please input only numbers in "+(details.get(i).getText().substring(0,details.get(i).getText().length()-1))+"!");
                    JOptionPane.showMessageDialog(new JFrame(), "Please input only numbers in "+(details.get(i).getText().substring(0,details.get(i).getText().length()-1))+"!",
                       "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                    ((JTextField)fields.get(i)).setText("");
                    ((JTextField)fields.get(i)).requestFocusInWindow();
                    return false;
           }
    	}
    	if((i==6||i==7||i==8)&&(((JTextField)fields.get(i)).getText().trim().length() != 0)) {
    		if(!((JTextField)fields.get(i)).getText().trim().contains("@")||!((JTextField)fields.get(i)).getText().trim().contains(".")) {
    			//showStatus("Please input a proper email address in " +(details.get(i).getText().substring(0,details.get(i).getText().length()-1))+"!");
    			JOptionPane.showMessageDialog(new JFrame(), "Please input a proper email address in " +(details.get(i).getText().substring(0,details.get(i).getText().length()-1))+"!",
                        "Incorrect Input", JOptionPane.ERROR_MESSAGE);
    			((JTextField)fields.get(i)).setText("");
                ((JTextField)fields.get(i)).requestFocusInWindow();
                return false;
    		}
    	}
    	if((i==11||i==12||i==13||i==14)&&(((JTextField)fields.get(i)).getText().trim().length() != 0)) {
    		if(!((JTextField)fields.get(i)).getText().trim().contains("-")) {
    			//showStatus("Please input a proper email address in " +(details.get(i).getText().substring(0,details.get(i).getText().length()-1))+"!");
    			JOptionPane.showMessageDialog(new JFrame(), "Please select a proper " +(details.get(i).getText().substring(0,details.get(i).getText().length()-1))+"!",
                        "Incorrect Input", JOptionPane.ERROR_MESSAGE);
    			((JTextField)fields.get(i)).setText("");
                //((JTextField)fields.get(i)).requestFocusInWindow();
                return false;
    		}
    	}
    	
    	if(i==fields.size()-1) {
    		alright=true;
    	}
    	return true;
    }
    private static void updateDB() throws InvalidFormatException, IOException {
    	String excelFileName = "Machint_Employee_Details.xlsx";//name of excel file
		String sheetName = "Main";//name of sheet
		Workbook wb = new XSSFWorkbook(new FileInputStream(excelFileName));
		XSSFSheet sheet = (XSSFSheet) wb.getSheet(sheetName);
		int rowNum=0;
		Iterator rows = sheet.rowIterator();
		while (rows.hasNext())
		{
			rows.next();
			rowNum++;
		}
		XSSFRow row = sheet.createRow(rowNum);
        // Creating cells
        for(int i = 0; i < columns.size(); i++) {
        	XSSFCell cell = row.createCell(i);
            cell.setCellValue(columns.get(i));
            //cell.setCellStyle(headerCellStyle);
        }

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
    }
    @SuppressWarnings("serial")
	private static void showStatus(String s) {
    	submissionStatus.setText(s);
    	submissionStatus.setVisible(true);
    	int timerDelay = 350;
        new Timer(timerDelay , new ActionListener() {
           int timeLeft = 5;

           @Override
           public void actionPerformed(ActionEvent e) {
              if (timeLeft > 0) {
                 timeLeft--;
              } else {
                 ((Timer)e.getSource()).stop();
                 submissionStatus.setVisible(false);
              }
           }
        }){{setInitialDelay(0);}}.start();
    }
    private static void clearAllData() {
    	for(int i=0;i<fields.size();i++) {
    		
    		if(fields.get(i) instanceof JTextField) {
    				((JTextField)fields.get(i)).setText("");
    		}
    		if(fields.get(i) instanceof JComboBox) {
    			((JComboBox)fields.get(i)).setSelectedIndex(0);
    		}
    	
    	}
    	area.setText("");
    }
}