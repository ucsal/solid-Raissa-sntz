package br.com.ucsal.olimpiadas;

public class CalculadoraNotaSimples implements CalculadoraNota {

    @Override
    public int calcular(Tentativa tentativa) {

        return (int) tentativa.getRespostas()
                .stream()
                .filter(Resposta::isCorreta)
                .count();
    }
}
