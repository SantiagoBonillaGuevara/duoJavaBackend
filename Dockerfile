# Paso 1: Compilar la aplicación (Usando una imagen con Gradle 8.7 y JDK 17 preinstalados)
FROM gradle:8.7-jdk17 AS build
WORKDIR /app

# Copiar los archivos de configuración (Ya no necesitamos gradlew ni la carpeta gradle)
COPY build.gradle settings.gradle ./

# Descargar dependencias usando el 'gradle' nativo de la imagen (sin el ./ del wrapper)
RUN gradle dependencies --no-daemon

# Copiar el código fuente y compilar el JAR
COPY src src
RUN gradle bootJar -x test --no-daemon

# Paso 2: Imagen de ejecución (Se mantiene idéntica, JRE ligero)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Puerto configurado dinámicamente para Render
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]