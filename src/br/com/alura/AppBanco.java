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

        System.out.println(Thread.currentThread().getName());

    }

}
