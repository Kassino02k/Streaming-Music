import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StreamingMusica {

    // ArrayLists para armazenar os dados das músicas
    static ArrayList<Musica> musicas = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        adicionarMusicasTeste();
        adicionarUsuariosTeste();
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }
    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar música por título");
        System.out.println("4. Buscar músicas por artista");
        System.out.println("5. Buscar músicas por gênero");
        System.out.println("6. Exibir estatísticas");
        System.out.println("7. Cadastrar usuário");
        System.out.println("8. Listar usuários cadastrados");
        System.out.println("9. Criar Playlist");
        System.out.println("10. Listar Playlists");
        System.out.println("11. Adicionar Música em uma Playlist");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }
    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarMusica();
                break;
            case 2:
                listarMusicas();
                break;
            case 3:
                buscarPorTitulo();
                break;
            case 4:
                buscarPorArtista();
                break;
            case 5:
                buscarPorGenero();
                break;
            case 6:
                exibirEstatisticas();
                break;
            case 7:
                cadastrarUsuario();
                break;
            case 8:
                listarUsuariosCadastrados();
                break;
            case 9:
                criarPlaylist();
                break;
            case 10:
                listPlaylistsPorUsuario();
                break;
            case 11:
                adicionarMusicaPlaylist();
                break;
            default:
                break;
        }
    }
    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");
        System.out.println("Digite o titulo da música:");
        Musica musica = new Musica();
        musica.titulo = scanner.nextLine();
        if (musica.titulo.isEmpty()) {
            System.out.println("Titulo não pode estar em branco");
            return;
        }
        System.out.println("Digite o artista:");
        musica.artista = scanner.nextLine();
        if (musica.artista.isEmpty()) {
            System.out.println("Artista não pode estar em branco");
            return;
        }
        System.out.println("Digite a duração da música:");
        musica.duracaoSegundos = scanner.nextInt();
        scanner.nextLine();
        if(musica.duracaoSegundos <= 0) {
            System.out.println("Duração da música é inválida");
            return;
        }
        System.out.println("Digite o gênero da musica:");
        musica.genero = scanner.nextLine();
        if (musica.genero.isEmpty()) {
            System.out.println("Gênero da música é inválido");
            return;
        }
        // 7. Adicionar no ArrayList
        musicas.add(musica);
        // 8. Exibir mensagem de sucesso
        System.out.println("Musica cadastrada com sucesso!");
    }
    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");
        if(musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada");
            return;
        }
        for (int i = 0; i < musicas.size(); i++) {
            musicas.get(i).exibir();
        }
    }
    public static void buscarPorTitulo() {
        System.out.println("\n--- BUSCAR POR TÍTULO ---\n");
        System.out.print("Digite o título: ");
        String busca = scanner.nextLine().toLowerCase();
        boolean encontrou = false;
        for (int i = 0; i < musicas.size(); i++) {
            encontrou = musicas.get(i).contemTitulo(busca);
            if (encontrou) {
                System.out.println("Musicas encontradas\n");
                musicas.get(i).exibir();
            }
        }
        if(encontrou) {
            System.out.println("Fim da busca!");
        } else {
            System.out.println("Musica não encontrada!");
            System.out.println("Fim da busca!");
        }
    }
    public static void buscarPorArtista() {
        System.out.println("\n--- BUSCAR POR ARTISTA ---\n");
        System.out.println("Digite o artista: ");
        String busca = scanner.nextLine().toLowerCase();
        boolean encontrou = false;
        for (int i = 0; i < musicas.size(); i++) {
            encontrou = musicas.get(i).contemArtista(busca);
            if (encontrou) {
                System.out.println("Musicas encontradas\n");
                musicas.get(i).exibir();
            }
        }
        if(encontrou) {
            System.out.println("Fim da busca!");
        } else {
            System.out.println("Musica não encontrada!");
            System.out.println("Fim da busca!");
        }
    }
    public static void buscarPorGenero() {
        System.out.println("\n--- BUSCAR POR GÊNERO ---\n");
        System.out.println("Digite o gênero: ");
        String busca = scanner.nextLine().toLowerCase();
        boolean encontrou = false;
        for (int i = 0; i < musicas.size(); i++) {
            encontrou = musicas.get(i).contemGenero(busca);
            if (encontrou) {
                System.out.println("Musicas encontrada\n");
                musicas.get(i).exibir();
            }
        }
        if(encontrou) {
            System.out.println("Fim da busca!");
            return;
        }
        System.out.println("Musica não encontrada!");
        System.out.println("Fim da busca!");
    }
    public static void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===\n");
        int totalMusicas = musicas.size();
        for(int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            for(int j = 0; j < usuario.playlists.size(); j++) {
                Playlist playlist = usuario.playlists.get(j);
                totalMusicas += playlist.musicas.size();
            }
        }
        System.out.println("Total de músicas: " + totalMusicas);
        int duracaoTotal = 0;
        for (int i = 0;i < musicas.size(); i++) {
            duracaoTotal += musicas.get(i).duracaoSegundos;
        }
        for (int i = 0;i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            for (int j = 0; j < usuario.playlists.size(); j++) {
                Playlist playlist = usuario.playlists.get(j);
                duracaoTotal += playlist.getDuracaoTotal();
            }
        }
        System.out.println("Duração total: " + formatarDuracao(duracaoTotal));
        System.out.println("Duração média: " + formatarDuracao(duracaoTotal / totalMusicas));
        Map<String, Integer> generosMaisCadastrado = new HashMap<>();
        generosMaisCadastrado.put(musicas.getFirst().genero, 1);
        for (int i = 1; i < musicas.size(); i++) {
            Musica musica = musicas.get(i);
            if(generosMaisCadastrado.containsKey(musica.genero)) {
                generosMaisCadastrado.put(musica.genero, generosMaisCadastrado.get(musica.genero) + 1);
            } else {
                generosMaisCadastrado.put(musica.genero, 1);
            }
        }
        for (int i = 0; i < usuarios.size(); i ++) {
            Usuario usuario = usuarios.get(i);
            for(int j = 0; j < usuario.playlists.size(); j++) {
                Playlist playlist = usuario.playlists.get(j);
                for(int m = 0; m < playlist.musicas.size(); m++) {
                    Musica musica = musicas.get(m);
                    if(generosMaisCadastrado.containsKey(musica.genero)) {
                        generosMaisCadastrado.put(musica.genero, generosMaisCadastrado.get(musica.genero) + 1);
                    } else {
                        generosMaisCadastrado.put(musica.genero, 1);
                    }
                }
            }
        }
        String generoMaisCadastrado = null;
        int maisVezesCadastrado = 0;
        for(Map.Entry<String, Integer> entry : generosMaisCadastrado.entrySet()) {
            if(entry.getValue() > maisVezesCadastrado) {
                maisVezesCadastrado = entry.getValue();
                generoMaisCadastrado = entry.getKey();
            }
        }
        System.out.println("Gênero mais cadastrado: " + generoMaisCadastrado + " (" + maisVezesCadastrado + ") músicas");
    }
    public static void cadastrarUsuario() {
        System.out.println("\n--- CADASTRAR USUÁRIO ---\n");
        System.out.println("Digite o nome do usuário: ");
        Usuario usuario = new Usuario();
        usuario.nome = scanner.nextLine();
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }
    public static void listarUsuariosCadastrados() {
        System.out.println("\n--- USUÁRIOS CADASTRADOS ---\n");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(i + 1 + ". " + usuarios.get(i).nome);
        }
    }
    public static Usuario procurarUsuario(String nome) {
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).nome.equalsIgnoreCase(nome)) {
                return usuarios.get(i);
            }
        }
        System.out.println("Usuário não encontrado");
        return null;
    }
    public static void criarPlaylist() {
        System.out.println("\n--- CRIAR PLAYLIST ---\n");
        System.out.println("Digite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        Usuario usuario = procurarUsuario(nomeUsuario);
        if(usuario == null) {
            System.out.println("Usuário não encontrado");
            return;
        }
        System.out.println("Digite o nome do Playlist: ");
        String nomePlaylist = scanner.nextLine();
        usuario.criarPlaylist(nomePlaylist);
        System.out.println("Playlist " + nomePlaylist + "criada com sucesso.");
    }
    public static void listPlaylistsPorUsuario() {
        System.out.println("\n--- LISTAR PLAYLISTS ---\n");
        System.out.println("Digite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        Usuario usuario = procurarUsuario(nomeUsuario);
        if(usuario == null) {
            System.out.println("Usuário não encontrado");
            return;
        }
        usuario.listarPlaylists();
    }
    public static String formatarDuracao(int duracaoSegundos) {
        int min = duracaoSegundos / 60;
        int seg = duracaoSegundos % 60;
        return String.format("%d:%02d", min, seg);
    }
    public static void adicionarMusicaPlaylist() {
        System.out.println("\n--- ADICIONAR MÚSICA EM UMA PLAYLIST ---\n");
        System.out.println("Digite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        Usuario usuario = procurarUsuario(nomeUsuario);
        if(usuario == null) {
            System.out.println("Usuário não encontrado");
            return;
        }
        usuario.listarPlaylists();
        System.out.println("Digite o indice da Playlist ao qual vo^ce deseja adicionar uma música: ");
        int indicePlaylist = scanner.nextInt() - 1;
        scanner.nextLine();
        Playlist playlist = usuario.getPlaylist(indicePlaylist);
        Musica musica = new Musica();
        System.out.println("Digite o titulo da música:");
        musica.titulo = scanner.nextLine();
        if (musica.titulo.isEmpty()) {
            System.out.println("Titulo não pode estar em branco");
            return;
        }
        System.out.println("Digite o artista:");
        musica.artista = scanner.nextLine();
        if (musica.artista.isEmpty()) {
            System.out.println("Artista não pode estar em branco");
            return;
        }
        System.out.println("Digite a duração da música:");
        musica.duracaoSegundos = scanner.nextInt();
        scanner.nextLine();
        if(musica.duracaoSegundos <= 0) {
            System.out.println("Duração da música é inválida");
            return;
        }
        System.out.println("Digite o gênero da musica:");
        musica.genero = scanner.nextLine();
        if (musica.genero.isEmpty()) {
            System.out.println("Gênero da música é inválido");
            return;
        }
        playlist.adicionarMusica(musica);
        System.out.println(musica.titulo + " criada com sucesso!");
    }
    public static void adicionarMusicasTeste() {
        Musica musicaTeste1 = new Musica();
        musicaTeste1.titulo = "Bohemian Rhapsody";
        musicaTeste1.artista = "Queen";
        musicaTeste1.duracaoSegundos = 354;
        musicaTeste1.genero = "Rock";
        Musica musicaTeste2 = new Musica();
        musicaTeste2.titulo = "Billie Jean";
        musicaTeste2.artista = "Michael Jackson";
        musicaTeste2.duracaoSegundos = 293;
        musicaTeste2.genero = "Pop";
        Musica musicaTeste3 = new Musica();
        musicaTeste3.titulo = "In the Morning";
        musicaTeste3.artista = "Paramore";
        musicaTeste3.duracaoSegundos = 222;
        musicaTeste3.genero = "Rock";
        Musica musicaTeste4 = new Musica();
        musicaTeste4.titulo = "The Only Exception";
        musicaTeste4.artista = "Paramore";
        musicaTeste4.duracaoSegundos = 332;
        musicaTeste4.genero = "Rock";
        Musica musicaTeste5 = new Musica();
        musicaTeste5.titulo = "Gunslinger";
        musicaTeste5.artista = "Avenged Sevenfold";
        musicaTeste5.duracaoSegundos = 666;
        musicaTeste5.genero = "Heavy Metal";
        musicas.add(musicaTeste1);
        musicas.add(musicaTeste2);
        musicas.add(musicaTeste3);
        musicas.add(musicaTeste4);
        musicas.add(musicaTeste5);
    }
    public static void adicionarUsuariosTeste() {
        Usuario usuario1 = new Usuario();
        usuario1.nome = "Gian";
        Usuario usuario2 = new Usuario();
        usuario2.nome = "Cassiano";
        Usuario usuario3 = new Usuario();
        usuario3.nome = "Vinicius";
        Usuario usuario4 = new Usuario();
        usuario4.nome = "Gustavo";
        Usuario usuario5 = new Usuario();
        usuario5.nome = "Yuri viado";
        Usuario usuario6 = new Usuario();
        usuario6.nome = "Ale";
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario4);
        usuarios.add(usuario5);
        usuarios.add(usuario6);
    }
}
