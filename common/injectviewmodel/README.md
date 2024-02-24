# Инжект ViewModel через Dagger

Предоставляет механизм инжекта зависимостей в ViewModel через Dagger. Использует мультибиндинг по ключу `ViewModelKey`. Получать
ViewModel через фабрику `InjectViewModelFactory`.

### Использование:

1. Забиндить ViewModel в dagger:

```kotlin
@Binds
@IntoMap
@ViewModelKey(ScreenViewModel::class)
abstract fun dashboardViewModel(viewModel: ScreenViewModel): ViewModel
```

2. На экране (Fragment/Activity) получить ViewModel с помощью одного из делегатов:

- Fragment `by appViewModels { injectViewModelFactory }`
- Fragment `by appActivityViewModels { injectViewModelFactory }`