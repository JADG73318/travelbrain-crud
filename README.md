# TravelBrain - Módulo CRUD de Destinos

Evidencia **GA7-220501096-AA2-EV01: Codificación de módulos del software**
Programa: Tecnólogo en Análisis y Desarrollo de Software (SENA)

## Descripción
Módulo de gestión de **destinos turísticos** de la plataforma TravelBrain. Está hecho en Java, se conecta a MySQL con JDBC y permite registrar, consultar, actualizar y eliminar destinos desde un menú de consola.

## Tecnologías
- Java 17 o superior
- Maven
- MySQL 8
- JDBC (mysql-connector-j)

## Estructura
```
sql/travelbrain.sql                      Script de base de datos
src/main/java/com/travelbrain/
  Main.java                              Punto de entrada
  modelo/Destino.java                    Entidad
  dao/DestinoDAO.java                    CRUD con JDBC (patrón DAO)
  util/ConexionBD.java                   Conexión a MySQL
  vista/MenuConsola.java                 Menú de consola
ESTANDAR_CODIFICACION.md                 Estándar de codificación aplicado
```

## Cómo ejecutarlo (Windows / PowerShell)
1. Crear la base de datos desde MySQL:
   ```
   mysql -u root -p < sql/travelbrain.sql
   ```
2. Definir las credenciales (no se guardan en el código):
   ```powershell
   $env:TB_DB_USER = "root"
   $env:TB_DB_PASSWORD = "su_clave"
   ```
   Opcional: `$env:TB_DB_URL` si la base no está en `localhost:3306`.
3. Compilar y ejecutar:
   ```powershell
   mvn compile exec:java
   ```

## Operaciones del CRUD
| Opción | Operación | Método DAO |
|---|---|---|
| 1 | Create | `insertar()` |
| 2 | Read (todos) | `listar()` |
| 3 | Read (uno) | `buscarPorId()` |
| 4 | Update | `actualizar()` |
| 5 | Delete | `eliminar()` |

## Control de versiones
El proyecto se versiona con Git y se publica en GitHub con commits por cada avance.
