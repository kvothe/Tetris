package jku.se.tetris.ui.swing.dialogs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	public void close() {
		setVisible(false);
	}

	// ---------------------------------------------------------------------------

	private void createContent() {
		final Container cp = getContentPane();
		cp.setPreferredSize(new Dimension(350, 350));
		cp.setLayout(new GroupLayout(cp));
		// --

		final JTextField benutzernameinput = new JTextField();
		final JPasswordField passwortinput = new JPasswordField();
		// --
		JLabel anmelden = new JLabel("Sign In");
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
		benutzernameinput.setToolTipText("3 to 20 characters");
		cp.add(benutzernameinput);
		passwortinput.setBounds(75, 175, 200, 25);
		passwortinput.setToolTipText("8-character minimum; case sensitive");
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
				if (benutzername.equalsIgnoreCase("Max") && passwort.equalsIgnoreCase("Mustermann")) {
					JOptionPane.showMessageDialog(cp.getParent(), "You are now signed in.", "Success", JOptionPane.INFORMATION_MESSAGE);
					// --
					benutzernameinput.setText("");
					passwortinput.setText("");
					// --
					close();
				} else {
					JOptionPane.showMessageDialog(cp.getParent(), "Username or password incorrect.", "Failure", JOptionPane.ERROR_MESSAGE);
					// --
					benutzernameinput.setText("");
					passwortinput.setText("");
				}
			}
		});
	}
}
