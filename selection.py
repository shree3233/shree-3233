def selection_sort(arr):
    n = len(arr)
    print("Original array:", arr)

    for i in range(n):
        # Assume the current index is the minimum
        min_index = i

        # Greedily search for the smallest element in the unsorted part
        for j in range(i + 1, n):
            if arr[j] < arr[min_index]:
                min_index = j

        # Swap the found minimum element with the current element
        arr[i], arr[min_index] = arr[min_index], arr[i]

        print(f"Step {i+1}: {arr}")

    print("Sorted array:", arr)


arr = [64, 25, 12, 22, 11]
selection_sort(arr)
