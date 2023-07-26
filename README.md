# Desafío coopeuch backend

A continuación se presenta la solución (diseño e implementación) del desafío Coopeuch correspondiente al backend. El desafío consta de una parte en el frontend y una parte en el backend. El enunciado del desafío se encuentra [AQUÍ](docs/images/challenge.png)

## Diseño

Se trata de dar solución al problema con un enfoque de **"design-first"**. Para el diseño de la solución se comienza definiendo las entidades del dominio, encontrándose solo una: Tareas

![Diagrama de la solución](docs/images/challenge-diagram-01.png | width=50)

Se establecen algunos atributos iniciales que puede tener la entidad de negocio Tarea, como se muestra en la siguiente imagen:

![Diagrama de la solución](docs/images/challenge-diagram-02.png  | width=50)

Según el enunciado se deben realizar operaciones CRUD sobre esta entidad y se deben exponer como API REST. También se deben realizar algunas validaciones en los datos de entrada, persistir los datos y realizar pruebas.

Con lo anterior se puede realizar un diagrama de secuencia que permita visualizar el flujo de estas operaciones dentro del sistema para su mejor entendimiento. A continuación, se muestra un diagrama de secuencia con las operaciones CRUD para la gestión de las tareas:

![Diagrama de secuencia](docs/images/challenge-sequence-01.png)
![Diagrama de secuencia](docs/images/challenge-sequence-02.png)
![Diagrama de secuencia](docs/images/challenge-sequence-03.png)
![Diagrama de secuencia](docs/images/challenge-sequence-04.png)

Una vez realizado el diagrama de secuencia se puede concluir que no existen lógica de negocio compleja sino que corresponde a un CRUD simple. Sin embargo, se debe validar la información de entrada y controlar posibles errores.

Con respecto a las entradas y salidas del sistema se busca alcanzar el máximo nivel de madurez para la API, es decir, utilizar los métodos y verbos HTTP para las distintas operaciones e incluir el principio HATEOS. También, se busca una salida consistente para ser tratada por el cliente de manera simple.

## OPEN API (Swagger)

Con lo anterior y considerando que solo existen operaciones CRUD para la gestión de tareas, es posible documentar la API.

En el siguiente [link](https://app.swaggerhub.com/apis-docs/FAQ_CODES/coopeuch_api_test/1.0.0) se encuentra la documentación.

![Enlace Swagger](docs/images/challenge-open-api.png)

Este es un intento previo para probar el funcionamiento y visualización de los objetos que la API retorna, sin encontrarse aún implementada.

En la implementación de la solución se utilizará las herramientas que provee Sprintboot para la documentación de la API a través de Swagger.


## DETALLE DE DISEÑO

Con la información obtenida hasta ahora se puede realizar un diseño detallado de los objetos involucrados en la solución del problema. A continuación, se muestra un esquema de los objetos a crer y su interacción:

![Diagrama de la solución](docs/images/challenge-diagram-03.png)