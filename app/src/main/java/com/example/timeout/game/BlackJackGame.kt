package com.example.timeout.game

import com.example.timeout.model.Deck
import com.example.timeout.model.Hand
import com.example.timeout.model.Player

class BlackJackGame(
    playerBet: Int,
    private var totalFinance: Int
) {
    companion object {
        private const val MAX_HANDS = 4
    }

    private val deck = Deck()
    private val player = Player()
    private val dealer: Hand

    private var currentHandIndex = 0

    init {
        player.addHand(
            0,
            Hand(
                deck.draw(),
                deck.draw(),
                playerBet
            )
        )

        dealer = Hand(
            deck.draw(),
            deck.draw(),
            0
        )
    }

    fun hit() {
        currentHand().addCard(deck.draw())

        if (currentHand().isBust()) {
            moveToNextHand()
        }
    }
    fun stand() {
        moveToNextHand()
    }

    fun split() {
        if (!canSplit()) return

        val hand = currentHand()

        val firstHand = Hand(
            hand.card1,
            deck.draw(),
            hand.bet
        )

        val secondHand = Hand(
            hand.card2,
            deck.draw(),
            hand.bet
        )

        player.removeHand(currentHandIndex)

        player.addHand(currentHandIndex, secondHand)
        player.addHand(currentHandIndex, firstHand)

        totalFinance -= hand.bet
    }
    fun doubleDown() {
        if (!canDoubleDown()) return

        val hand = currentHand()

        totalFinance -= hand.bet

        hand.bet *= 2
        hand.addCard(deck.draw())

        moveToNextHand()
    }
    fun canHit(): Boolean {
        val hand = currentHand()

        return !hand.isBust() &&
                !hand.isBlackJack()
    }
    fun canStand(): Boolean {
        val hand = currentHand()

        return !hand.isBust() &&
                !hand.isBlackJack()
    }
    fun canSplit(): Boolean {
        val hand = currentHand()

        return hand.canSplit() &&
                player.allHands.size < MAX_HANDS &&
                totalFinance >= hand.bet
    }

    fun canDoubleDown(): Boolean {
        val hand = currentHand()

        return hand.cardCount == 2 &&
                !hand.isBlackJack() &&
                totalFinance >= hand.bet
    }
    fun isRoundOver(): Boolean {
        return currentHandIndex >= player.allHands.size
    }


    private fun dealerTurn() {
        while (dealer.value() < 17 ||
            (dealer.value() == 17 && dealer.isSoft())
        ) {
            dealer.addCard(deck.draw())
        }
    }
    private fun evaluateRound() {
        val dealerValue = dealer.value()

        for (hand in player.allHands) {

            if (hand.isBust()) {
                continue
            }

            if (dealer.isBust()) {
                totalFinance += hand.bet * 2
                continue
            }

            if (hand.isBlackJack() && !dealer.isBlackJack()) {
                totalFinance += (hand.bet * 2.5).toInt()
                continue
            }

            if (dealer.isBlackJack() && !hand.isBlackJack()) {
                continue
            }

            if (hand.value() > dealerValue) {
                totalFinance += hand.bet * 2
            }
            else if (hand.value() == dealerValue) {
                totalFinance += hand.bet
            }
        }
    }
    private fun moveToNextHand() {
        currentHandIndex++

        if (currentHandIndex >= player.allHands.size) {
            dealerTurn()
            evaluateRound()
        }
    }
    private fun currentHand(): Hand {
        return player.getHand(currentHandIndex)
    }
}