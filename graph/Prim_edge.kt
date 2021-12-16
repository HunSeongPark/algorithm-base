package com.hunseong.al_prac

import android.annotation.SuppressLint
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val edges = ArrayList<Edge>()
    edges.add(Edge(7, "A", "B"))
    edges.add(Edge(5, "A", "D"))
    edges.add(Edge(8, "B", "C"))
    edges.add(Edge(9, "B", "D"))
    edges.add(Edge(7, "D", "E"))
    edges.add(Edge(5, "C", "E"))
    edges.add(Edge(7, "B", "E"))
    edges.add(Edge(6, "D", "F"))
    edges.add(Edge(8, "E", "F"))
    edges.add(Edge(9, "E", "G"))
    edges.add(Edge(11, "F", "G"))
    println(Prim().primFun(edges, "A"))
}

class Prim {

    @SuppressLint("NewApi")
    fun primFun(edges: ArrayList<Edge>, startVertex: String): ArrayList<Edge> {

        val mst = arrayListOf<Edge>()
        val connectedVertices = arrayListOf<String>()
        val graph = HashMap<String, ArrayList<Edge>>()
        val queue = PriorityQueue<Edge>()

        var currentEdge: Edge
        var currentList: ArrayList<Edge>

        var adjacentEdges: ArrayList<Edge>

        // graph key(vertex)에 대해 빈 arrayList로 초기화
        for (i in edges.indices) {
            currentEdge = edges[i]
            if (!graph.containsKey(currentEdge.u)) {
                graph[currentEdge.u] = arrayListOf()
            }
            if (!graph.containsKey(currentEdge.v)) {
                graph[currentEdge.v] = arrayListOf()
            }
        }

        // graph key(vertex)에 대해 인접한 Edge 추가
        for (i in edges.indices) {
            currentEdge = edges[i]
            currentList = graph[currentEdge.u]!!
            currentList.add(currentEdge)
            currentList = graph[currentEdge.v]!!
            currentList.add(Edge(currentEdge.weight, currentEdge.v, currentEdge.u))
        }

        // start vertex의 인접 edge들을 우선순위 큐에 추가
        connectedVertices.add(startVertex)
        queue.addAll(graph.getOrDefault(startVertex, emptyList<Edge>()))

        while (queue.isNotEmpty()) {
            currentEdge = queue.remove()
            // 현재 vertex 기준으로 연결된 vertex가 이미 mst에 존재하는 vertex가 아닐 경우에만 connectedVertices, mst에 추가
            if (!connectedVertices.contains(currentEdge.v)) {
                connectedVertices.add(currentEdge.v)
                mst.add(currentEdge)
                // 현재 vertex 기준으로 연결된 vertex의 인접 edges
                adjacentEdges = graph.getOrDefault(currentEdge.v, arrayListOf())

                for (i in adjacentEdges.indices) {
                    // 현재 vertex 기준으로 연결된 vertex에 연결된 vertex가 이미 mst에 존재하는 vertex가 아닐 경우에만 우선순위 큐에 추가
                        // 즉, 현재 vertex - vertex - vertex에 대한 확인
                    if (!connectedVertices.contains(adjacentEdges[i].v)) {
                        queue.add(adjacentEdges[i])
                    }
                }
            }
        }
        return mst
    }
}

data class Edge(
    val weight: Int,
    val u: String,
    val v: String,
) : Comparable<Edge> {

    override fun compareTo(other: Edge): Int {
        return weight - other.weight
    }
}