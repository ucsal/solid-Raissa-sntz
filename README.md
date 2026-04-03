# Sistema de Olimpíadas 

## Descrição
Sistema em Java para cadastro de participantes, provas, questões e aplicação de provas com cálculo de nota.

## Refatoração e melhorias

* **SRP (Responsabilidade Única):**
  Cada classe possui uma função específica (ex: `ParticipanteService`, `ProvaService`, etc.).

* **OCP (Aberto/Fechado):**
  Uso da interface `CalculadoraNota`, permitindo novos cálculos sem alterar o código existente.

* **DIP (Inversão de Dependência):**
  As dependências são injetadas na classe `App`.

* **DRY:**
  Criação de método reutilizável (`escolherId`) para evitar repetição.

## Estrutura

* **Modelos:** Participante, Prova, Questao, Resposta, Tentativa
* **Serviços:** ParticipanteService, ProvaService, QuestãoService, TentativaService
* **Cálculo:** CalculadoraNota, CalculadoraNotaSimples
* **Main:** App.java

## Restrições

✔ Lógica original mantida
✔ Nenhuma funcionalidade removida
✔ Sem uso de frameworks externos

---
