# Перечень документации

1. <a href="https://github.com/LeontevTest/QA_Diplom/blob/main/docs/Plan.md" style="font-size: 18px">План автоматизации
   проекта</a> <br>
2. <a href="https://github.com/LeontevTest/QA_Diplom/blob/main/docs/Report.md" style="font-size: 18px">Отчет по итогам тестирования
   </a> <br>
3. <a href="https://github.com/LeontevTest/QA_Diplom/blob/main/docs/Summary.md">Отчет по итогам автоматизации</a> <br>


# Процедура запуска автотестов

# Инструкция подключения БД и запуска SUT
1. Склонировать проект из репозитория командой ``` git clone ```
1. Открыть склонированный проект в Intellij IDEA
1. Для запуска контейнеров с MySql, PostgreSQL и Node.js использовать команду ``` docker-compose up --build ```
1. Запуск SUT
- для MySQL ввести в терминале команду

``` java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app  -jar artifacts/aqa-shop.jar```

- для PostgreSQL ввести в терминале команду

``` java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app  -jar artifacts/aqa-shop.jar ```

5. Запуск тестов (Allure)
-  для запуска на MySQL ввести команду

``` gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app allureReport ```

- для запуска на PostgreSQ ввести команду

``` gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app allureReport ```

6. После окончания тестов завершить работу приложения (Ctrl+C), остановить контейнеры командой ``` docker-compose down ```
