# Library App

Android-приложение для управления библиотекой на Kotlin, Jetpack Compose (Material 3) и ViewModel.

## Возможности
- Список книг (`LazyColumn`, `Card`)
- Поиск по названию и автору
- Фильтр «только доступные»
- Добавление новой книги
- Отметка «выдана / доступна» (`Checkbox`)
- Демонстрация `remember` vs `rememberSaveable` vs `ViewModel` при повороте экрана

## Структура
- `model/Book.kt` — модель данных
- `viewmodel/LibraryViewModel.kt` — состояние и логика
- `ui/Components.kt` — переиспользуемые Composable: `SearchField`, `BookCard`, `AddBookForm`, `RotationDemo`
- `ui/LibraryScreen.kt` — главный экран

## Запуск
1. Откройте папку проекта в Android Studio (File → Open)
2. Дождитесь синхронизации Gradle
3. Выберите эмулятор или устройство и нажмите Run

## Скриншоты
![Главный экран](screenshots/main.png)
![Поиск и добавление](screenshots/rotation.png)

## remember vs rememberSaveable vs ViewModel
- `remember` — состояние живёт, пока Composable в композиции; при повороте экрана (пересоздании Activity) теряется.
- `rememberSaveable` — то же, но значение сохраняется в `Bundle`; переживает поворот и смерть процесса. Для небольших данных (текст в поле).
- `ViewModel` — хранит состояние экрана и логику; переживает поворот, не зависит от жизненного цикла Activity.

В демо-блоке «Демо поворота экрана» поле `remember` очищается при повороте, а поле `rememberSaveable` и данные из ViewModel (книги, поиск, фильтр) сохраняются.
