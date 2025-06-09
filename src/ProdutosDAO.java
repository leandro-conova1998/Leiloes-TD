

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    // Cadastro de produto
    public void cadastrarProduto (ProdutosDTO produto){
        String sql = "INSERT INTO produtos (nome,valor,status) VALUES (?,?,?)";
        try{
             conn = new conectaDAO().connectDB();
             prep = conn.prepareStatement(sql);
             prep.setString(1, produto.getNome());
             prep.setInt(2, produto.getValor());
             prep.setString(3, produto.getStatus());
             prep.executeUpdate();
             JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        }
        
        //conn = new conectaDAO().connectDB();  
    } 
    public ArrayList<ProdutosDTO> listarProdutos(){
       String sql = "SELECT * FROM produtos";
        try{
             conn = new conectaDAO().connectDB();
             prep = conn.prepareStatement(sql);
             resultset = prep.executeQuery();
             
             
             while(resultset.next()){
                 ProdutosDTO produto = new ProdutosDTO();
                 produto.setId(resultset.getInt("id"));
                 produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
             }
             return listagem;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        } 
    }
    
    
    
        
}

