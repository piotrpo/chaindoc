package pl.com.digita.docblock.block

class StringPayload(private val stringValue: String) : TransactionalPayload {
    override val hashBytes: ByteArray
        get() = stringValue.toByteArray()
}
