#include <iostream>
#include <cuda_runtime.h>

using namespace std;

__global__ void vectorAdd(int *A, int *B, int *C, int n)
{
    int i = blockIdx.x * blockDim.x + threadIdx.x;

    if (i < n)
    {
        C[i] = A[i] + B[i];
    }
}

int main()
{
    int n;

    cout << "Enter size of vector: ";
    cin >> n;

    int size = n * sizeof(int);

    int *A = new int[n];
    int *B = new int[n];
    int *C = new int[n];

    cout << "Enter elements of first vector:\n";
    for (int i = 0; i < n; i++)
        cin >> A[i];

    cout << "Enter elements of second vector:\n";
    for (int i = 0; i < n; i++)
        cin >> B[i];

    int *d_A, *d_B, *d_C;

    cudaMalloc((void**)&d_A, size);
    cudaMalloc((void**)&d_B, size);
    cudaMalloc((void**)&d_C, size);

    cudaMemcpy(d_A, A, size, cudaMemcpyHostToDevice);
    cudaMemcpy(d_B, B, size, cudaMemcpyHostToDevice);

    int threads = 256;
    int blocks = (n + threads - 1) / threads;

    vectorAdd<<<blocks, threads>>>(d_A, d_B, d_C, n);

    cudaMemcpy(C, d_C, size, cudaMemcpyDeviceToHost);

    cout << "\nResult Vector:\n";
    for (int i = 0; i < n; i++)
        cout << C[i] << " ";

    cudaFree(d_A);
    cudaFree(d_B);
    cudaFree(d_C);

    delete[] A;
    delete[] B;
    delete[] C;

    return 0;
}