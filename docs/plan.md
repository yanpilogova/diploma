<h1 align="center">План автоматизации тестирования покупки тура "Марракэш"</h1>

## Объект тестирования
Веб-сервис оплаты путешествия двумя способами:

* дебетовой картой  
* выдачей кредита по реквизитам банковской карты 


## Валидные данные тестовых карт  

* Номер разрешённой (APPROVED) карты: *4444 4444 4444 4441*  
* Номер запрещённой (DECLINED) карты: *4444 4444 4444 4442*  
* Месяц: двузначное число в диапазоне *01...12*  
* Год: *больше текущего на два, в формате двузначного числа*  
* Владелец: *Имя и Фамилия латиницей*  
* CVC: Трёхзначное число в диапазоне *000...999*  

# Перечень автоматизируемых сценариев:

####  Предусловия для покупки дебетовой картой
##### Выбрать "Купить"  


#### 1. Успешная покупка дебетовой валидной картой 
- Заполнить данные разрешённой карты  
- Нажать "Продолжить"  
- Ожидаемое поведение: уведомление "Успешно". В БД статус операции: "APPROVED"  

#### 2. Отказ в оплате дебетовой картой с пустым полем номер  
- Заполнить все поля валидными данными, кроме номера карты, оставить номер карты пустым 
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "Неверный формат"   

#### 3.  Отказ в оплате  дебетовой картой с пустым полем месяца 
- Заполнить все поля, кроме месяца, валидными данными  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверный формат"  

#### 4.  Отказ в оплате  дебетовой картой с пустым полем год 
- Заполнить все поля, кроме года, валидными данными  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Год" подчёркнуто красным, под полем есть сообщение "Неверный формат" 

#### 5.  Отказ в оплате  дебетовой картой с пустым полем Владелец 
- Заполнить все поля, кроме поля Владелец, валидными данными  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"

#### 6.  Отказ в оплате  дебетовой картой с пустым полем CVC 
- Заполнить все поля, кроме поля CVC, валидными данными  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "CVC" подчёркнуто красным, под полем есть сообщение "Неверный формат"

#### 7. Отказ в оплате дебетовой картой с 0 в поле номер  
- Заполнить все поля валидными данными, кроме номера карты, в поле номер карты ввести 0 
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "Неверный формат"   

#### 8.  Отказ в оплате дебетовой картой с 0 в поле месяца 
- Заполнить все поля валидными данными, кроме поля месяц, в поле месяц ввести 0  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверный формат"  

#### 9.  Отказ в оплате  дебетовой картой с 0 в поле год 
- Заполнить все поля валидными данными, кроме года, в поле год ввести 0   
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Год" подчёркнуто красным, под полем есть сообщение "Неверный формат" 

#### 10.  Отказ в оплате дебетовой картой с введенными специальными символами в поле Владелец 
- Заполнить все поля валидными данными, кроме поля Владелец, в поле Владелец внести @&55  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"

#### 11.  Отказ в оплате дебетовой картой с 13 месяцем в поле месяца 
- Заполнить все поля валидными данными, кроме поля месяц, в поле месяц ввести 13  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверный формат"  

#### 12.  Отказ в оплате запрещенной дебетовой картой 
- Заполнить все поля, кроме номера, валидными данными  
- В поле "Номер карты" ввести: 4444 4444 4444 4442  
- Ожидаемое поведение: уведомление "Отказано". В БД статус операции: "DECLINED"  

#### 13. Отказ в оплате дебетовой картой просроченой на месяц  
- Заполнить все поля, кроме года и месяца, валидными данными  
- В поле "Месяц" ввести прошедший  
- В поле "Год" ввести текущий год  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверно указан срок действия карты"  

#### 14. Оплата дебетовой картой просроченой на год  
- Заполнить все поля, кроме года и месяца, валидными данными  
- В поле "Месяц" ввести текущий  
- В поле "Год" ввести прошлый год  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Год" подчёркнуто красным, под полем есть сообщение "Истёк срок действия карты"

#### 15. Отказ в оплате дебетовой картой с 20-значным номером карты  
- Заполнить все поля, кроме Номер карты, валидными данными  
- В поле "Номер карты" ввести значение 4444 4444 4444 4444 4444 
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "Неверный формат"  

####  Предусловия для оплаты кредитов
##### Выбрать "Купить в кредит"  

#### 1. Успешная покупка в кредит валидной картой 
- Заполнить данные разрешённой карты  
- Нажать "Продолжить"  
- Ожидаемое поведение: уведомление "Успешно". В БД статус операции: "APPROVED"  

#### 2. Отказ в оплате в кредит картой с пустым полем номер  
- Заполнить все поля валидными данными, кроме номера карты, оставить номер карты пустым 
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "Неверный формат"   

#### 3.  Отказ в оплате в кредит картой с пустым полем месяца 
- Заполнить все поля, кроме месяца, валидными данными  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверный формат"  

#### 4.  Отказ в оплате в кредит картой с пустым полем год 
- Заполнить все поля, кроме года, валидными данными  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Год" подчёркнуто красным, под полем есть сообщение "Неверный формат" 

#### 5.  Отказ в оплате в кредит картой с пустым полем Владелец 
- Заполнить все поля, кроме поля Владелец, валидными данными  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"

#### 6.  Отказ в оплате в кредит картой с пустым полем CVC 
- Заполнить все поля, кроме поля CVC, валидными данными  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "CVC" подчёркнуто красным, под полем есть сообщение "Неверный формат"

#### 7. Отказ в оплате в кредит картой с 0 в поле номер  
- Заполнить все поля валидными данными, кроме номера карты, в поле номер карты ввести 0 
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "Неверный формат"   

#### 8.  Отказ в оплате в кредит картой с 0 в поле месяца 
- Заполнить все поля валидными данными, кроме поля месяц, в поле месяц ввести 0  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверный формат"  

#### 9.  Отказ в оплате в кредит картой с 0 в поле год 
- Заполнить все поля валидными данными, кроме года, в поле год ввести 0   
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Год" подчёркнуто красным, под полем есть сообщение "Неверный формат" 

#### 10.  Отказ в оплате в кредит картой с введенными специальными символами в поле Владелец 
- Заполнить все поля валидными данными, кроме поля Владелец, в поле Владелец внести @&55  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Владелец" подчёркнуто красным, под полем есть сообщение "Неверный формат"

#### 11.  Отказ в оплате в кредит картой с 13 месяцем в поле месяца 
- Заполнить все поля валидными данными, кроме поля месяц, в поле месяц ввести 13  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверный формат"  

#### 12.  Отказ в оплате в кредит запрещенной картой 
- Заполнить все поля, кроме номера, валидными данными  
- В поле "Номер карты" ввести: 4444 4444 4444 4442  
- Ожидаемое поведение: уведомление "Отказано". В БД статус операции: "DECLINED"  

#### 13. Отказ в оплате в кредит картой просроченой на месяц  
- Заполнить все поля, кроме года и месяца, валидными данными  
- В поле "Месяц" ввести прошедший  
- В поле "Год" ввести текущий год  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Месяц" подчёркнуто красным, под полем есть сообщение "Неверно указан срок действия карты"  

#### 14. Оплата в кредит картой просроченой на год  
- Заполнить все поля, кроме года и месяца, валидными данными  
- В поле "Месяц" ввести текущий  
- В поле "Год" ввести прошлый год  
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Год" подчёркнуто красным, под полем есть сообщение "Истёк срок действия карты" 

#### 15. Отказ в оплате кредитом с 20-значным номером карты  
- Заполнить все поля, кроме Номер карты, валидными данными  
- В поле "Номер карты" ввести значение 4444 4444 4444 4444 4444 
- Нажать "Продолжить"  
- Ожидаемое поведение: Оплата не происходит. Поле "Номер карты" подчёркнуто красным, под полем есть сообщение "Неверный формат" 

## Перечень используемых инструментов с обоснованием выбора  
* Java 8 - версия Java  
* MySQL - БД (база данных) с которой работает приложение  
* PostgreSQL - вторая БД с которой может работать приложение  
* node-js - окружение в котором работает симулятор банковского сервиса  
* Junit 5 - позволяет создавать автоматизированные тесты на Java  
* Selenide - для автоматизации UI тестов на Java   
* Faker - генератор случайных данных владельца карты  
* lombok - упрощает написание программ на Java  
* Allure - генератор отчётов тестирования  


## Перечень и описание возможных рисков при автоматизации  
- У тестировщика нет достаточного опыта в работе с инструментарием  
- Нет документации к тестируемому приложению. Нед данных о корректном поведении приложения.  

## Интервальная оценка с учётом рисков (в часах)  
Без рисков: до 61 часа:  
20 часов на исследование приложения;  
10 часов на планирование;  
25 часов на написание основы тестов и утилит;  
2 часа на написание самих сценариев;  
2 часа отладку сценариев;  
2 часа на описание багов и отчёт.  

С рисками: до 85 часов:  
61 часа без рисков + ещё 24 часа на изучение инструментов автоматизации.  


## План сдачи работ  
- Написание и отладка тестов: 01 августа 2022  
- Оформление отчётов: 17 августа 2022  
