package pl.com.digita.docblock.block

import java.security.MessageDigest


class Transaction(val previousHash: Hash, val payload: TransactionPayload) {

    val hash: Hash
        get() {
            return calculateHash(previousHash + payload)
        }

    private fun calculateHash(hashable: Hashable): Hash {
        return Hash(MessageDigest.getInstance("SHA-256").digest(hashable.hashBytes))
    }


}
