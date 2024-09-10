# Перечень документации

1. <a href="" style="font-size: 18px">Задание дипломного
   проекта</a> <br>


# Процедура запуска автотестов

## Примечание

Содержимое в файлах `application.properties`, `build.gradle`
и `gradle.yml`.

Содержимое файла `application.properties`, в которой настроена работа `MySQL`:

```
spring.credit-gate.url=http://localhost:9999/credit
spring.payment-gate.url=http://localhost:9999/payment
spring.datasource.url=jdbc:mysql://localhost:3306/app
#spring.datasource.url=jdbc:postgresql://localhost:5432/app
spring.datasource.username=app
spring.datasource.password=pass
```

Фрагмент содержимого файла `build.gradle`, в которой настроена работа `MySQL`:

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


## Шаг 1

Открыть консоль с помощью сочетания клавиш `Ctrl + Alt + T` и перейти в директорию, куда будет клонироваться проект.

## Шаг 2

Необходимо склонировать удаленный репозиторий на свой ПК с помощью следующей команды:

```
git clone 
```

## Шаг 3

Находясь в директории склонированого проекта, необходимо запустить контейнеры в `Docker` с
помощью команды:

```
docker-compose up --build
```

## Шаг 4

В новом окне `Terminal` запускаем `aqa-shop.jar` из директории с помощью команды:

```
java -jar ./artifacts/aqa-shop.jar
```

## Шаг 6

В новом окне `Terminal` запускаем тесты командой:

```
./gradlew test --info -Dselenide.headless=true
```
