package ru.eruditeonline.app.presentation.managers

object DeepLink {
    object Auth {
        const val REGISTRATION = "/registration.html"
        const val CONFIRM_EMAIL_QUERY_NAME = "token"
        const val LOGIN = "/login.html"
    }

    object Profile {
        const val PROFILE = "/lk.html"
        const val COMMON_RESULTS = "/results.html"
        const val SEARCH_RESULTS_BY_EMAIL = "/reward.html"
    }

    object Rating {
        const val DAY = "/rating-day.html"
        const val MONTH = "/rating-month.html"
        const val YEAR = "/rating-year.html"
    }

    object CompetitionList {
        const val ITEMS = "/konkurs_all.html"
        const val FILTER_PREFIX = "/filter"
        const val SUBJECT_PATH_PART = "/subject/"
        const val AGE_QUERY_NAME = "age"
    }

    object Competition {
        const val PREFIX = "/konkurs/"
        const val TEST = "/quiz.html"
        const val TEST_QUERY_NAME = "test"
    }
}