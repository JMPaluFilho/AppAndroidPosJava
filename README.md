# AppAndroidPosJava

Entrega 1 - Formulário de cadastro com TextView, EditText, Button, Toast, RadioGroup, RadioButton, CheckBox e Spinner
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

Deve-se utilizar:

  - O Android Studio 4.2.1 ou superior (apenas versões estáveis);
  - Versão do Gradle específica da versão do Android Studio utilizada;
  - Minimum API Level 16 (minSdkVersion 16 no build.gradle (Module: app), que irá alterar a geração do AndroidManifest.xml);
  - Compile SDK Version para o API Level 29 (Android Q).
