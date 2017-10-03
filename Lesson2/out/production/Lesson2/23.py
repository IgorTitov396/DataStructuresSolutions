def good(a):
    return (a // w) * (a // h) >= n

w, h, n = map(int, input().split())
left = 0
right = max(w * n, h * n)
while right != left + 1:
    middle = (right + left) // 2
    if good(middle):
        right = middle
    else:
        left = middle
print(right) 