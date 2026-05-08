#include <iostream>
#include <vector>
#include <stack>
#include <queue>
#include <omp.h>

using namespace std;

const int MAX = 100000;

vector<int> graph[MAX];
bool visited[MAX];

// Parallel DFS
void dfs(int node)
{
    stack<int> s;
    s.push(node);

    while (!s.empty())
    {
        int curr_node = s.top();
        s.pop();

        if (!visited[curr_node])
        {
            visited[curr_node] = true;
            cout << curr_node << " ";

            #pragma omp parallel for
            for (int i = 0; i < graph[curr_node].size(); i++)
            {
                int adj_node = graph[curr_node][i];

                if (!visited[adj_node])
                {
                    #pragma omp critical
                    s.push(adj_node);
                }
            }
        }
    }
}

// Parallel BFS
void bfs(int node)
{
    queue<int> q;
    q.push(node);
    visited[node] = true;

    while (!q.empty())
    {
        int curr_node = q.front();
        q.pop();

        cout << curr_node << " ";

        #pragma omp parallel for
        for (int i = 0; i < graph[curr_node].size(); i++)
        {
            int adj_node = graph[curr_node][i];

            if (!visited[adj_node])
            {
                #pragma omp critical
                {
                    if (!visited[adj_node])
                    {
                        visited[adj_node] = true;
                        q.push(adj_node);
                    }
                }
            }
        }
    }
}

int main()
{
    int n, m, start_node;

    cout << "Enter No of Nodes, Edges and Start Node: ";
    cin >> n >> m >> start_node;

    cout << "Enter Pair of Edges:\n";

    for (int i = 0; i < m; i++)
    {
        int u, v;
        cin >> u >> v;

        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    // Initialize visited array
    #pragma omp parallel for
    for (int i = 0; i < n; i++)
    {
        visited[i] = false;
    }

    cout << "\nDFS Traversal: ";
    dfs(start_node);

    // Reset visited for BFS
    #pragma omp parallel for
    for (int i = 0; i < n; i++)
    {
        visited[i] = false;
    }

    cout << "\nBFS Traversal: ";
    bfs(start_node);

    return 0;
}