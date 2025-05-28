# 📱 PowerRoutine App 💪

**PowerRoutine** es una aplicación Android desarrollada en **Java** que te ayuda a crear, personalizar y hacer seguimiento de tus rutinas de entrenamiento de forma inteligente y adaptada a tu perfil.  
El backend [ApiRest](https://github.com/carlosbackdev/PowerRoutineApi) está construido con **Spring Boot** y una base de datos **PostgreSQL**, siguiendo buenas prácticas de arquitectura tanto en el frontend como en el backend.

---

## 🚀 ¿Para qué sirve PowerRoutine?

PowerRoutine está diseñada para personas que desean mejorar su entrenamiento con rutinas personalizadas y seguimiento de progreso.  
Permite:

- Registrar tus preferencias y características personales (objetivo, nivel, días disponibles, etc.).
- Recibir recomendaciones automáticas de rutinas de entrenamiento adaptadas a tu perfil.
- Seleccionar las rutinas que más te gusten, y después elegir los ejercicios que vas a realizar en cada una.
- Consultar los detalles de cada ejercicio: series, repeticiones, descanso, material, técnica, músculo implicado, etc.
- Marcar ejercicios y rutinas como completadas y hacer seguimiento semanal de tu avance.
- Modificar tus preferencias y rutinas cuando quieras.
- Todo el flujo está enfocado en la **personalización** y el **seguimiento estructurado** del progreso.

---

## 🏗️ Arquitectura de la Aplicación

### Android App (Java + MVVM)

- **Lenguaje principal:** Java (todas las Activities y lógica central están en Java).
- **Patrón MVVM:** Separación clara entre Model (datos y lógica), View (interfaces), y ViewModel (gestión de estados y lógica de presentación).
- **Componentes principales:**
  - **UserPreferences:** Gestiona las preferencias del usuario (nivel, objetivo, días, etc.) y calcula repeticiones, descansos, etc.
  - **Activities principales:**  
    - `PerfilActivity`: Edición de perfil y preferencias.
    - `RutineSelecetedActivity` y `EjercicesSelectedActivity`: Selección de rutinas y ejercicios.
    - `EjerciceDetailsActivity`: Consulta y registro de detalles de cada ejercicio.
    - `CalendarActivity`: Seguimiento semanal de rutinas completadas.
  - **Persistencia y sincronización:** Conexión al backend mediante Retrofit y comunicación con una API RESTful.
  - **Temas y personalización:** Opciones claro/oscuro mediante preferencias y componentes reutilizables.

#### Flujo típico de usuario

1. **Registro de preferencias:** El usuario define objetivo, nivel, días de entrenamiento, etc.
2. **Selección de rutinas:** La app recomienda rutinas y el usuario escoge cuáles seguir.
3. **Elección de ejercicios:** Dentro de cada rutina, el usuario selecciona los ejercicios disponibles y los guarda.
4. **Seguimiento:** Al realizar los ejercicios, puede marcar series/repeticiones y completar rutinas.
5. **Visualización de progreso:** Calendar y perfil muestran rutinas completadas y detalles de avance.

---

## 🛠️ Backend: Spring Boot + PostgreSQL

- **API RESTful:** Desarrollada con Spring Boot, arquitectura en capas (Controlador, Servicio, Repositorio, DTOs).
- **Base de datos relacional:** PostgreSQL almacena usuarios, rutinas, ejercicios, músculos, objetivos, niveles y registros de progreso.
- **Personalización avanzada:**  
  - Las rutinas se generan según los parámetros del usuario.
  - Cambios en nivel u objetivo modifican automáticamente series, repeticiones y ejercicios sugeridos.
  - Algoritmo para evitar rutinas incompatibles muscularmente en el mismo periodo.
- **Sin registro obligatorio:** Puedes explorar rutinas sin necesidad de crear cuenta.

### Ejemplo de flujo backend

```
Android (Cliente)
   ⬇️
Controlador REST (Spring Boot)
   ⬇️
Servicio (Java)
   ⬇️
Repositorio (JPA/Hibernate)
   ⬇️
Base de datos (PostgreSQL)
   ⬆️
DTOs y JSON para Android
```

---

## 📊 Características Técnicas

- **Lenguaje:** Java (Android, Spring Boot)
- **Arquitectura:** MVVM (frontend), Clean/Layered (backend)
- **Persistencia:** PostgreSQL
- **Comunicación:** Retrofit para llamadas HTTP
- **Gestión de estado:** Preferencias de usuario, seguimiento de rutinas completadas, cambio de tema, etc.
- **UI:** Activities y componentes reutilizables (Views, Navigators, etc.)

---

## ✨ ¿Por qué usar PowerRoutine?

- **Personalización total:** No hay dos usuarios iguales, y tus rutinas tampoco lo serán.
- **Seguimiento fácil:** Marca tus avances y consulta tu progreso semanal.
- **Escalable y mantenible:** Arquitectura profesional tanto en app como backend, pensado para crecer.
- **Experiencia de usuario:** Interfaz intuitiva, cambios de tema y navegación fluida.
- **Open Source:** Inspírate o contribuye en el desarrollo.

---

## 📸 ¿Quieres saber más?

¡Explora el código y la app en este repositorio!  
¿Tienes dudas sobre la arquitectura, el flujo, o quieres colaborar?  
**Contáctame por LinkedIn, estaré encantado de hablar sobre PowerRoutine, arquitectura Android, Java, y más!**

---
## Demo


https://github.com/user-attachments/assets/fa07695d-bd36-46e1-994a-18fe90f30e1c


---

**PowerRoutine** - Personaliza tu entrenamiento, sigue tu progreso, ¡y alcanza tus objetivos!.

---
