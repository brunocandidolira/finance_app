# Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copiamos TUDO da pasta target para termos certeza
COPY --from=build /app/target/*.jar ./

# Em vez de ENTRYPOINT, usamos um script curto no CMD que lista os arquivos
# antes de tentar rodar. Isso vai nos dar o "pulo do gato".
CMD sh -c "ls -lh && java -jar $(ls *.jar | grep -v plain | head -n 1)"