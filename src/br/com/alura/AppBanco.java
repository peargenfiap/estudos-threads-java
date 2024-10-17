package br.com.alura;

import java.math.BigDecimal;

public class AppBanco {

    public static void main(String[] args) {
        var cliente = new Cliente("João");
        var conta = new Conta(cliente,  new BigDecimal("150"));

        var operacaoSaque = new OperacaoSaque(conta, new BigDecimal("150"));

        // Para realizar operações simultâneas, é necessário utilziar Threads.

        // Criação de uma Thread
        Thread saqueDoJoao = new Thread(operacaoSaque);
        Thread saqueDaMaria = new Thread(operacaoSaque);

        // Inicializando as Threads
        saqueDoJoao.start();
        saqueDaMaria.start();

        /*
         * Primeiro, a main começa executar e chega até esse ponto.
         * Quando damos o Join, a main fica aguardando a finalização das Threads.
         * Quando as Threads terminam, a main continua a execução.
         */
        try {
            // Aguardando a finalização das Threads para exibir o saldo final
            saqueDoJoao.join();
            saqueDaMaria.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName());
        System.out.println("Saldo final: " + conta.getSaldo());

    }

}
