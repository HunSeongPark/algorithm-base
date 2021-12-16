package com.hunseong.al_prac

import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val vertices: ArrayList<String> = ArrayList(listOf("A", "B", "C", "D", "E", "F", "G"))
    val edges = ArrayList<Edge>()
    edges.add(Edge(7, "A", "B"))
    edges.add(Edge(5, "A", "D"))
    edges.add(Edge(7, "B", "A"))
    edges.add(Edge(8, "B", "C"))
    edges.add(Edge(9, "B", "D"))
    edges.add(Edge(7, "B", "E"))
    edges.add(Edge(8, "C", "B"))
    edges.add(Edge(5, "C", "E"))
    edges.add(Edge(5, "D", "A"))
    edges.add(Edge(9, "D", "B"))
    edges.add(Edge(7, "D", "E"))
    edges.add(Edge(6, "D", "F"))
    edges.add(Edge(7, "E", "B"))
    edges.add(Edge(5, "E", "C"))
    edges.add(Edge(7, "E", "D"))
    edges.add(Edge(8, "E", "F"))
    edges.add(Edge(9, "E", "G"))
    edges.add(Edge(6, "F", "D"))
    edges.add(Edge(8, "F", "E"))
    edges.add(Edge(11, "F", "G"))
    edges.add(Edge(9, "G", "E"))
    edges.add(Edge(11, "G", "F"))

    val kruskal = Kruskal()
    kruskal.kruskalFun(vertices, edges).forEach {
        println(it.toString())
    }
}


data class Edge(
    val weight: Int,
    val U: String,
    val V: String,
) : Comparable<Edge> {
    override fun toString(): String {
        return "($U - $V / $weight)"
    }

    override fun compareTo(other: Edge): Int {
        return weight - other.weight
    }
}

class Kruskal {
    // 각 vertex의 parent vertex 저장
    private val parent = HashMap<String, String>()

    // 각 vertex의 rank 저장
    private val rank = HashMap<String, Int>()

    // vertex를 개별 집합으로 초기화
    private fun initVertex(vertex: String) {
        parent[vertex] = vertex
        rank[vertex] = 0
    }

    // root vertex를 find
    private fun find(vertex: String): String {

        // root vertex가 아닐 때
        if (parent[vertex] != vertex) {
            // 재귀 함수를 통해 root를 찾기 위해 지나는 모든 vertex를 root의 child로 연결
            parent[vertex] = find(parent[vertex]!!)
        }
        // 최종적으로 root 반환
        return parent[vertex]!!
    }

    // 두 vertex가 포함된 tree를 합침
    private fun union(u: String, v: String) {
        val uRoot = find(u)
        val vRoot = find(v)

        if (rank[uRoot]!! > rank[vRoot]!!) {
            parent[vRoot] = uRoot
        } else {
            parent[uRoot] = vRoot
            if (rank[uRoot] == rank[vRoot]) {
                rank[vRoot] = rank[vRoot]!! + 1
            }
        }
    }

    fun kruskalFun(vertices: ArrayList<String>, edges: ArrayList<Edge>): ArrayList<Edge> {

        // Mininum Spanning Tree (result)
        val mst = arrayListOf<Edge>()
        var currentEdge: Edge

        // 모든 vertex에 대해 개별 집합으로 초기화
        for (i in vertices.indices) {
            initVertex(vertices[i])
        }

        // edges를 weight에 대해 오름차순 정렬
        edges.sort()

        // 모드 edge 순회
        for (i in edges.indices) {
            currentEdge = edges[i]

            // edge의 두 vertex 포함된 tree 연결 시 cycle이 발생하지 않는 경우 union
            if (find(currentEdge.U) != find(currentEdge.V)) {
                union(currentEdge.U, currentEdge.V)
                mst.add(currentEdge)
            }
        }
        return mst
    }
}