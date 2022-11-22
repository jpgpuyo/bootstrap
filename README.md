# Bootstrap

This is a **Bootstrap**, used for create a new app.
Just boilerplate code with simple features, but very useful to avoid the endless process of configure again dependencies,
define architecture and a way of working.

In the future, I can evolve this project to test new libraries or new ways of working.
Currently, this is not a Kotlin multiplatform project, but in the future, I can create a branch
for Kotlin multiplatform.

## Features
- Display user list and detail from api https://reqres.in/
- Flavors:
  - mock: Use mock data
  - dev: Use data from api
- Build types: 
  - debug
  - release: Not configured (pending to add signing configs)

# Show user list
- Display user list from endpoint: https://reqres.in/api/users
- Pull to refresh
- Error handling: Internet not available

# User detail
- Display user detail from endpoint: https://reqres.in/api/user/1
- Pending to add error handling: Internet not available

## Modules
* androidApp: Android module with the UI layer (android, kotlin)
* buildSrc: Kotlin module to share all the project dependencies
* shared: Core module that can be shared by multiple app modules

# Tech Stack

Currently used:
- MMVM: https://en.wikipedia.org/wiki/Model-view-viewmodel
- Jetpack Compose: https://developer.android.com/jetpack/compose
- Navigation -> Navigation component: https://developer.android.com/jetpack/compose/navigation
- Load network images -> Coil: https://coil-kt.github.io/coil/compose/
- Injecting dependencies -> Koin (service locator): https://insert-koin.io/docs/quickstart/android/
- Manage state -> MutableState: https://developer.android.com/jetpack/compose/state
- Async Programming -> Coroutines: https://kotlinlang.org/docs/coroutines-overview.html
- Fetch data from network-> Ktor client: https://ktor.io/clients
- Mock data from network -> Ktor client mock: https://ktor.io/docs/http-client-testing.html
- Error handling -> Custom class called Either: https://github.com/jpgpuyo/bootstrap/blob/main/shared/src/main/java/com/jpuyo/bootstrap/domain/model/Either.kt
- Firebase -> Crashlytics, Analytics and push notifications: https://firebase.google.com/

Libraries added but not used:
- Database: Sqldelight -> https://github.com/cashapp/sqldelight
- Preferences: Multiplatform settings -> https://github.com/russhwolf/multiplatform-settings
- Date time: Klock -> https://github.com/korlibs/korge/tree/main/klock

Testing:
- Unit tests: Coming soon. Several libraries added: mockito kotlin 2, kotlin test. Pending to review official samples to choose one library.
- UI tests: Coming soon.

# Error handling

To handle errors, we use a custom class inspired in arrow library called 'Either'.
I am using a simplified version of:
https://github.com/android10/Android-CleanArchitecture-Kotlin/blob/main/app/src/main/kotlin/com/fernandocejas/sample/core/functional/Either.kt

To return a successful operation we can return, for example, Either.Right(Success) or Either.Right(List<PaymentMeans>)
To return a error we can return, for example, Either.Left(DomainError.NoInternet) or Either.Left(DomainError.InvalidCredentials)

In this way, and combined with operator 'when' we are forced to handle all possible errors.

More detailed explanation here:
https://ashishchaudhary.in/better-error-handling-in-kotlin-with-either-type

# How to use http mock engine
Just modify the current mock engine from this class:
https://github.com/jpgpuyo/bootstrap/blob/main/shared/src/main/java/com/jpuyo/bootstrap/common/datasource/remote/engine/HttpMockEngine.kt

When we execute mock flavor, then mock engine will be used.

More info:
https://ktor.io/docs/http-client-testing.html#test-client

# TODO List
- Improve jetpack compose lifecycle
- Add unit tests
- Add UI tests
- Create activities and fragments without compose, but without modifying the view model
- Move hardcoded mocks to json file
- Add Material Design 3 and implement custom design properly
- Add database
- Use shared preferences for read/store something
- Add pagination

And anything that can help to reduce the boilerplate code needed to create a mew app. 