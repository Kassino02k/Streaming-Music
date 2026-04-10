public class InterfaceUsuario {
    public static void exibirMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===\n");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }
    public static void exibirMenuBuscarMusica() {
        System.out.println("\n=== BUSCAR MÚSICA ===\n");
        System.out.println("1. Buscar música por título");
        System.out.println("2. Buscar música por artista");
        System.out.println("3. Buscar música por gênero");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
    }
    public static void exibirMenuGerenciarPlaylists() {
        System.out.println("\n=== GERENCIAR PLAYLISTS ===\n");
        System.out.println("1. Listar minhas playlists");
        System.out.println("2. Adicionar música a uma playlist");
        System.out.println("3. Remover música de uma playlist");
        System.out.println("4. Exibir detalhes de uma playlist");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
    }
    public static int validarOpcao(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
