package br.com.alura;

import java.math.BigDecimal;

public class AppBanco {

    public static void main(String[] args) {
        var cliente = new Cliente("João");
        var conta = new Conta(cliente,  new BigDecimal("150"));

        var operacaoSaque = new OperacaoSaque(conta, new BigDecimal("150"));

        // Saque do João
        operacaoSaque.executa();
        // Saque da Maria
        operacaoSaque.executa();

        // Para realizar operações simultâneas, é necessário utilziar Threads.

    }

}
