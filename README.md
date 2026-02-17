# 📌 TaskManager

## 📖 Descripción del Proyecto

**TaskManager** es un Sistema de Gestión de Tareas desarrollado en Java, cuyo objetivo principal es demostrar la aplicación de buenas prácticas y estándares profesionales de desarrollo.

El proyecto se enfoca en tres pilares fundamentales:

- ✅ Trazabilidad del sistema (Logging)
- ✅ Manejo estructurado de errores (Excepciones personalizadas)
- ✅ Aseguramiento de calidad (Testing con JUnit 5)

---

## 🏗️ Arquitectura del Sistema

El proyecto está organizado bajo una estructura de paquetes que separa claramente las responsabilidades:


### 📦 Descripción de Paquetes

- **model**: Contiene la clase `Tarea`, que representa la entidad principal del sistema.
- **exception**: Define las excepciones personalizadas utilizadas para el control de errores.
- **service**:
    - `TareaService`: Implementa la lógica de negocio.
    - `ValidacionService`: Contiene las reglas de validación.
---

## ⚙️ Implementación Técnica

### 🔎 1. Manejo de Excepciones

Se implementó una estrategia diferenciada para manejar errores del sistema y errores de usuario:

#### 📌 Excepciones Verificadas (Checked)
- `TareaNoEncontradaException`
- Hereda de `Exception`
- Se utiliza cuando el sistema debe manejar obligatoriamente la ausencia de un recurso (ej. buscar una tarea por ID inexistente).

#### 📌 Excepciones No Verificadas (Unchecked)
- `OperacionInvalidaException`
- Hereda de `RuntimeException`
- Se utiliza para errores de validación lógica (IDs negativos, campos vacíos, etc.).

#### 📌 Bloque try-catch-finally
Implementado en la clase principal para:
- Capturar excepciones
- Informar al usuario sin detener la ejecución
- Ejecutar procesos de limpieza en el bloque `finally`

---

### 📝 2. Estrategia de Logging

Se utiliza **SLF4J** como fachada de logging junto con **Log4j2** como implementación.

#### 🔧 Configuración
- Archivo `log4j2.xml`
- Política `RollingFile`
- Segmentación automática por tamaño y fecha

#### 📊 Niveles de Log Implementados
- `DEBUG` → Seguimiento técnico
- `INFO` → Flujo normal de ejecución
- `WARN` / `ERROR` → Manejo de fallos y excepciones

Esto permite una trazabilidad completa del comportamiento del sistema y facilita la depuración en entornos productivos.

---

### 🧪 3. Pruebas Unitarias

Se implementaron pruebas unitarias utilizando **JUnit 5** para garantizar el correcto funcionamiento del sistema.

#### ✔️ Casos cubiertos:
- Creación de tareas
- Listado de tareas
- Eliminación de registros
- Validación de excepciones usando `assertThrows`

Las pruebas aseguran que las funcionalidades principales operen sin errores lógicos.

---

## 🚀 Guía de Instalación y Ejecución

### 📌 Requisitos

- Java JDK 17 o superior
- Maven 3.6+


