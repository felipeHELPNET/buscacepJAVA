package cep;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		getContentPane().setForeground(SystemColor.activeCaption);
		setResizable(false);
		setTitle("SOBRE");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/about_smartwatch_help_icon.png")));
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblBuscacepVer = new JLabel("Busca-CEP - Ver. 1.0");
		lblBuscacepVer.setBounds(51, 28, 308, 15);
		getContentPane().add(lblBuscacepVer);
		
		JLabel lblauthor = new JLabel("@Author: Felipe Daniel");
		lblauthor.setBounds(51, 80, 193, 15);
		getContentPane().add(lblauthor);
		
		JLabel lblWebService = new JLabel("WEB Service");
		lblWebService.setBounds(51, 129, 86, 15);
		getContentPane().add(lblWebService);
		
		JLabel lblRepubliavirtualcombr = new JLabel("republicavirtual.com.br");
		lblRepubliavirtualcombr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://www.republicavirtual.com.br/");
			}
		});
		lblRepubliavirtualcombr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRepubliavirtualcombr.setForeground(SystemColor.activeCaption);
		lblRepubliavirtualcombr.setBounds(145, 129, 184, 15);
		getContentPane().add(lblRepubliavirtualcombr);
		
		JButton btnGithub = new JButton("");
		btnGithub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://github.com/felipeHELPNET");
			}
		});
		btnGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/github_icon.png")));
		btnGithub.setBounds(366, 196, 40, 40);
		getContentPane().add(btnGithub);
	}
	
	private void link(String site) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI (site);
			desktop.browse(uri);
		}catch (Exception e){
			System.out.println(e);
		}
	}
}
