#include <iostream>
#include <vector>
#include <stack>
#include <omp.h>

using namespace std;

const int MAX = 100000;

vector<int> graph[MAX];
bool visited[MAX];

void dfs(int node)
{
    stack<int> s;
    s.push(node);

    while (!s.empty())
    {
        int curr_node = s.top();
        s.pop();

        bool process = false;

        // Critical section for visited checking
        #pragma omp critical
        {
            if (!visited[curr_node])
            {
                visited[curr_node] = true;
                process = true;
            }
        }

        if (process)
        {
            cout << curr_node << " ";

            // Parallel processing of adjacent nodes
            #pragma omp parallel for
            for (int i = 0; i < graph[curr_node].size(); i++)
            {
                int adj_node = graph[curr_node][i];

                if (!visited[adj_node])
                {
                    // Protect stack push
                    #pragma omp critical
                    {
                        s.push(adj_node);
                    }
                }
            }
        }
    }
}

int main()
{
    int n, m, start_node;

    cout << "Enter No of Node, Edges, and Start Node: ";
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

    cout << "DFS Traversal: ";
    dfs(start_node);

    return 0;
}