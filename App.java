package MINI_SENHA;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import javax.swing.*;

//Validar comparação das respotas
//Tornar desabilitado os próximos botões para habilitar apenas quando jogas

public class App {

    //Qual a diferença de manusear o frame ou o contentPane
    private final JFrame frame = new JFrame("Senha");
    private final Container contentPane = frame.getContentPane();

    private final List<Pino> resposta = new ArrayList<>();
    private final Pino[][] respostaJogador = new Pino[4][4]; //Matriz para alteração futura por indice e linhas

    public String nomesJogadores;
    private int tentativas = 0;
    private int controle1 = 0;
    private Dica dica = new Dica();

    public void jogar(ActionEvent jg) {
        contentPane.removeAll();
        frame.setSize(350, 380);
        getPinos();
        JButton verificar = new JButton("OK");
        contentPane.add(verificar);
        verificar.addActionListener(this::submeter);

    }

    //Teste com a resposta para o usuário
    public void testar(ActionEvent ts) {
        contentPane.removeAll();
        frame.setSize(350, 380);
        getResposta();
        getPinos();

    }

    public void criaJanelaPrincipal() {
        JLabel insiraNome = new JLabel("Insira seu nome");
        JLabel texto = new JLabel("Escolha o modo de jogo: ");
        JTextField nomeJogador = new JTextField(5);
        this.nomesJogadores = nomeJogador.getText();
        JButton jogar = new JButton("Jogar");
        JButton testar = new JButton("Teste");


        contentPane.setLayout(new FlowLayout());

        //Adiocionando elemento na tela
        contentPane.add(texto);
        contentPane.add(jogar);
        contentPane.add(testar);
        contentPane.add(insiraNome);
        contentPane.add(nomeJogador);

        jogar.addActionListener(this::jogar);
        testar.addActionListener(this::testar);

        //Cuida da disposição dos elementos - JANELA
        //BorderLayout para definir uma melhor janela
        frame.setSize(220, 120);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void getPinos() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                respostaJogador[i][j] = PinoColorido.criaPinoColorido("GRAY");
            }
        }

        JPanel pnCores = new JPanel();
        pnCores.setLayout(new GridLayout(4, 4, 5, 35));


        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                pnCores.add(respostaJogador[i][j]);

            }
        }


        contentPane.add(dica.getDicasInicial());
        contentPane.add(pnCores);
        frame.revalidate();
    }

    public void getResposta() {
        //Array de cores
        JTextField respostaTela = new JTextField("Resposta: ");
        respostaTela.setEditable(false);

        ArrayList<String> cores = new ArrayList<>();
        cores.add("RED");
        cores.add("BLUE");
        cores.add("GREEN");
        cores.add("YELLOW");
        cores.add("PINK");
        cores.add("MAGENTA");
        cores.add("ORANGE");

        //GERAR SENHA ALEATÓRIA - TORNAR NÃO EDITAVEL
        for (int i = 0; i < 4; i++) {
            int n = new Random().nextInt(0, 4);
            resposta.add(PinoColorido.criaPinoColorido(cores.get(n)));
            cores.remove(n);
        }
        contentPane.add(respostaTela);

        //Adiciona o componente na tela
        for (Pino p : resposta) {
            contentPane.add(p).setEnabled(false);
        }

        JButton verificar = new JButton("OK");
        contentPane.add(verificar);
        verificar.addActionListener(this::submeter);


    }

    public void submeter(ActionEvent vr) {
        tentativas++;
        if (tentativas == 4) {
            JOptionPane.showMessageDialog(frame, "FIM DO JOGO! DERROTA!");
        }

        // ARRUMAR DISPONIBBILIZAÇÃO DAS DICAS!!
        int acertouCor = 0, acertouCorLugarCerto = 0;


        //Colocar para rodar com debug
            for (int j = 0; j < resposta.size(); j++) { // Controla largura
                Cor res = resposta.get(j).getCor();
                for(int k = 0; k < 4; k++) { //Controle coluna
                    Cor comp = respostaJogador[controle1][k].getCor();
                    if (res.equals(comp) && k == j) {
                        acertouCorLugarCerto++;
                        break;
                    } else if (res.equals(comp) &&  k != j) {
                        acertouCor++;
                        break;
                    }
                }
            }


        //Quatro pinos para cada pino de resposta - Branco (existe mas posição incorreta), Preto (posição correta), vazio (Não existe na resposta)


        if (acertouCorLugarCerto == 4) {
            JOptionPane.showMessageDialog(frame, nomesJogadores +", VOCÊ GANHOU COM APENAS "+ tentativas+" TENTATIVAS!");

        }

        controle1++;
        if(acertouCor == 0 && acertouCorLugarCerto == 0) {
            System.exit(0);
        }
        dica.getDicas(acertouCor, acertouCorLugarCerto);
        frame.revalidate();

    }

    //SUBSTITUIDO POR CLASSE
    public void getDicas(int white, int black) {
//        JPanel pnDicas = new JPanel();
//        // arrumar para mostrar caixas ao invés de fileira
//        dicas.clear();
//        pnDicas.setLayout(new GridLayout(8, 2, 0, 4));
//        if (white == 0 && black == 0) {
//            for (int k = 0; k < 16; k++) {
//                dicas.add(PinoPB.criaPinoVazio());
//            }
//
//        } else {
//            for (int i = 0; i < white; i++) {
//                dicas.add(PinoPB.criaPinoBranco());
//            }
//
//            for (int j = 0; j < black; j++) {
//                dicas.add(PinoPB.criaPinoPreto());
//
//            }
//
//            //Cria todos os quatro pinos caso ainda não tenha
//            int qnt = white + black;
//            for (int k = qnt; k < 4; k++) {
//                dicas.add(PinoColorido.criaPinoColorido("GRAY"));
//            }
//
//        }
//
//
//        for (Pino d : dicas) {
//            pnDicas.add(d);
//        }
//
//        // Agrupar as cores pretas e brancas
//        return contentPane.add(pnDicas);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                App app = new App();
                app.criaJanelaPrincipal();
            }
        });
    }
}