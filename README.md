# jplay2Dtest
Projeto para teste da aplicação jplay2D.

## Config
1. abra um terminal
2. navegue até a pasta "lib" (dentro do projeto)
3. execute o comando 

> "mvn install:install-file -Dfile=jbox2d-2.0.1-library-only.jar -DgroupId=br.com.wellington.jbox2d -DartifactId=jbox2d-2.0.1-library-only -Dversion=2.0.1 -Dpackaging=jar -DgeneratePom=true"

> mvn clean install -U

Obs.: Ou copie a pasta "repository" para dentro do ".m2"
Obs.: isso criará a dependencia jbox2d
