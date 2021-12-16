package com.hunseong.al_prac

import android.annotation.SuppressLint
import java.util.*
import kotlin.collections.HashMap

fun main() {
    val mygraph = HashMap<String, HashMap<String, Int>>()

    var edges: HashMap<String, Int> = HashMap()
    edges["B"] = 7
    edges["D"] = 5
    mygraph["A"] = edges

    edges = HashMap()
    edges["A"] = 7
    edges["D"] = 9
    edges["C"] = 8
    edges["E"] = 7
    mygraph["B"] = edges

    edges = HashMap()
    edges["B"] = 8
    edges["E"] = 5
    mygraph["C"] = edges

    edges = HashMap()
    edges["A"] = 5
    edges["B"] = 9
    edges["E"] = 7
    edges["F"] = 6
    mygraph["D"] = edges

    edges = HashMap()
    edges["B"] = 7
    edges["C"] = 5
    edges["D"] = 7
    edges["F"] = 8
    edges["G"] = 9
    mygraph["E"] = edges

    edges = HashMap()
    edges["D"] = 6
    edges["E"] = 8
    edges["G"] = 11
    mygraph["F"] = edges

    edges = HashMap()
    edges["E"] = 9
    edges["F"] = 11
    mygraph["G"] = edges

    Prim().primFun(mygraph, "A")
}

class Prim {
    @SuppressLint("NewApi")
    fun primFun(graph: HashMap<String, HashMap<String, Int>>, start: String) {
        var sum = 0
        val queue = PriorityQueue<Edge>()
        val dict = HashMap<String, Edge>()
        var edgeObj: Edge
        var currentEdge: Edge
        var adjacentEdges: HashMap<String, Int>
        var linkedEdge: Edge

        // start weight 0 , 나머지 inf로 dict 초기화, queue에 넣기
        for (key in graph.keys) {
            if (key == start) {
                edgeObj = Edge(key, 0)
            } else {
                edgeObj = Edge(key, Int.MAX_VALUE)
            }

            queue.add(edgeObj)
            dict[key] = edgeObj
        }

        // 하나씩 꺼내면서 dict, queue 업데이트
        while (queue.isNotEmpty()) {

            // 해당 vertex 이미 순회했으니 queue, dict에서 삭제, weight에 더함
            currentEdge = queue.remove()
            dict.remove(currentEdge.node)
            sum += currentEdge.weight

            adjacentEdges = graph.getOrDefault(currentEdge.node, HashMap())

            // 인접 vertex 순회하며 dict에 저장된 가중치와 비교 / 업데이트
            for (key in adjacentEdges.keys) {
                // key가 dict 내에 있을 경우에만 수행
                if (dict.containsKey(key)) {
                    linkedEdge = dict[key]!!
                    if (adjacentEdges[key]!! < linkedEdge.weight) {
                        linkedEdge.weight = adjacentEdges[key]!!

                        // 우선순위 큐 정렬 업데이트
                        queue.remove(linkedEdge)
                        queue.add(linkedEdge)
                    }
                }
            }
        }
        println(sum)
    }
}

data class Edge(
    val node: String,
    var weight: Int,
): Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return weight - other.weight
    }
}