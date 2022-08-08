<h1 align="center">Документация</h1>

* #### [План автоматизации тестирования](http://example.com)

<h1 align="center">Процедура запуска авто-тестов</h1>

### Перед запуском авто-тестов необходимо:  
* Получить доступ к виртуальной машине (доступ выдает Артем Романов в дискорде)   
* [Инструкция по подключению виртуальной машины](https://github.com/netology-code/aqa-homeworks/blob/master/docker/timeweb-instruction.md)  

### Запуск авто-тестов:
1. Запустить контейнеры СУБД MySQl, PostgerSQL и Node.js командой в терминале:
```
docker-compose up -d
```
2. Запустить SUT, работающей на СУБД MySQl командой:
```
java -Dspring.datasource.url=jdbc:mysql://185.119.57.172:3306/app -jar artifacts/aqa-shop.jar
```
3. на СУБД PostgreSQL командой:
```
java -Dspring.datasource.url=jdbc:postgresql://185.119.57.172:5432/app -jar artifacts/aqa-shop.jar
```

4. В новом терминале запустить авто-тесты командой для MySQL:
```
gradlew clean test -Durl=jdbc:mysql://185.119.57.172:3306/app
```

5. для PostgreSQL:
```
gradlew clean test -Durl=jdbc:postgresql://185.119.57.172:5432/app
```

6. Сгенерировать отчеты двумя командами:
```
gradlew allureReport
```
```
gradlew allureServe
```
7. Остановить и удалить все контейнеры командой:
```
docker-compose down 
```
