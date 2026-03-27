import java.util.ArrayList;

public class Usuario {
    String nome;
    ArrayList<Playlist> playlists = new ArrayList<>();
    void criarPlaylist(String nome) {
        Playlist playlist = new Playlist();
        playlist.nome = (nome);
        playlists.add(playlist);
    }
    Playlist getPlaylist(int indice) {
        return playlists.get(indice);
    }
    void listarPlaylists() {
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println(i + 1 + ". " + playlists.get(i).nome);
        }
    }
}
