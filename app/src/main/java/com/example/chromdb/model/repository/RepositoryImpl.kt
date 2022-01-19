package com.example.chromdb.model.repository

import com.example.chromdb.model.entities.MovieItem

class RepositoryImpl : Repository {


    private fun movieListFromServer(): List<MovieItem> {
        val movieList = mutableListOf<MovieItem>()
        IMAGES.shuffle()
        for (i in 0 until IMAGES.size) {
            movieList.add(
                MovieItem(
                    i, "Matrix$i", IMAGES[i], 2000 + i, (0..10).random(),
                    DESCRIPTION[i], false
                )
            )
        }
        return movieList
    }

    private val movieListFromLocalStorage: List<MovieItem> = listOf(
        MovieItem(1, "Matrix3", IMAGES[2], 2003, 1, "description1", false),
        MovieItem(2, "Matrix4", IMAGES[3], 2004, 2, "description2", false)
    )

    override fun getMovieItemFromServer() = movieListFromServer()

    override fun getMovieItemFromLocalStorage() = movieListFromLocalStorage

    companion object {
        private val IMAGES = mutableListOf(
            "https://images.unsplash.com/photo-1600267185393-e158a98703de?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NjQ0&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1579710039144-85d6bdffddc9?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0Njk1&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1488426862026-3ee34a7d66df?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0ODE0&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1620252655460-080dbec533ca?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NzQ1&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1613679074971-91fc27180061?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NzUz&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
            "https://images.unsplash.com/photo-1485795959911-ea5ebf41b6ae?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NzU4&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
        )
        private val DESCRIPTION: List<String> = listOf(
            "Герой приходит в царство, о котором люди рассказывают истории про крайнюю жестокость и ужас." +
                    " Столкнувшись с злым повелителем, он начинает понимать, что повелитель довольно приятный и разумный человек, а граждане просто очень сильно ноют",
            "Двое мужчин обедают в пустом ресторане. Один богат, а один беден. Они хорошие друзья. Через две минуты один из них погибнет",
            "Несколько лет назад ты помог потерянной маленькой девочке. Ты только что умер и увидел, что она стоит над тобой с косой в руке. Внезапно она узнает тебя.",
            "Вы можете оживлять свои рисунки в реальной жизни. Вы рисуете портал в другой мир с эльфами, орками и гоблинами.",
            "Один из мощных телескопов в мире ловит радиосигнал. Новость об этом подхватывают все СМИ, но учёные уверяют, что это волна исходила от телевизионного спутника или чего-то вроде этого. Вот только радиосигнал был отчетлив и был точно неземной ошибкой. Величайшие умы заняты расшифровкой сообщения, которое никак не похоже языком и шифровкой и работу человека. Спустя некоторое время космонавты на МКС получают видеосигнал. Он оттуда же, откуда и первый сигнал. И существа на нем выглядят в точности как люди.",
            "Один человек теряет свою любовь из за несчастного случая. Его жизнь не имеет смысла, и он начинает творить зло. По случайности, он открывает портал в другую вселенную..."
        )
    }
}