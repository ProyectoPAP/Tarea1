# Sistema de Biblioteca - GUI de Controladores

Este proyecto contiene una interfaz gráfica (GUI) para ejecutar los diferentes controladores del sistema de biblioteca.

## Descripción

La GUI permite interactuar con los siguientes controladores del sistema:

1. **Alta Usuario**: Permite crear usuarios (lectores y bibliotecarios)
2. **Alta Donación Libro**: Registra donaciones de libros
3. **Alta Donación Material**: Registra donaciones de materiales

## Cómo ejecutar

### Opción 1: Usando la clase Main
```bash
javac -cp "src/main/java" src/main/java/presentacion/Main.java
java -cp "src/main/java" presentacion.Main
```

### Opción 2: Usando la clase GUI directamente
```bash
javac -cp "src/main/java" src/main/java/presentacion/GUI.java
java -cp "src/main/java" presentacion.GUI
```

## Funcionalidades

### 1. Alta Usuario
- **Nombre**: Nombre completo del usuario
- **Correo**: Correo electrónico del usuario
- **Tipo**: Tipo de usuario (Lector o Bibliotecario)
- **Dirección**: Dirección del lector (solo para lectores)
- **Número Empleado**: Número de empleado (solo para bibliotecarios)
- **Zona**: Zona asignada al usuario
- **Estado**: Estado del lector (ACTIVO, SUSPENDIDO)
- **Fecha Registro**: Fecha de registro del usuario

### 2. Alta Donación Libro
- **Título**: Título del libro donado
- **Autor**: Autor del libro
- **Cantidad de Páginas**: Número de páginas del libro

### 3. Alta Donación Material
- **Nombre**: Nombre del material donado
- **Descripción**: Descripción del material
- **Tipo**: Tipo de material donado (DVD, CD, Revista, Otro)

## Estructura del Proyecto

```
src/
├── main/
│   └── java/
│       ├── datatypes/          # Tipos de datos del sistema
│       ├── interfaces/         # Interfaces de los controladores
│       ├── logica/            # Lógica de negocio y controladores
│       │   ├── CtrlAltaUsuario.java      # Controlador de alta de usuarios
│       │   ├── CtrlAltaMaterial.java     # Controlador de alta de materiales
│       │   └── ...                        # Otros controladores
│       └── presentacion/      # Interfaz gráfica
│           ├── GUI.java       # Interfaz principal
│           └── Main.java      # Clase principal para ejecutar
```

## Notas Importantes

- La GUI utiliza Swing para la interfaz gráfica
- Todos los resultados se muestran en el área de texto inferior
- Los errores se capturan y muestran en la misma área de texto
- La interfaz es intuitiva y fácil de usar
- Los controladores incluyen validaciones básicas y manejo de errores
- Las operaciones se simulan para demostrar la funcionalidad

## Requisitos

- Java 8 o superior
- Swing (incluido en el JDK estándar)

## Desarrollo

Para agregar nuevos controladores a la GUI:

1. Crear el método correspondiente en la clase `GUI`
2. Agregar una nueva pestaña en el `JTabbedPane`
3. Implementar la lógica de interacción con el controlador
4. Manejar los errores apropiadamente

## Controladores Implementados

### CtrlAltaUsuario
- `altaUsuario(String nombre, String correo, String tipo)`: Registra un nuevo usuario
- `crearLector(String direccion, DtFecha fechaRegistro, EstadoLector estado, Zona zona)`: Crea un lector
- `crearBibliotecario(int numEmpleado, Zona zona)`: Crea un bibliotecario

### CtrlAltaMaterial
- `altaDonacionMaterial(String nombre, String descripcion, String tipo)`: Registra donación de material
- `altaDonacionLibro(String titulo, String autor, int cantPaginas)`: Registra donación de libro
