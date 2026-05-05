package com.igoroliveira.observer;

public class Main {
    public static void main(String[] args) {
        AcaoBolsa petr4 = new AcaoBolsa("PETR4", 38.50);

        AlertaInvestidor joao = new AlertaInvestidor("Joao");
        AlertaInvestidor maria = new AlertaInvestidor("Maria");
        PainelCotacoes painel = new PainelCotacoes(5.0);

        petr4.adicionarObservador(joao);
        petr4.adicionarObservador(maria);
        petr4.adicionarObservador(painel);

        System.out.println("=== Bolsa de Valores - PETR4 ===");
        petr4.setPreco(40.00);
        petr4.setPreco(36.00);
        petr4.setPreco(36.00); // sem alteracao, nao notifica
        petr4.setPreco(42.00);

        System.out.println("\nMaria se desinscreve...");
        petr4.removerObservador(maria);
        petr4.setPreco(39.00);
    }
}
