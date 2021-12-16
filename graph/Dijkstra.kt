package com.hunseong.al_prac

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@RequiresApi(Build.VERSION_CODES.N)
fun main() {

    val graph = HashMap<String, ArrayList<Edge>>()
    graph["A"] =
        ArrayList(listOf(Edge(8, "B"), Edge(1, "C"), Edge(2, "D")))
    graph["B"] = ArrayList()
    graph["C"] =
        ArrayList(listOf(Edge(5, "B"), Edge(2, "D")))
    graph["D"] =
        ArrayList(listOf(Edge(3, "E"), Edge(5, "F")))
    graph["E"] = ArrayList(listOf(Edge(1, "F")))
    graph["F"] = ArrayList(listOf(Edge(5, "A")))

    val dik = Dijkstra()
    println(dik.dijkstraFunc(graph, "A"))
}

data class Edge(
    val distance: Int,
    val vertex: String,
) : Comparable<Edge>{
    override fun toString(): String {
        return "vertex : $vertex, distance : $distance"
    }

    override fun compareTo(other: Edge): Int {
        return distance - other.distance
    }
}

class Dijkstra {

    @RequiresApi(Build.VERSION_CODES.N)
    fun dijkstraFunc(graph: HashMap<String, ArrayList<Edge>>, start: String): HashMap<String, Int> {
        val distances = HashMap<String, Int>()

        // start vertex 제외 distance 모두 inf로 초기화
        for (key in graph.keys) {
            distances[key] = Int.MAX_VALUE
        }
        distances[start] = 0

        val priorityQueue = PriorityQueue<Edge>()

        // start vertex Edge 우선순위 큐에 추가
        priorityQueue.add(Edge(distances[start]!!, start))

        // Algorithm
        var edge: Edge
        var adjacentEdge: Edge
        var adjacentDistance: Int
        var distance: Int
        var adjacentVertex: String
        var currentDistance: Int
        var currentVertex: String
        var nodeList: ArrayList<Edge>

        // 우선순위 큐가 비어있지 않을 때까지 반복
        while (priorityQueue.isNotEmpty()) {
            // 우선순위 큐에서 Edge 꺼내옴
            edge = priorityQueue.remove()
            // 현재 Edge의 distance
            currentDistance = edge.distance
            // 현재 Edge의 vertex 이름
            currentVertex = edge.vertex

            // distance 배열에 들어있는 distance보다 priority queue에 들어있는 distance가 더 클 경우 계산 종료
            if (distances[currentVertex]!! < currentDistance) {
                continue
            }

            // 해당 vertex와 인접한 Node 모두 꺼내옴
            nodeList = graph[currentVertex]!!

            // 인접한 Node 모두 순회
            for (i in nodeList.indices) {
                adjacentEdge = nodeList[i]
                adjacentDistance = adjacentEdge.distance
                adjacentVertex = adjacentEdge.vertex
                // 새로 찾은 distance (현재 distance + 인접한 node의 distance)
                distance = currentDistance + adjacentDistance

                // 새로 찾은 distance가 기존 최단 경로보다 짧을 경우 값 교체 후 우선순위 큐에 저장
                if (distances[adjacentVertex]!! > distance) {
                    distances[adjacentVertex] = distance
                    priorityQueue.add(Edge(distance, adjacentVertex))
                }
            }
        }

        return distances
    }
}

