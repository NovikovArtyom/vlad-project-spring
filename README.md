# Описание проекта

Проект представляет собой REST API для управления сущностями: пользователями, статьями, видео и комментариями.

### Технологический стек

- Язык программирования: Java

- Фреймворк: Spring Framework

- База данных: PostgreSQL

- Формат данных: JSON

### Сущности API

1. Пользователи (Users)

2. Статьи (Articles)

3. Видео (Videos)

4. Комментарии (Comments)

5. Роли (Roles)

### Таблицы и связи

````
users
├── id (UUID, PK)
├── email (VARCHAR(50), UNIQUE, NOT NULL)
└── password (VARCHAR(60), NOT NULL)

article (1:N)
├── id (UUID, PK)
├── user_id (UUID, FK → users.id, NOT NULL)
├── name (VARCHAR(50), NOT NULL)
└── article (VARCHAR(255), NOT NULL)

video (1:N)
├── id (UUID, PK)
├── user_id (UUID, FK → users.id, NOT NULL)
├── name (VARCHAR(50), NOT NULL)
└── url (VARCHAR(50), NOT NULL)

comment (1:N)
├── id (UUID, PK)
├── user_id (UUID, FK → users.id, NOT NULL)
├── comment (VARCHAR(255), NOT NULL)
├── article_id (UUID, FK → article.id, NULLABLE)
└── video_id (UUID, FK → video.id, NULLABLE)

user_role (M:N через связующую таблицу)
├── id (UUID, PK)
├── user_id (UUID, FK → users.id, NOT NULL)
└── role_id (UUID, FK → role.id, NOT NULL)

role
├── id (UUID, PK)
├── title (VARCHAR(25, NOT NULL)
└── description (VARCHAR(255))
````

### Описание таблиц

1. users - таблица пользователей
   - Содержит основную информацию о пользователях
   - Имеет связи с таблицами article, video, comment и user_role

2. article - таблица статей
   - Каждая статья принадлежит одному пользователю
   - Содержит название и текст статьи

3. video - таблица видео
   - Каждое видео принадлежит одному пользователю
   - Содержит название и URL видео

4. comment - таблица комментариев
   - Каждый комментарий принадлежит одному пользователю
   - Может относиться либо к статье, либо к видео (одно из полей article_id/video_id обязательно должно быть заполнено)

5. role - таблица ролей
   - Содержит предопределенные роли USER и ADMIN
   - Описывает права доступа

6. user_role - связующая таблица между users и role
   - Реализует отношение многие-ко-многим между пользователями и ролями

## Запуск проекта

### Предусловие

Для запуска приложения, на локальной машине должно быть установлено:

- jdk-21
- Maven-3.9.11
- Docker
- docker-compose

### Алгоритм запуска:
1. Открыть корневую директорию проекта
2. Перейти в ветку develop
3. Сбилдить проект:
``mvn clean install``
4. Перейти в директорию с docker-compose файлом: ``cd docker/prod``
5. Поднять контейнеры с приложением: ``docker compose up -d --build``
6. Открыть swagger проекта: http://localhost:8081/swagger-ui/index.html#

