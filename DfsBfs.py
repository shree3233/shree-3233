def dfs(visited, graph, node):
    if node not in visited:
        print(node, end=" ")
        visited.add(node)
        for neighbour in graph[node]:
            dfs(visited, graph, neighbour)


def bfs(visited, graph, node, queue):
    visited.add(node)
    queue.append(node)

    while queue:
        s = queue.pop(0)
        print(s, end=" ")
        for neighbour in graph[s]:
            if neighbour not in visited:
                visited.add(neighbour)
                queue.append(neighbour)


def main():
    visited1 = set()  # For DFS
    visited2 = set()  # For BFS
    queue = []
    n = int(input("Enter number of nodes: "))
    graph = dict()

    for i in range(1, n + 1):
        edges = int(input(f"Enter number of edges for node {i}: "))
        if i not in graph:
            graph[i] = []

        for j in range(1, edges + 1):
            node = int(input(f"Enter edge {j} for node {i}: "))
            graph[i].append(node)

            # Ensure the graph is undirected (add reverse edge)
            if node not in graph:
                graph[node] = []
            graph[node].append(i)

    print("\nThe following is DFS:")
    dfs(visited1, graph, 1)

    print("\n\nThe following is BFS:")
    bfs(visited2, graph, 1, queue)


if __name__ == "__main__":
    main()
