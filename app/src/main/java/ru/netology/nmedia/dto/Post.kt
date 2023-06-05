package ru.netology.nmedia.dto

import java.math.BigDecimal
import java.math.RoundingMode

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int,
    var shares: Int,
    var views: Int,
    var likedByMe: Boolean = false,
    var sharedByMe: Boolean = false
    )

fun formatNumber(number: Int): String {
    val outputNumber = BigDecimal(number)
    val divisorForThousands = BigDecimal(1_000.0)
    val divisorForMillions = BigDecimal(1_000_000.0)

    return    when (number) {

        in 1_000..1_099 -> "${outputNumber.divide(divisorForThousands, 0, RoundingMode.DOWN)}K"
        in 1_100..9_999 -> "${outputNumber.divide(divisorForThousands, 1, RoundingMode.DOWN)}K"
        in 10_000..999_999 -> "${outputNumber.divide(divisorForThousands, 0, RoundingMode.DOWN)}K"
        in 1_000_000..1_099_999 -> "${outputNumber.divide(divisorForMillions, 0, RoundingMode.DOWN)}M"
        in 1_100_000..999_999_999 -> "${outputNumber.divide(divisorForMillions, 1, RoundingMode.DOWN)}M"
        else -> number.toString()
    }


}