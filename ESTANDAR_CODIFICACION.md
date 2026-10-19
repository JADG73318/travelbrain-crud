# Estándar de codificación - TravelBrain

Se adopta la convención de código de Java (Oracle / Google Java Style) con estas reglas:

## Nombres
| Elemento | Regla | Ejemplo |
|---|---|---|
| Paquetes | minúsculas | `com.travelbrain.dao` |
| Clases | PascalCase | `DestinoDAO` |
| Métodos y variables | camelCase | `buscarPorId`, `mejorEpoca` |
| Constantes | MAYÚSCULAS_CON_GUION_BAJO | `SQL_INSERTAR` |
| Tablas y columnas SQL | minúsculas_con_guion_bajo | `mejor_epoca` |

## Estructura
- `modelo`: entidades. `dao`: acceso a datos. `util`: utilidades. `vista`: interacción con el usuario.
- Una clase pública por archivo.

## Formato
- Indentación de 4 espacios, sin tabuladores.
- Llaves en la misma línea de apertura.
- Una instrucción por línea; líneas de máximo 120 caracteres.

## Buenas prácticas
- Documentar cada clase y método público con Javadoc.
- Usar siempre `PreparedStatement` (evita inyección SQL).
- Cerrar recursos con `try-with-resources`.
- No dejar credenciales en el código: se leen de variables de entorno.
- Mensajes de commit con prefijo: `feat:`, `fix:`, `docs:`, `refactor:`.
