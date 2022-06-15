package pl.com.digita.docblock.block

class Hash(override val hashBytes: ByteArray) : Hashable {
    companion object {
        fun first(): Hash {
            return Hash("Start".toByteArray())
        }
    }

    override fun equals(other: Any?): Boolean {
        other as Hash?
        if (other?.hashBytes?.size != hashBytes.size) {
            return false
        }
        other?.hashBytes.indices.forEach {
            if (other?.hashBytes[it] != hashBytes[it]) {
                return false
            }
        }
        return true
    }

    override fun hashCode(): Int {
        return hashBytes.contentHashCode()
    }
}
