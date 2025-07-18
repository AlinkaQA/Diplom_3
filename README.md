# Проект №3: UI-тесты веб-приложения Stellar Burgers

Финальный проект курса «Автоматизация тестирования на Java» от Яндекс Практикума.  
Цель — протестировать веб-приложение Stellar Burgers с помощью Page Object в Google Chrome и Яндекс.Браузере.

🛠 **Технологии**
- Java 11
- Selenium WebDriver
- JUnit 4
- Maven
- Allure Framework
- WebDriverManager

🚀 **Как запустить тесты и отчёт**
1. **Запуск тестов в Chrome**:
   ```bash
   mvn clean test
2. **Запуск тестов в Яндекс.Браузере**:
    ```bash
    mvn clean test -Dbrowser=yandex  
3. **Генерация и просмотр Allure-отчёта**:
    ```bash
    mvn allure:serve

📌 Что тестируется

Регистрация
✅ Успешная регистрация

❌ Ошибка при некорректном пароле (меньше 6 символов)

Вход в аккаунт
✅ Вход через кнопку «Войти в аккаунт» на главной

✅ Вход через кнопку «Личный кабинет»

✅ Вход из формы регистрации

✅ Вход из формы восстановления пароля

Личный кабинет
✅ Переход в Личный кабинет по клику на соответствующую ссылку

✅ Возврат в Конструктор via кнопки «Конструктор» и логотипа Stellar Burgers

Выход из аккаунта
✅ Разлогин по кнопке «Выйти» в Личном кабинете

Раздел «Конструктор»
Проверяем переключение вкладок ингредиентов:

✅ Булки

✅ Соусы

✅ Начинки

🧪 Требования к тестам

Все тесты независимы и изолированы

Для каждой страницы реализован свой класс Page Object

Тесты структурированы по функциональности

Все шаги покрыты аннотациями @Step для Allure

Генерация тестовых данных и очистка выполняются в тестах

При необходимости тестовый пользователь создаётся через API

📂 Структура проекта

src/
├── main/
│   └── java/ru/yandex/prakticum/pages/      ← Page Object классы
└── test/
    └── java/ru/yandex/prakticum/tests/      ← Тестовые классы
pom.xml                                     ← Конфигурация Maven

⚠️ Не забудьте

Не коммитить папку target/ и файлы сессий браузера

Добавить в .gitignore:
    /target/
    .idea/
    *.log
Если нужно форсировать коммит Allure-результатов:
    ```bash
    git add -f ./target/allure-results/
    git commit -m "add allure results"
    git push



