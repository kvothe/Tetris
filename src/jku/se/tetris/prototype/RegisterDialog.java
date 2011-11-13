package jku.se.tetris.prototype;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		cp.setPreferredSize(new Dimension(350, 600));
		cp.setLayout(new GroupLayout(cp));
		// --
		final JTextField neubenutzerinput = new JTextField();
		final JTextField neupasswortinput = new JTextField();
		final JTextField neupasswortcorrinput = new JTextField();
		// --
		JLabel reg = new JLabel("Registrieren: ");
		reg.setFont(new Font("Ueberschrift", 0, 20));
		reg.setBounds(100, 345, 200, 25);
		cp.add(reg);
		// --
		JLabel neubenutzername = new JLabel("Benutzername");
		JLabel neupasswort = new JLabel("Wiederholte Eingabe");
		JLabel neupasswortcorr = new JLabel("Passwort");
		// --
		neubenutzerinput.setBounds(100, 400, 200, 25);
		cp.add(neubenutzerinput);
		neubenutzerinput.setToolTipText("Benutzername");
		neupasswortinput.setBounds(100, 450, 200, 25);
		neupasswortinput.setToolTipText("Passwort");
		cp.add(neupasswortinput);
		neubenutzername.setBounds(100, 375, 200, 25);
		// --
		cp.add(neubenutzername);
		neupasswort.setBounds(100, 475, 200, 25);
		neupasswortcorr.setBounds(100, 425, 200, 25);
		cp.add(neupasswortcorr);
		cp.add(neupasswort);
		neupasswortcorrinput.setBounds(100, 500, 200, 25);
		neupasswortcorrinput.setToolTipText("Passwort");
		cp.add(neupasswortcorrinput);
		// --
		JButton zurueckreg = new JButton("Reset");
		zurueckreg.setBounds(200, 530, 100, 25);
		cp.add(zurueckreg);
		JButton registrieren = new JButton("Register");
		registrieren.setBounds(100, 530, 100, 25);
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
				String passwort = neupasswortinput.getText();
				if (benutzername.equalsIgnoreCase("Biene") && neupasswortinput.getText().equalsIgnoreCase(neupasswortcorrinput.getText()) && passwort.equalsIgnoreCase("Maier")) {
					neubenutzerinput.setText("");
					neupasswortinput.setText("");
					neupasswortcorrinput.setText("");
				} else {
					// showmenue();
				}
			}
		});
	}
}
