package com.hunseong.al_prac

fun main() {
    val heapTest = Heap()
    heapTest.push(15)
    heapTest.push(10)
    heapTest.push(8)
    heapTest.push(5)
    heapTest.push(4)
    heapTest.push(20)
    println(heapTest.arr)

    heapTest.pop()
    println(heapTest.arr)
}

class Heap {
    val arr = arrayListOf(-1)

    fun push(value: Int) {

        // Heap이 비어있을 때
        if (arr.size == 1) {
            arr.add(value)
            return
        }

        // Heap의 마지막 index에 새로운 값 저장
        arr.add(value)

        // current index
        var current = arr.lastIndex

        while (isUp(current)) {
            // swap
            arr[current / 2] = arr[current].also { arr[current] = arr[current / 2] }
            current /= 2
        }

    }

    fun pop() : Int? {
        // Heap이 비어있을 때
        if (arr.size == 1) {
            return null
        }

        // Root와 Last swap, Last 값 제거
        val popValue = arr.last()
        arr[arr.lastIndex] = arr[1].also { arr[1] = arr[arr.lastIndex] }
        arr.removeLast()

        var current = 1
        while (isDown(current)) {
            val left = current * 2
            val right = left + 1

            // Case 1 : left child만 존재 (완전이진트리 이므로 right child만 존재할 수는 없음)
            if (right >= arr.size) {
                arr[right] = arr[current].also { arr[current] = arr[right] }
                current = right
            } else {
                // Case 2 : left, right child 모두 존재
                if (arr[left] > arr[right]) {
                    arr[left] = arr[current].also { arr[current] = arr[left] }
                    current = left
                } else {
                    arr[right] = arr[current].also { arr[current] = arr[right] }
                    current = right
                }
            }
        }
        return popValue
    }

    private fun isUp(current: Int): Boolean {
        // 만약 현재 위치가 root라면 false
        if (current <= 1) {
            return false
        }

        return arr[current / 2] < arr[current]
    }

    private fun isDown(current: Int): Boolean {
        val left = current * 2
        val right = current * 2 + 1
        // Case 1 : 현재 위치가 leaf (left, right child 모두 없음)
        if (left >= arr.size) {
            return false
        }
        // Case 2 : left child만 존재 (완전이진트리 이므로 right child만 존재할 수는 없음)
        if (right >= arr.size) {
            return arr[current] < arr[left]
        }
        // Case 3 : left, right child 모두 존재
        return (arr[current] < arr[left] || arr[current] < arr[right])
    }
}