package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    static final List<Participante> participantes = new ArrayList<>();
    static final List<Prova> provas = new ArrayList<>();
    static final List<Questao> questoes = new ArrayList<>();
    static final List<Tentativa> tentativas = new ArrayList<>();

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        
        // Injeção de Dependência (DIP) manual no Main
        ParticipanteService participanteService = new ParticipanteService(participantes, 1);
        ProvaService provaService = new ProvaService(provas, 1);
        
        // OCP: Podemos trocar para qualquer outra implementação de CalculadoraNota aqui
        CalculadoraNota calculadora = new CalculadoraNotaSimples(); 
        
        TentativaService tentativaService = new TentativaService(tentativas, 1, calculadora, in);
        
        // Corrigindo o uso do QuestãoService que você já tinha criado
        QuestãoService questaoService = new QuestãoService(questoes, provas, 1, in, 0);

        seed(provaService);

        while (true) {
            exibirMenu();
            String opcao = in.nextLine();

            switch (opcao) {
                case "1" -> cadastrarParticipante(participanteService);
                case "2" -> cadastrarProva(provaService);
                case "3" -> questaoService.cadastrar(); // Delegando totalmente ao serviço
                case "4" -> aplicarProva(tentativaService);
                case "5" -> tentativaService.listarTentativas();
                case "0" -> { System.out.println("Saindo..."); return; }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== SISTEMA OLIMPÍADAS (REFATORADO) ===");
        System.out.println("1) Cadastrar participante");
        System.out.println("2) Cadastrar prova");
        System.out.println("3) Cadastrar questão");
        System.out.println("4) Aplicar prova");
        System.out.println("5) Listar tentativas");
        System.out.println("0) Sair");
        System.out.print("> ");
    }

    private static void cadastrarParticipante(ParticipanteService service) {
        System.out.print("Nome: ");
        String nome = in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();
        service.cadastrar(nome, email);
        System.out.println("Sucesso.");
    }

    private static void cadastrarProva(ProvaService service) {
        System.out.print("Título da prova: ");
        service.cadastrar(in.nextLine());
        System.out.println("Sucesso.");
    }

    private static void aplicarProva(TentativaService tentativaService) {
        if (participantes.isEmpty() || provas.isEmpty()) {
            System.out.println("Erro: Verifique se há participantes e provas cadastrados.");
            return;
        }

        // Idealmente, a lógica de escolha abaixo também poderia ir para um "Helper" de UI
        Long pId = escolherId("Participantes", participantes);
        Long prId = escolherId("Provas", provas);

        if (pId != null && prId != null) {
            List<Questao> questoesDaProva = questoes.stream()
                    .filter(q -> q.getProvaId() == prId).toList();
            
            if (questoesDaProva.isEmpty()) {
                System.out.println("Prova sem questões.");
            } else {
                tentativaService.aplicarProva(pId, prId, questoesDaProva);
            }
        }
    }

    // Método genérico para reduzir repetição (DRY + SRP)
    private static Long escolherId(String titulo, List<?> lista) {
        System.out.println("\n" + titulo + ":");
        // Aqui você usaria um toString ou reflexão simples para mostrar os itens
        System.out.print("Digite o ID: ");
        try { return Long.parseLong(in.nextLine()); } 
        catch (Exception e) { return null; }
    }

    static void seed(ProvaService provaService) {
        provaService.cadastrar("Olimpíada 2026 • Nível 1");
    }
}