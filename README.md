# mini-autorizador

Esse projeto foi criado utilizando arquitetura Hexagonal, onde toda camada de aplicação está isolada do domínio, assim como toda camada de configuração também, os beans foram criados manualmente em uma classe de configuração para isolar o domínio e sua injeção por constructor.

Os serviços de cartão de crédito foram reutilizados e injetados no serviço de transações sendo assim evitando duplicidade de código, optando pela reusabilidade, modularidade. Aplicação do princípio SOLID, as classes tendo apenas uma responsabilidade assim como seus métodos.

O banco de dados utilizado foi o MySQL.

Para evitar o problema de requisições simultâneas e concorrentes, utilizamos o conceito de PESSIMIST LOCKING garantindo que uma transação aguarda até que a outra termine a operação de escrita.
