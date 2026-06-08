# Paso 1: Compilar la aplicación (Usando Gradle Wrapper)
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

# Copiar los archivos de configuración de Gradle para aprovechar la caché de capas
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Dar permisos de ejecución y descargar dependencias (esto ayuda a que los builds sean más rápidos si no cambian las dependencias)
RUN chmod +x ./gradlew
RUN ./gradlew dependencies --no-daemon

# Copiar el código fuente y compilar el JAR
COPY src src
RUN ./gradlew bootJar -x test --no-daemon

# Paso 2: Imagen de ejecución (JRE más ligero)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
# Spring Boot por defecto genera un archivo terminado en -SNAPSHOT.jar o similar
COPY --from=build /app/build/libs/*.jar app.jar

# Puerto configurado dinámicamente para Render
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
