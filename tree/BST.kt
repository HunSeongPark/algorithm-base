package com.hunseong.al_prac

import java.util.*

fun main() {
    val myTree = TreeManager()

    myTree.put(10)
    myTree.put(15)
    myTree.put(13)
    myTree.put(11)
    myTree.put(14)
    myTree.put(18)
    myTree.put(16)
    myTree.put(19)
    myTree.put(17)
    myTree.put(7)
    myTree.put(8)
    myTree.put(6)
    println(myTree.delete(15))
    println("HEAD: " + myTree.head!!.value)
    println("HEAD LEFT: " + myTree.head!!.left!!.value)
    println("HEAD LEFT LEFT: " + myTree.head!!.left!!.left!!.value)
    println("HEAD LEFT RIGHT: " + myTree.head!!.left!!.right!!.value)

    println("HEAD RIGHT: " + myTree.head!!.right!!.value)
    println("HEAD RIGHT LEFT: " + myTree.head!!.right!!.left!!.value)
    println("HEAD RIGHT RIGHT: " + myTree.head!!.right!!.right!!.value)

    println("HEAD RIGHT RIGHT LEFT: " + myTree.head!!.right!!.right!!.left!!.value)
    println("HEAD RIGHT RIGHT RIGHT: " + myTree.head!!.right!!.right!!.right!!.value)


}

class TreeManager {
    var head: Node? = null

    fun put(value: Int) {
        // CASE 1 : Tree가 비었을 때 (head = null)
        if (head == null) {
            head = Node(value = value)
            return
        }

        var parentNode = head
        while (true) {
            if (parentNode!!.value < value) {
                if (parentNode.right != null) {
                    parentNode = parentNode.right
                } else {
                    // CASE 2 : Tree가 비어있지 않고, 찾은 parent 노드의 오른쪽에 삽입
                    parentNode.right = Node(value = value)
                    break
                }
            } else {
                if (parentNode.left != null) {
                    parentNode = parentNode.left
                } else {
                    // CASE 3 : Tree가 비어있지 않고, 찾은 parent 노드의 오른쪽에 삽입
                    parentNode.left = Node(value = value)
                    break
                }
            }
        }
    }

    fun get(value: Int) : Node? {
        // CASE 1 : Tree가 비었을 때 (head = null)
        if (head == null) {
            return null
        }

        var node = head
        while (node != null) {
            if (node.value == value) {
                return node
            }
            node = if (node.value < value) {
                node.right
            } else {
                node.left
            }
        }
        return null
    }

    fun delete(value: Int) : Boolean {
        // Case 1 : Tree가 비었을 때 (head = null)
        if (head == null) {
            return false
        }

        // Case 2 : Node가 1개이고, 삭제해야 할 노드일 때
        if (head!!.value == value && head!!.left == null && head!!.right == null) {
            head = null
            return true
        }

        // 삭제 할 Node와 Parent Node 탐색
        var parent = head!!
        var current = head
        while (current != null) {
            if (current.value == value) {
                break
            } else if (current.value < value) {
                parent = current
                current = current.right
            } else {
                parent = current
                current = current.left
            }
        }

        // Case 3 : 삭제할 Node가 존재하지 않을 때
        if (current == null) return false

        // Case 4 : 삭제할 Node가 Leaf Node일 때
        if (current.left == null && current.right == null) {
            if (parent.value > current.value) {
                parent.left = null
            } else {
                parent.right = null
            }
        }

        // Case 5 : 삭제할 Node가 Child Node를 1개만 가지고 있을 때
        if (current.left != null && current.right == null) {
            // Case 5-1 : Left Child Node를 가지고 있을 때
            if (parent.value > current.value) {
                parent.left = current.left
            } else {
                parent.right = current.left
            }
        } else {
            // Case 5-2 : Right Child Node를 가지고 있을 때
            if (parent.value > current.value) {
                parent.left = current.right
            } else {
                parent.right = current.right
            }
        }

        // Case 6 : 삭제할 Node가 Child Node를 2개 가지고 있을 때
        // 1. 삭제할 Node의 Right Child Node 중 Most Left Child Node으로 교체 (해당 방법 사용)
        // 2. 삭제할 Node의 Left Child Node 중 Most Right Child Node으로 교체

        var changeParent = current.right!!
        var change = current.right!!

        // change node 및 change node의 parent 탐색
        while (change.left != null) {
            changeParent = change
            change = change.left!!
        }

        if (parent.value > current.value) {
            if (change.right == null) {
                // Case 6-1 : change node가 leaf node 일 때
                parent.left = change
                changeParent.left = null
                change.left = current.left
                change.right = current.right
            } else {
                // Case 6-2 : change node가 right child node를 가질 때 (왼쪽 자식은 가질 수 없음)
                parent.left = change
                changeParent.left = change.right
                change.left = current.left
                change.right = current.right
            }
        } else {
            if (change.right == null) {
                // Case 6-1 : change node가 leaf node 일 때
                parent.right = change
                changeParent.left = null
                change.left = current.left
                change.right = current.right
            } else {
                // Case 6-2 : change node가 right child node를 가질 때 (왼쪽 자식은 가질 수 없음)
                parent.right = change
                changeParent.left = change.right
                change.left = current.left
                change.right = current.right
            }
        }
        return true
    }
}

data class Node(
    var left: Node? = null,
    var right: Node? = null,
    var value: Int,
)