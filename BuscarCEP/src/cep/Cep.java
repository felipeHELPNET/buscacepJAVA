package cep;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Cep extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox cboUf;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cep() {
		setTitle("Busca-CEP");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/home_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CEP:");
		lblNewLabel.setBounds(29, 28, 52, 17);
		contentPane.add(lblNewLabel);

		txtCep = new JTextField();
		txtCep.setBounds(79, 26, 135, 19);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(29, 62, 106, 17);
		contentPane.add(lblEndereco);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(105, 61, 300, 19);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(29, 98, 70, 15);
		contentPane.add(lblBairro);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(29, 132, 70, 15);
		contentPane.add(lblCidade);

		JLabel lblUf = new JLabel("UF:");
		lblUf.setBounds(273, 132, 32, 15);
		contentPane.add(lblUf);

		txtBairro = new JTextField();
		txtBairro.setBounds(105, 96, 300, 19);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		txtCidade = new JTextField();
		txtCidade.setBounds(105, 130, 155, 19);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(306, 127, 81, 24);
		contentPane.add(cboUf);

		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(29, 213, 117, 25);
		contentPane.add(btnLimpar);

		JButton btnCEP = new JButton("BUSCAR");
		btnCEP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Verifica se o CEP contém apenas números
				if (!txtCep.getText().matches("[0-9]+")) {
					JOptionPane.showMessageDialog(null, "O CEP deve conter apenas números!");
					txtCep.requestFocus();
					return;
				}

				int cepSize = (txtCep.getText().split("")).length;
				if (cepSize != 8) {
					JOptionPane.showMessageDialog(null, "CEP necessita ter 08 números!");
					txtCep.requestFocus();
					return;
				}

				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP com 08 dígitos, use apenas números!");
					txtCep.requestFocus();
				} else {
					
					buscarCep();

				}
			}
		});

		btnCEP.setBounds(248, 24, 89, 25);
		contentPane.add(btnCEP);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Cep.class.getResource("/img/about_smartwatch_help_icon.png")));
		btnNewButton.setBounds(369, 5, 40, 40);
		contentPane.add(btnNewButton);

		lblStatus = new JLabel("");
		lblStatus.setBounds(212, 13, 40, 36);
		contentPane.add(lblStatus);

	}

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check_sign_icon.png")));
					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void limpar() {
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtCep.requestFocus();
		lblStatus.setIcon(null);
	}
}
