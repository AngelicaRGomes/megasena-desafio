package br.com.vainaweb.backendt3.megasena;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Megasena {
    public static void main(String[] args) {
        try {
            int[] numerosEscolhidos = escolhaNumeros();
            System.out.println("Seus números da sorte são:");
            imprimirNumeros(numerosEscolhidos);

            int[] numerosSorteados = sorteioMegaSena();
            System.out.println("\nNúmeros sorteados na Mega Sena:");
            imprimirNumeros(numerosSorteados);

            contemNumero(numerosEscolhidos, numerosSorteados);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e);
        }
    }

    public static int[] escolhaNumeros() {
        Scanner scanner = new Scanner(System.in);
        int[] numerosEscolhidos = new int[7];

        System.out.println("Digite 7 números de 0 a 100:");

        for (int i = 0; i < 7; i++) {
            boolean entradaValida = false;

            while (!entradaValida) {
                System.out.print("Número " + (i + 1) + ": ");

                if (scanner.hasNextInt()) {
                    int numeroInserido = scanner.nextInt();
                    if (contemNumero(numerosEscolhidos, numeroInserido)) {
                        System.out.println("Número já escolhido. Por favor, escolha outro número.");
                    } else {
                        numerosEscolhidos[i] = numeroInserido;
                        entradaValida = true;
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                    scanner.next();
                    scanner.close();
                }
            }
        }
        Arrays.sort(numerosEscolhidos);
        return numerosEscolhidos;
    }

    public static int[] sorteioMegaSena() {
        int[] numerosSorteados = new int[7];
        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            int numeroSorteado;

            do {
                numeroSorteado = random.nextInt(101);
            } while (contemNumero(numerosSorteados, numeroSorteado));

            numerosSorteados[i] = numeroSorteado;
        }
        Arrays.sort(numerosSorteados);
        return numerosSorteados;
    }

    public static boolean contemNumero(int[] array, int numero) {
        for (int i : array) {
            if (i == numero) {
                return true;
            }
        }
        return false;
    }

    public static void contemNumero(int[] numerosEscolhidos, int[] numerosSorteados) {
        int contador = 0;
        for (int i : numerosEscolhidos) {
            for (int j : numerosSorteados) {
                if (i == j) {
                    contador++;
                }
            }
        }
        distribuirPremios(contador);
    }

    public static void distribuirPremios(int contador) {
        Map<Integer, String> premios = new HashMap<>();
        premios.put(5, "\nVocê acertou " + contador + " dezenas e ganhou 10 mil reais. Parabéns!");
        premios.put(6, "\nVocê acertou " + contador + " dezenas e ganhou 50 mil reais. Parabéns!");
        premios.put(7, "\nVocê acertou " + contador + " dezenas e ganhou 200 mil reais. Parabéns!");
        
        System.out.println(premios.getOrDefault(contador, "\nVocê perdeu! Acertou "
                + contador + " dezena(s). Ganhou nadinha..."));
    }

    public static void imprimirNumeros(int[] numeros) {
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
    }
}