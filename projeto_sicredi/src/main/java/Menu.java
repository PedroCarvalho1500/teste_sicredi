import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;

class Menu implements ActionListener

{
    private static String[] ListaTestes = {"ADD CUSTOMER","EXCLUDE CUSTOMER"};
    static JLabel text;

    //Driver function

    public static void main(String args[])

    {

        //Create a frame

        JFrame frame = new JFrame("MENU DE TESTES");

        frame.setSize(1320,720);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new FlowLayout());


        //Create an object

        Menu obj = new Menu();

        //Create a Menu

        JMenu menu = new JMenu("CLICK HERE TO SELECT A TEST AND EXECUTE");

        //Create Menu Items

        JMenuItem item[] = new JMenuItem[5];



        for(int i=0;i<2;i++)

        {

            item[i]=new JMenuItem(" "+ListaTestes[i]);

            item[i].addActionListener(obj);

            menu.add(item[i]);

        }

        //Create a menu bar

        JMenuBar mb=new JMenuBar();

        mb.add(menu);

        frame.setJMenuBar(mb);

        //Create the label

        text = new JLabel();

        frame.add(text);

        //Display the frame

        frame.setVisible(true);

    }

    //Function to display the menu item selected

    public void actionPerformed(ActionEvent e)

    {

        text.setText("Menu Item Selected : "+e.getActionCommand());
        
    }

}