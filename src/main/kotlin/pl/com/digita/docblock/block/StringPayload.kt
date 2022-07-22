package pl.com.digita.docblock.block

class StringPayload(val stringValue: String) : TransactionalPayload {
    override val hashBytes: ByteArray
        get() = stringValue.toByteArray()
}
