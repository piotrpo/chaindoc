package pl.com.digita.docblock.block

class Block<T : TransactionalPayload> {

    private val rules = mutableSetOf<Rule<T>>()
    val chain = mutableSetOf<Transaction<T>>()
    private val rejected = mutableMapOf<Hash, Transaction<T>>()
    val length: Int
        get() {
            return chain.size
        }

    val lastHash: Hash
        get() = chain.lastOrNull()?.hash ?: Hash.first()


    fun addTransaction(transaction: Transaction<T>): AttachTransactionResult {
        return if (canBeAttachedToTheEnd(transaction) && followRules(transaction)) {
            chain.add(transaction)
            reprocessUnchained(transaction)
            AttachTransactionResult(AttachTransactionResult.ResultType.ADDED)
        } else {
            rejected[transaction.previousHash] = transaction
            AttachTransactionResult(AttachTransactionResult.ResultType.DROPPED)
        }
    }

    private fun followRules(transaction: Transaction<T>): Boolean {
        return rules.filter { !it.validate(this, transaction) }.isEmpty()
    }

    private fun canBeAttachedToTheEnd(transaction: Transaction<T>) =
        lastHash == transaction.previousHash

    private fun reprocessUnchained(transaction: Transaction<T>) {
        val block1 = rejected[transaction.hash]
        block1?.let { this.addTransaction(it) }

    }

    fun addRule(rule: Rule<T>) {
        rules.add(rule)
    }
}

class AttachTransactionResult(val result: ResultType) {
    enum class ResultType {
        ADDED,
        DROPPED
    }

}
