# Respuestas 
## 1. Añadir un nuevo tipo de ataque
### ¿Qué problema te encuentras al añadir "Meteoro"?
Que tendría que modificar la función y añadir otro if.
### ¿Qué pasa si mañana piden 10 ataques más?
Tendría que modificarlo 10 veces, siendo totalmente ineficiente.
### ¿Qué patrón permitiría añadir ataques sin modificar CombatEngine?
El patrón **Factory Method**.Permite delegar la creación de ataques a una fábrica (AttackFactory) y a clases individuales que implementan AttackI, de modo que CombatEngine nunca se modifica al añadir nuevos ataques.

## 2. Añadir una nueva fórmula de daño
### ¿Qué principio SOLID se viola al añadir otro case en el switch?
Viola el principio SOLID **Open to extend, closed to modify**, ya que con los switch deberíamos estar modificando todo el rato dicho código.
### ¿Qué patrón permitiría tener fórmulas de daño intercambiables sin tocar el código existente?
El patrón Strategy

## 3. Crear personajes con muchas estadísticas
### ¿Qué problema tiene un constructor con muchos parámetros?
El problema principal es que cuando lees el constructor te puedes perder a la hora de saber qué atributo es cada uno.
### ¿Cómo harías para que new Character(...) sea legible cuando hay valores por defecto?
Usando el patrón Builder. En lugar de un constructor con muchos parámetros, se encadenan métodos con nombre que indican qué valor se está asignando, y los valores opcionales simplemente no se llaman.
### ¿Qué patrón permite construir objetos complejos paso a paso?
El patrón Builder.

## 4. Un único almacén de batallas
### ¿Qué pasaría si dos clases crean su propio BattleRepository sin el static?
Se crearían instancias distintas de BattleRepository, cada una con su propio Map de batallas, por lo que no compartirían los datos almacenados.
### ¿Cómo asegurar que toda la aplicación use la misma instancia de almacenamiento?
Creando una sola instancia que se comparte con todos, usando el Singleton.
### ¿Qué patrón garantiza una única instancia de una clase?
El patrón Singleton

## 5. Recibir datos de un API externo
### ¿Qué problema hay en poner la lógica de conversión en el controller?
Se viola el Principio de Responsabilidad Única (SRP): el controller solo debería recibir peticiones y devolver respuestas, no encargarse de convertir formatos externos a nuestro dominio.
### ¿Cómo aislar la conversión "formato externo → nuestro dominio" para no ensuciar el controller?
Crearíamos una instancia la cual recibe los datos en formato externo y los convierte en objetos de nuestro dominio, así delegando el controller la conversión al adapter.
### ¿Qué patrón permite que un objeto "adaptado" se use como si fuera uno de los nuestros?
El patrón adapter



