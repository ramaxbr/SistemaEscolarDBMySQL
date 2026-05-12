package org.example;

import database.sqlConn;
import model.Aluno;
import daoImplements.AlunoDAOImplements;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AlunoDAOImplements alunoDAOImplements = new AlunoDAOImplements();
        Scanner sc = new Scanner(System.in);

        sqlConn.testConnection();

        int opcao;

        do{
            System.out.println("==== MENU ====");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Atualizar Aluno");
            System.out.println("3. Excluir Aluno");
            System.out.println("4. Listar Aluno");
            System.out.println("5. ListarID");
            System.out.println("0. Sair do programa");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("Cadastro de aluno.");
                    break;
                case 2:
                    System.out.println("Atualizar aluno.");
                    AlunoDAOImplements DAOAluno = new AlunoDAOImplements();

                    LocalDate data = LocalDate.parse("2000-02-20");

                    Aluno updateAluno = new Aluno(1, "teste", "cpf","teste@gmail.com", data, "11956567263" );

                   DAOAluno.atualizarAluno(updateAluno);


                    break;
                case 3:
                    System.out.println("Excluir aluno.");
                    break;
                case 4:
                    System.out.println("Listar Aluno");

                    List<Aluno> todosAlunos = alunoDAOImplements.listarTodosAlunos();

                    if(todosAlunos.isEmpty()){
                        System.out.println("Nenhum aluno.");
                    } else {
                        for(Aluno aluno : todosAlunos){
                            System.out.println(aluno);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Selecione o ID do aluno que queira listar.");
                    int idAluno = sc.nextInt();
                    sc.nextLine();

                    System.out.println(alunoDAOImplements.buscarPorId(idAluno));
                    break;
                case 0:
                    opcao = 0;
                    break;
            }
        }while (opcao != 0);
    }
}
