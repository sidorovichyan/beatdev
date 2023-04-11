# Инструкция по развертыванию

1) Клонировать репозиторий
2) Изменить логин и пароль для базы данных в [application.properties](/src/main/resources/application.properties)

```
spring.datasource.username='your_username'
spring.datasource.password='your_password'
```

3) Создать базу данных beatdev и указать корректный url в [application.properties](/src/main/resources/application.properties)

```
spring.datasource.url=jdbc:mysql://localhost:3306/beatdev
```
4) Открыть терминал в папке проекта и выполнить команду
```
mvn spring-boot:run
```
Пример запроса:
```
http://localhost:80/user/1
```
# Документация API

## Создание нового пользователя

URL
```
POST /user/
```

Тело запроса: JSON объект, содержащий данные пользователя:
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "imageUrl": "https://example.com/avatar.png",
  "status": "Online"
}
```
#### Параметры:

**name:** имя пользователя

**email:** адрес электронной почты пользователя

**imageUrl:** URL изображения профиля пользователя

**status:** статус пользователя 

Ответ:

Идентификатор созданного пользователя:

```json
   1
```

#### Коды состояния HTTP

**201 Created:** пользователь успешно создан

**400 Bad Request:** некорректные данные пользователя

**500 Internal Server Error:** ошибка на сервере



## Получение информации о пользователе

URL
```
GET /user/{userId}
```

Параметры запроса
**userId:** идентификатор пользователя

Ответ
JSON объект, содержащий информацию о пользователе:

```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "imageUrl": "https://example.com/avatar.png",
  "status": "Online"
}
```
#### Параметры:

**name:** имя пользователя

**email:** адрес электронной почты пользователя

**imageUrl:** URL изображения профиля пользователя 

**status:** статус пользователя 

#### Коды состояния HTTP

**200 OK:** информация о пользователе успешно получена

**404 Not Found:** пользователь с указанным идентификатором не найден

**500 Internal Server Error:** ошибка на сервере

## Обновление статуса пользователя

```
PUT /user/{userId}/{status}
```

Параметры запроса
**userId:** идентификатор пользователя

**status:** новый статус пользователя (возможные значения: "Online", "Offline")

Ответ
JSON объект, содержащий информацию о пользователе:

```json
{
  "userId": 123,
  "previousStatus": "Online",
  "status": "Offline"
}
```
#### Параметры:

**userId:** идентификатор пользователя

**previousStatus:** предыдущий статус пользователя

**status:** текущий статус пользователя

#### Коды состояния HTTP

**200 OK:** информация о пользователе успешно обновлена

**404 Not Found:** пользователь с указанным идентификатором не найден

**500 Internal Server Error:** ошибка на сервере
