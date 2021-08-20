# Demo TestNg Parallel Testing (rozetka.com.ua)
Проект примеров параллельного тестирования веб-приложения rozetka.com.ua на основе каркаса TestNg
## Предварительные требования
- OS Windows 10 / Linux Ubuntu 20.04 / Другие современные настольные OS (не проверено)
- JDK 11 / 15
- Maven 3.6
- браузеры Chrome, Firefox
## Скачивание и запуск
- скачать:
**git clone** https://github.com/tyaa10/Task4-TestNGParallel.git
- запустить все тесты: **mvn test**
- запустить только кейс givenFilter_whenTheMostExpensiveProductAddedToCart_thenTotalPriceLessThanBound в классе SumVerifyTest:
**mvn -Dtest**=SumVerifyTest#givenFilter_whenTheMostExpensiveProductAddedToCart_thenTotalPriceLessThanBound **test**
## Показаны техники:
- BDD-именование методов тест-кейсов
- TestNg
- параллельное выполнение кейсов
- передача исходных текстовых данных в кейс при помощи dataProvider
- преобразование исходных текстовых данных из XML-документа Java-объект при помощи JAXB
- сложные выдержки времени при последовательных реакциях веб-интерфейса на результаты AJAX-запросов
- шаблоны проектирования "Мультитон" (многопоточный синглтон), "Фасад", "Декоратор"