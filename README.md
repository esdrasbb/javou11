# javou#11

Aplicação didática para palestra com o título: "Hazelcast: Dê um UP na sua aplicação"

## Banco de dados
- Postgres 9.4
- url: jdbc:postgresql://localhost:5432/hazelcast
- ddl e dml: arquivos na pasta **sql**

##Iniciar a aplicação
__mvn spring-boot:run__
 
Exemplos de chamada:
- curl 'http://localhost:8080/estado/all'
- curl 'http://localhost:8080/cidade/ce'
- curl 'http://localhost:8080/clear' 