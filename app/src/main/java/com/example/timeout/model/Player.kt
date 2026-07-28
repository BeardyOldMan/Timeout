package com.example.timeout.model


class Player {
    private val hands = mutableListOf<Hand>()
    fun addHand(hand: Hand) {
        hands.add(hand)
    }
    fun removeHand(hand: Hand) {
        hands.remove(hand)
    }
    fun getHand(index: Int): Hand {
        return hands[index]
    }
    val handCount: Int
        get() = hands.size
    fun clearHands() {
        hands.clear()
    }
}