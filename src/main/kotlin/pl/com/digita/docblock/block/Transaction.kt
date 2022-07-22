package pl.com.digita.docblock.block

import java.security.MessageDigest


class Transaction<out T : TransactionalPayload>(val previousHash: Hash, val payload: T) {

    val hash: Hash
        get() {
            return calculateHash(previousHash + payload)
        }

    private fun calculateHash(hashable: Hashable): Hash {
        return Hash(MessageDigest.getInstance("SHA-256").digest(hashable.hashBytes))
    }


}
