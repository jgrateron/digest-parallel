### Calcular digest de archivos

## Ejecutar
- java -jar build/digest-0.0.1.jar -a algoritmo -f "archivo o directorio"
- Parametro opcional "-o" para ordenar por el nombre de los archivos  

## Compilar
- Instalar jdk min 1.8
- Instalar maven
- mvn clean package
- java -jar target/digest-0.0.1.jar -a md5 -f .

## Digest soportados
- MD2
- MD5
- SHA
- SHA256
- SHA384
- SHA512
- SHA3-256
- SHA3-512
