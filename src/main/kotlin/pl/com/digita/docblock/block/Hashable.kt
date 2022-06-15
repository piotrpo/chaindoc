package pl.com.digita.docblock.block

interface Hashable {
    val hashBytes: ByteArray

    operator fun plus(append: Hashable): Hashable {
        return Hash(this.hashBytes + append.hashBytes)
    }

}
