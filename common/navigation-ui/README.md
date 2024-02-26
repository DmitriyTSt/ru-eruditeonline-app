# Navigation-ui

Модуль является реализацией навигации с помощью `navigation-core` на Android с GoogleNavigationComponent для навигации по
фрагментам.

Модуль реализует следующие варианты навигации, наследующиеся от `AbstractScreenDestination`:

- `NavigationUiScreenDestination.Action` - принимает Action из Google NavigationComponent и NavOptions
- `NavigationUiScreenDestination.DeepLink` - принимает NavDeepLinkRequest из GoogleNavigationComponent и NavOptions
- `NavigationUiScreenDestination.Activity` - принимает Android Intent

Для удобного использования добавлены расширения:
- `Destination.Action()` = `Destination.Screen(NavigationUiScreenDestination.Action())`
- `Destination.DeepLink()` = `Destination.Screen(NavigationUiScreenDestination.DeepLink())`
- `Destination.Activity()` = `Destination.Screen(NavigationUiScreenDestination.Activity())`