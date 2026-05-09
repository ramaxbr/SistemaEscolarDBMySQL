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
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, email = ?, data_nascimento = ?, telefone = ?";
        try(Connection conn = sqlConn.getConnection()){
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getCpf());
        stmt.setString(3, aluno.getEmail());
        stmt.setDate(4, java.sql.Date.valueOf(aluno.getData_Nascimento()));
        stmt.setString(5, aluno.getTelefone());
        stmt.setInt(6, aluno.getId());

        stmt.executeUpdate();

            System.out.println("Aluno atualizado com sucesso!");

        }catch (SQLException e){
            System.out.println("Falha em atualizar o nome.");
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
