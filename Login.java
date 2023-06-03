import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame{

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Downloads\\BBALZ.png"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel landingPagePanel = new JPanel();
		landingPagePanel.setBounds(0, 0, 984, 561);
		frame.getContentPane().add(landingPagePanel);
		landingPagePanel.setLayout(null);
		
		JPanel residentPanel = new JPanel();
		residentPanel.setBounds(130, 95, 280, 340);
		landingPagePanel.add(residentPanel);
		residentPanel.setOpaque(false);
		residentPanel.setLayout(null);
		
		JLabel ResidentImg = new JLabel("");
		ResidentImg.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		ResidentImg.setBounds(10, 65, 260, 260);
		residentPanel.add(ResidentImg);
		ResidentImg.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/residentlabel (1).png")));
		
		JLabel biggerResidentImg = new JLabel("");
		residentPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ResidentImg.setVisible(false);
				biggerResidentImg.setVisible(true);
				
			}
			public void mouseExited(MouseEvent e) {
				biggerResidentImg.setVisible(false);
				ResidentImg.setVisible(true);
				
			}
		});
		biggerResidentImg.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/residentlabel (1) (1).png")));
		biggerResidentImg.setHorizontalAlignment(SwingConstants.CENTER);
		biggerResidentImg.setBounds(10, 65, 260, 260);
		biggerResidentImg.setVisible(false);
		residentPanel.add(biggerResidentImg);
		
		JPanel guestPanel = new JPanel();
		guestPanel.setBounds(570, 95, 280, 340);
		landingPagePanel.add(guestPanel);
		guestPanel.setLayout(null);
		guestPanel.setOpaque(false);
		
		JLabel GuestLabel = new JLabel("");
		GuestLabel.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/guestlabell (1).png")));
		GuestLabel.setBounds(10, 65, 265, 260);
		guestPanel.add(GuestLabel);
		
		JLabel biggerGuestImg = new JLabel("");
		guestPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				GuestLabel.setVisible(false);
				biggerGuestImg.setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				biggerGuestImg.setVisible(false);
				GuestLabel.setVisible(true);
			}
		});
		biggerGuestImg.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/guestlabell (1) (1).png")));
		biggerGuestImg.setBounds(0, 60, 260, 270);
		biggerGuestImg.setVisible(false);
		guestPanel.add(biggerGuestImg);
		
		JLabel adminImg = new JLabel("");
		adminImg.setBounds(10, 485, 65, 65);
		landingPagePanel.add(adminImg);
		adminImg.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/adminlabel (1).png")));
		
		JLabel adminLabel = new JLabel("ADMIN");
		adminImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				adminLabel.setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				adminLabel.setVisible(false);
			}
		});
		adminLabel.setForeground(new Color(0, 0, 0));
		adminLabel.setFont(new Font("Dubai", Font.BOLD, 20));
		adminLabel.setBounds(80, 505, 100, 30);
		adminLabel.setVisible(false);
		landingPagePanel.add(adminLabel);
		
		JLabel backgroundImg = new JLabel("");
		backgroundImg.setBounds(0, 0, 984, 561);
		landingPagePanel.add(backgroundImg);
		backgroundImg.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/HOME (1).png")));
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 984, 561);
		frame.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		loginPanel.setVisible(false);
		residentPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				landingPagePanel.setVisible(false);	
				loginPanel.setVisible(true);
			}
		});
		
		JPanel formPanel = new JPanel();
		formPanel.setBounds(605, 0, 379, 561);
		loginPanel.add(formPanel);
		formPanel.setLayout(null);
		formPanel.setOpaque(false);
		
		JLabel ResidentImg_1 = new JLabel("");
		ResidentImg_1.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/residentloginlabel.png")));
		ResidentImg_1.setBounds(59, 53, 260, 260);
		formPanel.add(ResidentImg_1);
		
		JLabel usernameIcon = new JLabel("");
		usernameIcon.setBounds(46, 368, 50, 40);
		formPanel.add(usernameIcon);
		usernameIcon.setHorizontalAlignment(SwingConstants.CENTER);
		usernameIcon.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/user (4).png")));
		
		txtUsername = new JTextField();
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtUsername.getText().equals("Username")) {
					txtUsername.setText("");
				}
				else {
					txtUsername.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtUsername.getText().equals("")){
					txtUsername.setText("Username");
				}
			}
		});
		txtUsername.setText("Username");
		txtUsername.setBounds(106, 385, 205, 15);
		formPanel.add(txtUsername);
		txtUsername.setBackground(new Color(255, 255, 255));
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (pwdPassword.getText().equals("Password")) {
					pwdPassword.setEchoChar('*');
					pwdPassword.setText("");
				}
				else {
					pwdPassword.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (pwdPassword.getText().equals("")){
					pwdPassword.setText("Password");
					pwdPassword.setEchoChar((char)0);
				}
			}
		});
		pwdPassword.setText("Password");
		pwdPassword.setEchoChar((char)0);
		pwdPassword.setBounds(106, 424, 205, 15);
		formPanel.add(pwdPassword);
		
		JLabel passwordIcon = new JLabel("");
		passwordIcon.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/padlock.png")));
		passwordIcon.setHorizontalAlignment(SwingConstants.CENTER);
		passwordIcon.setBounds(46, 409, 50, 40);
		formPanel.add(passwordIcon);
		
		JButton btnNewButton = new JButton("LOG IN");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = txtUsername.getText();
				String pass = pwdPassword.getText();
				if(user.contains("Resident") && pass.contains("Res1")) {
					
					checker.main(null);
					frame.setVisible(false);
					
				}
			}
		});
		btnNewButton.setBounds(140, 460, 100, 20);
		formPanel.add(btnNewButton);
		
		JLabel sanAntonioImg = new JLabel("");
		sanAntonioImg.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/sanantonio.png")));
		sanAntonioImg.setBounds(81, 94, 415, 385);
		loginPanel.add(sanAntonioImg);
		
		JButton backBtn = new JButton("");
		backBtn.setOpaque(false);
		backBtn.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/back (1).png")));
		backBtn.setBounds(10, 10, 60, 50);
		loginPanel.add(backBtn);
		
		JLabel formBgImg = new JLabel("");
		formBgImg.setIcon(new ImageIcon(Login.class.getResource("/IMAGES/loginpagee.png")));
		formBgImg.setBounds(0, 0, 984, 561);
		loginPanel.add(formBgImg);
	}
	
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
