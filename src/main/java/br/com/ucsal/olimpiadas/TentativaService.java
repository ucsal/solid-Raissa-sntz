package br.com.ucsal.olimpiadas;

import java.util.List;
import java.util.Scanner;

public class TentativaService {

		    private List<Tentativa> tentativas;
		    private long proximaTentativaId;
		    private CalculadoraNota calculadoraNota;
		    private Scanner in;

		    public TentativaService(List<Tentativa> tentativas, long proximaTentativaId,
		                            CalculadoraNota calculadoraNota, Scanner in) {
		        this.tentativas = tentativas;
		        this.proximaTentativaId = proximaTentativaId;
		        this.calculadoraNota = calculadoraNota;
		        this.in = in;
		    }

		    public void aplicarProva(Long participanteId, Long provaId, List<Questao> questoesDaProva) {

		        Tentativa tentativa = new Tentativa();
		        tentativa.setId(proximaTentativaId++);
		        tentativa.setParticipanteId(participanteId);
		        tentativa.setProvaId(provaId);

		        System.out.println("\n--- Início da Prova ---");

		        for (Questao q : questoesDaProva) {

		            System.out.println("\nQuestão #" + q.getId());
		            System.out.println(q.getEnunciado());

		            System.out.println("Posição inicial:");

		            for (String alt : q.getAlternativas()) {
		                System.out.println(alt);
		            }

		            System.out.print("Sua resposta (A–E): ");

		            char marcada;

		            try {
		                marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
		            } catch (Exception e) {
		                System.out.println("resposta inválida (marcando como errada)");
		                marcada = 'X';
		            }

		            Resposta r = new Resposta();
		            r.setQuestaoId(q.getId());
		            r.setAlternativaMarcada(marcada);
		            r.setCorreta(q.isRespostaCorreta(marcada));

		            tentativa.getRespostas().add(r);
		        }

		        tentativas.add(tentativa);

		        int nota = calculadoraNota.calcular(tentativa);

		        System.out.println("\n--- Fim da Prova ---");
		        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
		    }

		    public void listarTentativas() {

		        System.out.println("\n--- Tentativas ---");

		        for (Tentativa t : tentativas) {

		            int nota = calculadoraNota.calcular(t);

		            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
		                    t.getId(),
		                    t.getParticipanteId(),
		                    t.getProvaId(),
		                    nota,
		                    t.getRespostas().size());
		        }
		    }
		}