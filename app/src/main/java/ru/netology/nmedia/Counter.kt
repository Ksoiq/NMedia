package ru.netology.nmedia

object Counter {
        fun displayFormatting(count: Int): String {
        return when (count) {
            in 0..999 -> count.toString()
            in 1000..1099 -> "${count / 1000}K"
            in 1100..9999 -> "${count / 1000}.${count / 100 % 10}K"
            in 10_000..999_999 -> "${count / 1000}K"
            in 1_000_000..1_099_999 -> "${count / 1_000_000}M"
            else -> "${(count / 10.pow(2)) / 10_000}.${((count / 1000) / 10.pow(2)) % 10}M"
        }
    }

    private fun Int.pow(x: Int): Int = (2..x).fold(this) { R, _ ->
        R * this
    }
}