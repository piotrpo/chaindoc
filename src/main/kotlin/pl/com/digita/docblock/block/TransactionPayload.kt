package pl.com.digita.docblock.block

class TransactionPayload(val s: String) : Hashable {
    override val hashBytes: ByteArray
        get() = s.toByteArray()

}
