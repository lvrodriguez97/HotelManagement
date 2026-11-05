# Hotel Management

Sistema de gestión de un único hotel en Java: habitaciones (individual, doble, familiar y suite), huéspedes, reservas con estados y comprobantes. Taller 03 de Programación Orientada a Objetos (POO 2026).

## Estructura

```text
HotelManagement/
├── src/
│   ├── model/   clases del dominio
│   └── view/    Main.java: inicialización y pruebas por consola
└── docs/        enunciado, decisiones de diseño y diagrama de clases UML
```

## Documentación

- [Enunciado](docs/Enunciado.md)
- [Decisiones de diseño](docs/DecisionesDiseno.md)
- [Diagrama de clases](docs/DiagramaClasesHotel.png) (fuente PlantUML: [DiagramaClases.puml](docs/DiagramaClases.puml))

## Compilar y ejecutar

Desde la carpeta del proyecto, con Java 21:

```bash
javac -d bin src/model/*.java src/view/*.java
java -cp bin view.Main
```

También se puede ejecutar desde VS Code con la extensión *Extension Pack for Java*: abrir `src/view/Main.java` y pulsar **Run**.
