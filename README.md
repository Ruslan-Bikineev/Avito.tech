## Задание 1  
 - Ссылка на гугл документ: https://docs.google.com/spreadsheets/d/1W2FaMWl6dACpKUyIjn6AkmvMEHt_L4VbuyBmnMAvso0/edit?usp=sharing

## Задание 2
 - Выполнен Вариант 1
 - Используемые инструменты: Java, Maven, REST-assured, Allure
 - Ссылка на гугл документ с тест-кейсами: https://docs.google.com/spreadsheets/d/1qmZyYHHmg0Q9Se99HtlrkCSDDBtAPNtQ2l-SOMW9Fqw/edit?usp=sharing
 - Для запуска тестов необходимо:
   1. Запустить команду: mvn clean test -P run-api-tests
   2. После прохождения тестов можно сформировать отчет allure запустив команду: mvn allure:serve
 Если тест testGetAllDeclarationsBySellerId() не проходит значит есть пересечения по используемому sellerId с другими пользователями, необходимо в data.TestData изменить SELLER_ID_TO_CREATE_DECLARATIONS на какой-либо другой.