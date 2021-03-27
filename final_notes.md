# Look at Xing Repo

**LookAtXing** is a sample app Android application 📱 built to demonstrate use of Modern Android development tools️. 

## About
It simply loads gitub data from an API and stores it in persistence storage. Github repositories will be always saved in the  local database.

- The app works offline but it needs to load data at least once from the API.
- You can click in git hub repositories to open the browser with their respective github link.
- The app supports portrait and landscape position

## Built With 
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Hilt](https://dagger.dev/hilt) - Dependency Injection Framework
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
- [Glide](https://bumptech.github.io/glide/) - An image loading library for Android.
- [MocKK](https://mockk.io/) - A kotlin library for Android unit test.
- [Espresso](https://developer.android.com/training/testing/espresso) - Espresso to write concise, beautiful, and reliable Android UI tests.

## Package Structure
```
| --app
    | -- data
        | -- remote
        | -- local
    | -- di
    | -- domain
        | -- github
    | -- ui
        | -- main
 
| -- common
    | -- annotations
    | -- domain
    | -- util
```   
## Architecture
This app uses [***MVVM and Clean Architecture***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://miro.medium.com/max/1200/1*XxYlayLVyJ7SkwrqOTt10w.png)
