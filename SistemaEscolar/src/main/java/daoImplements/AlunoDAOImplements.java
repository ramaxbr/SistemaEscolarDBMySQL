package daoImplements;


import IAlunoDAO.IAlunoDAO;
import java.sql.PreparedStatement;
import database.sqlConn;
import model.Aluno;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

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

    }

    @Override
    public void excluirAluno(int id) {
    }

    @Override
    public List<Aluno> listarID(){
        int where = 1;
        String sql = "SELECT * FROM ALUNO WHERE idAluno = " + where;


        List<Aluno>



        return
    }
}
