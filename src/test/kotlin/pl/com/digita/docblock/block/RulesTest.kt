package pl.com.digita.docblock.block

import kotlin.test.Test
import kotlin.test.assertEquals

class RulesTest {
    @Test
    fun testDoubleCloseNotAllowed() {
        val block = Block<WorkFlowPayload>()
        block.addRule { bl, transaction ->
            if (transaction.payload.action == WorkFlowPayload.WorkFlowAction.CLOSE) {
                val count = bl.chain.count { it.payload.action == WorkFlowPayload.WorkFlowAction.CLOSE }
                count == 0
            } else {
                true
            }
        }

        assertEquals(
            AttachTransactionResult.ResultType.ADDED, block.addTransaction(
                Transaction(
                    block.lastHash, WorkFlowPayload(
                        WorkFlowPayload.WorkFlowAction.OPEN
                    )
                )
            ).result
        )
        assertEquals(
            AttachTransactionResult.ResultType.ADDED, block.addTransaction(
                Transaction(
                    block.lastHash, WorkFlowPayload(
                        WorkFlowPayload.WorkFlowAction.CLOSE
                    )
                )
            ).result
        )
        assertEquals(
            AttachTransactionResult.ResultType.DROPPED, block.addTransaction(
                Transaction(
                    block.lastHash, WorkFlowPayload(
                        WorkFlowPayload.WorkFlowAction.CLOSE
                    )
                )
            ).result
        )

        assertEquals(2, block.length)
    }
}
