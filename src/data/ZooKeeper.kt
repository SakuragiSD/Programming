package data

class ZooKeeper {

    private val paths = hashMapOf<String, HashSet<String>>()
    private val map = hashMapOf<String, String>()
    private val registerMap = hashMapOf<String, () -> Unit> ()

    public fun create(path: String, value: String) {
        val split = "\\/".toRegex()
        val p = path.split(split)
        if (lookup(p)) {
            map[path] = value
            updatePaths(p)
        } else {
            throw Exception()
        }
    }

    private fun lookup(p: List<String>): Boolean {
        for (i in p.indices) {
            if (i != p.size - 1 && !paths.containsKey(p[i])) {
                return false
            } else if (i == p.size - 1 && paths.containsKey(p[i])) {
                return false
            }
        }

        return true
    }

    private fun updatePaths(p: List<String>) {
        paths[p[p.size - 1]] = hashSetOf<String>()

        if (p.size > 1) {
            paths[p[p.size - 2]]!!.add(p[p.size - 1])
        }
    }

    public fun read(path: String): String {
        return map[path]!!
    }

    public fun update(path: String, value: String) {
        val v = map[path] ?: throw  Exception()

        map[path] = value
    }

    public fun watch(path: String, function: () -> Unit) {
        registerMap[path] = function

        println("inside????")
        function.invoke()
        var j = path.length - 1

        while (j > 0) {
            while (j > 0 && path[j] != '/') {
                j--
            }


            val parent = path.substring(0, j)
            println("parent is $parent")
            if (registerMap.containsKey(parent)) {
                registerMap[parent]!!
            }
            j--
        }
    }
}