# Sistema de Gestión

Sistema de gestión desarrollado en Java con interfaz gráfica Swing.

## Requisitos

- Java JDK 8 o superior
- Sistema operativo: Windows, Linux o macOS

## Estructura del Proyecto

```
src/main/java/
├── datatypes/          # Tipos de datos
├── interfaces/          # Interfaces del sistema
├── logica/             # Lógica de negocio
├── persistencia/       # Capa de persistencia
├── presentacion/       # Interfaz de usuario
    └── Principal.java   # Punto de entrada principal
```

## Ejecución del Proyecto

### Opción 1: Scripts automáticos (Recomendado)

#### Windows
```bash
# Ejecutar directamente
ejecutar.bat

# O para compilación rápida
compilar_rapido.bat
```

#### Linux/Mac
```bash
# Dar permisos de ejecución
chmod +x ejecutar.sh

# Ejecutar
./ejecutar.sh
```

### Opción 2: Comandos manuales

1. **Abrir terminal en la carpeta del proyecto**

2. **Compilar el proyecto:**
```bash
# Crear directorio de salida
mkdir -p target/classes

# Compilar (Windows)
javac -d target\classes -cp "src\main\java" src\main\java\datatypes\*.java src\main\java\interfaces\*.java src\main\java\logica\*.java src\main\java\persistencia\*.java src\main\java\presentacion\*.java

# Compilar (Linux/Mac)
javac -d target/classes -cp "src/main/java" src/main/java/datatypes/*.java src/main/java/interfaces/*.java src/main/java/logica/*.java src/main/java/persistencia/*.java src/main/java/presentacion/*.java
```

3. **Ejecutar la aplicación:**
```bash
# Windows
java -cp "target\classes" presentacion.Principal

# Linux/Mac
java -cp "target/classes" presentacion.Principal
```

### Opción 3: Usando Maven

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="presentacion.Principal"
```

## Funcionalidades del Sistema

### Menú Altas
- **Agregar Usuario**: Crear nuevos lectores y bibliotecarios
- **Agregar Material**: Agregar libros y artículos al sistema

### Menú Modificaciones
- **Agregar Préstamo**: Registrar nuevos préstamos de materiales

### Menú Listados
- **Listar Donaciones**: Ver todos los materiales donados
- **Listar Préstamos**: Ver todos los préstamos del sistema
- **Listar Préstamos Activos por Lector**: Ver préstamos activos de un lector específico

## Solución de Problemas

### Error: "javac no se reconoce como comando"
- Instalar Java JDK y agregar al PATH del sistema
- Verificar instalación: `java -version` y `javac -version`

### Error: "No se ha encontrado o cargado la clase principal"
- Verificar que la compilación fue exitosa
- Verificar que existe `target/classes/presentacion/Principal.class`

### Error de compilación
- Verificar que todos los archivos Java están en las ubicaciones correctas
- Verificar que no hay errores de sintaxis en el código

## Autor

Alejandro Machado

## Notas Técnicas

- La aplicación utiliza el patrón Singleton para la fábrica de controladores
- La interfaz gráfica está construida con Swing
- El sistema maneja usuarios (Lectores y Bibliotecarios) y materiales (Libros y Artículos)
- Utiliza JPA para persistencia de datos

