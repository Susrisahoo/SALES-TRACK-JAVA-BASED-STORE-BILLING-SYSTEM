import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class Login extends JFrame implements ActionListener{
    JFrame f  = this;
    String logUsername,logPassword;
    JRadioButton admin,clerk;
    JTextField usernameField,passwordField;
    public  Login(){
        GridBagLayout layout  = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        ImageIcon image = new ImageIcon("C:\\Users\\mukes\\IdeaProjects\\Billing_System_AppDev\\images\\login.png");
        Image img = image.getImage().getScaledInstance(100,100,Image.SCALE_AREA_AVERAGING);
        image = new ImageIcon(img);
        JLabel login_img = new JLabel(image);
        login_img.setHorizontalAlignment(JLabel.CENTER);
        login_img.setVerticalAlignment(JLabel.NORTH);
        JPanel logo = new JPanel();
        logo.add(login_img);
        JLabel name = new JLabel("STORE BILLING SYSTEM");
        name.setFont(new Font("serif",Font.BOLD,20));
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setVerticalAlignment(JLabel.NORTH);
        name.setForeground(Color.red);
//        JLabel log = new JLabel("Login In here");
//        log.setFont(new Font("serif",Font.ITALIC,16));
////        log.setHorizontalAlignment(JLabel.CENTER);
        admin = new JRadioButton("Admin");
        clerk = new JRadioButton("Clerk");
        ButtonGroup bg = new ButtonGroup();
        bg.add(admin);bg.add(clerk);
        JPanel choose = new JPanel();
        choose.add(admin);choose.add(clerk);choose.setLayout(new FlowLayout());choose.setSize(200,50);
        JLabel username = new JLabel("Username : ");
        usernameField = new JTextField(20);
        JLabel password = new JLabel("Password : ");
        JButton loginButton = new JButton("Log in");
        passwordField = new JPasswordField(20);
        passwordField.addActionListener(this);
        loginButton.addActionListener(this);
        gbc.insets = new Insets(10,0,0,0);
        JPanel up = new JPanel();
        up.setLayout(new GridBagLayout());
        gbc.gridx=0;gbc.gridy=0;up.add(username,gbc);
        gbc.gridx=1;gbc.gridy=0;up.add(usernameField,gbc);
        gbc.gridx=0;gbc.gridy=1;up.add(password,gbc);
        gbc.gridx=1;gbc.gridy=1;up.add(passwordField,gbc);
        gbc.gridx = 0;gbc.gridy = 0;add(name,gbc);
        gbc.gridx = 0;gbc.gridy = 1;add(logo,gbc);
        gbc.gridx = 0;gbc.gridy = 2;add(choose,gbc);
        gbc.gridx = 0;gbc.gridy = 3;add(up,gbc);
        gbc.gridx = 0;gbc.gridy = 4;add(loginButton,gbc);
        setVisible(true);
        setTitle("Login Page");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        logUsername = usernameField.getText();
        logPassword = passwordField.getText();
        if(logPassword == "" || logUsername == "") {
            JOptionPane.showMessageDialog(f, "Please enter Username & Password");
        } else {
            if (admin.isSelected() || clerk.isSelected()) {
                validateLogin();

            } else {
                JOptionPane.showMessageDialog(f, "Please select account type");
            }
        }
    }
    public void validateLogin(){
        ResultSet rs;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing","root","tiger");
            Statement smt = con.createStatement();
            System.out.println("select * from admins where username = '" + logUsername + "' and password = '" + logPassword + "'");
            if(admin.isSelected()) {
                rs = smt.executeQuery("select * from admins where username = '" + logUsername + "' and password = '" + logPassword + "'");
                if(rs.next()){
                    String[] str = {logUsername, logPassword};
                    AdminPanel.main(str);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(f,"Incorrect username or password");
                }
            }else{
                rs = smt.executeQuery("select * from clerk where username = '" + logUsername + "' and password = '" + logPassword + "'");
                if(rs.next()){
                    new ClerkPanel().setVisible(true);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(f,"Incorrect username or password");
                }
            }

            con.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(f,"Failed Connection");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Login login = new Login();
    }
}