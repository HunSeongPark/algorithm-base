package com.hunseong.al_prac

import java.lang.Math.random
import java.util.Collections.swap

fun main() {
    val array = ArrayList<Int>()

    for (i in 0..100) {
        array.add((random() * 100).toInt())
    }
    array.selectSort()
    println(array)
}

fun ArrayList<Int>.selectSort() {
    val n = this.size

    var min: Int
    // 마지막 값은 정렬할 필요 없으므로 0 until n - 1
    for (i in 0 until n - 1) {
        // 초기 index를 최솟값 index로 설정
        min = i
        // 초기 index의 다음 index부터 마지막 index까지 순회
        for (j in i+1 until n) {
            // 값 비교 후 mininum index 변경
            if (this[min] > this[j]) min = j
        }
        // 최솟값을 앞에 배치
        swap(this, i, min)
    }
}