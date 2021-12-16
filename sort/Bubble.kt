package com.hunseong.al_prac

import java.lang.Math.random
import java.util.Collections.swap

fun main() {
    val array = ArrayList<Int>()

    for (i in 0..100) {
        array.add((random() * 100).toInt())
    }
    array.bubbleSort()
    println(array)
}

fun ArrayList<Int>.bubbleSort() {
    val n = this.size

    // 바깥쪽 loop(배열을 순회할 횟수)
    for (i in 0 until n - 1) {
        var isSwap = false
        // 안쪽 loop(swap 여부를 판단할 횟수)
        // 바깥쪽 loop가 한 번 돌 때마다 배열의 끝에 정렬된 값이 들어오므로 n - 1 - i
        for (j in 0 until n - 1 - i) {
            if (this[j] > this[j + 1]) {
                swap(this, j, j + 1)
                isSwap = true
            }
        }

        // 정렬이 완료된 경우 바로 return (시간복잡도 감소)
        if (!isSwap) {
            return
        }
    }
}