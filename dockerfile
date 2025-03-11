# Usa un'immagine base di OpenJDK 8 (basata su Debian)
FROM openjdk:8-jdk

# Copia il file toolchains.xml (se usato) nella directory Maven (opzionale)
COPY toolchains.xml /root/.m2/toolchains.xml

# Imposta la directory di lavoro
WORKDIR /app

# Copia l'intero progetto nella directory /app
COPY . /app

# Assicurati che il Maven Wrapper sia eseguibile
RUN chmod +x mvnw

# Compila il progetto senza eseguire i test (veloce)
RUN ./mvnw clean package -DskipTests

# Compila esplicitamente i test per far sì che i file copiati vengano elaborati
RUN ./mvnw test-compile

# Espone la porta 8080
EXPOSE 8080

# Avvia l'applicazione
CMD ["./mvnw", "spring-boot:run"]
