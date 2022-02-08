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

### Estrutura de arquivos

A estrutura do app, foi dividida em camadas, onde temos as pastas

+ common
+ data
+ di
+ domain
+ presentation

A pasta common, possuí os arquivos comuns utilizados no app, como:

+ Constantes;
+ WraperClass para resposta da api;
+ enumClass para a barra de pesquisa;
+ Funções de conversão para cores;

A pasta data, possui a pasta remote, que é responsável por tudo relacionado a api, e a pasta repository, onde contem a implementação do Repositório
A pasta di, possui o módulo de injeção de dependencia para o Hilt
A pasta domain, contem a pasta model, com as datas class de dados que utilizamos, após a chama da api ser realizada, a pasta repository, com a interface do repositorio, facilitando assim a criação de testes, e a pasta de use_cases, que contem a lógica para cada caso utilizado neste app, no caso são get_pokemon, get_pokemons_by_type e get_pokemons_details.
A pasta presentation, contem todo o conteudo necessário para criação e exibição das telas, contendo informações de navegação, componentes de cada tela. 
