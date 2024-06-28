# Cpryt Crawler

## Informações Gerais

### Descrição
Este projeto é um jogo estilo Roguelike, onde o personagem está em uma masmorra muito perigosa, e deve explora-lá para
aprimorar as suas habilidades e derrotar o chefe final da masmorra!

### Objetivo
Neste projeto, os principais objetivos foram:
- Aprimorar nosso conhecimento em Java.
- Aplicar os conteúdos estudados em sala no projeto.
- Fazer um jogo para a plataforma Steam.

### Funcionalidades
- Interface gráfica para o jogador ler as informações do jogo em tempo real.
- Mini mapa atualizado em tempo real.
- Controles de fácil acesso, utilizando JFrame.

## Classes

### Main
**Descrição:** A classe main irá iniciar o menu principal do jogo (apenas para tematizar o jogo).

**Relações:** Cria uma instância de `MainMenu.java` para a criação de um JFrame.

### CryptCrawler
**Descrição:** A lógica principal do jogo, verificadores, carregar mapa, inicializar entidades etc...

**Relações:**
- `World.java` vai ler o arquivo `world.save` e salvar em uma instância da classe.
- `Entidade.java` usado para inicializar aliados.
- `Enemy.java` usado para inicializar inimigos.
- `Interface.java` usado para criar uma interface de jogo, onde tem várias telas para cada ocasião.
- `Combate.java` usado quando o jogador entra em combate

### Interface
**Descrição:** Nesta classe, tem todas as telas usadas na tela principal de jogo, usado para a taxa de atualização
da tela.

**Relações:** 
- `KeyListener` usa esta interface para adicionar ao JFrame a leitura de teclas apertadas pelo jogador

### Combate
**Descrição:** está presente a lógica de combate, como atacar, turno, inimigos / aliados mortos, etc.

**Relações:**
- `Interface.java` atualiza a tela de combate ao passar pela verificação de aliado ou inimigo morto, atualizar
status de aliados e inimigos, informar os controles básicos.

## Executar Projeto
> De preferência utilize o IntelliJ IDE para executar o jogo
> 1. Utilizando o GitHub Desktop ou terminal Git, clone o repositório para o diretório desejado
> 2. Abra o repositório, e crie uma pasta chama `World` na sua Área de Trabalho
> 3. Dentro da pasta `scr` do repositório copie o arquivo `world.save` e cole na pasta criada `World`
> 4. Verifique se a biblioteca `asciiPanel.jar` está referenciada na IDE, para mais detalhes vá para a aba `Adicionando a biblioteca`
> 5. Vá para a classe `Main.java` e clique no botão para compilar e rodar o código
> 6. Jogo iniciado! Para ver os controles vá para a aba Controles do README.md

## Uso do IA
Neste trabalho, o uso de IA foi fundamental para aprender novas funções em Java, como o KeyEventListener, MouseEventListener, e várias
outras funções que deixaram a jogabilidade muito mais intuitiva e fácil de entender. As IA's ajudaram muito em entender como funciona
a personalização de um JFrame / JPanel, para tematizar com o tema do jogo. Porém, a lógica do jogo, a maior parte fizemos por conta 
própria, pois usamos de base alguns projetos que implementam algumas funções e apenas aprimoramos.

## Referências e Recursos

### Bibliotecas Externas Usadas
- **AsciiPanel:** Biblioteca que simula um terminal em ASCII
- **OBS:** A biblioteca foi editada para adicionar novas fontes

### Recursos Adicionais
- **Java AWT e Swing:** Utilizadas para criar a interface gráfica do usuário.
- **Fontes:** 
- `alagard.ttf` utilizada para estilização do texto na interface do menu.
- `runescape_uf.ttf` utilizada para estilização do texto na interface do menu.

### Link Úteis
- [AsciiPanel GitHub Repository](https://github.com/trystan/AsciiPanel): Repositório oficial da biblioteca AsciiPanel.
- [Writing a Roguelike in Java](https://jellepelgrims.com/posts/roguelike_java): Site que nos inspiramos para fazer o nosso jogo.
- [Repositório roguelike inspiração, GitHub](https://github.com/jpelgrims/roguelike): Repósitorio do jogo de inspiração.
- [Alagard Font](https://www.dafont.com/pt/alagard.font): Repositório oficial da biblioteca AsciiPanel.
- [Runescape Font](https://www.dafont.com/pt/runescape-uf.font): Repositório oficial da biblioteca AsciiPanel.
- [Documentação Oficial do Java](https://docs.oracle.com/en/java/): Referência para a API do Java e guias de desenvolvimento.

## Adicionando biblioteca _asciiPanel.jar_

### Verificando se tem uma biblioteca desatualizada

1. Verifique se o repositório já está com uma biblioteca (desatualizada) adicionada
2. Para isso vá para o canto superior esquerdo na aba _"File"_ e então para _"Project Structure..."_ ou se preferir aperte as teclas _"Ctrl + Alt + Shift + S"_

<div align = "center">
    <img src="/Images/project-structure.png" height="400">
</div>

3. Na nova janela, no canto esquerdo selecione a aba _"Libraries"_
4. Se tiver alguma biblioteca, selecione e aperte no ícone de menos em vermelho para remover, em seguida aperte o botão _"Apply"_

<div align = "center">
    <img src="/Images/verificar-biblioteca.png" height="500">
</div>

### Adicionando biblioteca

1. No canto superior esquerdo do IntelliJ, abra a pasta _"Biblioteca"_, copie o arquivo _asciiPanel.jar_
2. Vá para a pasta _"src"_ e cole o arquivo dentro da pasta _"asciiPanel"_

<div align = "center">
    <img src="/Images/colar-biblioteca.png" height="400">
</div>

3. Na pasta _"asciiPanel"_, clique com o botão direito do mouse no arquivo e selecione a opção _"Add as Library..."_

<div align = "center">
    <img src="/Images/adicionando-biblioteca.png" height="600">
</div>

4. Aperte o botão _"OK"_

<div align = "center">
    <img src="Images/pressionar-OK.png">
</div>

5. **Biblioteca adicionada!**