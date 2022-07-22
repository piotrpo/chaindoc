package pl.com.digita.docblock.block

class WorkFlowPayload(val action: WorkFlowAction) : TransactionalPayload {
    override val hashBytes: ByteArray
        get() = action.toString().toByteArray()

    enum class WorkFlowAction {
        OPEN, CLOSE
    }
}




