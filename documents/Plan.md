#  План автоматизации тестирования приложения "Путешествие дня"

## 1. Перечень автоматизированных сценариев

###### Валидные данные:
1. *Номер* карты состоит из 16 цифр:
   <br/>*APPROVED* — операция успешна, номер карты 4444 4444 4444 4441;
   <br/>*DECLINED* — отказ в операции, номер карты 4444 4444 4444 4442.
2. *Месяц* двузначное число от 01 до 12, текущий месяц текущего года.
3. *Год* последние две цифры текущего года.
4. *Владелец*  имя и фамилия владельца карты, состоящие из двух слов через пробел на латинице (допустимо наличие дефиса в имя и/или фамилия) (Например Natalia Krasnykh)
5. *CVC/CVV* - 3-х значный номер (например 789)


### *Позитивные сценарии:*
#### Оплата по карте со статусом "APPROVED"
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты со статусом "APPROVED" : 4444 4444 4444 4441
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:* 
Появилось всплывающее окно с сообщением: *"Успешно. Операция одобрена Банком"*

#### Оплата в кредит по карте со статусом "APPROVED"
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты со статусом "APPROVED" : 4444 4444 4444 4441
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Появилось всплывающее окно с сообщением: *"Успешно. Операция одобрена Банком"*

#### Оплата по карте со статусом "DECLINED"
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты со статусом "DECLINED" : 4444 4444 4444 4442
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Появилось всплывающее окно с сообщением: *"Ошибка! Банк отказал в проведении операции"*

#### Оплата в кредит по карте со статусом "DECLINED"
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты со статусом "DECLINED" : 4444 4444 4444 4442
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Появилось всплывающее окно с сообщением: *"Ошибка! Банк отказал в проведении операции"*

### *Негативные сценарии:*
#### Оплата по карте невалидным номером карты:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 444
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Номер карты" выдает ошибку: *"Неверный формат".*

#### Оплата в кредит по карте невалидным номером карты:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 444
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Номер карты" выдает ошибку: *"Неверный формат".*

#### Отправка пустой формы:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Поле "Номер карты" оставить пустым
4. Поле "Месяц" оставить пустым
5. Поле "Год" оставить пустым
6. Поле "Владелец" оставить пустым
7. Поле "CVC/CVV" оставить пустым
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поля формы выдают ошибки.

#### Отправка пустой формы заявки на кредит:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Поле "Номер карты" оставить пустым
4. Поле "Месяц" оставить пустым
5. Поле "Год" оставить пустым
6. Поле "Владелец" оставить пустым
7. Поле "CVC/CVV" оставить пустым
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поля формы выдают ошибки.

#### Оплата по карте с невалидным месяцем карты:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" число 13.
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"


##### *Ожидаемый результат:*
Поле "Месяц" выдает ошибку: *"Неверно указан срок действия карты".*

#### Оплата в кредит картой с невалидным месяцем карты:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" число 13.
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"


##### *Ожидаемый результат:*
Поле "Месяц" выдает ошибку: *"Неверно указан срок действия карты".*

#### Оплата по карте с невалидным месяцем карты:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" число 00.
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"


##### *Ожидаемый результат:*
Поле "Месяц" выдает ошибку: *"Неверно указан срок действия карты".*

#### Оплата в кредит картой с невалидным месяцем карты:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" число 00.
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Месяц" выдает ошибку: *"Неверно указан срок действия карты".*

#### Оплата по карте с невалидным месяцем карты:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" число 5.
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"


##### *Ожидаемый результат:*
Поле "Месяц" выдает ошибку: *"Неверный формат".*

#### Оплата в кредит картой с невалидным месяцем карты:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" цифру 5.
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"


##### *Ожидаемый результат:*
Поле "Месяц" выдает ошибку: *"Неверный формат".*

#### Оплата картой с истекшим сроком действия в поле:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" число 23
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Год" выдает ошибку: *"Истёк срок действия карты"*.

#### Оплата в кредит картой с истекшим сроком действия:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" число 23
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Год" выдает ошибку: *"Истёк срок действия карты"*

#### Оплата картой с невалидным годом:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" вадидные данные
5. Ввести в поле "Год" цифру 6
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Год" выдает ошибку: *"Неверный формат"*

#### Оплата в кредит картой с невалидным годом:
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" цифру 6
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Год" выдает ошибку: *"Неверный формат"*

#### Оплата картой со значением кирилицей  в поле "Владелец":
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 444
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. В поле "Владелец" ввести значение на кириллице.
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Владелец" выдает ошибку: *"Неверный формат"*

#### Оплата в кредит картой со значением кирилицей  в поле "Владелец":
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 444
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. В поле "Владелец" ввести значение на кириллице.
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Владелец" выдает ошибку: *"Неверный формат"*

#### Оплата картой со значением цифры в поле "Владелец":
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 444
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. В поле "Владелец" ввести цифры (например 987654321).
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Владелец" выдает ошибку: *"Неверный формат"*

#### Оплата в кредит картой со значением цифры в поле "Владелец":
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 444
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. В поле "Владелец" ввести цифры (например 123456789).
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Владелец" выдает ошибку: *"Неверный формат"*

#### Оплата картой со значением спецсимволы в поле "Владелец" (искл. - и пробел):
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 444
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. В поле "Владелец" ввести спецсимволы (например @#^&*&^%).
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Владелец" выдает ошибку: *"Неверный формат"*

#### Оплата в кредит картой со значением спецсимволы в поле "Владелец" (искл. - и пробел):
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 444
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. В поле "Владелец" ввести спецсимволы (например @#^&*&^%).
7. Ввести в поле "CVC/CVV" валидные данные
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "Владелец" выдает ошибку: *"Неверный формат"*

#### Оплата картой с невалидным значением в поле "CVC/CVV":
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" - 2 цифры (12)
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "CVC/CVV" выдает ошибку: *"Неверный формат"*

#### Оплата в кредит картой с невалидным значением в поле "CVC/CVV":
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" - 2 цифры (12)
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "CVC/CVV" выдает ошибку: *"Неверный формат"*

#### Оплата картой с невалидным значением в поле "CVC/CVV":
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" - 1 цифру (9)
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "CVC/CVV" выдает ошибку: *"Неверный формат"*

#### Оплата в кредит картой с невалидным значением в поле "CVC/CVV":
1. Открыть в браузере страницу http://localhost:8080/
2. Нажать кнопку "Купить в кредит"
3. Ввести в поле "Номер карты" номер карты: 4444 4444 4444 4441
4. Ввести в поле "Месяц" валидные данные
5. Ввести в поле "Год" валидные данные
6. Заполнить поле "Владелец" валидными данными
7. Ввести в поле "CVC/CVV" - 1 цифру (9)
8. Нажать кнопку "Продолжить"

##### *Ожидаемый результат:*
Поле "CVC/CVV" выдает ошибку: *"Неверный формат"*

## 2. Перечень используемых инструментов с обоснованием выбора.

- *IntelliJ IDEA 2024.1.2* - бесплатная многофункциональная среда для разработки.
- *Java 11* - язык для написания авто-тестов, универсален, надежен.
- *Gradle* - система для сборки проекта с простым подключением зависимостей.
- *JUnit 5* - среда для написания и запуска тестов.
- *Docker* — это платформа для создания и запуска контейнеров, позволяет запускать приложение в любой среде, не беспокоясь о зависимостях.
- *Selenide* - функциональный и удобный фреймворк для авто-тестирования веб-интерфейса.
- *Lombok* - библиотека для упрощения и ускорения процесса разработки. Повышает читабельность кода.
- *Faker* - для генерации тестовых данных. Генерирует имена, даты и тд
- *Allure* - фреймворк для генерирования отчетов о тестировании наглядно отображает прохождение тестов и возникновение ошибок.
- *GitHub* - специализированный веб-сервис с удобным интерфейсом для удаленного хранения проекта.

## 3. Перечень и описание возможных рисков при автоматизации
1. Отсутствие технической документации
2. Сложность с настройкой SUT
3. Неполное покрытие тестов
4. Изменение интерфейса влияет на авто-тесты, возможны "падения"
5. Ложные срабатывания тестов
6. Временные затраты на написание автотестов могут нивелировать пользу от их написания.

## 4. Интервальная оценка с учётом рисков в часах
- Изучение технического задания, подготовка окружения проекта – 4 часов.
- План автоматизации тестирования - 6 часов.
- Написания автотестов - 60 часов.
- Подготовка отчётных документов по итогам тестирования - 6 часов.
- Подготовка отчётных документов по итогам автоматизации - 6 часа.

## 4. План сдачи работ
- Планирование автоматизации тестирования 17.12.2024
- Подготовка и отладка автотестов - 22.12.2024
- Подготовка отчётных документов по итогам автоматизированного тестирования: 24.12.2024
- Подготовка отчётных документов по итогам автоматизации:26.12.2024
