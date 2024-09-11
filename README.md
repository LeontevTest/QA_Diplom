# Перечень документации

1. <a href="https://github.com/LeontevTest/QA_Diplom/blob/main/docs/Plan.md" style="font-size: 18px">План автоматизации
   проекта</a> <br>
2. <a href="https://github.com/LeontevTest/QA_Diplom/blob/main/docs/Report.md" style="font-size: 18px">Отчет по итогам тестирования
   </a> <br>
3. <a href="https://github.com/LeontevTest/QA_Diplom/blob/main/docs/Summary.md">Отчет по итогам автоматизации</a> <br>


# Процедура запуска автотестов

## Примечание


Содержимое веток одинаково, имеются лишь небольшие отличия в файлах `application.properties`, `build.gradle`
и `gradle.yml`.

Содержимое файла `application.properties` для ветки `main`, в которой настроена работа `MySQL`:

```
spring.credit-gate.url=http://localhost:9999/credit
spring.payment-gate.url=http://localhost:9999/payment
spring.datasource.url=jdbc:mysql://localhost:3306/app
#spring.datasource.url=jdbc:postgresql://localhost:5432/app
spring.datasource.username=app
spring.datasource.password=pass
```

Фрагмент содержимого файла `build.gradle` для ветки `main`, в которой настроена работа `MySQL`:

```
test {
    useJUnitPlatform()
    systemProperty 'selenide.headless', System.getProperty('selenide.headless')
    systemProperty 'db.url', System.getProperty('db.url', 'jdbc:mysql://localhost:3306/app')
    //systemProperty 'db.url', System.getProperty('db.url', 'jdbc:postgresql://localhost:5432/app')
    systemProperty 'db.user', System.getProperty('db.user', 'app')
    systemProperty 'db.password', System.getProperty('db.password', 'pass')
    systemProperty 'sut.url', System.getProperty('sut.url', 'http://localhost:8080')
}
```

Фрагмент содержимого файла `gradle.yml` для ветки `main`:

```
on:
  push:
    branches:
      - main
```

Содержимое файла `application.properties` для ветки `master`, в которой настроена работа `PostgreSQL`:

```
spring.credit-gate.url=http://localhost:9999/credit
spring.payment-gate.url=http://localhost:9999/payment
#spring.datasource.url=jdbc:mysql://localhost:3306/app
spring.datasource.url=jdbc:postgresql://localhost:5432/app
spring.datasource.username=app
spring.datasource.password=pass
```

Фрагмент содержимого файла `build.gradle` для ветки `master`, в которой настроена работа `PostgreSQL`:

```
test {
    useJUnitPlatform()
    systemProperty 'selenide.headless', System.getProperty('selenide.headless')
    //systemProperty 'db.url', System.getProperty('db.url', 'jdbc:mysql://localhost:3306/app')
    systemProperty 'db.url', System.getProperty('db.url', 'jdbc:postgresql://localhost:5432/app')
    systemProperty 'db.user', System.getProperty('db.user', 'app')
    systemProperty 'db.password', System.getProperty('db.password', 'pass')
    systemProperty 'sut.url', System.getProperty('sut.url', 'http://localhost:8080')
}
```

Фрагмент содержимого файла `gradle.yml` для ветки `master`:

```
on:
  push:
    branches:
      - master
```


## Шаг 1

Открыть консоль с помощью сочетания клавиш `Ctrl + Alt + T` и перейти в директорию, куда будет клонироваться проект.

## Шаг 2

Необходимо склонировать удаленный репозиторий на свой ПК с помощью следующей команды:

```
git clone 
```
## Шаг 3
1. Если необходимо запустить поддержку `MySQL`, то этот шаг можно пропустить и перейти к следующему.
2. Если необходимо запустить поддержку `PostgreSQL`, то необходимо открыть новое окно в `Terminal` и переключиться на
   ветку `master`.

## Шаг 4

Находясь в директории склонированого проекта, необходимо запустить контейнеры в `Docker` с
помощью команды:

```
docker-compose up --build
```

## Шаг 5

В новом окне `Terminal` запускаем `aqa-shop.jar` из директории с помощью команды:

```
java -jar ./artifacts/aqa-shop.jar
```

## Шаг 6

В новом окне `Terminal` запускаем тесты командой:

```
./gradlew test --info -Dselenide.headless=true
```
