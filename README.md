# Sistema de Simulação de Detecção de Incêndios

Este projeto implementa um sistema de simulação de detecção de incêndios em uma floresta, utilizando programação paralela e distribuída com `pthreads` e monitores.

## Estrutura do Projeto

- `Main.java`: Classe principal que inicializa a floresta, nós sensores, gerador de incêndios e a central de controle.
- `SensorNode.java`: Implementa a lógica dos nós sensores, responsáveis por monitorar as células da floresta.
- `FireGenerator.java`: Thread que gera incêndios aleatórios a cada 3 segundos.
- `ControlCenter.java`: Thread responsável por gerenciar incêndios reportados pelos nós das bordas.

## Pré-requisitos

Para rodar o projeto, você precisa ter instalado:

- Java JDK 8 ou superior

## Compilação e Execução

1. Clone este repositório em sua máquina:
    ```bash
    git clone [link do repositório]
    cd [nome do diretório]
    ```

2. Compile os arquivos Java utilizando o comando:
    ```bash
    javac Main.java SensorNode.java FireGenerator.java ControlCenter.java
    ```

3. Execute o programa:
    ```bash
    java Main
    ```

O sistema iniciará e exibirá a floresta no terminal, atualizando a cada 3 segundos. Durante a execução, incêndios serão gerados aleatoriamente e os nós sensores reagirão de acordo com suas funções.

## Funcionamento

- A floresta é representada por uma matriz de 30x30, onde:
    - `T`: representa uma célula monitorada por um nó sensor.
    - `-`: representa uma área livre.
    - `@`: representa uma célula em chamas.
    - `/`: representa uma célula queimada.

- Incêndios são gerados aleatoriamente a cada 3 segundos em células monitoradas.
- Os nós sensores detectam os incêndios e os propagam para os vizinhos.
- A central de controle coleta mensagens de incêndios nas bordas da floresta e registra os eventos.

## Logs e Visualização

O estado atual da floresta será exibido no terminal em intervalos de 3 segundos. Além disso, eventos como detecção de incêndios e ações tomadas pela central de controle serão registrados no terminal.

