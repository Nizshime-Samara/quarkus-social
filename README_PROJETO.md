
# quarkus-social
Este projeto usa Quarkus, o Supersonic Subatomic Java Framework.
Se você quiser aprender mais sobre o Quarkus visite o website: https://quarkus.io/ .

## Rodando a aplicação em dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

# Configurações especificas do projeto
### Configuração do Banco de Dados MySQL para Execução Local

Para executar o banco de dados MySQL para a aplicação Quarkus localmente, siga estas etapas:

- **Acessar o Services.msc**:
  - Pressione `Win + R` para abrir a caixa de diálogo Executar.
  - Digite `services.msc` e pressione Enter. Isso abrirá o Gerenciador de Serviços do Windows.

- **Localizar o Serviço MySQL**:
  - Na lista de serviços, procure por MySQL. O nome do serviço pode variar dependendo da instalação (por exemplo, MySQL57, MySQL80, etc.).

- **Iniciar o Serviço**:
  - Clique com o botão direito do mouse no serviço MySQL e escolha 'Iniciar'.
  - Se o serviço já estiver em execução e você quiser reiniciá-lo, escolha 'Reiniciar'.
  - Para verificar se o MySQL está rodando corretamente, use o MySQL Workbench ou qualquer cliente MySQL (como o DBeaver).

- **Configuração Automática**:
  - Para que o MySQL inicie automaticamente com o Windows, clique com o botão direito no serviço, selecione 'Propriedades' e, na aba 'Tipo de Inicialização', escolha 'Automático'.