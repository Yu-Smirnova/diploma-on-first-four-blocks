Процедура запуска тестов.

Для успешного запуска тестов на машине должны быть установлены следующие инструменты:
- Open JDK 11
- IntelliJ IDEA
- Docker
- браузер Google Chrome

Шаги запуска тестов:
1. Скачать репозиторий проекта с GitHub командой в терминале "git clone https://github.com/Yu-Smirnova/diploma-on-first-four-blocks"
2. Запустить IntelliJ IDEA
3. Открыть скачанный проект в IntelliJ IDEA
4. Запустить образы баз данных и симулятора командой "docker-compose up" в терминале
5. Запустить тестируемое приложение командой "java -jar artifacts/aqa-shop.jar" в терминале
6. Запустить тесты командой "./gradlew test" в терминале