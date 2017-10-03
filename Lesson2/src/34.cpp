#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

static int k = 0;
static int n = 0;
static vector<int> arr;

int f(int index) {
	int col = 0;
	for (int i = 0; i < n; i++) {
		col += arr[i] / index;
	}
	if (col < k) {
		return 0;
	} else {
		return index;
	}
}

int threeSearch(int leftIndex, int rightIndex) {
	int max = f(leftIndex);
	for (int i = leftIndex + 1; i <= rightIndex; i++) {
		if (f(i) > max) max = f(i);
	}
	return max;
}

int main() {
	ofstream out;
	ifstream in;
	in.open("input.txt");
	out.open("output.txt");
	in >> n >> k;
	int buf = 0;
	for (int i = 0; i < n; i++) {
		in >> buf;
		arr.push_back(buf);
	}
	int sumArr = 0;
	for (int i = 0; i < n; i++) sumArr += arr[i];
	int maxL = sumArr / k;
	out << threeSearch(1, maxL);
	out.close();
	in.close();
	return 0;
}