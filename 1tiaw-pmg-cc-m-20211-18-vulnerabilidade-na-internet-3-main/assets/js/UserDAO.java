import model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.*;



public class UserDAO {
	
private Connection conexao;
	
	public UserDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("ConexÃ£o efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("ConexÃ£o NÃƒO efetuada com o postgres -- Driver nÃ£o encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("ConexÃ£o NÃƒO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirCliente(User user) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO user (nome, usuario, email, senha) "
					    + "VALUES (" + user.getNome() + ",'" + user.getUsuario()+ ", '" + user.getEmail() + "', '"  
					    + user.getSenha() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarCliente(User user) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE user SET cidade = '" + user.getCidade() + "', cep = '"  
				    + user.getCep() + "', rua = '" + user.getRua() +"', bairro= '"+ user.getBairro() + "'"
                    + "', pet = '" + user.getPet()+ "', dataesp = '" + user.getDataEsp()
					+ " WHERE email = " + user.getEmail();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
    
    public boolean atualizarUserte(User user) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE User SET nome = '" + user.getNome() + "', sobrenome = '"  
				    + user.getSobrenome() + "', graudeUsert = '" + user.getGrauDeUsert() + "'"
					+ " WHERE email = " + user.getEmail();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;

	}
	public boolean atualizarSenhas(User user) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE User SET site= '" + user.getSite() + "', usuario = '"  
				    + user.getUsuario() + "', senha = '" + user.getSenha() + "'"
					+ " WHERE email = " + user.getEmail();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	
	public User[] getSenhas() {
		User[] user = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM User");		
	         if(rs.next()){
	            rs.last();
	            user = new User[rs.getRow()];
	            rs.beforeFirst();

	            for(int i = 0; rs.next(); i++) {
	                user[i] = new User(rs.getString("site"),rs.getString("ussite"), rs.getString("ssite") 
	                	                );
	            }
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return User;
	}
	public User getUser(String user) {
		User[] users = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE email = " + email);		
	         if(rs.next()){
	            rs.last();
	            user = new User[rs.getRow()];
	            rs.beforeFirst();

	            for(int i = 0; rs.next(); i++) {
	            	user[i] = new User(rs.getString("cidade"),rs.getInt("cep"), rs.getString("rua"), 
	                		                rs.getString("bairro"),rs.getPet("pet"),rs.getInt("dataesp"));
	            }
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return user[0];
	}
    public User getUserte(String user) {
		User[] users = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE email = " + email);		
	         if(rs.next()){
	            rs.last();
	            user = new User[rs.getRow()];
	            rs.beforeFirst();

	            for(int i = 0; rs.next(); i++) {
	                user[i] = new User(rs.getString("nome"),rs.getString("sobrenome"), rs.getInt("graudeUsert")); 
	            }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return user[0];
	}
    private User getGeraSenha(String email){
        String c;
        x1=new User[rs.getRow()];
        x2=new User[rs.getRow()];
        x1=getUserte(email);
        x2=getUser(email);
        Random random = new Random();

        int a = random.nextInt(3);
        int a1=random.nextInt(2);
        int a3=random.nextInt(6);
        String b;
        if(a3==0){
            b="!";
        }else if(a3==1){
            b="@";
        }else if(a3==2){
            b="#";
        }else if(a3==3){
            b="$";
        }else if(a3==4){
            b="%";
        }else if(a3==5){
            b="?";
        }else if(a3==6){
            b="&";
        }else if(a3==7){
            b="*";
        }
        int a4=random.nextInt(3);

        
        String dados;
        int dados1;
        if(a==0){
            dados=x2.cidade;
        }else if(a==1){
            dados=x2.rua;
        }else if(a==2){
            dados=x2.bairro;
        }else{ 
            dados=x2.pet;
        }
        
        int a2=random.nextInt(2);
        if(a2==0){
        dados1=x2.cep;
        }else if(a==1){
            dados1=x1.graudeUsert;
        }
        else{
        dados1=x2.data;
        }
    String dados3;
        if(a2==0){
            dados3=x1.nome;
        }else{
            dados3=x1.sobrenome;
        }
        String senha;
        senha=null;
        String s=Integer.parseInt(dados1);
        if(a4==0){

    
        for(int i=0;i<=a1;i++){
            senha=senha+dados.charAt(i);

        }
        for(int j=0;j<a;j++){
            senha=senha+s.charAt(j);

        }
        senha=senha+b;
        for(int k=0;k<a2;j++){
            senha=senha+dados3.charAt(k);
        }
    }else if(a4==1){
        for(int j=0;j<a;j++){
            senha=senha+s.charAt(j);
        }
        senha=senha+b;
        for(int k=0;k<a2;j++){
            senha=senha+dados3.charAt(k);
        }
        for(int i=0;i<=a1;i++){
            senha=senha+dados.charAt(i);

        }
    }else if(a4==2){
        senha=senha+b;
        for(int i=0;i<=a1;i++){
            senha=senha+dados.charAt(i);

        }
        for(int k=0;k<a2;j++){
            senha=senha+dados3.charAt(k);
        }
        for(int j=0;j<a;j++){
            senha=senha+s.charAt(j);
        }
    }else{
        senha=senha+b;
        for(int k=0;k<a2;j++){
            senha=senha+dados3.charAt(k);
        }
        for(int i=0;i<=a1;i++){
            senha=senha+dados.charAt(i);

        }
        for(int j=0;j<a;j++){
            senha=senha+s.charAt(j);
        }
	return senha;
    }
	
    

	}   

	
	
}