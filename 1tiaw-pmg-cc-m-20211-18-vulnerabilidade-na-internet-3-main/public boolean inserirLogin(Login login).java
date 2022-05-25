public  boolean inserirLogin(Login login) {
    boolean status = false;
    try {
        Statement st = conexao.createStatement();
        st.executeUpdate("INSERT INTO login (site,email, senha) "
                       + "VALUES ("+login.getSite()+ ", '" + login.getEmail() + "', '"
                       + login.getSenha() + "');");
        st.close();
        status = true;
    } catch (SQLException u) {
        throw new RuntimeException(u);
    }
    return status;
public Login getLogin(int id) {
    Login[] logins = null;

    try {
        Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("SELECT * FROM login WHERE id = " + id);
         if(rs.next()){
             rs.last();
             logins = new Login[rs.getRow()];
             rs.beforeFirst();

             for(int i = 0; rs.next(); i++) {
                logins[i] = new Logins(rs.getString("site"),rs.getString("email"), rs.getString("senha")); 

             }
          }
          st.close();
    } catch (Exception e) {
        System.err.println(e.getMessage());
    }
    return logins[0];
}