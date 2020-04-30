# GOLD - проект тестового фреймворка

## Запуск тестов

### Запуск тестов в IDEA
Для запуска с jvm параметром надо указать в
Run - Edit Configuration для конктретного запуска

в VM options :
-ea -DTAGS=@testtag -Dautotest_target_url=http://testurl/app

### Запуск тестов через MAVEN

mvn clean install -DTAGS=@smoke -Dautotest_target_url=http://testurl/app

## Параметры запуска
* -DTAGS - указание тега теста
* -Dautotest_target_url - тестовый url

## Использование ALM plugin
    Для использования ALM plugin в pom файле необходимо указать
    --plugin efr.alm.AlmFormatter
    в argLine у maven-surefire-plugin

## Для однопоточного запуска на сервере Selenoid:
    Необходимо раскоментировать настройки для Selenoid в application.properties
    Указать свой адрес сервера selenoid в настройке webdriver.url = http://<свой IP>:4444/wd/hub/

##  Для многопоточного запуска тестов на Selenoid:
    При запуске mvn использовать profile parallel для этого в конец добавить -P parallel
    mvn clean install -DTAGS=@smoke -Dautotest_target_url=http://testurl/app -P parallel
