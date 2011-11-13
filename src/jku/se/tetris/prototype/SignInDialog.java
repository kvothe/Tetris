package jku.se.tetris.prototype;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignInDialog extends JDialog {
	private static final long serialVersionUID = -5147339756430909616L;

	// ---------------------------------------------------------------------------

	public SignInDialog(JFrame parent) {
		super(parent);
		// --
		setTitle("Sign in...");
		createContent();
		pack();
		// --
		setModal(true);
		// --
		setLocationRelativeTo(parent);
	}

	// ---------------------------------------------------------------------------

	public void open() {
		setVisible(true);
	}

	// ---------------------------------------------------------------------------

	private void createContent() {
		Container cp = getContentPane();
		cp.setPreferredSize(new Dimension(350, 350));
		cp.setLayout(new GroupLayout(cp));
		// --

		final JTextField benutzernameinput = new JTextField();
		final JPasswordField passwortinput = new JPasswordField();
		// --
		JLabel anmelden = new JLabel("Login: ");
		anmelden.setFont(getParent().getFont().deriveFont(20f));
		anmelden.setBounds(75, 70, 200, 25);
		cp.add(anmelden);
		JLabel benutzername = new JLabel("Name");
		JLabel passwort = new JLabel("Password");
		benutzername.setBounds(75, 100, 200, 25);
		cp.add(benutzername);
		passwort.setBounds(75, 150, 200, 25);
		cp.add(passwort);
		benutzernameinput.setBounds(75, 125, 200, 25);
		benutzernameinput.setToolTipText("Name");
		cp.add(benutzernameinput);
		passwortinput.setBounds(75, 175, 200, 25);
		passwortinput.setToolTipText("Password");
		cp.add(passwortinput);
		// --
		JButton login = new JButton("Login");
		login.setBounds(75, 205, 100, 25);
		cp.add(login);
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String benutzername = benutzernameinput.getText();
				String passwort = new String(passwortinput.getPassword());
				if (benutzername.equalsIgnoreCase("Biene") && passwort.equalsIgnoreCase("Maier")) {
					benutzernameinput.setText("");
					passwortinput.setText("");
				} else {
					benutzernameinput.setText("");
					passwortinput.setText("");
				}
			}
		});
	}
}
