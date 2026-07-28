package com.example.timeout.model


class Dealer {

    lateinit var hand: Hand

    fun newHand(hand: Hand) {
        this.hand = hand
    }

    fun clearHand() {
        hand = Hand(0)
    }
}