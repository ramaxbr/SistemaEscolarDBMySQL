package daoImplements;


import IAlunoDAO.IAlunoDAO;
import java.sql.PreparedStatement;
import database.sqlConn;
import model.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;


public class AlunoDAOImplements implements IAlunoDAO {
    //sqlConn sqlConn = new sqlConn();

    @Override
    public void salvarAluno(Aluno aluno) {
    String sql = "INSERT INTO aluno (nome, cpf, email, data_nascimento, telefone) VALUES (?, ?, ?, ?, ?)";
           try(Connection conn = sqlConn.getConnection()) {
               PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

               stmt.setString(1, aluno.getNome());
               stmt.setString(2, aluno.getCpf());
               stmt.setString(3, aluno.getEmail());
               stmt.setDate(4, Date.valueOf(aluno.getData_Nascimento()));
               stmt.setString(5, aluno.getTelefone());

               ResultSet chavepk = stmt.getGeneratedKeys();

               if (chavepk.next()) {
                   aluno.setId(chavepk.getInt(1));
               }

               stmt.executeUpdate();
               System.out.println("Aluno cadastrado com sucesso!");

           }catch (SQLException e){
               throw new RuntimeException("Erro ao cadastrar" + e.getMessage());
           }
    }

    @Override
    public List<Aluno> listarTodosAlunos() {
        String sql = "SELECT * FROM ALUNO ORDER BY nome ASC";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = sqlConn.getConnection()){

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                alunos.add(new Aluno (
                        rs.getInt("idAluno"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (alunos);
    }

    @Override
    public void atualizarAluno(Aluno aluno) {

        String sql = "UPDATE aluno SET nome = ?, cpf = ?, email = ?, telefone = ? WHERE idAluno = ?";

        try(Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

           stmt.setString(1, aluno.getNome());
           stmt.setString(2, aluno.getCpf());
           stmt.setString(3, aluno.getEmail());
           stmt.setString(4, aluno.getTelefone());
           stmt.setInt(5, aluno.getId());
           stmt.executeUpdate();

           System.out.println("Atualizado com sucesso!");

        }catch (SQLException errorSql){
            throw new RuntimeException("Erro ao atualizar o sql", errorSql);
        }



    }

    @Override
    public void excluirAluno(Aluno aluno) {
        String sql = "DELETE FROM aluno WHERE idAluno = ?";

        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, aluno.getId());
            stmt.executeUpdate();

            System.out.println("Aluno excluído com sucesso!");
        }catch (SQLException error){
            System.out.println("Erro ao excluir aluno: " + error.getMessage());
        }
    }

    @Override
    public Optional<Aluno> buscarPorId(int id){
        String sql = "SELECT * FROM aluno WHERE idAluno = ?";

        try(Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                Aluno aluno = new Aluno(
                        rs.getInt("idAluno"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone")
                );

                return Optional.of(aluno);
            }
        }catch (SQLException e){
            System.err.println("Erro ao buscar aluno " + e.getMessage());
        }
        return Optional.empty();
    }





}
