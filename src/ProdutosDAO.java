

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
    public boolean venderProduto(int id){
        String sql = "UPDATE produtos SET status = 'vendido' WHERE id = ?";
        try{
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");
            return true;
        }catch(Exception e){
            System.out.println("Erro ao vendar produto: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        listagem = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
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
                System.out.println("Produto: " + produto.getNome() + " - " + produto.getStatus());
                listagem.add(produto);
            }
        }catch(Exception ex){
            System.out.println("Erro ao listar produtos: " + ex.getMessage());
            return null;
        }
        return listagem;
    }
        
}

