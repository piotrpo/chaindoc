package pl.com.digita.docblock.block

fun interface Rule<T : TransactionalPayload> {
    fun validate(block: Block<T>, transaction: Transaction<T>): Boolean
}
