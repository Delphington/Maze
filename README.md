# Лабиринты
Проект 2 [Академии Бэкенда 2024](https://education.tbank.ru/academy/backend/)

Приложение состоит из консольной программы для генерация лабиринта и пути решения. Пользовать вводит параметры: размер лабиринта, метод генерации(Kraskal, DFS), метод решения(BFS, DFS) или же решение лабиринта с препятствиями с помошью алгоритма Dijkstra. На основе полученных данных генерится лабиринт и запрашиваются точки старта и финиша, после генериться решение лабиринта. 

## Демонстрация работы
### Пример 1: Генерация с препятсвиями

![Example1](https://github.com/user-attachments/assets/a78905ac-2207-4f16-bbd8-b7222293f8b2)

### Пример 2: Генерация без препятсвий 
![Example2](https://github.com/user-attachments/assets/3fad7676-a1dc-41a4-9dfa-1589e89fb987)


## Структура проекта

Это типовой Java-проект, который собирается с помощью инструмента автоматической
сборки проектов [Apache Maven](https://maven.apache.org/). Необходим JDK 21+

Проект состоит из следующих директорий и файлов:

- [pom.xml](./pom.xml) – дескриптор сборки, используемый maven, или Project
  Object Model. В нем описаны зависимости проекта и шаги по его сборке
- [src/](./src) – директория, которая содержит исходный код приложения и его
  тесты:
  - [src/main/](./src/main) – здесь находится код приложения
  - [src/test/](./src/test) – здесь находятся тесты приложения
- [mvnw](./mvnw) и [mvnw.cmd](./mvnw.cmd) – скрипты maven wrapper для Unix и
  Windows, которые позволяют запускать команды maven без локальной установки
- [checkstyle.xml](checkstyle.xml),
  [checkstyle-suppression.xml](checkstyle-suppression.xml), [pmd.xml](pmd.xml) и
  [spotbugs-excludes.xml](spotbugs-excludes.xml) – в проекте используются
  [линтеры](https://en.wikipedia.org/wiki/Lint_%28software%29) для контроля
  качества кода. Указанные файлы содержат правила для используемых линтеров
- [.mvn/](./.mvn) – служебная директория maven, содержащая конфигурационные
  параметры сборщика
- [lombok.config](lombok.config) – конфигурационный файл
  [Lombok](https://projectlombok.org/), библиотеки помогающей избежать рутинного
  написания шаблонного кода
- [.editorconfig](.editorconfig) – файл с описанием настроек форматирования кода
- [.github/workflows/build.yml](.github/workflows/build.yml) – файл с описанием
  шагов сборки проекта в среде Github
- [.gitattributes](.gitattributes), [.gitignore](.gitignore) – служебные файлы
  для git, с описанием того, как обрабатывать различные файлы, и какие из них
  игнорировать

## Начало работы

Подробнее о том, как приступить к разработке, описано в разделах
[курса][course-url] `1.8 Настройка IDE`, `1.9 Работа с Git` и
`1.10 Настройка SSH`.

Для того чтобы собрать проект, и проверить, что все работает корректно

```shell
mvn clean verify
```

Запуск только компиляции основных классов:

```shell
mvn compile
```

Запуск тестов:

```shell
mvn test
```

Запуск линтеров:

```shell
mvn checkstyle:check modernizer:modernizer spotbugs:check pmd:check pmd:cpd-check
```
