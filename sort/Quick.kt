package com.hunseong.al_prac

import java.lang.Math.random

fun main() {
    val arr = arrayListOf<Int>()

    for (i in 0 until 100) {
        arr.add((random() * 100).toInt())
    }
    println(quickMerge(arr))
}

fun quickMerge(arr: ArrayList<Int>): ArrayList<Int> {

    if (arr.size <= 1) return arr

    // index의 처음 값 pivot 설정
    val pivot = arr.removeFirst()

    val left = arrayListOf<Int>()
    val right = arrayListOf<Int>()

    for (i in arr.indices) {

        // pivot과 배열의 각 값 비교하여 left or right 배열에 추가
        if (arr[i] > pivot) {
            right.add(arr[i])
        } else {
            left.add(arr[i])
        }
    }

    val mergedList = arrayListOf<Int>()

    // 재귀함수를 통해 정렬된 left, right 배열 left + pivot + right 순으로 merge
    mergedList.addAll(quickMerge(left))
    mergedList.add(pivot)
    mergedList.addAll(quickMerge(right))
    return mergedList
}