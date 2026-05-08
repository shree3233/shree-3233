#include <iostream>
#include <cuda_runtime.h>

using namespace std;

__global__ void matrixMul(int *A, int *B, int *C, int n)
{
    int row = blockIdx.y * blockDim.y + threadIdx.y;
    int col = blockIdx.x * blockDim.x + threadIdx.x;

    if (row < n && col < n)
    {
        int sum = 0;

        for (int k = 0; k < n; k++)
        {
            sum += A[row * n + k] * B[k * n + col];
        }

        C[row * n + col] = sum;
    }
}

int main()
{
    int n;

    cout << "Enter size of matrix (n x n): ";
    cin >> n;

    int size = n * n * sizeof(int);

    int *A = new int[n * n];
    int *B = new int[n * n];
    int *C = new int[n * n];

    cout << "Enter first matrix:\n";
    for (int i = 0; i < n * n; i++)
        cin >> A[i];

    cout << "Enter second matrix:\n";
    for (int i = 0; i < n * n; i++)
        cin >> B[i];

    int *d_A, *d_B, *d_C;

    cudaMalloc((void**)&d_A, size);
    cudaMalloc((void**)&d_B, size);
    cudaMalloc((void**)&d_C, size);

    cudaMemcpy(d_A, A, size, cudaMemcpyHostToDevice);
    cudaMemcpy(d_B, B, size, cudaMemcpyHostToDevice);

    dim3 threads(16, 16);
    dim3 blocks((n + 15) / 16, (n + 15) / 16);

    matrixMul<<<blocks, threads>>>(d_A, d_B, d_C, n);

    cudaMemcpy(C, d_C, size, cudaMemcpyDeviceToHost);

    cout << "\nResult Matrix:\n";

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cout << C[i * n + j] << " ";
        }
        cout << endl;
    }

    cudaFree(d_A);
    cudaFree(d_B);
    cudaFree(d_C);

    delete[] A;
    delete[] B;
    delete[] C;

    return 0;
}