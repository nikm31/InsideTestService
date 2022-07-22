==== Подготовка к запуску ==== 
В корне проекта запустите docker-compose.yml

1 - перейдите в директорию проекта: cd 'путь к директории InsideTestService'
2 - запустите контейнеры командой: docker-compose up

Описание:
Контейнер flyway произведет миграцию тестовых данных из папки flyway/test в контейнер postgres

Для подключения к БД в контейнере используйте:
host: localhost
port: 5433
username: postgres
password: postgres

==== Запуск ====

Запустите проект в IDE
После запуска, вам будет доступно описание эндпоинтов API - http://localhost:8190/test/swagger-ui.html

==== Работа с приложением (cURL) ====

1 - Авторизация и получение токена

пример запроса:
curl -XPOST -H "Content-type: application/json" -d '{"name": "nikolay","password": "100"}' 'http://localhost:8190/test/api/v1/auth'

пример успешного ответа:
{"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaWtvbGF5Iiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJleHAiOjE2NTg1MDcwMjQsImlhdCI6MTY1ODQ3MTAyNH0.z-SyGGXPD1rk9kq19G11JNcEfo4NgqN3ylhxVi9XHis"}

пример ошибки авторизации:
{"messages":["Не верное имя пользователя или пароль"],"date":"2022-07-22T06:24:40.788+00:00"}

2 - Работа с сообщениями (используйте полученный токен выше)

пример запроса на создание нового сообщения:
curl -XPOST -H 'Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaWtvbGF5Iiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJleHAiOjE2NTg1MDcwMjQsImlhdCI6MTY1ODQ3MTAyNH0.z-SyGGXPD1rk9kq19G11JNcEfo4NgqN3ylhxVi9XHis' -H "Content-type: application/json" -d '{"name": "nikolay","message": "new added message"}' 'http://localhost:8190/test/api/v1/message'

пример запроса на получение последних 10 сообщений пользователя:
curl -XPOST -H 'Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaWtvbGF5Iiwicm9sZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJleHAiOjE2NTg1MDcwMjQsImlhdCI6MTY1ODQ3MTAyNH0.z-SyGGXPD1rk9kq19G11JNcEfo4NgqN3ylhxVi9XHis' -H "Content-type: application/json" -d '{"name": "nikolay","message": "history 10"}' 'http://localhost:8190/test/api/v1/message'

пример ответа на получение последних 10 сообщений пользователя:
[{"message":"new added message"},{"message":"Message #12"},{"message":"Message #11"},{"message":"Message #10"},{"message":"Message #9"},{"message":"Message #8"},{"message":"Message #7"},{"message":"Message #6"},{"message":"Message #5"},{"message":"Message #4"}]

==== Тесты ====
Все тесты находятся в соответствующей директории проекта

==== Завершение работы ==== 
Остановите и удалите контейнеры, при необходимости командой: docker-compose down
