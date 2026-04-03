package br.com.ucsal.olimpiadas;

import java.util.List;

public class ParticipanteService {

    private List<Participante> participantes;
    private long proximoId;

    public ParticipanteService(List<Participante> participantes, long proximoId) {
        this.participantes = participantes;
        this.proximoId = proximoId;
    }

    public Participante cadastrar(String nome, String email) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        Participante p = new Participante();

        p.setId(proximoId++);
        p.setNome(nome);
        p.setEmail(email);

        participantes.add(p);

        return p;
    }
}
