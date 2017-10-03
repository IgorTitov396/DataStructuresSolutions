#include <iostream>
#include <fstream>
using namespace std;

static int w = 0;
static int h = 0;
static int n = 0;

bool isContains(int index) {
	return (index / h) * (index / w) >= n;
}

int threeSearch(int leftIndex, int rightIndex) {
	int left = leftIndex;
	int right = rightIndex;
	while (left + 1 != right) {
		int mid = (left + right) / 2;
		if (isContains(mid)) right = mid;
		else left = mid;
	}
	return right;
}

int main()
{
	ofstream out;
	ifstream in;
	in.open("diplomas.in");
	out.open("diplomas.out");
	in >> w >> h >> n;
	int start = 0;
	if (w > h) {
		start = w * n;
	} else {
		start = h * n;
	}
	out << threeSearch(0, start);
	out.close();
	in.close();
}