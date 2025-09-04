This file is a merged representation of the entire codebase, combined into a single document by Repomix.

# File Summary

## Purpose
This file contains a packed representation of the entire repository's contents.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.

## File Format
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  a. A header with the file path (## File: path/to/file)
  b. The full contents of the file in a code block

## Usage Guidelines
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.

## Notes
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Files are sorted by Git change count (files with more changes are at the bottom)

# Directory Structure
```
.github/
  workflows/
    maven.yml
  copilot-instructions.md
src/
  main/
    java/
      unrn/
        model/
          AgendaTelefonica.java
          Contacto.java
          NumeroTelefono.java
  test/
    java/
      unrn/
        model/
          AgendaTelefonicaTest.java
          ContactoTest.java
          NumeroTelefonoTest.java
.gitignore
pom.xml
README.md
spotbugs-exclude.xml
```

# Files

## File: .gitignore
````
target/
!.mvn/wrapper/maven-wrapper.jar
!**/src/main/**/target/
!**/src/test/**/target/

### IntelliJ IDEA ###
.idea/modules.xml
.idea/jarRepositories.xml
.idea/compiler.xml
.idea/libraries/
*.iws
*.iml
*.ipr

### Eclipse ###
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
.sts4-cache

### NetBeans ###
/nbproject/private/
/nbbuild/
/dist/
/nbdist/
/.nb-gradle/
build/
!**/src/main/**/build/
!**/src/test/**/build/

### VS Code ###
.vscode/

### Mac OS ###
.DS_Store
````

## File: spotbugs-exclude.xml
````xml
<FindBugsFilter>
    <Match>
        <Bug pattern="CT_CONSTRUCTOR_THROW"/>
        <Class name="unrn.model.Contacto"/>
    </Match>
    <Match>
        <Bug pattern="CT_CONSTRUCTOR_THROW"/>
        <Class name="unrn.model.NumeroTelefono"/>
    </Match>
</FindBugsFilter>
````

## File: src/test/java/unrn/model/NumeroTelefonoTest.java
````java
package unrn.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumeroTelefonoTest {

  @Test
  @DisplayName("Crear número válido instancia correctamente el objeto")
  void crearNumero_valido_instanciaCorrecta() {
    // Setup: Preparar el escenario
    // Ejercitación: Ejecutar la acción a probar
    NumeroTelefono numero = new NumeroTelefono("2991", "1234567");
    // Verificación: Verificar el resultado esperado
    assertNotNull(numero, "El número de teléfono debe instanciarse correctamente");
  }

  @Test
  @DisplayName("Crear número con prefijo nulo lanza excepción")
  void crearNumero_prefijoNulo_lanzaExcepcion() {
    // Setup: Preparar el escenario
    // Ejercitación y Verificación: Ejecutar la acción a probar y verificar el resultado esperado
    var ex = assertThrows(RuntimeException.class, () -> new NumeroTelefono(null, "1234567"));
    assertEquals(
        NumeroTelefono.ERROR_CODIGO_INVALIDO,
        ex.getMessage(),
        "Debe lanzar excepción por prefijo nulo");
  }

  @Test
  @DisplayName("Crear número con número nulo lanza excepción")
  void crearNumero_numeroNulo_lanzaExcepcion() {
    // Setup: Preparar el escenario
    // Ejercitación y Verificación: Ejecutar la acción a probar y verificar el resultado esperado
    var ex = assertThrows(RuntimeException.class, () -> new NumeroTelefono("2991", null));
    assertEquals(
        NumeroTelefono.ERROR_NUMERO_INVALIDO,
        ex.getMessage(),
        "Debe lanzar excepción por número nulo");
  }

  @Test
  @DisplayName("Crear número con número demasiado largo lanza excepción")
  void crearNumero_numeroLargo_lanzaExcepcion() {
    // Setup: Preparar el escenario
    String numeroLargo = "12345678";
    // Ejercitación y Verificación: Ejecutar la acción a probar y verificar el resultado esperado
    var ex = assertThrows(RuntimeException.class, () -> new NumeroTelefono("2991", numeroLargo));
    assertEquals(
        NumeroTelefono.ERROR_NUMERO_LARGO,
        ex.getMessage(),
        "Debe lanzar excepción por número largo");
  }

  @Test
  @DisplayName("Crear número con número demasiado corto lanza excepción")
  void crearNumero_numeroCorto_lanzaExcepcion() {
    // Setup: Preparar el escenario
    String numeroCorto = "12345";
    // Ejercitación y Verificación: Ejecutar la acción a probar y verificar el resultado esperado
    var ex = assertThrows(RuntimeException.class, () -> new NumeroTelefono("2991", numeroCorto));
    assertEquals(
        NumeroTelefono.ERROR_NUMERO_CORTO,
        ex.getMessage(),
        "Debe lanzar excepción por número corto");
  }

  @Test
  @DisplayName("Crear número válido retorna codigo area + numero")
  void crearNumberoValido_retornaCodigoMasNumero() {
    var numero = new NumeroTelefono("2652", "788987");
    assertEquals("2652 788987", numero.numero());
  }
}
````

## File: .github/workflows/maven.yml
````yaml
name: Java CI with Maven

permissions:
  contents: read
  pages: write
  id-token: write

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  security:
    runs-on: ubuntu-latest
    needs: coverage
    steps:
      - uses: actions/checkout@v4
      - name: Run Trivy vulnerability scanner
        uses: aquasecurity/trivy-action@master
        with:
          scan-type: fs
          ignore-unfixed: true
          severity: HIGH,CRITICAL,LOW
          format: table
          output: trivy-report.txt
      - name: Mostrar reporte Trivy en consola
        run: cat trivy-report.txt
      - name: Upload Trivy Report
        uses: actions/upload-artifact@v4
        with:
          name: trivy-report
          path: trivy-report.txt
  install:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 23
        uses: actions/setup-java@v4
        with:
          java-version: '23'
          distribution: 'temurin'
          cache: maven
      - name: Maven Install
        run: mvn install --file pom.xml

  test:
    runs-on: ubuntu-latest
    needs: install
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 23
        uses: actions/setup-java@v4
        with:
          java-version: '23'
          distribution: 'temurin'
          cache: maven
      - name: Maven Test
        run: mvn test --file pom.xml

  coverage:
    runs-on: ubuntu-latest
    needs: install
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 23
        uses: actions/setup-java@v4
        with:
          java-version: '23'
          distribution: 'temurin'
          cache: maven
      - name: Run JaCoCo Coverage
        run: mvn verify jacoco:report --file pom.xml
      - name: Upload JaCoCo XML
        uses: actions/upload-artifact@v4
        with:
          name: jacoco-xml
          path: target/site/jacoco/jacoco.xml

  sonar:
    runs-on: ubuntu-latest
    needs: [test, coverage]
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 23
        uses: actions/setup-java@v4
        with:
          java-version: '23'
          distribution: 'temurin'
          cache: maven
      - name: Download JaCoCo XML
        uses: actions/download-artifact@v4
        with:
          name: jacoco-xml
          path: target/site/jacoco/
      - name: SonarCloud Analysis
        run: mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=hmunoz_taller-cicd -Dsonar.token=c90fcfa40f9a9e3ab4ffe20e1aa190aa792aff9e -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
  deploy:
    environment:
      name: github-pages
      url: ${{ steps.deployment.outputs.page_url }}
    runs-on: ubuntu-latest
    needs: [sonar, security]
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 23
        uses: actions/setup-java@v4
        with:
          java-version: '23'
          distribution: 'temurin'
          cache: maven
      - name: Run JaCoCo Coverage
        run: mvn test jacoco:report --file pom.xml
      - name: Setup Pages
        uses: actions/configure-pages@v5
      - name: Upload artifact
        uses: actions/upload-pages-artifact@v3
        with:
          path: 'target/site/jacoco'
      - name: Deploy to GitHub Pages
        id: deployment
        uses: actions/deploy-pages@v4
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: target/site/jacoco
````

## File: .github/copilot-instructions.md
````markdown
# Descripcion general

- Este es un proyecto escrito en Java 23 utilizando el paradigma orientado a objetos.
- El modelo de dominio es donde se implementan todas las reglas de negocio.

## Estructura de Carpetas

- Es un proyecto maven clásico.
- src/main/java para los fuentes
- en unrn.model van las clases del modelo de dominio
- src/test/java para los tests

# Modelo de Dominio

- Nunca generes getters ni setters. No quiero objetos anémicos
- Si necesitas un getter de una lista encapsulada, devolvela solo lectura.
- Los objetos se inicializan siempre por constructor, para generar siempre objetos completos, listos para usar.
- Pone todas las validaciones en el constructor, siempre que sea posible, para instanciar objetos válidos.
- Cuando lances exceptions, siempre usa RuntimeException. Y el mensaje de error ponelo en una constante estática con
  visibilidad de paquete para poder usarla en los tests después.
- Cada validación de un constructor hacela en un método de instancia privado que se llama assert{LO_QUE_ESTAS_VALIDANDO}
- Usa el principio tell don't ask siempre que sea posible. Por ejemplo: esto es INCORRECTO:

```java
private void assertContactoUnico(Contacto contacto) {
    for (Contacto c : contactos) {
        if (c.nombre().equals(contacto.nombre())) {
            throw new RuntimeException(ERROR_CONTACTO_DUPLICADO);
        }
    }
}
```

## Testing Automatizado con JUnit 5.13

### 1. Nombre claro y descriptivo

- Nombra los métodos de test siguiendo este patrón:  
  **`cuestionATestear_resultadoEsperado`**
- Agrega la anotacion `@DisplayName` explicando en lenguaje natural el objetivo del test con este formato:

```java
  @DisplayName("CuestionATestear resultadoEsperado")
  ```

### 2. No uses Mock, Stubs o Fakes

- Los tests unitarios corren todos en memoria ejutando el código real.
- No uses mocks, stubs o fakes, ya que no son necesarios y complican la lectura del test.
- Solo usaremos mocks en consumo de servicios externos (pagos online, emails, etc).

### 3. Estructura del test

- Cada test debe tener la siguiente estructura:
  ```java
  @Test
  @DisplayName("Nombre del test")
  void testNombreDelMetodo() {
      // Setup: Preparar el escenario
      // Ejercitación: Ejecutar la acción a probar
      // Verificación: Verificar el resultado esperado
  }
  ```
- Incorpora comentarios de la estructura del test para facilitar la comprensión del código.

### 4. Un solo caso de prueba por test

- Cada test debe evaluar un único caso de prueba.
- Si necesitas evaluar múltiples casos, crea un test separado para cada uno.

### 5. Usa Asserts claros y descriptivos

- Utiliza aserciones claras y descriptivas agregando mensajes que expliquen el propósito de la verificación.
    - Por ejemplo:
      ```java
      assertEquals(expectedValue, actualValue, "El valor esperado no coincide con el valor actual");
      ```

### 6. Probá Casos límites

- Valores nulos (null)
- Listas vacías o inputs vacíos
- Números negativos o fuera de rango
- Estados inválidos o excepciones esperadas

### 7. Verifica excepciones correctamente

- Utiliza `assertThrows` para verificar que se lanza la excepción esperada.
- En general mi código lanzará RuntimeException, pero verificaló primero.
- Ejemplo:
  ```java
    var ex = assertThrows(RuntimeException.class, () -> {
            // Código que debería lanzar la excepción 
        });
        assertEquals("Mensaje de error esperado", ex.getMessage());
    ```
- Sobre el "Mensaje de error esperado", antes de poner una constante dura en el test verifica que la constante definida
  en el codigo real y si es asi úsala.

### 8. Testing de Integración

- Usamos test-data.sql como set up inicial de la BD.
- Siempre usar como beforeEach el truncate ya que resetea la base de datos despues de cada test que corre:

```java
void beforeEach() {
    emf.getSchemaManager().truncate();
}
```

- No incluyas casos de tests que pueden ser testeados con tests unitarios.
````

## File: src/main/java/unrn/model/AgendaTelefonica.java
````java
package unrn.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AgendaTelefonica {
  private final List<Contacto> contactos;

  public AgendaTelefonica() {
    contactos = new ArrayList<>();
  }

  public void agregarContacto(String nombre, NumeroTelefono telefono) {
    var first = contactos.stream().filter(c -> c.esDe(nombre)).findFirst();
    first.ifPresentOrElse(
        (c) -> {
          c.nuevoNumero(telefono);
        },
        () -> {
          Contacto nuevo = new Contacto(nombre);
          nuevo.nuevoNumero(telefono);
          contactos.add(nuevo);
        });
  }

  List<Contacto> listarContactos() {
    return Collections.unmodifiableList(contactos);
  }
}
````

## File: src/main/java/unrn/model/NumeroTelefono.java
````java
package unrn.model;

public class NumeroTelefono {
  static final String ERROR_CODIGO_INVALIDO = "El código de área debe tener 4 dígitos";
  static final String ERROR_NUMERO_LARGO = "El número no debe tener más de 7 caracteres";
  static final String ERROR_NUMERO_CORTO = "El número debe tener al menos 6 caracteres";
  static final String ERROR_NUMERO_INVALIDO = "El número no puede ser null";

  private final String codigoArea;
  private final String numero;

  NumeroTelefono(String codigoArea, String numero) {
    assertCodigoArea(codigoArea);
    assertNumberInvalido(numero);
    assertNumeroLargo(numero);
    assertNumeroCorto(numero);
    this.codigoArea = codigoArea;
    this.numero = numero;
  }

  private void assertNumberInvalido(String numero) {
    if (numero == null) {
      throw new RuntimeException(ERROR_NUMERO_INVALIDO);
    }
  }

  public String numero() {
    return codigoArea + " " + numero;
  }

  private void assertCodigoArea(String codigoArea) {
    if (codigoArea == null
        || codigoArea.length() != 4
        || !codigoArea.chars().allMatch(Character::isDigit)) {
      throw new RuntimeException(ERROR_CODIGO_INVALIDO);
    }
  }

  private void assertNumeroLargo(String numero) {
    if (numero.length() > 7) {
      throw new RuntimeException(ERROR_NUMERO_LARGO);
    }
  }

  private void assertNumeroCorto(String numero) {
    if (numero.length() < 6) {
      throw new RuntimeException(ERROR_NUMERO_CORTO);
    }
  }
}
````

## File: src/test/java/unrn/model/AgendaTelefonicaTest.java
````java
package unrn.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AgendaTelefonicaTest {

  @Test
  @DisplayName("Agregar un contacto nuevo lo almacena en la agenda")
  void agregarContactoNuevo_contactoSeAgrega() {
    // Setup
    AgendaTelefonica agenda = new AgendaTelefonica();
    NumeroTelefono telefono = new NumeroTelefono("2991", "1234567");
    // Ejercitación
    agenda.agregarContacto("Juan", telefono);
    // Verificación
    List<Contacto> contactos = agenda.listarContactos();
    assertEquals(1, contactos.size(), "Debe haber un contacto en la agenda");
    assertTrue(contactos.get(0).esDe("Juan"), "El contacto agregado debe ser 'Juan'");
  }

  @Test
  @DisplayName("Agregar un número a un contacto existente lo agrega correctamente")
  void agregarNumeroAContactoExistente_numeroSeAgrega() {
    // Setup
    AgendaTelefonica agenda = new AgendaTelefonica();
    NumeroTelefono telefono1 = new NumeroTelefono("2991", "1234567");
    NumeroTelefono telefono2 = new NumeroTelefono("2991", "7654321");
    agenda.agregarContacto("Ana", telefono1);
    // Ejercitación
    agenda.agregarContacto("Ana", telefono2);
    // Verificación
    List<Contacto> contactos = agenda.listarContactos();
    assertEquals(1, contactos.size(), "Debe haber un solo contacto");
  }

  @Test
  @DisplayName("Listar contactos en agenda vacía devuelve lista vacía")
  void listarContactos_agendaVacia_listaVacia() {
    // Setup
    AgendaTelefonica agenda = new AgendaTelefonica();
    // Ejercitación
    List<Contacto> contactos = agenda.listarContactos();
    // Verificación
    assertTrue(contactos.isEmpty(), "La lista debe estar vacía si no hay contactos");
  }

  @Test
  @DisplayName("La lista de contactos es solo lectura")
  void listarContactos_listaSoloLectura() {
    // Setup
    AgendaTelefonica agenda = new AgendaTelefonica();
    NumeroTelefono telefono = new NumeroTelefono("2991", "1234567");
    agenda.agregarContacto("Pedro", telefono);
    List<Contacto> contactos = agenda.listarContactos();
    // Verificación
    assertThrows(
        UnsupportedOperationException.class,
        () -> contactos.add(new Contacto("Otro")),
        "No se puede modificar la lista de contactos");
  }
}
````

## File: src/test/java/unrn/model/ContactoTest.java
````java
package unrn.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContactoTest {

  @Test
  @DisplayName("Crear contacto con nombre válido lo instancia correctamente")
  void crearContacto_nombreValido_instanciaCorrecta() {
    // Setup & Ejercitación
    Contacto contacto = new Contacto("Juan");
    // Verificación
    assertTrue(contacto.esDe("Juan"), "El contacto debe tener el nombre 'Juan'");
  }

  @Test
  @DisplayName("Crear contacto con nombre mayor a 35 caracteres lanza excepción")
  void crearContacto_nombreMuyLargo_lanzaExcepcion() {
    // Setup
    String nombreLargo = "a".repeat(36);
    // Ejercitación & Verificación
    var ex = assertThrows(RuntimeException.class, () -> new Contacto(nombreLargo));
    assertEquals(
        Contacto.ERROR_NOMBRE_LARGO, ex.getMessage(), "Debe lanzar excepción por nombre largo");
  }

  @Test
  @DisplayName("Crear contacto con nombre menor a 2 caracteres lanza excepción")
  void crearContacto_nombreMuyCorto_lanzaExcepcion() {
    // Setup
    String nombreCorto = "A";
    // Ejercitación & Verificación
    var ex = assertThrows(RuntimeException.class, () -> new Contacto(nombreCorto));
    assertEquals(
        Contacto.ERROR_NOMBRE_CORTO, ex.getMessage(), "Debe lanzar excepción por nombre corto");
  }

  @Test
  @DisplayName("Agregar un nuevo número a un contacto lo almacena correctamente")
  void nuevoNumero_agregaNumero() {
    // Setup
    Contacto contacto = new Contacto("Ana");
    NumeroTelefono telefono = new NumeroTelefono("2991", "1234567");
    // Ejercitación
    contacto.nuevoNumero(telefono);
    // Verificación
    assertTrue(contacto.esDe("Ana"), "El contacto debe seguir siendo 'Ana'");
  }

  @Test
  @DisplayName("El método es devuelve true si el nombre coincide")
  void es_De_nombreCoincide_true() {
    // Setup
    Contacto contacto = new Contacto("Pedro");
    // Ejercitación & Verificación
    assertTrue(contacto.esDe("Pedro"), "Debe devolver true si el nombre coincide");
  }

  @Test
  @DisplayName("El método es devuelve false si el nombre no coincide")
  void es_De_nombreNoCoincide_false() {
    // Setup
    Contacto contacto = new Contacto("Pedro");
    // Ejercitación & Verificación
    assertFalse(contacto.esDe("Juan"), "Debe devolver false si el nombre no coincide");
  }
}
````

## File: src/main/java/unrn/model/Contacto.java
````java
package unrn.model;

import java.util.ArrayList;
import java.util.List;

public class Contacto {
  static final String ERROR_NOMBRE_LARGO = "El nombre no debe tener más de 35 caracteres";
  static final String ERROR_NOMBRE_CORTO = "El nombre debe tener al menos 2 caracteres";
  private final String nombre;
  private final List<NumeroTelefono> telefonos;

  public Contacto(String nombre) {
    assertNombreLargo(nombre);
    assertNombreCorto(nombre);
    this.nombre = nombre;
    this.telefonos = new ArrayList<>();
  }

  public void nuevoNumero(NumeroTelefono numeroTelefono) {
    this.telefonos.add(numeroTelefono);
  }

  private void assertNombreLargo(String nombre) {
    if (nombre.length() > 35) {
      throw new RuntimeException(ERROR_NOMBRE_LARGO);
    }
  }

  private void assertNombreCorto(String nombre) {
    if (nombre.length() < 2) {
      throw new RuntimeException(ERROR_NOMBRE_CORTO);
    }
  }

  public boolean esDe(String nombre) {
    return this.nombre.equals(nombre);
  }
}
````

## File: pom.xml
````xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>unrn.aop</groupId>
    <artifactId>taller-persistencia-apiweb</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <sonar.organization>hmunoz</sonar.organization>

        <maven.compiler.source>23</maven.compiler.source>
        <maven.compiler.target>23</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- tests -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.13.4</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>5.13.4</version>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M9</version>
            </plugin>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.13</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report-and-check</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>report</goal>
                            <goal>check</goal>
                        </goals>
                        <configuration>
                            <rules>
                                <rule>
                                    <element>BUNDLE</element>
                                    <limits>
                                        <limit>
                                            <counter>LINE</counter>
                                            <value>COVEREDRATIO</value>
                                            <minimum>0.80</minimum>
                                        </limit>
                                        <limit>
                                            <counter>BRANCH</counter>
                                            <value>COVEREDRATIO</value>
                                            <minimum>0.80</minimum>
                                        </limit>
                                    </limits>
                                </rule>
                            </rules>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.sonarsource.scanner.maven</groupId>
                <artifactId>sonar-maven-plugin</artifactId>
                <version>5.1.0.4751</version>
            </plugin>

             <plugin>
                <groupId>com.diffplug.spotless</groupId>
                <artifactId>spotless-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <phase>verify</phase>
                        <goals><goal>check</goal></goals>
                    </execution>
                </executions>
                <configuration>
                    <java><googleJavaFormat/></java>
                </configuration>
            </plugin>

            <plugin>
                <groupId>com.github.spotbugs</groupId>
                <artifactId>spotbugs-maven-plugin</artifactId>
                <configuration>
                    <excludeFilterFile>${project.basedir}/spotbugs-exclude.xml</excludeFilterFile>
                </configuration>
                <executions>
                    <execution>
                        <phase>verify</phase>
                        <goals><goal>check</goal></goals>
                    </execution>
                </executions>
            </plugin>

        </plugins>
    </build>
</project>
````

## File: README.md
````markdown
# Taller - Persistencia, Servicios Web y Testing Automatizado

## Diseño Bottom-up vs Top-Down

El diseño Top-Down consiste en comenzar con una visión general del sistema y descomponerla en partes más pequeñas y
específicas. Primero se define la arquitectura, luego se diseñan los componentes individuales.

El diseño Bottom-up consiste en construir primero componentes individuales o de bajo nivel (en Objetos es el Modelo de
Dominio), y luego integrarlos con compontes de más alto nivel formando subsistemas y finalmente el sistema completo.

## TDD Inside-out vs Outside-in

El enfoque **inside-out** comienza escribiendo tests unitarios de bajo nivel sobre las clases del dominio, y
gradualmente se construye hacia fuera, integrando otros componentes del sistema.

- Se empieza por el **núcleo de la lógica de negocio**. En objetos, el modelo de dominio.
- Los tests unitarios guían la implementación de clases individuales.
- Luego se agregan capas externas como servicios, controladores, APIs.
- Conocido como: método clásico, Chicago school.
- **Menos riesgo** de escribir lógica fuera del modelo de dominio.
- Poca necesidad de usar fakes/mocks.

El enfoque **outside-in** comienza escribiendo tests de aceptación o de alto nivel desde el punto de vista del usuario o
cliente. Luego se escriben los tests de colaboración entre objetos (con mocks) para guiar el diseño interno.

- Se parte del **comportamiento visible del sistema**.
- Primero se escriben tests de aceptación o de capa externa (como APIs o UI).
- Se crean objetos simulados (*fakes*) para las dependencias internas aún no implementadas.
- El diseño interno emerge a medida que se satisfacen esos contratos.
- Conocido como: London school, Mockist style
- Aparece en el libro *Growing Object-Oriented Software, Guided by Tests*, de Steve Freeman y Nat Pryce.

## Requerimientos (Contactos Telefónicos)

- Un contacto conoce una lista de números de teléfono
- Un contacto posee un nombre que no debe tener más de 35 caracteres ni menos de 2.
- Un número de teléfono tiene un código de área y el número en sí.
- El código de áre tiene 4 dígitos el número un máximo de 7 caracteres y mínimo de 6.
- Una agenda conoce todos sus contactos y permite listarlos.
- La agenda permite agregar contactos.
- No deben existir contactos con el mismo nombre.

## Testing Unitario

- Usamos JUnit
- Nombre claro y descriptivo de la forma: *cuestionATestear_resultadoEsperado*
- Estructura del test:
    - Setup: Preparar el escenario
    - Ejercitación: Ejecutar la acción a probar
    - Verificación: Comprobar el resultado esperado
- Utiliza mensajes que expliquen el propósito de la verificación.
    - Por ejemplo:
      ```java
      assertEquals(expectedValue, actualValue, "El valor esperado no coincide con el valor actual");
      ```
- Verifica excepciones correctamente
    - Utiliza `assertThrows` para verificar que se lanza la excepción esperada.
    - Ejemplo:
      ```java
        var ex = assertThrows(RuntimeException.class, () -> {
                // Código que debería lanzar la excepción 
            });
            assertEquals("Mensaje de error esperado", ex.getMessage());
        ```

## Persistencia

- Para persistencia usaremos JPA 3.2
  y [Hibernate 7](https://docs.jboss.org/hibernate/orm/7.0/introduction/html_single/Hibernate_Introduction.html)

- Agregamos dependencias en pom.xml
- Definimos cuál clase representa el sistema.
    - Será `AgendaTelefonica`
    - No la vamos a mapear como entidad porque manejar los contactos como collecion mapeada uno a muchos, dado que la
      cantidad de contactos puede ser grande sabemos que no performa bien.
    - Esta clase representa la entrada a la lógica de negocios del sistema.
    - Responsabilidades:
        - Gestiona las Transacciones
        - Sus servicios reciben tipos primitivos, o estructuras de datos simples.
        - Crea instancias del modelo, invoca sus servicios y los coordina.
        - Persiste o remueve si es necesario.
- Agrego Mapeos
    - Entidades con Id`@Entity`, `@Id`
    - Lombok: `@NoArgsConstructor(access = AccessLevel.PROTECTED)`, `@Getter(AccessLevel.PRIVATE)`, `@Setter(
      AccessLevel.PRIVATE)`
    - Y relaciones.
- Al implementar `AgendaTelefonica.agregarContacto(...)`
    - Se vuelve necesario validar el nombre de contacto cuya validación se encuentra en `Contacto`.
    - ¿Cómo reuso esa validación? Con un value object:`NombreDeContacto`.
- Al implementar `AgendaTelefonica.listarContactos()`
    - No puedo devolver grafos de objetos proxieados.
    - Además tengo que paginar si devuelvo colecciones.

## Testing Integracion

- [Hibernate 7 Docs](https://docs.jboss.org/hibernate/orm/7.0/introduction/html_single/Hibernate_Introduction.html#testing).
- ¿Qué testeamos aca?
    - End to end desde la clase de entrada a mi modelo, hasta la BD
    - Cambiamos la BD real para usar una en memoria (para que sea más rapido).
    - Cada test debe iniciar con la BD en el mismo estado (no hay dependencia entre los tests idealmente).
    - No re-testear lógica cubierta por tests del modelo.
    - Verifica persistencia real y recuperación de objetos de la BD

### Repositorios

> For each type of object that needs global access, create an object that can provide the illusion of an in-memory
> collection of all objects of that type. Set up access through a well-known global interface. Provide methods to *add*
> and *remove* objects, which will encapsulate the actual insertion or removal of data in the data store. Provide
> methods that *select objects based on some criteria* and return *fully instantiated objects* or collections of objects
> whose attribute values meet the criteria, thereby encapsulating the actual storage and query technology. Provide
> REPOSITORIES only for AGGREGATE roots that actually need direct access. Keep the client focused on the model,
> delegating all object storage and access to the REPOSITORIES. **Eric Evans DDD Book**.

- Collection-like interface. Con semántica de un Set (sin repetidos).
    - add(Contacto contacto)
    - remove(Contacto contacto)
    - Optional<Contacto> findByName(String name)
    - Optional<Contacto> findById(Long id)
    - List<Contacto> findXXX(...)
- Un Repository por agregate root.
- Se instancian recibiendo la transacción iniciada por quien lo inova.
- Otra idea clave es que no necesitas "volver a guardar" los objetos modificados que ya están en el Repositorio.
  Piensa nuevamente en cómo modificarías un objeto que forma parte de una colección. En realidad, es muy simple: solo
  recuperarías de la colección la referencia al objeto que deseas modificar y luego le pedirías al objeto que ejecute
  algún comportamiento de transición de estado invocando un método de comando. Implementing Domain Driven Design Vaughn
  Vernon.
    - Esto es posible por persistencia por alcance. No tiene que ver con el patron repositorio, sino que tiene que ver
      con el ORM utilizado para implementar el repositorio.

## Servicios Web

En términos de arquitectura de software, un `servicio` es una aplicación o proceso que se encuentra *escuchando* en un
determinado host y puerto. Esperando recibir solicitudes de otros programas (clientes).
Un **servicio web** es un tipo especial de servicio que:

- Utiliza protocolos web como HTTP o HTTPS para comunicarse,
- Expone su funcionalidad a través de URLs,

Se llama *web* porque se construye sobre tecnologías propias de la web (como HTTP, URIs y formatos como JSON o XML).

Los servicios web permiten:

- Separar el frontend (cliente) del backend (servidor),
- Reutilizar lógica de negocio o datos en distintas interfaces (por ejemplo, web, móvil, otros sistemas),

### Reglas generales para nombres de URIs API REST

- ✅ Usar **nombres de recursos en plural**
- ✅ Usar **nombres sustantivos, no verbos**
- ✅ Evitar extensiones como `.json`, `.xml` en la URI
- ✅ El **verbo va en el método HTTP**, no en la URI

### 🔸 GET

| Acción                  | URI ejemplo                      | Descripción                     |
|-------------------------|----------------------------------|---------------------------------|
| Obtener todos           | `GET /users`                     | Lista de usuarios               |
| Obtener uno             | `GET /users/{id}`                | Usuario por ID                  |
| Sub-recursos            | `GET /users/{id}/posts`          | Posts del usuario               |
| Filtro con query params | `GET /products?category=zapatos` | Filtrar productos por categoría |

---

### 🔸 POST

| Acción            | URI ejemplo                  | Descripción                    |
|-------------------|------------------------------|--------------------------------|
| Crear recurso     | `POST /users`                | Crear un nuevo usuario         |
| Crear sub-recurso | `POST /users/{id}/telefonos` | Crear un post para ese usuario |

---

### 🔸 PUT

| Acción             | URI ejemplo       | Descripción                        |
|--------------------|-------------------|------------------------------------|
| Reemplazar recurso | `PUT /users/{id}` | Reemplaza completamente al usuario |

---

### 🔸 DELETE

| Acción           | URI ejemplo          | Descripción             |
|------------------|----------------------|-------------------------|
| Eliminar recurso | `DELETE /users/{id}` | Borra un usuario por ID |

## Otros Casos

| Caso           | URI ejemplo                | Descripción        |
|----------------|----------------------------|--------------------|
| Login          | `POST /auth/login`         | Autenticación      |
| Logout         | `POST /auth/logout`        | Cierre de sesión   |
| Acción puntual | `POST /orders/{id}/cancel` | Cancelar una orden |

## ✅ Códigos de respuesta recomendados

| Método | Código recomendado          | Cuándo usarlo                          |
|--------|-----------------------------|----------------------------------------|
| GET    | `200 OK`                    | Recurso(s) obtenido(s) correctamente   |
| POST   | `201 Created`               | Recurso creado exitosamente            |
| PUT    | `200 OK` / `204 No Content` | Actualización o creación de recurso    |
| DELETE | `200 OK` / `204 No Content` | Ok o Eliminación exitosa sin contenido |

### SpringBoot

### Exception Handling Global

- Queremos manejar las excepciones de forma global y para ello el framework Web que usamos en general nos da una forma
  de hacerlo.
- Usar `@RestControllerAdvice` para anotar una clase que maneje excepciones globalmente.
- Dentro de esa clase, podemos definir métodos que manejen excepciones específicas usando
  `@ExceptionHandler(Exception.class)`.

### Testing Integracion Servicios Web

- MockMvc and WebTestClient: [Spring Docs](https://docs.spring.io/spring-framework/reference/testing.html).
- MockMvc ejecuta el controller y todo el stack en memoria, sin servidor, sin red. Perfecto para tests de integración
  rápidos y realistas a nivel de capa web.
- La otra es WebTestCliente como cliente y levantar un server real con @SpringBootTest(webEnvironment =
  WebEnvironment.DEFINED_PORT o RANDOM_PORT))
- Teniendo Tests escritos unitario y de integración a nivel servicio. ¿Qué podemos testear de la capa web?
    - Todo lo relacionado a las pocas líneas de código que debería haber en el controlador. Pero principalmente:
        - Que lleguen bien los parametros
        - Que retorne el json que esperamos en el formato que esperamos
        - Que retorne errores en el formato que esperamos.

## Troubleshooting

- Si tenes este error:
    - jakarta.servlet.ServletException: Request processing failed: java.lang.IllegalArgumentException: Name for argument
      of type [int] not specified, and parameter name information not available via reflection. Ensure that the compiler
      uses the '-parameters' flag.
- En Settings > Build, Execution, Deployment > Compiler > Java Compiler, en Javac Options, agregar:
    - -parameters
- Luego ReBuild Project

# CI/CD
## SDK man
sdk install java 23.0.2-amzn
sdk use java 23.0.2-amzn
java -version

## CI/CD con GitHub Actions

Este proyecto implementa un flujo de integración y despliegue continuo (CI/CD) usando GitHub Actions. El pipeline automatiza la instalación, testeo, análisis de cobertura, escaneo de seguridad, análisis de calidad y despliegue de reportes.

### Flujo de trabajo (`.github/workflows/maven.yml`)

1. **Security Scan**  
   - Usa Trivy para escanear vulnerabilidades en el código y dependencias.
   - Genera y sube un reporte de seguridad.

2. **Instalación**  
   - Instala dependencias usando Maven (`mvn install`).
   - Configura JDK 23 con Temurin.

3. **Testing**  
   - Ejecuta los tests unitarios con Maven (`mvn test`).

4. **Cobertura**  
   - Genera el reporte de cobertura con JaCoCo (`mvn verify jacoco:report`).
   - Sube el reporte XML de cobertura.

5. **SonarCloud**  
   - Descarga el reporte de cobertura.
   - Ejecuta el análisis de calidad con SonarCloud usando el plugin de Maven y el token de SonarCloud.
   - El reporte de cobertura se integra en el análisis.

6. **Despliegue**  
   - Genera nuevamente el reporte de cobertura.
   - Configura GitHub Pages.
   - Sube el reporte de cobertura a GitHub Pages para visualización web.


### Dependencias y Plugins necesarios en `pom.xml`

Para que el pipeline funcione correctamente, se agregaron las siguientes dependencias y plugins:

- **JUnit Jupiter**  
  Para testing unitario:
  ```xml
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.13.4</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>5.13.4</version>
    <scope>test</scope>
  </dependency>
  ```

- **JaCoCo Maven Plugin**  
  Para generar reportes de cobertura:
  ```xml
  <plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.13</version>
    <executions>
      <execution>
        <goals>
          <goal>prepare-agent</goal>
        </goals>
      </execution>
      <execution>
        <id>report</id>
        <phase>test</phase>
        <goals>
          <goal>report</goal>
        </goals>
      </execution>
      <execution>
        <id>check</id>
        <goals>
          <goal>check</goal>
        </goals>
      </execution>
    </executions>
    <configuration>
      <rules>
        <rule>
          <element>BUNDLE</element>
          <limits>
            <limit>
              <counter>LINE</counter>
              <value>COVEREDRATIO</value>
              <minimum>0.80</minimum>
            </limit>
            <limit>
              <counter>BRANCH</counter>
              <value>COVEREDRATIO</value>
              <minimum>0.80</minimum>
            </limit>
          </limits>
        </rule>
      </rules>
    </configuration>
  </plugin>
  ```

- **Sonar Maven Plugin**  
  Para análisis de calidad con SonarCloud:
  ```xml
  <plugin>
    <groupId>org.sonarsource.scanner.maven</groupId>
    <artifactId>sonar-maven-plugin</artifactId>
    <version>5.1.0.4751</version>
  </plugin>
  ```

### Variables y Tokens

- El análisis de SonarCloud requiere el token de SonarCloud (`sonar.token`) y el project key (`sonar.projectKey`).
- El despliegue a GitHub Pages usa el token `${{ secrets.GITHUB_TOKEN }}`.


### Quality Gates en SonarCloud

El proyecto utiliza Quality Gates en SonarCloud para asegurar la calidad del código nuevo y existente. Las condiciones principales son:

#### Condiciones sobre Nuevo Código

- No se introducen nuevos bugs
- El rating de confiabilidad es **A**
- No se introducen nuevas vulnerabilidades
- El rating de seguridad es **A**
- La deuda técnica está limitada
- El rating de mantenibilidad es **A**
- Todos los nuevos security hotspots están revisados (**100%**)
- El nuevo código tiene suficiente cobertura de tests (**>= 80%**)
- El nuevo código tiene baja duplicación (**<= 3%** de líneas duplicadas)

Estas condiciones se aplican tanto a ramas como a Pull Requests, y garantizan que el código que se incorpora al proyecto cumple con los estándares de calidad, seguridad y mantenibilidad definidos.


## spotless-maven-plugin

mvn verify
mvn spotless:apply


## spotbugs-maven-plugin
mvn verify
mvn spotbugs:check
mvn spotbugs:gui
````
