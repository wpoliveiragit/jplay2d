#JPLAY2D
    Framework de criação de jogos 2D.
    
**Sobre:** `Este framework inicialmente foi criado por:`  

 * The use this code commercially must only be done with permission of the author.
 * Any modification shall be advised and sent to the author.
 * The author is not responsible for any problem therefrom the use of this frameWork.  
 
>autor: Gefersom Cardoso Lima
Federal Fluminense University
Computer Science
 
#CONFIGURANDO O GIT DO PROJETO
    Toda a configuração estará no diretorio 'openshift' localizado na raiz do projeto.
**Adicione em 'libraries'**:
* `jbox2d-library-2.0.1-b250.jar`

##USANDO O FRAMEWORK
**br.com.jplay2D.url:**  
*Url:* `Classe que busca a url de um arquivo. Crie a estrutura abaixo na raiz.`
* **resources:** `Diretorio raiz dos recursos dos recursos.`
    - **backdrop:** `Diretorio de Imagens de plano de fundo.`
    - **image:** `Diretório de Imagens gerais.`
    - **scene:** `Diretório dos cenários.`
    - **sound:** `Diretório dos efeitos sonoros.`
    - **sprite:** `Diretório das imagens de movimentos.`
    - **tile:** `Diretório das imagens do cenário.`  

**br.com.jplay2D.scene:**  
*SceneFile:* `Faz o controle do arquivo de cena do jogo.`
* loadfile(String):void - `carrega um arquivo de .scn.`
* saveFile(String):void - `salva as alterações feitas no arquivo carregado.`
* Padrão do Arquivo .src
     - Por convenção use a extenção '.scn'.
     - Defina arquivos apenas na extenção '.png'.
     - Todas as tiles DEVEM possuir a mesma dimensão (sugestão 32x32).
     - Use a convenção 0-9, 00-99, 009, etc ao construir um cenario.
     - O índice 0 (zero) no mapa informa que 'este ponto' não possui
      tile e aparecerá um treixo do backdrop.
     - Não use um índices que não possua uma imagem definida.
     - Evite usar mais de 1.000.000 imagens no arquivo.
     - (retirar isso do codigo)A tile que ficará como pano de fundo 
     deve existir.
     - A primeira linha deve conter a quantidade **desejada** de 
     imagens no arquivo.
     - Da linha **2** a **n**, onde **n** será o número definido na primeira
      linha+1, deve conter o nome de uma imagem ou um '-'.
     - Após a linha **n**, onde **n** será o número definido na primeira
      linha+1, irá conter a matriz do mapa.
     - A matriz do mapa deve ser construida usando o indice da imagem desejada,
      onde o indice da imagem será o numero da linha-1.
     - Use um ',' para separa  um indice do outro na construção da matriz.
     - A linha posterior a da matriz deve conter um '%' que inicará o final da estrutura.
     - A última linha do arquivo deve conter o nome do arquivo de backdrop do mapa.  

```     
Exemplo de um mapa
+- arquivoCena01.scn --------------------+
5
parede.png
grama.png
-
-
-
01,01,01,01,01,01,01
01,02,02,02,02,02,01
01,02,00,00,00,02,01
01,02,00,00,00,02,01
01,02,00,00,00,02,01
01,02,02,02,02,02,01
01,01,01,01,01,01,01
%
backdrop.png
+----------------------------------------+
```

####TAREFAS
`Sempre que atualizar o projeto, atualize o tópico **USANDO O FRAMEWORK** deste arquivo.`  
`Sempre que identificar uma nova tarefa, mas não for a realizar de imediato, registre neste tópico.`
* (Adicionar todas as classes aqui)
* Alterar a classe Url, definir um metodo de criação do local da raiz, um método que cria os nomes das pastas de arquivo e um que busque a imagem a partir do nome do arquivo e  pasta.
* Criar documentação.
* Adicionar JUnit ao projeto
* Na documentação, trocar todas as palavras frames por quadros
* refazer a classe watch
* add hitbox em GameObject
* trocar todos os "Window.getInstance()" por "Window.INSTANCE"
* sprite - criar uma gravidade de pulo ou mesmo uma ideia de altura de pulo
* Criar uma hitbox em GameObject
* mudar o nome da classe scene para scenery
* Estudar a possibilidade da retirada do caracter '%' na criação do mapa

#OUTROS
The use this code commercially must only be done with permission of the author.
Any modification shall be advised and sent to the author.
The author is not responsible for any problem therefrom the use of this frameWork.

>author Gefersom Cardoso Lima
Federal Fluminense University
Computer Science

Usando MD
---
*italico* | `renders` | **negrito** |~riscado~

[link](www.site.com.br)

1. tópico 1
    * sub topico 1
    - sub topico 2
       * d
       + sub topico 3
* tópico 1
+ tópico 2

```não entra no texto
javascript
var s = "JavaScript syntax highlighting";
alert(s);
```
>bloco de artigo
