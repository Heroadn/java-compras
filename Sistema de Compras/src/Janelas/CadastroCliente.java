/*Classe Responsavel por cadastrar pessoas e mandar para a 
 *lista de pessoas na classe JMenu*/
package Janelas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.Cliente;
import Listas.ListaCliente;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CadastroCliente extends JFrame {

	protected static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JTextField nome_campo;
	protected JTextField idade_campo;
	protected JTextField senha_campo;
	protected JTextField senha_confirm;

	/*Fun��o que recebe uma lista de pessoas do JMenu
	 compartilhando com o resto das janelas*/
	public CadastroCliente(ListaCliente lc, ListagemCliente lp) {
		setAlwaysOnTop(true);
		setTitle("Cadastro de Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 387, 261);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setFocusableWindowState(true);
		
		//Campo de texto para o usuario inserir o nome
		nome_campo = new JTextField();
		nome_campo.setBounds(145, 27, 135, 20);
		contentPane.add(nome_campo);
		nome_campo.setColumns(10);
		
		//Campo de texto para o usuario inserir a idade
		idade_campo = new JTextField();
		idade_campo.setBounds(145, 58, 135, 20);
		contentPane.add(idade_campo);
		idade_campo.setColumns(10);
		
		//Label Nome
		JLabel nome = new JLabel("Nome:");
		nome.setBounds(42, 30, 92, 14);
		contentPane.add(nome);
		
		//Label Idade
		JLabel idade = new JLabel("Idade:");
		idade.setBounds(42, 59, 93, 14);
		contentPane.add(idade);
		
		//Botao responsavel por enviar as informa�oes do cadastro
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setIcon(new ImageIcon(CadastroCliente.class.getResource("/com/jtattoo/plaf/icons/large/check_symbol_14x14.png")));
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				efetuarRegistro(lc);
				lp.insertRow(lc, lp.getTm());
			}});
		btnEnviar.setBounds(60, 153, 118, 43);
		contentPane.add(btnEnviar);
		
		//Botao responsavel por dar um dispose na janela("fechar ela, sem fechar o programa junto")
		JButton btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(CadastroCliente.class.getResource("/com/jtattoo/plaf/icons/medium/error_32x32.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(188, 153, 118, 43);
		contentPane.add(btnSair);
		
		//LABELS
		JLabel senha = new JLabel("Senha:");
		senha.setBounds(42, 90, 91, 14);
		contentPane.add(senha);
		
		JLabel senha_confirmar = new JLabel("Senha Confirmar:");
		senha_confirmar.setBounds(42, 119, 92, 14);
		contentPane.add(senha_confirmar);
		
		//JPasswordField campo da senha
		senha_campo = new JPasswordField();
		senha_campo.setColumns(10);
		senha_campo.setBounds(145, 87, 135, 20);
		contentPane.add(senha_campo);
		
		//JPasswordField Confimar a senha
		senha_confirm = new JPasswordField();
		senha_confirm.setColumns(10);
		senha_confirm.setBounds(145, 116, 135, 20);
		contentPane.add(senha_confirm);
	}
	
	public void efetuarRegistro(ListaCliente lc) {
		//Verificar se os campos est�o vazios
		if(nome_campo.getText().equalsIgnoreCase("") || idade_campo.getText().equalsIgnoreCase("") || 
	      senha_campo.getText().equalsIgnoreCase("") || senha_campo.getText().equalsIgnoreCase(""))
		{
			System.out.println("Campos Vazios");
		}else {
			
			//Verficar se o nome ja existe no banco de dados
			if(lc.checkNomeRepetido(nome_campo.getText())) {
				System.out.println("Login repetido");
				
			}else {
				//Verificar se as senhas digitadas sao iguais.
				if(senha_campo.getText().equals(senha_confirm.getText())) {
					Cliente p = new Cliente();
					p.setNome(nome_campo.getText());
					p.setIdade(Integer.parseInt(idade_campo.getText()));
					p.setSenha(senha_campo.getText());
					p.setSaldo(1000);
					
					//Adicionar a pessoa no banco de dados
					lc.addCliente(p);
					
					//Limpando os campos de texto
					nome_campo.setText("");
					
					idade_campo.setText("");
					
					senha_campo.setText("");
					
					senha_confirm.setText("");	
				}else {
					System.err.println("Por favor verifique as senhas digitadas.");
					//JOptionPane.showMessageDialog(null, "Por favor verifique as senhas digitadas.","Error: ", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}
}
