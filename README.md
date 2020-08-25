# Sobre o projeto
O projeto é voltado ao desenvolvimento de um sistema de venda de carros, no qual o usuario poderá se cadastrar para ter acesso as informações
dos veiculos, que por sua vez, possui informações do automovel, tais como: imagens, modelo, preço, ano, cor, portas, cambio entre outros.
O sistema disponibiliza uma recuperação de senha, caso hajá esquecimento por parte do usuario.
O Administrador terá acesso ao gerenciamento da loja de carro, que consiste na inserção de um novo veiculo,
atualização do proprio e até mesmo a exclusão, tornando o sistema bem flexivel e fácil de dar manuntenção. 
Assim, o sistema disponbiliza visualização publica para os veiculos mostrado e também disponibiliza a administração do sistema

# Tecnologias Utilizadas no Projeto

Nesse projeto utilizei:

* JavaEE 11
* STS 4.6.0 RELEASE
* Maven 4.0.0
* Spring Security
* JWT 0.7.0
* MYSQL 8.0.15
* H2 Database (Banco de dados utilizado para ambiente de teste)

# Configuração do projeto
O Projeto utiliza banco de dados relacional. Configurações de banco de dados
estão nos arquivos application-dev.properties e application-test.properties.
Dependencias estão no pom.xml
O sistema ele tem dois ambiente de desenvolvimento:

Desenvolvedor
 * Para utilizar o ambiente dev, va até o application.properties e mude o profile.active para dev.
  feito isso, vá até o pacote com.crash.config e na classe testconfig mude o @Profile para dev
  
Teste
 * Para utilizar o ambiente de teste, é a mesma configuração do dev, mudando apenas o nome para test
 
 Aviso:
 
  * Caso não queira trabalhar com envio de email. Recomendo que remova as configurações de email
  se não, o sistema dará erro sem as configurações adequadas.
  nos arquivos application-dev.properties, application-test.properties. Remova toda a configuração do SMTP.gmail.com do google, deixe apenas
  as configuração do banco de dados
  e no pacote de service, vá até a classe SMTPEmailServiceIMPL e remova MailSender
  
  
  
  
  
