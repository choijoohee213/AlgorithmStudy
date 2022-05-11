N = int(input())
A = list(map(int, list(input())))
B = list(map(int, list(input())))
cnt = [0, 1];
for s in range(2):
    tmp = [0] * N
    for i in range(N): tmp[i] = A[i]
    if s == 1: # 스위치 상태 변경
        tmp[0] = 1-tmp[0]
        tmp[1] = 1-tmp[1]
    for i in range(1, N):
        if tmp[i-1] != B[i-1]:
            tmp[i-1] = 1-tmp[i-1]
            tmp[i] = 1-tmp[i]
            if i+1 < N: tmp[i+1] = 1-tmp[i+1]
            cnt[s] += 1
    if tmp != B: cnt[s] = -1
if cnt[0] >= 0 and cnt[1] >= 0: print(min(cnt[0], cnt[1]))
elif cnt[0] >= 0 and cnt[1] < 0: print(cnt[0])
elif cnt[0] < 0 and cnt[1] >= 0: print(cnt[1])
else: print(-1)