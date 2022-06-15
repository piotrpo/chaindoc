package pl.com.digita.docblock.block

import kotlin.test.Test
import kotlin.test.assertEquals


internal class BlockTest {
    @Test
    fun addBlockToChain() {

        val block = Block()
        val lastHash = block.lastHash
        val payload = TransactionPayload("some payload")

        val transaction = Transaction(lastHash, payload)
        block.addTransaction(transaction)

        assertEquals(transaction.hash, block.lastHash)
    }

    @Test
    fun rejectWrongHash() {
        val block = Block()
        val lastHash = block.lastHash
        val payload = TransactionPayload("some payload")

        val firstTransaction = Transaction(lastHash, payload)
        val corruptedTransaction = Transaction(Hash("corrupted".toByteArray()), payload)

        block.addTransaction(firstTransaction)
        block.addTransaction(corruptedTransaction)

        assertEquals(1, block.length)
    }

    @Test
    fun addValidBlocks() {
        val block = Block()
        val payload = TransactionPayload("some payload")

        (1..4).forEach { _ ->
            val transaction = Transaction(block.lastHash, payload)
            block.addTransaction(transaction)
        }
        assertEquals(4, block.length)
    }

    @Test
    fun cacheInvalidBlockUntilItsValid() {
        val block = Block()
        val payload = TransactionPayload("Payload")
        val firstTransaction = Transaction(block.lastHash, payload)
        val lazyTransaction = Transaction(firstTransaction.hash, payload)
        val lastTransaction = Transaction(lazyTransaction.hash, payload)

        block.addTransaction(firstTransaction)
        block.addTransaction(lastTransaction)
        block.addTransaction(lazyTransaction)

        assertEquals(3, block.length)
    }
}
