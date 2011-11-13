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

public class RegisterDialog extends JDialog {
	private static final long serialVersionUID = -5147339756430909616L;

	// ---------------------------------------------------------------------------

	public RegisterDialog(JFrame parent) {
		super(parent);
		// --
		setTitle("Register...");
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
		final JTextField neubenutzerinput = new JTextField();
		final JPasswordField neupasswortinput = new JPasswordField();
		final JPasswordField neupasswortcorrinput = new JPasswordField();
		// --
		JLabel reg = new JLabel("Registrieren: ");
		reg.setFont(getParent().getFont().deriveFont(20f));
		reg.setBounds(75, 70, 200, 25);
		cp.add(reg);
		// --
		JLabel neubenutzername = new JLabel("Name");
		JLabel neupasswort = new JLabel("Enter a password");
		JLabel neupasswortcorr = new JLabel("Retype password");
		// --
		neubenutzerinput.setBounds(75, 125, 200, 25);
		cp.add(neubenutzerinput);
		neubenutzerinput.setToolTipText("Name");
		neupasswortinput.setBounds(75, 175, 200, 25);
		neupasswortinput.setToolTipText("6-character minimum; case sensitive");
		cp.add(neupasswortinput);
		neubenutzername.setBounds(75, 100, 200, 25);
		cp.add(neubenutzername);
		neupasswort.setBounds(75, 150, 200, 25);
		neupasswortcorr.setBounds(75, 200, 200, 25);
		cp.add(neupasswortcorr);
		cp.add(neupasswort);
		neupasswortcorrinput.setBounds(75, 225, 200, 25);
		neupasswortcorrinput.setToolTipText("Retype password");
		cp.add(neupasswortcorrinput);
		// --
		JButton zurueckreg = new JButton("Reset");
		zurueckreg.setBounds(175, 255, 100, 25);
		cp.add(zurueckreg);
		JButton registrieren = new JButton("Register");
		registrieren.setBounds(75, 255, 100, 25);
		cp.add(registrieren);
		// --
		zurueckreg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				neubenutzerinput.setText("");
				neupasswortinput.setText("");
				neupasswortcorrinput.setText("");
			}
		});
		// --
		registrieren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String benutzername = neubenutzerinput.getText();
				String passwort = new String(neupasswortinput.getPassword());
				String passwortwieder = new String(neupasswortinput.getPassword());
				if (benutzername.equalsIgnoreCase("Biene") && passwort.equalsIgnoreCase(passwortwieder) && passwort.equalsIgnoreCase("Maier")) {
					neubenutzerinput.setText("");
					neupasswortinput.setText("");
					neupasswortcorrinput.setText("");
				} else {

				}
			}
		});
	}
}
