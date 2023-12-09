# Guía para Commits Siguiendo el Estándar Conventional Commits

El objetivo de este documento es proveer una serie de lineamientos para asegurar que los mensajes de commit sean claros, útiles y sigan una estructura coherente que nos ayude a mantener un flujo de trabajo eficiente y una base de código mantenible. Utilizaremos el estándar de los **Conventional Commits**.

## ¿Qué son los Conventional Commits?

Los Conventional Commits son una convención para los mensajes de commit que enlaza la descripción del commit con el seguimiento de versiones semánticas (semver) y la generación de notas de versiones. Proporciona un conjunto de reglas simples para crear un historial de commits explícito.

## Estructura del Commit

Un commit Conventional tiene la siguiente estructura:

```
<type>[optional: (scope)]: <description>

[optional: body]

[optional: footer]
```

Cada elemento tiene un propósito específico y algunas reglas:

- **Type**: Debe ser uno de los siguientes:
    - **feat**: Una nueva característica
    - **fix**: Una corrección de errores
    - **change**: Cambios en el código.
    - **docs**: Cambios en la documentación
    - **style**: Cambios que no afectan el significado del código (espacios en blanco, formateo, falta de puntos y comas, etc)
    - **refactor**: Cambio de código que ni añade una característica ni arregla un error
    - **perf**: Cambios de código que mejoran el rendimiento
    - **test**: Adición o modificación de pruebas
    - **revert**: Revertir un commit anterior.
    - **release**: Cambios relacionados con la preparación y lanzamiento de una versión del proyecto.
    - **chore**: Cambios en el proceso de build o herramientas auxiliares, cambios en la estructura, tareas de mantenimiento, etc.

- **Scope**: Es opcional y especifica el lugar del código donde tiene efecto el commit (por ejemplo, componente o nombre del archivo).

- **Description**: Una breve descripción de los cambios, en tiempo pasado. Por ejemplo, "añadido" en lugar de "añade" o "corregido" en lugar de "corrige".

- **Body**: Es opcional. Se utiliza para explicar en más detalle el motivo de los cambios.

- **Footer**: Es opcional. Se utiliza para referenciar issues relacionados, como bugs que se cierran con el commit.

## Indicación de Cambios que Rompen la Compatibilidad

En el estándar Conventional Commits, utilizamos el término `BREAKING CHANGE` o el símbolo `!` para indicar que el commit introduce un cambio que rompe la compatibilidad con versiones anteriores. Esto significa que el cambio podría hacer que el código deje de funcionar como se esperaba si se utilizaban versiones anteriores del mismo. Por lo general, estos son cambios significativos en la API, la funcionalidad, las dependencias, o cualquier otro aspecto que requiera que los usuarios modifiquen su código cuando actualizan a la nueva versión.

Aquí hay dos formas de indicar un cambio que rompe la compatibilidad en Conventional Commits:

1. **Usando el símbolo `!`**: Este símbolo se coloca inmediatamente después del type y/o el scope, pero antes de los dos puntos. Por ejemplo:

    ```bash
    feat!: remove deprecated method
    ```

   o

    ```bash
    refactor(runtime)!: drop support for Node 10
    ```

2. **Usando el término `BREAKING CHANGE`**: Esto se coloca al principio de un nuevo párrafo en el cuerpo del mensaje del commit o en el pie de página. Debe estar seguido de dos puntos y un espacio, luego de un resumen del cambio que rompe la compatibilidad. Por ejemplo:

    ```bash
    feat: remove deprecated method

    BREAKING CHANGE: The deprecated `doSomething` method was removed. Use the `doSomethingElse` method instead.
    ```

Estos cambios que rompen la compatibilidad se reflejan en el número de versión del software de acuerdo a la versión semántica ([SemVer](https://semver.org/)). Un cambio que rompe la compatibilidad aumenta la versión `MAJOR` (por ejemplo, pasar de la versión 1.0.0 a la 2.0.0).

---

Sigue estos lineamientos para mantener un historial de commits limpio y fácil de entender para todos los contribuidores del proyecto.
