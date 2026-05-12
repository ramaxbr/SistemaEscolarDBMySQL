package daoImplements;


import IAlunoDAO.IAlunoDAO;
import java.sql.PreparedStatement;
import database.sqlConn;
import model.Aluno;

import javax.xml.transform.Result;
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
    public void excluirAluno(int id) {
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
