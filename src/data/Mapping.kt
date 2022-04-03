package data

object Mapping {
    fun get(key: String): Int {
        return map.getOrDefault(key, -1)
    }

    fun update(key: String, value: Int) {
        map.put(key, value)
    }

    fun size(): Int {
        return map.size
    }

    private val map = hashMapOf<String, Int>(
            "test" to 1
    )
}