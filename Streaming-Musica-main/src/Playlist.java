import java.util.ArrayList;

public class Playlist {
    String nome;
    ArrayList<Musica> musicas = new ArrayList<>();
    void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }
    void removerMusica(int indice) {
        musicas.remove(indice);
    }
    void listasMusicas() {
        for (int i = 0; i < musicas.size(); i++) {
            musicas.get(i).exibir();
        }
    }
    int getDuracaoTotal() {
        int duracaoTotal = 0;
        for (int i = 0; i < musicas.size(); i++) {
            duracaoTotal += musicas.get(i).duracaoSegundos;
        }
        return  duracaoTotal;
    }
    String getDuracaoTotalFormatada() {
        int duracaoTotal = this.getDuracaoTotal();
        int min = duracaoTotal / 60;
        int seg = duracaoTotal % 60;
        return String.format("%d:%02d", min, seg);
    }
    int getQuantidadeDeMusicas() {
        return musicas.size();
    }
}
