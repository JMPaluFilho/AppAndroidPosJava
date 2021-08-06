# AppAndroidPosJava
App criado para a matéria de "Desenvolvimento Java Para Dispositivos Móveis" da pós graduação em tecnologia JAVA na UTFPR.

Será composto de 5 entregas:
  - Entrega Parcial 1 até 01/08/2021;
  - Entrega Parcial 2 até 08/08/2021;
  - Entrega Parcial 3 até 15/08/2021;
  - Entrega Parcial 4 até 22/08/2021;
  - Entrega Parcial 5 até 29/08/2021;
  - Entrega Final entre 09/09 a 12/09/2021;

Tema do App:
  Controle de Posts em Redes Sociais.
  
Objetivo do App:
  Manter um cadastro de empresas que solicitaram os serviços de um autônomo que gerencia mídias sociais.

Entrega 2 - Listagem de Itens carregados de Arrays do Resource

Faça uma nova versão do projeto submetido na Entrega 1 criando uma nova Activity que listará itens, estes serão objetos de um tipo de Entidade relacionada ao Tema do Projeto. 

Neste projeto além do entregue na versão anterior, deve-se:

  - Criar uma classe de Entidade relacionada ao Tema do Projeto (Pelo menos 4 atributos);
  - Carregar de Arrays do Resource um conjunto de dados (pelos menos 4 tipos de informação) que possibilite a instanciação de objetos da Entidade (Pelo menos 10);
  - Armazenar as Entidades instanciadas em um ArrayList;
  - Criar uma Activity que exiba um componente de listagem de itens ocupando toda tela, pode ser uma ListView ou RecyclerView;
  - Criar um Adapter customizado para exibir os dados de cada Entidade na listagem de Itens;
  - Ao clicar em um item deve-se mostrar uma mensagem em um Toast indicando que o mesmo foi clicado. A mensagem deve conter informações que identifiquem o elemento;
  - A Activity criada de listagem deve ser a principal do Aplicativo (Launcher), para tal no AndroidManifest.xml ela terá mapeada a ação de MAIN e a categoria LAUNCHER. Dica: ao criar uma nova Activity em um projeto já existente marque o item Launcher na tela Assistente de criação (Wizard).

Deve-se utilizar:

  - O Android Studio 4.2.1 ou superior (apenas versões estáveis);
  - Versão do Gradle específica da versão do Android Studio utilizada;
  - Minimum API Level 16 (minSdkVersion 16 no build.gradle (Module: app), que irá alterar a geração do AndroidManifest.xml);
  - Compile SDK Version para o API Level 29 (Android Q).
