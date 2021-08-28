# AppAndroidPosJava
App criado para a matéria de "Desenvolvimento Java Para Dispositivos Móveis" da pós graduação em tecnologia JAVA na UTFPR.

Será composto de 6 entregas:
  - Entrega Parcial 1 até 01/08/2021;
  - Entrega Parcial 2 até 08/08/2021;
  - Entrega Parcial 3 até 15/08/2021;
  - Entrega Parcial 4 até 22/08/2021;
  - Entrega Parcial 5 até 29/08/2021;
  - Entrega Final entre 09/09 a 12/09/2021;

Tema do App:
 - Controle de Posts em Redes Sociais.
  
Objetivo do App:
 - Manter um cadastro de empresas que solicitaram os serviços de um autônomo que gerencia mídias sociais.
  
Deve-se utilizar:
 - O Android Studio 4.2.1 ou superior (apenas versões estáveis);
 - Versão do Gradle específica da versão do Android Studio utilizada;
 - Minimum API Level 16 (minSdkVersion 16 no build.gradle (Module: app), que irá alterar a geração do AndroidManifest.xml);
 - Compile SDK Version para o API Level 29 (Android Q).
  
/*====================================*/

SEMANA 1: 

Crie o projeto de um aplicativo com Nome relacionado ao Tema Escolhido e Aprovado pelo professor.

Neste projeto crie uma Activity que implemente um formulário de cadastro de uma das entidades previstas com as seguintes características:

 - Uso de elementos Textview; 
 - Uso de pelo menos 4 elementos EditText;
 - Uso de 2 elementos Button;
 - Uso de elementos RadioButton (pelo menos 3) com pelo menos um RadioGroup; 
 - Uso de elementos CheckBox (pelo menos 1);
 - Uso de elementos Spinner (pelo menos 1);
 - Um dos Buttons deve ter o rótulo "Limpar" e ao ser clicado limpará os valores dos elementos EditText e desmarcará os RadioButtons e CheckBox, e depois será mostrado um Toast indicando a ação realizada;
 - Um dos Buttons deve ter o rótulo "Salvar" e ao ser clicado recuperará os valores dos elementos EditText, CheckBox e Spinner, e o RadioButton selecionado. Caso algum EditText esteja vazio ou nenhum RadioButton selecionado, deverá ser mostrado uma mensagem de erro em um Toast e o foco de edição voltará para o campo vazio (caso seja possível).

Caso o formulário de cadastro fique maior do que a tela do dispositivo será necessário colocar barra de rolagem, para tal, utilize a classe ScrollView ou HorizontalScrollView.

/*====================================*/

SEMANA 2: 

Faça uma nova versão do projeto submetido na Entrega 1 criando uma nova Activity que listará itens, estes serão objetos de um tipo de Entidade relacionada ao Tema do Projeto. 

Neste projeto além do entregue na versão anterior, deve-se:

 - Criar uma classe de Entidade relacionada ao Tema do Projeto (Pelo menos 4 atributos);
 - Carregar de Arrays do Resource um conjunto de dados (pelos menos 4 tipos de informação) que possibilite a instanciação de objetos da Entidade (Pelo menos 10);
 - Armazenar as Entidades instanciadas em um ArrayList;
 - Criar uma Activity que exiba um componente de listagem de itens ocupando toda tela, pode ser uma ListView ou RecyclerView;
 - Criar um Adapter customizado para exibir os dados de cada Entidade na listagem de Itens;
 - Ao clicar em um item deve-se mostrar uma mensagem em um Toast indicando que o mesmo foi clicado. A mensagem deve conter informações que identifiquem o elemento;
 - A Activity criada de listagem deve ser a principal do Aplicativo (Launcher), para tal no AndroidManifest.xml ela terá mapeada a ação de MAIN e a categoria LAUNCHER. Dica: ao criar uma nova Activity em um projeto já existente marque o item Launcher na tela Assistente de criação (Wizard).

/*====================================*/

SEMANA 3: 

Faça uma nova versão do projeto submetido na Entrega 2 criando uma nova Activity que exibirá os dados de autoria do App, e também efetivará a transição entre as Activities feitas anteriormente. 

Neste projeto além do entregue na versão anterior, deve-se:

 - Crie uma Activity que exibirá os dados de Autoria do App, são eles: Nome do aluno, curso e e-mail, breve descrição do que faz o aplicativo, logo e nome da UTFPR;
 - Altere a Activity de Listagem de dados (Feito na Entrega 2), que agora não irá carregar dados de Arrays do Resource, e sim exibir dados cadastrados pela Activity de Cadastro. Para tal coloque no layout:
   - Button com rótulo Adicionar, que ao ser clicado abrirá a Activity de cadastro esperando um resultado (startActivityForResult);
   - Button com rótulo Sobre, que ao ser clicado abrirá a Activity de Autoria do App (startActivity). 
 - Na Activity de Cadastro (Feito na Entrega 1):
   - Ao clicar no Button "Salvar" deverá ser recuperado os dados da interface, validados e devolvidos a Activity de Listagem com o método setResult e resultado RESULT_OK;
   - Ao executar a ação de Voltar do sistema Android devolva RESULT_CANCELED, para tal inclua o setResult dentro do método que sobrescreve o onBackPressed().
 - Na Activity de Listagem trate o retorno da Activity de Cadastro dentro do método que sobrescreve o onActivityResult. Neste método receba os valores retornados, crie um objeto da entidade (Feita na Entrega 2), adicione ao ArrayList relacionado ao Adapter customizado, e por fim chame o método notifyDataSetChanged() do Adapter que forçará o redesenho dos itens dentro da ListView ou RecyclerView.

/*====================================*/

SEMANA 4: 

Faça uma nova versão do projeto submetido na Entrega 3 substituindo os Buttons por MenuItens em Menus de Opções, incluindo um Menu de Ação contextual, além de botões Ups nas Activities secundárias.

Neste projeto além do entregue na versão anterior, deve-se:

 - Altere a Activity de Listagem:
   - retirando os Buttons e incluindo um menu de opções com:
     - Um MenuItem com o rótulo "Adicionar", um ícone relacionado a esta ação, e com o parâmetro showAsAction com o valor ifRoom; Ao ser clicado deve-se abrir a Activity de cadastro esperando um resultado (startActivityForResult);
     - Um MenuItem com o rótulo "Sobre", sem ícone, que ao ser clicado abrirá a Activity de Autoria do App (startActivity). 
   - incluindo um Menu de Ação Contextual que será aberto quando o usuário manter pressionado um dos itens exibidos (na ListView ou RecyclerView). Este menu deve conter:
     - um MenuItem com o rótulo "Editar", um ícone relacionado a esta ação, e com o parâmetro showAsAction com o valor ifRoom; Ao ser acionado deve-se abrir a Activity de Cadastro passando os dados do Item selecionado (para que o usuário possa alterá-los) e esperando um resultado dela (startActivityForResult);
     - um MenuItem com o rótulo "Excluir", um ícone relacionado a esta ação, e com o parâmetro showAsAction com o valor ifRoom; Ao ser clicado deve-se remover o Item do ArrayList e na sequência chamar o método notifyDataSetChanged() do Adapter, que forçará o redesenho dos itens dentro da ListView ou RecyclerView.
   - modificando o método onActivityResult para que quando se retorne da Activity de Cadastro com sucesso (RESULT_OK) e com os novos valores de um item que sofreu edição, estes possam ser recuperados e atribuídos ao objeto da Entidade correspondente. Não esquecendo de após alterar as Entidades do ArrayList chamar o método notifyDataSetChanged() do Adapter, que forçará o redesenho dos itens dentro da ListView ou RecyclerView.
 - Altere a Activity de Cadastro:
   - permitindo que a mesma seja aberta em modo de edição, onde ela já é aberta com dados de uma Entidade já cadastrada, e o usuário poderá alterar os atributos preenchidos;
   - retirando os Buttons e incluindo um menu de opções com:
     - Um MenuItem com o rótulo "Salvar, um ícone relacionado a esta ação, e com o parâmetro showAsAction com o valor ifRoom; Ao ser clicado deve-se recuperar os dados da interface, validá-los e devolvê-los para a Activity de Listagem com o método setResult e resultado RESULT_OK;
     - Um MenuItem com o rótulo "Limpar, um ícone relacionado a esta ação, e com o parâmetro showAsAction com o valor ifRoom. Ao ser clicado deve-se limpar os valores cadastrados (EditText, CheckBox e RadioButtons) e mostrar uma mensagem em um Toast indicando a ação realizada.
   - incluindo um botão Up na barra do App, que quando clicado retorna para a Activity de Listagem cancelando a inclusão ou edição de dados aberta.
 - Altere a Activity com informações sobre a Autoria do Aplicativo:
   - incluindo um botão Up na barra do App, que quando clicado retorna para a Activity de Listagem.

/*====================================*/

SEMANA 5: 

Faça uma nova versão do projeto submetido na Entrega 4 incluindo a persistência através de SharedPreferences de configurações do aplicativo e a internacionalização para dois idiomas. 

Neste projeto além do entregue na versão anterior, deve-se:

 - Internacionalizar o aplicativo incorporando suporte a dois idiomas, o Inglês geral como padrão e o português do Brasil como opcional.
   - Todos os textos fixos de interface devem ter as duas opções de tradução, sejam os apresentados na Activity ou os mostrados em janelas modais ou em caixas de mensagens (como Toast).
 - Incorporar alguma funcionalidade de configuração/personalização do aplicativo por parte do usuário, sendo que as escolhas feitas por ele serão persistidas no dispositivo através do uso de SharedPreferences.
   - Exemplos: escolher forma de ordenação de itens em uma lista; se campos do cadastro já aparecerão com sugestão de preenchimento; se o aplicativo será apresentado utilizando outro tema ou o modo noturno; mostrar as funcionalidades mais usadas em destaque; e etc.
   - Não serão aceitos trocar a cor de fundo de layout (exemplo passado), ou salvar login e senha de usuário (visto que o aplicativo é para uso sem conexão).
