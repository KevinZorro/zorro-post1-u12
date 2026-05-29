# Pedidos

## Arquitectura del sistema

El proyecto sigue una arquitectura hexagonal (Ports & Adapters). El dominio está aislado en la capa `dominio`, mientras que los adaptadores de entrada y salida se encuentran en `adaptadores` e `infraestructura`.

- `adaptadores/rest/PedidoController.java` expone la API HTTP y utiliza la fachada de negocio.
- `adaptadores/facade/FachadaPedidos.java` actúa como punto central de orquestación del caso de uso "crear pedido" y "buscar pedido".
- `dominio` contiene entidades, enums, eventos y puertos.
- `adaptadores/procesadores/*` implementa la lógica específica de cálculo para cada tipo de pedido.
- `infraestructura/persistencia/RepositorioPedidosJpa.java` y `PedidoJpaRepository.java` implementan el puerto de persistencia.
- `infraestructura/notificaciones/*` responde a eventos de dominio sin acoplar la lógica de negocio a la notificación.

## Justificación de cada patrón

### Hexagonal / Ports & Adapters
- Separa la lógica de negocio del framework y de la infraestructura.
- Facilita la prueba unitaria de la lógica central sin cargar Spring ni JPA.
- Permite cambiar la implementación de repositorio o notificación sin tocar el dominio.

### Fachada (Facade)
- `FachadaPedidos` centraliza el flujo de creación de pedidos: seleccionar procesador, guardar y publicar evento.
- Simplifica al controlador REST a un único punto de entrada de negocio.

### Factory / Estrategia
- `ProcesadorPedidoFactory` resuelve el `ProcesadorPedido` apropiado según el `TipoPedido`.
- Cada estrategia de procesamiento (`ESTANDAR`, `EXPRESS`, `INTERNACIONAL`) tiene su propia clase.
- Esto evita `if/else` en el dominio y permite agregar nuevos tipos con mínimo cambio.

### Repository
- `RepositorioPedidos` expone un puerto de persistencia.
- `RepositorioPedidosJpa` implementa el puerto con Spring Data JPA.
- `PedidoRepository` adapta el puerto de dominio para clientes que esperan un método `save`.

### Event-driven / Publisher
- `ApplicationEventPublisher` publica `PedidoProcesadoEvent` tras guardar el pedido.
- Las notificaciones (`NotificacionEmail`, `NotificacionLog`) escuchan el evento y actúan de forma desacoplada.
- Esto mejora la extensibilidad: se pueden agregar más listeners sin modificar el flujo principal.

### Adaptador legacy
- La clase legacy central del proyecto se mantuvo como servicio principal en capas anteriores.
- En la refactorización actual, `FachadaPedidos` se comporta como el servicio de orquestación para el flujo de pedidos.

## Métricas antes / después

### Antes
- El proyecto tenía muy poca o ninguna cobertura de tests automatizados.
- El flujo de dominio estaba acoplado a implementaciones concretas, lo que dificultaba pruebas aisladas.
- El único test existente era `PedidosApplicationTests` que solo validaba la carga del contexto.

### Después
- `mvn test` pasa correctamente con los nuevos tests unitarios.
- Se generó reporte JaCoCo en `target/site/jacoco`.
- Cobertura de línea reportada: **93.95%**.
- `mvn clean verify` y el análisis de SonarQube se ejecutaron con éxito.

## Imágenes

### Cobertura antes de refactorizar
![CC antes de refactorizar](docs/CC%20antes_de_refactorizar.png)

### Cobertura post refactorizar
![CC post refactorizar](docs/CC%20post%20refactorizar.png)

### Dashboard final de SonarQube
![Dashboard final](docs/Dashboard%20final.png)

## Cómo ejecutar

```bash
./mvnw.cmd clean verify
```

Esto compila el proyecto, ejecuta los tests y genera el reporte de cobertura JaCoCo.
