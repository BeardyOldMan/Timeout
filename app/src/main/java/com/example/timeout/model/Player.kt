package com.example.timeout.model


class Player {
    private val hands = mutableListOf<Hand>()
    val allHands: List<Hand>
        get() = hands
    fun addHand(index: Int, hand: Hand) {
        hands.add(index, hand)
    }
    fun removeHand(index: Int) {
        hands.removeAt(index)
    }
    fun getHand(index: Int): Hand {
        return hands[index]
    }
}