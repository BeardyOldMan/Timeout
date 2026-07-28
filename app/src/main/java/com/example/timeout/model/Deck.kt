package com.example.timeout.model

class Deck{
    private val cards=mutableListOf<Card>()
    init{
        for (suit in Suit.entries){
            for (rank in Rank.entries) {
                cards.add(Card(suit, rank))
            }
        }
        cards.shuffle()
    }
    fun draw(): Card{
        return cards.removeAt(cards.lastIndex)
    }
}