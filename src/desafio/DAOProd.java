package desafio;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

public class DAOProd {
	Connection conn;
	
	public DAOProd() {
		conn = ConnManager.getConn();
		
	}
	
	public boolean insert(Produto p) {
		try {
			Statement st = conn.createStatement();
			st.execute("INSERT INTO produto(nome,descricao,preco) VALUES ('"+p.getNome()+"','"+p.getDescricao()+"',"+p.getPreco()+")");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean update(Produto p) {
		try {
			Statement st = conn.createStatement();
			st.execute("UPDATE produto SET nome = '"+p.getNome()+"', descricao ='"+p.getDescricao()+"', preco = " + p.getPreco() +" WHERE id = " + p.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean remove(Produto p) {
		try {
			Statement st = conn.createStatement();
			st.execute("DELETE FROM produto WHERE id = " + p.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public ResultSet getAll() {
		try {
			Statement st = conn.createStatement();
			return st.executeQuery("select * from produto");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
