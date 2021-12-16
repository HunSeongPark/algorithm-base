package com.hunseong.al_prac

import java.lang.Math.random
import java.util.Collections.swap

fun main() {
    val array = ArrayList<Int>()

    for (i in 0..100) {
        array.add((random() * 100).toInt())
    }
    array.insertSort()
    println(array)
}

fun ArrayList<Int>.insertSort() {
    val n = this.size

    // 첫번째 값은 삽입의 대상이 아니므로 0 until n-1
    for (i in 0 until n - 1) {
        // i + 1 번째 값에 대해 삽입 대상으로 지정
        for (j in i + 1 downTo 1) {
            // j - 1 값과 비교하며 더 작을 시 앞 index로 이동
            if (this[j] < this[j - 1]) swap(this, j, j - 1)
            // j - 1이 더 작을 시 비교 종료
            else break
        }
    }
}