package MINI_SENHA;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Dica {
    private final List<Pino> dicas = new ArrayList<>();
    private int linha = 0;
    private int coluna = 0;
    JPanel pnDicas = new JPanel();


    public JPanel getDicasInicial() {
        pnDicas.setLayout(new GridLayout(8, 2, 0, 4));

        for (int i = 0; i < 16; i++) {
            dicas.add(PinoColorido.criaPinoColorido("GRAY"));
        }


        for (Pino p : dicas) {
            pnDicas.add(p);
        }

        return pnDicas;
    }

    public JPanel getDicas(int white, int black) {

        for (int i = 0; i < white; i++) {
            altera(i);
            dicas.add(coluna, PinoPB.criaPinoBranco());
        }

        for (int i = 0; i < black; i++) {
            altera(i);
            dicas.add(coluna, PinoPB.criaPinoPreto());
        }

        int qnt = black + white / 4;
        if (qnt != 4) {
            for (int i = 0; i < qnt; i++) {
                altera(i);
                dicas.add(coluna, PinoColorido.criaPinoColorido("GRAY"));
            }
        }

        pnDicas.setLayout(new GridLayout(8, 2, 0, 4));
        //Preenche o painel de dicas
        for (Pino p : dicas) {
            pnDicas.add(p);
        }

        linha++;
        return pnDicas;
    }

    private void altera(int parametro) {
        //vai para a linha de baixo e volta para a coluna inicial

        if (parametro % 2 == 0) {
            linha++;
            coluna = 0;
        } else {
            coluna++;
        }

    }
}
