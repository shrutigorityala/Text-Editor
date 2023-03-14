import java.awt.*;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.io.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
import java.awt.event.*;
//public class Main {
//
//}
class editor extends JFrame implements ActionListener{
    JFrame f;
    JTextArea t;
    editor(){
        //initializing frame
        f=new JFrame("Notepad");

        //setting theme of noteped
        try{
            UIManager.setLookAndFeel("import javax.swing.plaf.metal.MetalLookandFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch (Exception e){

        }

        //creating text component
        t=new JTextArea();

        //creating menubar
        JMenuBar menuBar =new JMenuBar();

        //creating file menu
        JMenu file = new JMenu("File") ;

        //creating file menuitems
        JMenuItem m1 = new JMenuItem("New");
        JMenuItem m2 = new JMenuItem("Open");
        JMenuItem m3 = new JMenuItem("Save");
        JMenuItem m4 = new JMenuItem("Print");

        //adding actionlisteners to the file menuitems
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        //adding menuitems to file menu
        file.add(m1);
        file.add(m2);
        file.add(m3);
        file.add(m4);

        JMenu edit = new JMenu("Edit") ;

        //creating edit menuitems
        JMenuItem m5 = new JMenuItem("Cut");
        JMenuItem m6 = new JMenuItem("Copy");
        JMenuItem m7 = new JMenuItem("Paste");

        //adding actionlisteners to the edit menuitems
        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);

        //adding menuitems to edit menu
        edit.add(m5);
        edit.add(m6);
        edit.add(m7);

        //creating and adding menuitem close to menubar
        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(this);

        //adding menus to menubar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(close);

        //adding menus to menubar
        f.setJMenuBar(menuBar);

        //adding textarea to menubar
        f.add(t);
        f.setSize(700,400);
        f.show();
    }

    //adding functionality to menubar
    public void actionPerformed(ActionEvent e) {

        //extracting button pressed
        String s = e.getActionCommand();

        if(s.equals("New")){
            t.setText("");
        }
        else if(s.equals("Open")){
            //initialising jfile chooser with diretcory path
            JFileChooser j = new JFileChooser("D:");

            //invoking opendialogbox with integer
            int r = j.showOpenDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){
                //set the lable to path from slected file location
                File fi = new File(String.valueOf(j.getSelectedFile().getAbsoluteFile()));

                try {
                    //string to copy the data fom sletcted file
                    String s1 = "", s2 = "";


                    FileReader fr = new FileReader(fi);

                    BufferedReader br = new BufferedReader(fr);

                    s2 = br.readLine();

                    while ((s1 = br.readLine()) != null) {
                        s2 = s2 + '\n'+ s1;
                    }
                    t.setText(s2);
                }
                catch(Exception et){
                    JOptionPane.showMessageDialog(f, et.getMessage());
                }
            }
        }
        else if(s.equals("Save")){
            JFileChooser j = new JFileChooser("D:");

            //invoking savedialogbox with integer
            int r = j.showSaveDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){
                //set the lable to path from slected file location
                File fi = new File(String.valueOf(j.getSelectedFile().getAbsoluteFile()));

                try {
                    FileWriter fw = new FileWriter(fi,false);

                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write(t.getText());

                    //after writing is finished flush the stream
                    bw.flush();
                    bw.close();
                }
                catch(Exception et){
                    JOptionPane.showMessageDialog(f, et.getMessage());
                }
            }


        }
        else if(s.equals("Print")){
            try {
                t.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s.equals("Cut")){
            t.cut();
        }
        else if(s.equals("Copy")){
            t.copy();
        }
        else if(s.equals("Paste")){
            t.paste();
        }
        else if(s.equals("Close")){
            f.setVisible(false);
        }
    }

    public static void main(String[] args) {

        editor e = new editor();
    }
}