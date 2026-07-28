package com.example.timeout.model

class Hand(
    val bet: Int
) {
    private val cards = mutableListOf<Card>()
    fun addCard(card: Card) {
        cards.add(card)
    }
    fun value(): Int {
        var total = 0
        var aces = 0
        for (card in cards) {
            when (card.rank) {
                Rank.ACE -> {
                    total += 11
                    aces++
                }
                Rank.TWO -> total += 2
                Rank.THREE -> total += 3
                Rank.FOUR -> total += 4
                Rank.FIVE -> total += 5
                Rank.SIX -> total += 6
                Rank.SEVEN -> total += 7
                Rank.EIGHT -> total += 8
                Rank.NINE -> total += 9
                Rank.TEN,
                Rank.JACK,
                Rank.QUEEN,
                Rank.KING -> total += 10
            }
        }
        while (total > 21 && aces > 0) {
            total -= 10
            aces--
        }
        return total
    }
    fun isBust(): Boolean {
        return value() > 21
    }
    fun isBlackjack(): Boolean {
        return cards.size == 2 && value() == 21
    }
}