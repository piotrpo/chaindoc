package pl.com.digita.docblock.block

class Block {
    private val chain = mutableListOf<Transaction>()
    private val unchained = mutableMapOf<Hash, Transaction>()
    val length: Int
        get() {
            return chain.size
        }

    val lastHash: Hash
        get() = chain.lastOrNull()?.hash ?: Hash.first()


    fun addTransaction(transaction: Transaction) {
        if (lastHash == transaction.previousHash) {
            chain.add(transaction)
            checkUnchained(transaction)
        } else {
            unchained[transaction.previousHash] = transaction
        }
    }

    private fun checkUnchained(transaction: Transaction) {
        val block1 = unchained[transaction.hash]
        block1?.let { this.addTransaction(it) }

    }
}
