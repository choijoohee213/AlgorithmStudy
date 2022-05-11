'''
비트마스크를 활용한다.
N에 대해 각 자리수를 '뒤집는다/안뒤집는다' 로 생각할 수 있다.
N=3 -> 010 -> 2번째 자리수가 1 -> 2번째 행을 뒤집었다는 것을 의미
'''
N = int(input())
coin = [list(input()) for _ in range(N)]
res = 4001
for bit in range(1 << N):
    tmp = [coin[i][:] for i in range(N)]
    for i in range(N):
        if bit & (1 << i):
            for j in range(N):
                if tmp[i][j] == 'H':
                    tmp[i][j] = 'T'
                else:
                    tmp[i][j] = 'H'
    tot = 0
    for i in range(N):
        cnt = 0
        for j in range(N):
            if tmp[j][i] == 'T': # 열에 대한 탐색
                cnt += 1
        tot += min(cnt, N-cnt)
    res = min(res, tot)
print(res)