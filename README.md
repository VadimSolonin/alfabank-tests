<h1>Демо проект по тестированию сайта "Альфа-Банк"</h1>


<p align="center">
<img src="images/logo/alfabank-logo.png" width="751" height="300" >
</p>

## Оглавление
+ [Описание](#Описание)
+ [Стек технологий](#Стек-технологий)
+ [Тестовые сценарии](#Тестовые-сценарии)
+ [Запуск тестов](#Запуск-тестов)
+ [Cборка тестов в Jenkins](#Cборка-тестов-в-Jenkins)
+ [Интеграция с Allure Report](#интеграция-с-allure-report)
    + [Диаграммы прохождения тестов](#Диаграммы-прохождения-тестов)
    + [Развернутый результат прохождения тестов](#Развернутый-результат-прохождения-тестов)
+ [Интеграция с Allure TestOps](#Интеграция-с-Allure-TestOps)
+ [Интеграция с Jira](#Интеграция-с-Jira)
+ [Уведомления в Telegram с использованием бота](#Уведомления-в-Telegram-с-использованием-бота)
+ [Пример видеозаписи прохождения тестов](#Пример-видеозаписи-прохождения-теста)

## Описание
Демо-проект с автотестами на Java. В качестве примера была выбрана страница "Вклады".
Особенности проекта:
- Page Object шаблон проектирования;
- Возможность запуска тестов: локально, удалённо, по тегам; 
- Faker для генерации данных;
- После выполнения каждого автотеста генерируется Allure отчет. Содержание отчета:
    - Шаги теста;
    - Скриншот страницы на последнем шаге;
    - Исходный код страницы в браузере;
    - Логи консоли браузера;
    - Видео выполнения автотеста.
- Интеграция с Jira;
- Уведомление о результатах прохождения в Telegram.

## Стек технологий
| IDEA | Java | GitHub | JUnit5 | Gradle | Selenide | Selenoid | Allure | Jenkins | Allure TO| Jira |
| ------ | ------ | ------ | ------ | ------ | ------ | ------ | ------ | ------ | ------ | ------ |
| <a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA" title="vs IDEA"/></a> | <a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java" title="vs Java"/></a> | <a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github" title="vs Github"/></a> | <a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5" title="vs JUnit 5"/></a> | <a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle" title="vs Gradle"/></a> | <a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50" alt="Selenide" title="vs Selenide"/></a>| <a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid" title="vs Selenoid"/></a> | <a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure" title="vs Allure"/></a> | <a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins" title="vs Jenkins"/></a> | <a href="https://docs.qameta.io/allure-testops/"><img width="50" height="50"  alt="AllureTestOps" src="images/logo/Allure_TO.svg" title="vs AllureTestOps"></a> | <a href="https://www.atlassian.com/ru/software/jira"><img width="50" height="50"  alt="Jira" src="images/logo/Jira.svg" title="vs Jira"></a>

## Тестовые сценарии
- [x] Переход к форме заполнения заявки на открытие вклада при нажатии `Открыть вклад`.
- [x] Проверка обязательности всех полей заявки на открытие вклада.
- [x] Проверка отключения поля заполнения отчества при нажатии `По паспорту без отчества`.
- [x] Заполнение поля даты рождения некорректным значением.
- [x] Заполнение поля мобильного телефона неполным значением.
- [x] Заполнение поля почты некорректным значением.
- [x] Отображение информационного блока для клиентов без гражданства РФ.

## Запуск тестов:
### 
<details>
   <summary>Локальный запуск</summary>
   
1. Клонировать проект и открыть в IntelliJ IDEA
2. Запустить тесты из терминала командой:
```
gradle clean regress_tests
```
3. Выполнить запрос на формирование отчета:
```
gradle allureReport
```
4. Открыть отчет в браузере:
```
gradle allureServe
```
</details>

### 
<details>
   <summary>Удаленный запуск</summary>

```
gradle clean regress_tests
```
| Параметры, которые можно добавить | Расшифровка | Значение по умолчанию |
|----------|----------|----------|
| -DbrowserName=${BROWSER}| chrome   | chrome   |
| -DbrowserVersion=${BROWSER_VERSION} | Номер версии браузера   | 100   |
| -DbrowserSize=${BROWSER_SIZE}| Разрешение экрана браузера   | 1920x1080   |
| -DremoteUrl=${REMOTE_URL}| адрес удаленного сервера, на котором будут запускаться тесты   | прописан в Jenkins   |
</details>

## Cборка тестов в Jenkins(<b><a target="_blank" href="https://jenkins.autotests.cloud/job/demo-alfabank-tests/3/allure/">Jenkins</a></b>)
<img src="images/screenshots/jenkins-project.png">

## Интеграция с Allure report
#### Диаграммы прохождения тестов:
<img src="images/screenshots/allure-main-report.png">

#### Развернутый результат прохождения тестов:
<img src="images/screenshots/allure-suites.png">


## Интеграция с Allure TestOps
Диаграммы прохождения тестов:
<img src="images/screenshots/allure-testops-dashboards.png">

## Интеграция с Jira
<img src="images/screenshots/jira-integration.png">

## Уведомления в Telegram с использованием бота
<img src="images/screenshots/telegram-notification.png">


## Пример видеозаписи прохождения теста
<p align="center">
  <img src="images/video/videoWeb.gif">
</p>
