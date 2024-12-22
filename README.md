# Дипломный проект по профессии «Тестировщик»

### О проекте

Дипломная работа представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

Приложение — это веб-сервис, предлагающий купить тур по определённой цене с помощью двух способов:
<br/>-оплата по дебетовой карте;
<br/>-выдача кредита по данным банковской карты.

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:
<br/>-сервису платежей, далее Payment Gate;
<br/>-кредитному сервису, далее Credit Gate.

Приложение в собственной СУБД должно сохранять информацию о том, успешно ли был совершён платёж и каким способом. Данные карт при этом сохранять не допускается.

### Prerequisites

Перед началом работы с проектом убедитесь, что у вас установлены следующие программы:
* IntelliJ IDEA
* JDK11
* GIT
* Docker & Docker Desktop
* Allure Report

### Начало работы

1. Склонируйте репозиторий на локальную машину командой в Git:
   git clone
2. Запустите Docker Desktop.
3. Запустите IntelliJ IDEA и откройте проект.

### Запуск

1. Запуск контейнеров
   <br/> Для запуска контейнеров с БД (MySQL, PostgreSQL), также симулятором банковских сервисов необходимо выполнить в терминале команду: <br/>docker-compose up
2. Запуск SUT
   <br/> В новой вкладке производим запуск приложения командами:
* Postgresql
  <br/> java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
* MysSQL
  <br/> java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
3. Запуск авто-тестов
   <br/> В терминале производим запуск автотестов командами:
* Postgresql
  <br/> ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app
* MysSQL
  <br/> ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app - для БД 
4. Генерация отчета Allure
   <br/> Отчет о прохождении тестов генерируется командой:
   <br/> gradlew allureServe