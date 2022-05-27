# Проект автоматизации тестирования для Sportmaster

##  Используемые инструменты

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="TestOps" src="images/logo/Allure_TO.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
</p>

>В данном проекте автотесты написаны на <code>Java</code> с использованием <code>Selenide</code> для UI / API / Mobile-тестов.
> 
> В качестве библиотеки для модульного тестирования используется <code>JUnit 5</code>.
>
> Для автоматизированной сборки проекта используется <code>Gradle</code>.
>
> <code>Selenoid</code> выполняет запуск браузеров в контейнерах <code>Docker</code>.
>
> <code>Allure Report</code> формирует отчет о запуске тестов.
>
> <code>Jenkins</code> выполняет запуск тестов.
>
> После завершения прогона отправляются уведомления с помощью бота в <code>Telegram</code>.

## Запуск UI web тестов
<details>
<summary>Подробнее ...</summary>

### Локальный запуск тестов

```./gradlew clean runTestsUi -Dclient=localBrowser -Dtype=ui```

### Локальный запуск тестов в Selenoid
<details>
<summary>Шаги запуска</summary>

>1. Создать файл с проперти
>> * путь указать ```./src/test/resources/config/remoteBrowser.properties```
>> * добавить данные в файл, пример( ```src/test/resources/config/exampleRemoteBrowser.properties``` )
>2. Запустить тесты
>>```./gradlew clean runTestsUi -Dclient=remoteBrowser -Dtype=ui```
</details>


### Удаленный запуск тестов в Jenkins
<details>
<summary>Шаги запуска</summary>

>1. Создать файл с проперти в Jenkins
>> * путь указать ```./src/test/resources/config/remoteBrowser.properties```
>> * добавить данные в файл, пример( ```src/test/resources/config/exampleRemoteBrowser.properties``` )
>2. Создать файл с настройками для Telegram в Jenkins
>> * путь указать ```./notifications/telegram_config.json```
>> * добавить данные в файл, пример(```./notifications/example_telegram_config.json```)
>3. Запустить тесты
>>```
>>clean
>>runTestsUi
>>-Dclient=remoteBrowser
>>-Dtype=ui
>>-Dthreads=3
>>```
</details>
</details>

## Запуск API тестов
<details>
<summary>Подробнее ...</summary>

### Локальный запуск тестов

```./gradlew clean runTestsApi -Dtype=api```

### Удаленный запуск тестов в Jenkins
<details>
<summary>Шаги запуска</summary>

>1. Создать файл с настройками для Telegram в Jenkins
>> * путь указать ```./notifications/telegram_config.json```
>> * добавить данные в файл, пример(```./notifications/example_telegram_config.json```)
>2. Запустить тесты
>>```clean runTestsApi -Dtype=api -Dthreads=5```

</details>
</details>

## Запуск Mobile тестов
<details>
<summary>Подробнее ...</summary>

### Локальный запуск тестов на реальном устройстве
<details>
<summary>Шаги запуска</summary>

>1. Добавить данные в файл ```real.properties```
>2. Запустить Appium ```appium -p 4723 --allow-insecure chromedriver_autodownload```
>3. Запустить тесты ```./gradlew clean runTestsMobile -Dclient=real -Dtype=mobile```

</details>

### Локальный запуск тестов на эмуляторе андроид
<details>
<summary>Шаги запуска</summary>

>1. Добавить данные в файл ```emulator.properties```
>2. Запустить Appium ```appium -p 4723 --allow-insecure chromedriver_autodownload```
>3. Запустить эмулятор
>4. Запустить тесты ```./gradlew clean runTestsMobile -Dclient=emulator -Dtype=mobile```

</details>

### Запуск тесто в Browserstack
<details>
<summary>Шаги запуска</summary>

>1. Добавить apk файл в Browserstack и записать данные для подключения в ```browserstack.properties```
>2. Пример файла с настройками ```exampleBrowserstack.properties```
>3. Пример файла apk в ```src/test/resources/apk/sportmaster.apk```
>4. Запустить тесты ```./gradlew clean runTestsMobile -Dclient=browserstack -Dtype=mobile```

</details>
</details>

# Пример запуска тестов Jenkins и отчеты
## <img width="4%" title="Jenkins" src="images/logo/Jenkins.svg"> Главная страница сборки [Jenkins](https://jenkins.autotests.cloud/job/Panin11HW-25Ui/)

<p align="center">
  <img src="images/screens/jenkins.png">
</p>

## <img width="4%" title="Allure Report" src="images/logo/Allure_Report.svg"> Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job/Panin11HW-13-2/16/allure/)

### Главная страница Allure-отчета

<p align="center">
<img title="Allure Overview" src="images/screens/allure_overview.png">
</p>

### Страница с тестами

<p align="center">
<img title="Allure Behaviors" src="images/screens/allure_behaviors.png">
</p>

### Основной дашборд

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/allure_overview_dashboard.png">
</p>

## <img width="4%" title="Telegram" src="images/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

> После завершения сборки бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом.
<p align="center">
<img title="Telegram Notifications" src="images/screens/telegram_notifications.png">
</p>

## <img width="4%" title="TestOps" src="images/logo/Allure_TO.svg"> Отчеты в TestOps

<p align="center">
<img title="Telegram Notifications" src="images/screens/testops.png">
<img title="Telegram Notifications" src="images/screens/testops_launches.png">
</p>

## <img width="4%" title="Selenoid" src="images/logo/Selenoid.svg"> Пример запуска теста в Browserstack

> К каждому тесту в отчете прилагается видео. Одно из таких видео представлено ниже.
<p align="center">
  <img title="Selenoid Video" src="images/gif/browserstack.gif">
</p>