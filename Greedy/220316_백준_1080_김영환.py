N, M = map(int, input().split())
A = [list(map(int, list(input()))) for _ in range(N)]
B = [list(map(int, list(input()))) for _ in range(N)]
cnt = 0
for i in range(N-2):
    for j in range(M-2):
        if A[i][j] != B[i][j]:
            for x in range(i, i+3):
                for y in range(j, j+3):
                    A[x][y] = 1 - A[x][y]
            cnt += 1
for i in range(N):
    for j in range(M):
        if A[i][j] != B[i][j]:
            print(-1)
            exit()
print(cnt)