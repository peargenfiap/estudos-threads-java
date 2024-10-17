# Gerenciamento de Threads no Sistema Operacional

Quando estamos usando um computador, muitas vezes podemos fazer diversas coisas ao mesmo tempo, como ouvir música, navegar na internet e editar um documento no Word. Para que isso seja possível, o **Sistema Operacional (SO)** precisa ser muito inteligente na forma como gerencia todas essas tarefas, garantindo que o computador funcione de maneira eficiente, sem que a gente perceba qualquer lentidão ou problemas.

Uma das maneiras de fazer isso é através do **gerenciamento de threads**.

---

## O que é uma Thread?

Imagine que um programa de computador é um escritório com muitos funcionários. Cada funcionário tem uma tarefa diferente para executar. No mundo dos computadores, cada um desses "funcionários" pode ser comparado a uma **thread**.

Uma **thread** é como uma linha de execução que o programa usa para realizar uma tarefa. Um programa pode ter várias threads, assim como um escritório pode ter vários funcionários trabalhando ao mesmo tempo.

---

## Como o Sistema Operacional Gerencia as Threads?

O SO é como o gerente do escritório. Ele precisa decidir qual funcionário (thread) vai trabalhar em qual momento e por quanto tempo. Isso é especialmente importante porque, na maioria das vezes, os computadores têm um número limitado de "mesas" (núcleos do processador) onde os funcionários podem trabalhar.

O gerenciamento eficaz garante que todos os trabalhos sejam feitos sem que ninguém fique esperando muito tempo.

---

## Escalonadores de Threads

Para organizar tudo isso, o SO usa algo chamado **escalonador de threads**. O escalonador é como um cronograma que diz quem trabalha, quando e por quanto tempo. Existem diferentes tipos de escalonadores, mas todos têm o mesmo objetivo: fazer com que o computador seja justo com as threads e use o processador da maneira mais eficiente possível.

### Exemplo de Escalonamento:

Vamos supor que temos três tarefas (threads): **A**, **B** e **C**. O escalonador pode decidir que:

- **A** vai trabalhar por 20 milissegundos,
- depois **B** por 20 milissegundos,
- por fim, **C** por 20 milissegundos.

Depois disso, ele volta para **A** e repete o ciclo.

---

## Gerenciadores de Concorrência

Às vezes, as threads precisam trabalhar na mesma tarefa ou acessar a mesma informação ao mesmo tempo. Isso pode causar problemas sérios, como dois funcionários tentando usar a mesma máquina de café ao mesmo tempo.

Para evitar isso, o SO usa **gerenciadores de concorrência**, que são como regras de quem pode usar o quê e quando.

### Exemplo de Gerenciamento de Concorrência:

Se a thread **A** está acessando um arquivo e a thread **B** também precisa acessar esse mesmo arquivo, o gerenciador de concorrência pode fazer com que a thread **B** espere até que a thread **A** termine o seu trabalho. Isso evita que o arquivo seja corrompido ou que aconteçam outros erros.

---

## Conclusão

O **gerenciamento de threads** é fundamental para a performance e a estabilidade do computador. Sem ele, teríamos muitos problemas, como programas travando, computadores lentos e até perda de informações.

**Estudar futuramente:**

- **Round-Robin**,
- **Prioridades**,
- **FIFO (First In, First Out)**.



# Estendendo da classe Thread

A classe **Thread** já implementa a interface **Runnable**, o que significa que uma thread também é um **Runnable**. Sabendo disso, é possível criar uma subclasse de `Thread` e sobrescrever o método `run` para definir a tarefa que a thread executará. Por exemplo:

```java
public class Multiplicador extends Thread {
  public void run () {
    // calculo demorado
  }
}

Multiplicador multiplicador = new Multiplicador();
multiplicador.start();
```

### Desvantagens

Embora seja uma solução simples, ela abusa da herança. Esse padrão é chamado de "herança por preguiça", onde herdamos métodos que não utilizamos. Além disso, essa abordagem não aproveita o polimorfismo trazido pela herança.

### Boas Práticas

A abordagem de **herdar de Thread** é considerada um mau exemplo de herança. Prefira **implementar a interface Runnable**, separando a responsabilidade de ser uma thread da tarefa que ela executa, seguindo as boas práticas da **programação orientada a objetos (OO)**.

---

# Utilizando Classes Anônimas e Lambdas

A forma tradicional de criar uma thread em Java envolve implementar a interface **Runnable** e instanciar uma `Thread` com o runnable. Embora funcione bem, essa abordagem pode ser simplificada em casos mais triviais, como mostrado a seguir:

```java
Runnable r = new Runnable() {
    public void run() {
        for (int i = 0; i < 10000; i++) System.out.println("programa 1 valor " + i);
    }
};

Thread t = new Thread(r);
t.start();
```

### Utilizando Lambdas

Podemos tornar o código ainda mais enxuto utilizando expressões **lambda**, reduzindo a verbosidade:

```java
Runnable r = () -> {
    for (int i = 0; i < 10000; i++) {
        System.out.println("programa 1 valor " + i);
    }
};

Thread t = new Thread(r);
t.start();
```

### Considerações sobre Legibilidade

Embora o código se torne mais compacto, devemos considerar que menos código significa menos clareza. A escolha entre uma abordagem mais enxuta ou mais descritiva deve ser ponderada, levando em conta **legibilidade** e **manutenção** do código.


# Problemas com Condição de Corrida

Quando trabalhamos com threads, podemos encontrar problemas com o saldo bancário, pois duas threads podem tentar acessar o mesmo recurso ao mesmo tempo. Essa situação é chamada de **condição de corrida**.

Para evitar condições de corrida, podemos usar mecanismos de sincronização, como o `synchronized`. Esse termo pode ser utilizado tanto na declaração de métodos quanto em blocos de código.

## Exemplo de Sincronização:

```java
public class Contador {
    private int contagem = 0;

    public void incrementar() {
        synchronized (this) {
            contagem++;
        }
        System.out.println("Incrementado com sucesso!");
    }
}
```

Neste exemplo, quando uma thread executa o método `incrementar()`, ela "trava" o objeto `Contador` para que nenhuma outra thread possa incrementar a contagem até que ela termine. Apenas o bloco de código crítico foi sincronizado, ao invés de sincronizar o método completo.

### Sintaxe do `synchronized`:

```java
synchronized (objeto a se sincronizar) {
    parte do código a ser sincronizada
}
```

Quando usamos `synchronized`, estamos criando um "monitor", que é como uma chave para garantir que apenas uma thread possa acessar o código sincronizado de cada vez.

---

# Usando Locks

O `synchronized` utiliza um conceito chamado "lock intrínseco". Cada objeto em Java tem um lock associado a ele. Quando um método é marcado como `synchronized`, ele usa o lock do objeto.

Além do `synchronized`, podemos usar **locks explícitos**, com classes que representam locks em Java, como a classe `ReentrantLock`. Isso oferece mais flexibilidade para travar e destravar o código em diferentes momentos.

## Exemplo usando `ReentrantLock`:

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Contador {
    private int contagem = 0;
    private final Lock lock = new ReentrantLock();

    public void incrementar() {
        lock.lock();
        try {
            contagem++;
        } finally {
            lock.unlock();
        }
    }
}
```

Com o `lock`, garantimos que apenas uma thread possa entrar na seção crítica do código de cada vez. No entanto, devemos ter cuidado com o risco de **deadlocks**.

### Problema de Deadlock:

Um **deadlock** ocorre quando duas ou mais threads ficam esperando indefinidamente uma pela outra para liberar recursos. Isso pode ser comparado a duas pessoas bloqueando um corredor estreito, onde nenhuma pode passar. Portanto, ao usar locks, devemos ter cuidado para evitar deadlocks.

---

# Conclusão

Condições de corrida, locks e deadlocks são conceitos importantes em programação concorrente. Ao trabalhar com esses problemas, é essencial escolher a solução mais adequada para o seu cenário.
