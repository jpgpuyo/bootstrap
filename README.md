# Bootstrap

This is a **Bootstrap**, used for create a new app.

## Features
TODO

## Modules
* androidApp: Android module with the UI layer (android, kotlin)
* buildSrc: Kotlin module to share all the project dependencies
* shared: Core module that can be shared by multiple app modules

## Overview
TODO

# Architecture

TODO

# Error handling

To handle errors, we use a custom class inspired in arrow library called 'Either'
To return a succesful operation we can return, for example, Either.Right(Success) or Either.Right(List<PaymentMeans>)
To return a error we can return, for example, Either.Left(DomainError.NoInternet) or Either.Left(DomainError.InvalidCredentials)

In this way, and combined with operator 'when' we are forced to handle all possible errors.

More detailed explanation here:
https://ashishchaudhary.in/better-error-handling-in-kotlin-with-either-type
