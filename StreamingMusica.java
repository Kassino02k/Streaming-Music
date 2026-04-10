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
        adicionarPlaylistsTeste();
        int opcao;
        do {
            InterfaceUsuario.exibirMenuPrincipal();
            opcao = InterfaceUsuario.validarOpcao(scanner.nextLine());
            processarOpcaoMenuPrincipal(opcao);
        } while (opcao != 0);
        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }
    public static void processarOpcaoMenuPrincipal(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarMusica();
                break;
            case 2:
                listarTodasAsMusicas();
                break;
            case 3:
                buscarMusica();
                break;
            case 4:
                criarPlaylist();
                break;
            case 5:
                gerenciarPlaylists();
                break;
            case 6:
                exibirEstatisticas();
                break;
            default:
                System.out.println("\nOpção inválida");
                break;
        }
    }
    public static void cadastrarMusica() {
        System.out.println("\n=== CADASTRAR MÚSICA ===\n");
        System.out.println("Digite o título da música: ");
        String titulo = scanner.nextLine();
        while (titulo.isEmpty()) {
            System.out.println("O título da música não pode estar vazio.");
            System.out.println("\nDigite o título da música: ");
            titulo = scanner.nextLine();
            titulo = titulo.trim();
        }
        System.out.println("Digite o nome do artista: ");
        String artista = scanner.nextLine();
        while (artista.isEmpty()) {
            System.out.println("O nome do artista não pode estar vazio.");
            System.out.println("\nDigite o nome do artista: ");
            artista = scanner.nextLine();
            artista = artista.trim();
        }
        System.out.println("Digite a duração da música em segundos: ");
        int duracaoSegundos = Musica.validarDuracaoSegundos(scanner.nextLine());
        while (duracaoSegundos <= 0 || duracaoSegundos > 3600) {
            System.out.println("Duração da música não pode ser menor do que 0 segundos nem maior do que 3600 segundos (1 hora).");
            System.out.println("\nDigite a duração da música em segundos: ");
            duracaoSegundos = Musica.validarDuracaoSegundos(scanner.nextLine());
        }
        System.out.println("Digite o gênero da musica: ");
        String genero = scanner.nextLine();
        while (genero.isEmpty()) {
            System.out.println("Gênero da música é não pode estar em branco");
            System.out.println("\nDigite o gênero da musica: ");
            genero = scanner.nextLine();
        }
        Musica musica = new Musica(titulo, artista, duracaoSegundos, genero);
        musicas.add(musica);
        System.out.println("Musica cadastrada com sucesso!");
    }
    public static void listarTodasAsMusicas() {
        System.out.println("\n=== MÚSICAS CADASTRADAS ===\n");
        if(musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }
        for (Musica musica : musicas) {
            musica.exibir();
        }
        for (Usuario usuario : usuarios) {
            for(int i = 0; i < usuario.getPlaylists().size(); i++) {
                usuario.getPlaylist(i).listarMusicas();
            }
        }
    }
    public static void buscarMusica() {
        int opcao;
        do {
            InterfaceUsuario.exibirMenuBuscarMusica();
            opcao = InterfaceUsuario.validarOpcao(scanner.nextLine());
            processarOpcaoMenuBuscarMusica(opcao);
        } while (opcao != 0);
    }
    public static void processarOpcaoMenuBuscarMusica(int opcao) {
        switch (opcao) {
            case 1:
                buscarMusicaPorTitulo();
                break;
            case 2:
                buscarMusicaPorArtista();
                break;
            case 3:
                buscarMusicaPorGenero();
                break;
            default:
                System.out.println("\nOpção inválida");
                break;
        }
    }
    public static void buscarMusicaPorTitulo() {
        System.out.println("\n=== BUSCAR MÚSICA POR TÍTULO ===\n");
        System.out.print("Digite o título da música: ");
        String busca = scanner.nextLine().toLowerCase();
        boolean encontrou = false;
        boolean encontrouFlag = false;
        System.out.println("\nMusicas encontradas:\n");
        for (Musica musica : musicas) {
            encontrou = musica.contemTitulo(busca);
            if (encontrou) {
                musica.exibir();
                encontrouFlag = true;
            }
        }
        if (!encontrouFlag) {
            System.out.println("Nenhuma música foi encontrada.\n");
        }
        System.out.println("Fim da busca!");
    }
    public static void buscarMusicaPorArtista() {
        System.out.println("\n=== BUSCAR MÚSICA POR ARTISTA ===\n");
        System.out.println("Digite o nome do artista: ");
        String busca = scanner.nextLine().toLowerCase();
        boolean encontrou = false;
        boolean encontrouFlag = false;
        System.out.println("\nMusicas encontradas:\n");
        for (Musica musica : musicas) {
            encontrou = musica.contemArtista(busca);
            if (encontrou) {
                musica.exibir();
                encontrouFlag = true;
            }
        }
        if (!encontrouFlag) {
            System.out.println("Nenhuma música foi encontrada.\n");
        }
        System.out.println("Fim da busca!");
    }
    public static void buscarMusicaPorGenero() {
        System.out.println("\n=== BUSCAR MÚSICA POR GÊNERO ===\n");
        System.out.println("Digite o gênero: ");
        String busca = scanner.nextLine().toLowerCase();
        boolean encontrou = false;
        boolean encontrouFlag = false;
        System.out.println("\nMusicas encontradas:\n");
        for (Musica musica : musicas) {
            encontrou = musica.contemGenero(busca);
            if (encontrou) {
                musica.exibir();
                encontrouFlag = true;
            }
        }
        if (!encontrouFlag) {
            System.out.println("Nenhuma música foi encontrada.\n");
        }
        System.out.println("Fim da busca!");
    }
    public static void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===\n");
        int totalMusicas = musicas.size();
        for (Usuario usuario : usuarios) {
            for (int i = 0; i < usuario.getPlaylists().size(); i++) {
                Playlist playlist = usuario.getPlaylists().get(i);
                totalMusicas += playlist.getMusicas().size();
            }
        }
        System.out.println("Total de músicas: " + totalMusicas);
        int duracaoTotal = 0;
        for (Musica musica : musicas) {
            duracaoTotal += musica.getDuracaoSegundos();
        }
        for (Usuario usuario : usuarios) {
            for (int i = 0; i < usuario.getPlaylists().size(); i++) {
                Playlist playlist = usuario.getPlaylists().get(i);
                duracaoTotal += playlist.getDuracaoTotal();
            }
        }
        System.out.println("Duração total: " + formatarDuracao(duracaoTotal));
        System.out.println("Duração média: " + formatarDuracao(duracaoTotal / totalMusicas));
        Map<String, Integer> generosMaisCadastrado = new HashMap<>();
        generosMaisCadastrado.put(musicas.getFirst().getGenero(), 1);
        for (int i = 1; i < musicas.size(); i++) {
            Musica musica = musicas.get(i);
            if(generosMaisCadastrado.containsKey(musica.getGenero())) {
                generosMaisCadastrado.put(musica.getGenero(), generosMaisCadastrado.get(musica.getGenero()) + 1);
            } else {
                generosMaisCadastrado.put(musica.getGenero(), 1);
            }
        }
        for (Usuario usuario : usuarios) {
            for (int i = 0; i < usuario.getPlaylists().size(); i++) {
                Playlist playlist = usuario.getPlaylists().get(i);
                for (int j = 0; j < playlist.getMusicas().size(); j++) {
                    Musica musica = musicas.get(j);
                    if (generosMaisCadastrado.containsKey(musica.getGenero())) {
                        generosMaisCadastrado.put(musica.getGenero(), generosMaisCadastrado.get(musica.getGenero()) + 1);
                    } else {
                        generosMaisCadastrado.put(musica.getGenero(), 1);
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
        System.out.println("\n=== CADASTRAR USUÁRIO ===\n");
        System.out.println("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        nome = nome.trim();
        Usuario usuario = new Usuario(nome);
        usuarios.add(usuario);
        System.out.println("Usuário " + usuario.getNome() + " cadastrado com sucesso.");
    }
    public static void listarUsuariosCadastrados() {
        System.out.println("\n=== USUÁRIOS CADASTRADOS ===\n");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(i + 1 + ". " + usuarios.get(i).getNome());
        }
    }
    public static Usuario buscarUsuario(String nome) {
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getNome().equalsIgnoreCase(nome)) {
                return usuarios.get(i);
            }
        }
        System.out.println("Usuário não encontrado\n");
        return null;
    }
    public static Usuario efetuarLogin() {
        System.out.println("Digite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        return buscarUsuario(nomeUsuario);
    }
    public static void gerenciarPlaylists() {
        boolean logged = false;
        Usuario usuario = null;
        do {
            usuario = efetuarLogin();
            if(usuario != null) {
                logged = true;
            }
        } while (!logged);
        if (logged) {
            int opcao;
            do {
                InterfaceUsuario.exibirMenuGerenciarPlaylists();
                opcao = InterfaceUsuario.validarOpcao(scanner.nextLine());
                processarOpcaoMenuGerenciarPlaylists(opcao);
            } while (opcao != 0);
        }
    }
    public static void processarOpcaoMenuGerenciarPlaylists(int opcao) {
        switch(opcao) {
            case 1:
                listarPlaylistsUsuario();
                break;
            case 2:
                try {
                    adicionarMusicaPlaylist();
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            case 3:
                try {
                    removerMusicaPlaylist();
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            case 4:
                try {
                    exibirDetalhesPlalist();
                } catch(Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            default:
                break;
        }
    }
    public static void criarPlaylist() {
        System.out.println("\n=== CRIAR PLAYLIST ===\n");
        boolean logged = false;
        Usuario usuario = null;
        do {
            usuario = efetuarLogin();
            if(usuario != null) {
                logged = true;
            }
        } while (!logged);
        if (logged) {
            System.out.println("Digite o nome da playlist: ");
            String nomePlaylist = scanner.nextLine();
            nomePlaylist = nomePlaylist.trim();
            usuario.criarPlaylist(nomePlaylist);
            System.out.println("Playlist " + nomePlaylist + " criada com sucesso.");
        }
    }
    public static void listarPlaylistsUsuario() {
        System.out.println("\n=== LISTAR PLAYLISTS ===\n");
        System.out.println("Digite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        Usuario usuario = buscarUsuario(nomeUsuario);
        if(usuario == null) {
            System.out.println("Usuário não encontrado");
            return;
        }
        usuario.listarPlaylists();
    }
    public static void adicionarMusicaPlaylist() throws Exception {
        System.out.println("\n=== ADICIONAR MÚSICA EM UMA PLAYLIST ===\n");
        System.out.println("Digite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        Usuario usuario = buscarUsuario(nomeUsuario);
        if(usuario == null) {
            System.out.println("Usuário não encontrado");
            return;
        }
        Playlist playlist = null;
        try {
            usuario.listarPlaylists();
            System.out.println("Digite o índice da Playlist ao qual você deseja adicionar uma música: ");
            int indicePlaylist = InterfaceUsuario.validarOpcao(scanner.nextLine()) - 1;
            playlist = usuario.getPlaylist(indicePlaylist);
            if (playlist == null) {
                throw new IllegalArgumentException("Playlist não encontrada.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("Digite o titulo da música:");
        String titulo = scanner.nextLine();
        if (titulo.isEmpty()) {
            System.out.println("Titulo não pode estar em branco");
            return;
        }
        System.out.println("Digite o artista:");
        String artista = scanner.nextLine();
        if (artista.isEmpty()) {
            System.out.println("Artista não pode estar em branco");
            return;
        }
        System.out.println("Digite a duração da música:");
        int duracaoSegundos = scanner.nextInt();
        scanner.nextLine();
        if(duracaoSegundos <= 0 || duracaoSegundos > 3600) {
            System.out.println("Duração da música é inválida");
            return;
        }
        System.out.println("Digite o gênero da musica:");
        String genero = scanner.nextLine();
        if (genero.isEmpty()) {
            System.out.println("Gênero da música é inválido");
            return;
        }
        Musica musica = new Musica(titulo, artista, duracaoSegundos, genero);
        playlist.adicionarMusica(musica);
        System.out.println(musica.getTitulo() + " adicionada com sucesso.");
    }
    public static void removerMusicaPlaylist() throws Exception {
        System.out.println("\n=== REMOVER MÚSICA EM UMA PLAYLIST ===\n");
        System.out.println("Digite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        Usuario usuario = buscarUsuario(nomeUsuario);
        if(usuario == null) {
            System.out.println("Usuário não encontrado");
            return;
        }
        Playlist playlist = null;
        try {
            usuario.listarPlaylists();
            System.out.println("Digite o índice da Playlist ao qual você deseja remover uma música: ");
            int indicePlaylist = InterfaceUsuario.validarOpcao(scanner.nextLine()) - 1;
            playlist = usuario.getPlaylist(indicePlaylist);
            if (playlist == null) {
                throw new IllegalArgumentException("Playlist não encontrada.");
            }
            for(int i = 0; i < playlist.getMusicas().size(); i++) {
                System.out.println(i + 1 + ". " + playlist.getMusicas().get(i).getTitulo());
            }
            System.out.println("Digite o índice da música que você deseja remover: ");
            int indiceMusica = InterfaceUsuario.validarOpcao(scanner.nextLine()) - 1;
            playlist.removerMusica(indiceMusica);
            System.out.println("Música removida com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    public static void exibirDetalhesPlalist() throws Exception {
        System.out.println("\n=== EXIBIR DETALHES PLAYLIST ===\n");
        System.out.println("Digite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        Usuario usuario = buscarUsuario(nomeUsuario);
        if(usuario == null) {
            System.out.println("Usuário não encontrado");
            return;
        }
        Playlist playlist = null;
        try {
            usuario.listarPlaylists();
            System.out.println("Digite o índice da Playlist que você deseja exibir os detalhes: ");
            int indicePlaylist = InterfaceUsuario.validarOpcao(scanner.nextLine()) - 1;
            playlist = usuario.getPlaylist(indicePlaylist);
            if (playlist == null) {
                throw new IllegalArgumentException("Playlist não encontrada.");
            }
            System.out.println("\n=== " + playlist.getNome().toUpperCase() + "===\n");
            System.out.println("Músicas:");
            ArrayList<Musica> musicas = playlist.getMusicas();
            for(Musica musica : musicas) {
                musica.exibir();
            }
            System.out.println("Duração Total: " + playlist.getDuracaoTotal());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    public static String formatarDuracao(int duracaoSegundos) {
        int min = duracaoSegundos / 60;
        int seg = duracaoSegundos % 60;
        return String.format("%d:%02d", min, seg);
    }
    public static void adicionarMusicasTeste() {
        Musica musicaTeste1 = new Musica("Bohemian Rhapsody", "Queen", 354, "Rock");
        Musica musicaTeste2 = new Musica("Billie Jean", "Michael Jackson", 293, "Pop");
        Musica musicaTeste3 = new Musica("In the Morning", "Paramore", 222, "Rock");
        Musica musicaTeste4 = new Musica("The Only Exception", "Paramore", 332, "Rock");
        Musica musicaTeste5 = new Musica("Gunslinger", "Avenged Sevenfold", 666, "Heavy Metal");
        musicas.add(musicaTeste1);
        musicas.add(musicaTeste2);
        musicas.add(musicaTeste3);
        musicas.add(musicaTeste4);
        musicas.add(musicaTeste5);
    }
    public static void adicionarUsuariosTeste() {
        Usuario usuario1 = new Usuario("Gian");
        Usuario usuario2 = new Usuario("Cassiano");
        Usuario usuario3 = new Usuario("Vinicius");
        Usuario usuario4 = new Usuario("Gustavo");
        Usuario usuario5 = new Usuario("Yuri viado");
        Usuario usuario6 = new Usuario("Ale");
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario4);
        usuarios.add(usuario5);
        usuarios.add(usuario6);
    }
    public static void adicionarPlaylistsTeste() {
        Usuario usuario = usuarios.getFirst();
        usuario.criarPlaylist("Avenged Sevenfold");
        Playlist playlist = usuario.getPlaylist(0);
        playlist.adicionarMusica(musicas.get(4));
    }
}
