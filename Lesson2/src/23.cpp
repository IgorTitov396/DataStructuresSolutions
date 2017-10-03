#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

static int w = 0;
static int h = 0;
static int n = 0;

int f(int index) {
    return max(w * (int)(ceil(n / (double)index)), h * index);
}

int threeSearch(int leftIndex, int rightIndex) {
    int midleIndexLeft = leftIndex + (rightIndex - leftIndex + 1) / 3;
    int midleIndexRight = leftIndex + (rightIndex - leftIndex + 1) * 2 / 3;
    if (leftIndex + 3 >= rightIndex) {
        int min = f(leftIndex);
        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (f(i) < min) min = f(i);
        }
        return min;
    } else {
        if (f(midleIndexLeft) > f(midleIndexRight)) threeSearch(midleIndexLeft, rightIndex); else {
            if (f(midleIndexRight) > f(midleIndexLeft)) threeSearch(leftIndex, midleIndexRight); else {
                vector<int> v;
                v.push_back(leftIndex);
                v.push_back(midleIndexLeft);
                v.push_back(midleIndexRight);
                v.push_back(rightIndex);
                int min = f(v[0]);
                for (int i = 0; i < v.size() - 1; i++) {
                    if (v[i] != v[i + 1]) {
                        if (threeSearch(v[i], v[i + 1]) < min) min = threeSearch(v[i], v[i + 1]);
                    }
                }
                return min;
            }
        }
    }
}

int main() {
    cin >> w >> h >> n;
    cout << threeSearch(1, n) << endl;
    return 0;
}