# pokedex-egsys

### Uma Pokedex, desenvolvida utilizandoa api PokeApi;
### Nela é possível visualizar

+ Listagem completa dos 898 pokemons;
+ Ao clicar em um pokemon, é possível ver informações referente ao mesmo pokemon;
+ O Floating Action Button, é responsável em exibir um pokemon aleatório;
+ Existem dois Filtros de pesquisa, um apenas para texto, que irá mostrar os pokemons de acordo com o texto digitado, ou uma lista vazia;
+ O outro filtro, utilizando um DropDown Menu, você escolhe o tipo de pokemon, e o app mostrará todos os pokemons referente aquele tipo especifico;

### FrameWorks Utilizados

+ Dagger Hilt - Para injeções de dependencia;
+ Retrofit - Para chamadas de API
+ Coroutines - Para trabalhos Assíncronos
+ Coil - Para tratamento de imagens
+ JetPack Compose - Para criação da UI

### Fluxo do App

+ Ao iniciar, o app começa em uma splash screen, e depois irá para a home, onde já listará todos os pokemons;
+ Clicando em um pokemon especifico, o app irá para a tela de detalhes do pokemon, onde exibirá suas informações;
+ O FAB é responsável em sortear um número de 0 a 898 e mostrar o pokemon correspondente a esse ID
