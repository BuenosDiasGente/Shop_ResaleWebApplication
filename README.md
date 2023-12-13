# Shop_ResaleWebApplication
![1456080627-146661](https://github.com/BuenosDiasGente/Shop_ResaleWebApplication/assets/123076580/c7e56ffa-8061-439a-9547-a95278e58bd2)

_Командная работа курса "Профессия  Java-разработчик" платформы SkyPro_

#### Демо:

## **Краткое описание:**
Приложение для размещения объявлений о товарах и услугах.

Функционал для неавторизованных пользователей:
-просмотр всех объявлений от всех пользователей.

Функционал для авторизованных пользователей:
-авторизация пользователей и создание аккаунтов для новых пользователей;
-составление/редактирование объявления, включая его название, описание, фотографии, а также контактных данных продавца;
-загрузка фотографии товара на сайт;
-просмотр личных объявлений;
-написание/редактирование личных комментариев под объявлениями других продавцов.

Функционал для администраторов:
-авторизация в роли администратора;
-редактирование всех комментариев пользователей;
-редактирование всех объявлений.

Приложение функционирует с помощью запросов с localhost:3000 к эндпоинтам на localhost:8080.

### **Спецификация:**
Функционал backend-части приложение разрабатывался под готовую [OpenAPI спецификацию](https://github.com/BizinMitya/front-react-avito/blob/v1.21/openapi.yaml)

### **Запуск frontend c помощью Docker:**
 В командной строке (или Terminal IDEA) выполнить следующую команду:<br>
' docker run -p 3000:3000 --rm ghcr.io/bizinmitya/front-react-avito:v1.21 '

## **Стек технологий**

- _Язык и окружение_: <br>
Java 11, Spring Boot, Spring Web, Spring Data/JPQL/CreteriaAPI, Spring Security, Lombok, Hibernate, PostgreSQL, Swagger, Postman, FlyWay, Mapstruct, ExceptionHandler, Docker.

- _Тестирование_: JUnit, Mockito,  h2database for tests, интеграционное тестирование

## Команда разработки:

- Гудова Марина
- Сторожилова Яна
- Шуранов Артём



