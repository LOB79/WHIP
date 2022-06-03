
package model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String DESCRICAO_PADRAO = "Novo User";
    public static final int MAX_ESTOQUE = 1000;
    private String nome;
    private String email;
    private String senha;
    private String usuario;
    private String cidade;
    private int cep;
    private String rua;
    private String bairro;
    private String pet;
    private int dataesp;
    private String nomep;
    private String sobrenomep;
    private int graudeparent;
    private String site;
    private String usite;
    private String ssite;

    // private int quantidade;

    // private LocalDate dataValidade;

    public User() {
        nome = null;
        email = null;
        senha = null;
        usuario = null;
        cidade = null;
        cep = null;
        rua = null;
        bairro = null;
        pet = null;
        dataesp = null;
        nomep = null;
        sobrenomep = null;
        graudeparent = null;
        site = null;
        usite = null;
        ssite = null;
        ///
        id = -1;
        descricao = DESCRICAO_PADRAO;
        preco = 0.01F;
        // quantidade = 0;
        dataFabricacao = null;
        // dataValidade = LocalDate.now().plusMonths(6); // o default Ã© uma validade de
        // 6 meses.
    }

    public User(String nome, String email, String senha, String usuario, String cidade, int cep, String rua,
            String bairro, String pet, int dataesp, String nomep, String sobrenomep, int graudeparent,
            String site, String usite, String ssite) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setUsuario(usuario);
        setCidade(cidade);
        setCep(cep);
        setRua(rua);
        setBairro(bairro);
        setPet(pet);
        setDataEsp(dataesp);
        setNomep(nomep);
        setSobrenomep(sobrenomep);
        setGrauDeParent(graudeparent);
        setSite(site);
        setUsite(usite);
        setSsite(ssite);

        // setQuant(quantidade);

        // setDataValidade(v);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao.length() >= 3)
            this.descricao = descricao;
    }

    public String getSite() {

        return preco;
    }

    public void setPreco(float preco) {
        if (preco > 0)
            this.preco = preco;
    }
    private boolean LoginUser (String email, String senha) {
        User[] user = null;
        try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM user WHERE email = " + email);
            if(rs.next()){
                rs.last();
                boolean x = false;
                rs.beforeFirst();
                String a;
                a=user.getString("senha")
                if (senha == a){
                    x = true
                }
            }
             st.close();
       } catch (Exception e) {
           System.err.println(e.getMessage());
       }
       return x;
   }
   public void Validlogin() {
       boolean = resultadoLogin;
    var username = document.getElementById('bdemail').value;
    var password = document.getElementById('bdpassword').value;
    resultadoLogin = LoginUser  (username, password);
    if (resultadoLogin) {
        window.location.href = "userPage.html" ;
    }
    else { // Se login falhou, avisa ao usuário
        alert ('Usuário ou senha invalidos');
    }
   }
    public String SenhaSegura(){
        String senha = null;
        var username = document.getElementById('bdemail').value;
        var password = document.getElementById('bdpassword').value;
        resultadoLogin = LoginUser  (username, password);
        if (resultadoLogin) {
            senha = getGeraSenha(username) ;
        }
        else { // Se login falhou, avisa ao usuário
            alert ('Usuário ou senha invalidos nao podemos gerar a senha');
        }
        let tela4 = document.getElementById('tela4');
        tela.innerHTML = senha;
    }
    public imprimirsite (){
        let tela = document.getElementById('tela');
        let tela1 = document.getElementById('tela1');
        let tela2 = document.getElementById('tela2');
        let strHtml1 = '';
        let strHtml2 = '';
        let strHtml3 = '';
        let objsite = getSenhas ();
    
        for (i=0; i < getSenhas.usuario.length; i++){
            strHtml1 = strHtml1 +  `<p> ${objsite.site[i]} </p>`
            strHtml2 = strHtml2 +  `<p> ${objsite.usite[i]} </p>`
            strHtml3 = strHtml3 +  `<p> ${objsite.ssite[i]} </p>`
        }
        tela.innerHTML = strHtml1;
        tela1.innerHTML = strHtml2;
        tela2.innerHTML = strHtml3;
    }
    
    // public int getQuant() {
    // return quantidade;
    // }

    // public void setQuant(int quantidade) {
    // if (quantidade >= 0 && quantidade <= MAX_ESTOQUE)
    // this.quantidade = quantidade;
    // }

    // public LocalDate getDataValidade() {
    // return dataValidade;
    // }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date date) {
        // Pega a Data Atual
        // LocalDateTime agora = LocalDateTime.now();
        // Garante que a data de fabricaÃ§Ã£o nÃ£o pode ser futura
        // if (agora.compareTo(date) >= 0)
        this.dataFabricacao = date;
    }

    // public void setDataValidade(LocalDate dataValidade) {
    // a data de fabricaÃ§Ã£o deve ser anterior Ã© data de validade.
    // if (getDataFabricacao().isBefore(dataValidade.atStartOfDay()))
    // this.dataValidade = dataValidade;
    //

    // public boolean emValidade() {
    // return LocalDateTime.now().isBefore(this.getDataValidade().atTime(23, 59));
    // }

    /**
     * MÃ©todo sobreposto da classe Object. Ã‰ executado quando um objeto precisa
     * ser exibido na forma de String.
     */
    @Override
    public String toString() {
        return "User: " + descricao + "   PreÃ§o: R$" + preco + "   FabricaÃ§Ã£o: "
                + dataFabricacao;
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((User) obj).getId());
    }
}
