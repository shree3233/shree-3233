#include <iostream>
#include <vector>
#include <omp.h>

using namespace std;

// Parallel Bubble Sort (Odd-Even Sort)
void parallelBubbleSort(vector<int>& arr)
{
    int n = arr.size();

    for (int i = 0; i < n; i++)
    {
        if (i % 2 == 0)
        {
            #pragma omp parallel for
            for (int j = 0; j < n - 1; j += 2)
            {
                if (arr[j] > arr[j + 1])
                {
                    swap(arr[j], arr[j + 1]);
                }
            }
        }
        else
        {
            #pragma omp parallel for
            for (int j = 1; j < n - 1; j += 2)
            {
                if (arr[j] > arr[j + 1])
                {
                    swap(arr[j], arr[j + 1]);
                }
            }
        }
    }
}

// Merge function
void merge(vector<int>& arr, int left, int mid, int right)
{
    vector<int> temp;
    int i = left;
    int j = mid + 1;

    while (i <= mid && j <= right)
    {
        if (arr[i] < arr[j])
            temp.push_back(arr[i++]);
        else
            temp.push_back(arr[j++]);
    }

    while (i <= mid)
        temp.push_back(arr[i++]);

    while (j <= right)
        temp.push_back(arr[j++]);

    for (int i = left; i <= right; i++)
    {
        arr[i] = temp[i - left];
    }
}

// Parallel Merge Sort
void parallelMergeSort(vector<int>& arr, int left, int right)
{
    if (left < right)
    {
        int mid = (left + right) / 2;

        #pragma omp parallel sections
        {
            #pragma omp section
            parallelMergeSort(arr, left, mid);

            #pragma omp section
            parallelMergeSort(arr, mid + 1, right);
        }

        merge(arr, left, mid, right);
    }
}

int main()
{
    int n;

    cout << "Enter number of elements: ";
    cin >> n;

    vector<int> arr(n), arr1;

    cout << "Enter elements:\n";
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }

    arr1 = arr;

    // Parallel Bubble Sort
    parallelBubbleSort(arr);

    cout << "\nBubble Sorted Array:\n";
    for (int i = 0; i < n; i++)
    {
        cout << arr[i] << " ";
    }

    // Parallel Merge Sort
    parallelMergeSort(arr1, 0, n - 1);

    cout << "\n\nMerge Sorted Array:\n";
    for (int i = 0; i < n; i++)
    {
        cout << arr1[i] << " ";
    }

    return 0;
}