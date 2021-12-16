package com.hunseong.al_prac

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val graph = HashMap<String, ArrayList<String>>()

    graph["A"] = arrayListOf("B", "C")
    graph["B"] = arrayListOf("A", "D")
    graph["C"] = arrayListOf("A", "G", "H", "I")
    graph["D"] = arrayListOf("B", "E", "F")
    graph["E"] = arrayListOf("D")
    graph["F"] = arrayListOf("D")
    graph["G"] = arrayListOf("C")
    graph["H"] = arrayListOf("C")
    graph["I"] = arrayListOf("C", "J")
    graph["J"] = arrayListOf("I")
    bfs(graph, "A")
}

fun bfs(graph: HashMap<String, ArrayList<String>>, startNode: String): ArrayList<String> {
    val visited = arrayListOf<String>()
    val needVisit = arrayListOf<String>()

    // startNode 저장
    needVisit.add(startNode)

    while (needVisit.isNotEmpty()) {
        // 방문했으므로 needVisit에서 first 값 제거
        val node = needVisit.removeFirst()

        // 이미 방문한 노드가 아니라면 인접 노드 needVisit 추가, 해당 노드 방문한 노드에 추가
        if (!visited.contains(node)) {
            needVisit.addAll(graph[node]!!)
            visited.add(node)
        }
    }
    return visited
}