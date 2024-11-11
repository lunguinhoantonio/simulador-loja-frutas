import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static final int QUANT_FRUTAS = 10;
    public static double valorTotal = 0.0;
    public static void divConsole() {
        System.out.println("-----------------------------------------");
    }

    public static void exibirMenu(double[] precoFrutas, String[] frutas) {
        System.out.println("0. Nada, tô só olhando.");
        System.out.println("ID |  Frutas   | Preço");
        divConsole();
        for (int i = 0; i < QUANT_FRUTAS; i++) {
            System.out.printf("%-3d| %-10s| R$%4.2f\n", i + 1, frutas[i], precoFrutas[i]);
        }
        divConsole();
    }

    public static void exibirNF(ArrayList<Double> precosFrutaNF, ArrayList<Integer> quantFrutasNF, ArrayList<String> frutasNF, double[] precoFrutas) {
            System.out.println("              NOTA FISCAL              ");
            divConsole();
            System.out.printf("%-10s %-8s %-6s %s\n", "Fruta", "Valor Unit", "Quant", "Preço (R$)");
            divConsole();
        for (int i = 0; i < precosFrutaNF.size(); i++) {
            System.out.printf("%-10s R$%-10.2f %-8d R$%-2.2f\n", frutasNF.get(i), precoFrutas[i], quantFrutasNF.get(i), precosFrutaNF.get(i));
            valorTotal += precosFrutaNF.get(i);
        }
        divConsole();
        System.out.printf("TOTAL: R$%.2f\n", valorTotal);
        divConsole();
    }
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> precosFrutaNF = new ArrayList<>();
        ArrayList<Integer> quantFrutasNF = new ArrayList<>();
        ArrayList<String> frutasNF = new ArrayList<>();

        int frutaEscolhida;
        int quantFrutas;
        char continuar = 'S';
        String[] frutas = {"Abacaxi", "Banana", "Kiwi", "Laranja", "Manga", "Goiaba", "Maçã", "Morango", "Pera", "Maracujá"};
        double[] precoFrutas = {4.0, 0.8, 2.8, 1.2, 2.0, 2.50, 1.5, 3.5, 2.0, 1.85};

        System.out.println("Bem vindo à loja de frutas!\nQual fruta deseja?");

        do {
            exibirMenu(precoFrutas, frutas);
            System.out.print("Resposta: ");
            frutaEscolhida = scanner.nextInt();
            if (frutaEscolhida == 0) {
                System.out.println("Até mais tarde! :D");
                System.exit(0);
            }
            if (frutaEscolhida < 0 || frutaEscolhida > QUANT_FRUTAS) {
                System.out.printf("Escolha uma opção entre 0 e %d!", QUANT_FRUTAS);
                continue;
            }
            do {
                System.out.print("Digite a quantidade (limitada a 1000): ");
                quantFrutas = scanner.nextInt();
                if (quantFrutas < 1 || quantFrutas > 1000) {
                    System.out.println("Você não pode colocar quantidades negativas ou absurdas!");
                }
            } while (quantFrutas < 1 || quantFrutas > 1000);
            divConsole();
            System.out.println("Pedido: " + frutas[frutaEscolhida - 1]);
            frutasNF.add(frutas[frutaEscolhida - 1]);
            System.out.println("Quantidade: " + quantFrutas);
            quantFrutasNF.add(quantFrutas);
            double soma = precoFrutas[frutaEscolhida - 1] * quantFrutas;
            precosFrutaNF.add(soma);
            System.out.printf("O valor do pedido vai ser de R$%.2f\n", soma);
            System.out.print("Vai querer mais alguma coisa? [S/N]: ");
            continuar = scanner.next().trim().toUpperCase().charAt(0);
            divConsole();
        } while (continuar == 'S');

        exibirNF(precosFrutaNF, quantFrutasNF, frutasNF, precoFrutas);
        int opcMetodoPag;

        do {
            System.out.println("Qual o método de pagamento?");
            System.out.println("1. Cartão de crédito/débito");
            System.out.println("2. Dinheiro");
            System.out.println("3. Pix");
            System.out.print("Resposta: ");
            opcMetodoPag = scanner.nextInt();
            divConsole();

            switch (opcMetodoPag) {
                case 1, 3:
                    String msg = "Aguardando pagamento.";
                    for (int i = 0; i < 3; i++) {
                        System.out.println(msg);
                        Thread.sleep(1000);
                        msg += ".";
                    }
                    Thread.sleep(1250);
                    System.out.println("Pagamento aprovado!");
                    Thread.sleep(800);
                    break;
                case 2:
                    double dinheiroTotal = 0.0;
                    double dinheiroEnviado;
                    do {
                        System.out.printf("Valor da compra: R$%.2f\n", valorTotal);
                        System.out.printf("Dinheiro dado: R$%.2f\n", dinheiroTotal);
                        System.out.print("Valor que você dará: R$");
                        dinheiroEnviado = scanner.nextDouble();
                        dinheiroTotal += dinheiroEnviado;
                        if (dinheiroTotal < valorTotal) {
                            System.out.println("O dinheiro que você deu não é suficiente para pagar a compra!");
                            divConsole();
                        } else {
                            System.out.printf("Seu troco é de R$%.2f\n", (float) Math.abs(valorTotal - dinheiroTotal));
                        }
                    } while (dinheiroTotal < valorTotal);
                    break;
                default:
                    System.out.println("Digite um valor válido!");
            }
        } while (opcMetodoPag < 1 || opcMetodoPag > 3);
        System.out.println("Volte sempre! :)");
        scanner.close();
    }
}