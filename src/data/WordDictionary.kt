package data

class WordDictionary() {
    class Trie(`val`: Char) {
        val children = arrayOfNulls<Trie>(26)
        var isLeaf = false
    }

    private val root = Trie('#')
    fun addWord(word: String) {
        var node = root
        for (w in word) {
            if (node.children[w - 'a'] == null) {
                node.children[w - 'a'] = Trie(w)
            }
            node = node.children[w - 'a']!!
        }
        node.isLeaf = true
    }

    fun search(word: String): Boolean {
        var node = root
        return search(word, 0, node)
    }

    private fun search(word: String, i: Int, node: Trie): Boolean {
        if (i == word.length) {
            return node.isLeaf
        }
        var current = node
        for (s in i until word.length) {
            val c = word[s]
            if (c == '.') {
                for (x in 'a'..'z') {
                    if (current.children[x - 'a'] != null) {
                        if (search(word, s + 1, current.children[x - 'a']!!)) {
                            return true
                        }
                    }
                }
                return false
            }

            if (current.children[c - 'a'] == null) {
                return false
            }
            current = current.children[c - 'a']!!
        }

        return current.isLeaf
    }
}