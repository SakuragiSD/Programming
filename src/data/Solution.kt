package data

import kotlin.random.Random

class Solution(head: ListNode?) {
        private val arr = mutableListOf<ListNode>()
        private var node = head
        private var size = 0

        init {
            while (node != null) {
                arr.add(node!!)
                node = node!!.next
            }
            size = arr.size
        }

        fun getRandom(): Int {
            if (size == 0) {
                return Int.MIN_VALUE
            }
            val value = Random.nextInt(size)
            return arr[value].`val`
        }

}