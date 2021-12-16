package com.hunseong.al_prac

import java.lang.Math.random

fun main() {
    val merge = Merge()

    val arr = arrayListOf<Int>()

    for (i in 0 until 100) {
        arr.add((random() * 100).toInt())
    }

    println(merge.split(arr))
}

class Merge {
    fun split(arr: ArrayList<Int>): ArrayList<Int> {

        if (arr.size <= 1) return arr

        val middle = arr.size / 2

        // 재귀함수를 통해 left, right split 및 sort하여 merge
        val left = split(ArrayList(arr.subList(0, middle)))
        val right = split(ArrayList(arr.subList(middle, arr.size)))

        return merge(left, right)
    }

    private fun merge(left: ArrayList<Int>, right: ArrayList<Int>): ArrayList<Int> {
        val mergedList = arrayListOf<Int>()
        var leftIndex = 0
        var rightIndex = 0

        // Case 1 : left, right 모두 존재
        while (leftIndex < left.size && rightIndex < right.size) {
            if (left[leftIndex] < right[rightIndex]) {
                mergedList.add(left[leftIndex])
                leftIndex++
            } else {
                mergedList.add(right[rightIndex])
                rightIndex++
            }
        }

        // Case 2 : right만 존재
        while (rightIndex < right.size) {
            mergedList.add(right[rightIndex])
            rightIndex++
        }

        // Case 3 : left만 존재
        while (leftIndex < left.size) {
            mergedList.add(left[leftIndex])
            leftIndex++
        }
        return mergedList
    }
}