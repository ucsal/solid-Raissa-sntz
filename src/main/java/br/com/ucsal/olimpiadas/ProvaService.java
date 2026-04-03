package br.com.ucsal.olimpiadas;

import java.util.List;

public class ProvaService {

    private List<Prova> provas;
    private long proximoId;

    public ProvaService(List<Prova> provas, long proximoId) {
        this.provas = provas;
        this.proximoId = proximoId;
    }

    public Prova cadastrar(String titulo) {
        Prova p = new Prova();
        p.setId(proximoId++);
        p.setTitulo(titulo);
        provas.add(p);
        return p;
    }
}