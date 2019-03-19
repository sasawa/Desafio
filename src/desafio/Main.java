package desafio;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Main {
	static DAOProd dao = new DAOProd();
	
	static void inserirProduto() {
		Produto prod = new Produto();
		
		prod.setNome(JOptionPane.showInputDialog("Nome; "));
		prod.setDescricao(JOptionPane.showInputDialog("Descricao; "));
		prod.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Preco").replaceAll(",", ".")));
		
		if(dao.insert(prod))
			JOptionPane.showMessageDialog(null, "Dados Validos e inseridos ");
		else
			JOptionPane.showMessageDialog(null, "Dados Invalidos falha a inserir");
		
	}
	static void editarProduto() {
		Produto prod = new Produto();
		ResultSet cursor = dao.getAll();
		String msg = "";
		try {
			while(cursor.next()) {
				msg += "\n"
						+cursor.getString("id")
						+" - "
						+cursor.getString("nome")
						+" - "
						+cursor.getString("descricao")
						+" - "
						+cursor.getString("preco");
			}
			prod.setId(Integer.parseInt(JOptionPane.showInputDialog("Escolha o id do produto que deseja editar: "+msg)));
			
			prod.setNome(JOptionPane.showInputDialog("Nome; "));
			prod.setDescricao(JOptionPane.showInputDialog("Descricao; "));
			prod.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Preco").replaceAll(",", ".")));
		
			if(dao.update(prod))
				JOptionPane.showMessageDialog(null, "Dados Validos e Atualizados ");
			else
				JOptionPane.showMessageDialog(null, "Dados Invalidos falha ao atualizar");
		} catch (Exception e) {
			// TODO: handle exception
		}
				
	}
	static void deletarProduto() {
		Produto prod = new Produto();
		ResultSet cursor = dao.getAll();
		String msg = "";
		try {
			while(cursor.next()) {
				msg += "\n"
						+cursor.getString("id")
						+" "
						+cursor.getString("nome")
						+" "
						+cursor.getString("descricao")
						+" "
						+cursor.getString("preco")
						+" ";
			}
			prod.setId(Integer.parseInt(JOptionPane.showInputDialog("Escolha o id de um produto para apagar: "+msg)));
		
			if(dao.remove(prod))
				JOptionPane.showMessageDialog(null, "Registro removido ");
			else
				JOptionPane.showMessageDialog(null, "Falha ao deletar, Contatar suporte");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private static void mostrarProdutos() {
		ResultSet cursor = dao.getAll();
		String msg = "";
	
			try {
				while(cursor.next()) {
					try {
						msg += "\n"
								+cursor.getString("id")
								+" "
								+cursor.getString("nome")
								+" "
								+cursor.getString("descricao")
								+" "
								+cursor.getString("preco")
								+" ";
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, msg);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		while(true) {
			try {
				int funcao = Integer.parseInt(JOptionPane.showInputDialog("O que deseja?"
						+ "\n 1 - Inserir Produtos"
						+ "\n 2 - Editar Produtos"
						+ "\n 3 - Deletar Produtos"
						+ "\n 4 - Mostrar Produtos"));
			
			
				switch (funcao) {
				case 1:
					inserirProduto();
					break;
				case 2:
					editarProduto();
					break;	
				case 3:
					deletarProduto();
					break;
				case 4:
					mostrarProdutos();
					break;
				default:
					JOptionPane.showMessageDialog(null, "Operação Invalida");
					break;
				}
			} catch (Exception e) {
				if (JOptionPane.showConfirmDialog(null, "Deseja Sair?") == 0)
					break;
				// TODO: handle exception
			}
		}

	}
	

}
