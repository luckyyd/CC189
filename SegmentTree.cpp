// SegmentTree.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int SIZE = (1 << 18) - 1;
const int MAX_N = 1 << 10;
int N, M;
int A[MAX_N];
int I[MAX_N], J[MAX_N], K[MAX_N];
int nums[MAX_N];
vector<int> dat[SIZE];

void init(int k, int l, int r) {
	if (r - l == 1) {
		dat[k].push_back(A[l]);
	}
	else {
		int lch = k * 2 + 1;
		int rch = k * 2 + 2;
		init(lch, l, (l + r) / 2);
		init(rch, (l + r) / 2, r);
		dat[k].resize(r - 1);
		merge(dat[lch].begin(), dat[lch].end(), dat[rch].begin(), dat[rch].end(), dat[k].begin());
	}
}

int query(int i, int j, int x, int k, int l, int r) {
	if (j <= l || i > r) {
		return 0;
	}
	else if (i <= l && j >= r) {
		return upper_bound(dat[k].begin(), dat[k].end(), x) - dat[k].begin();
	}
	else {
		int lc = query(i, j, x, k * 2 + 1, l, (l + r) / 2);
		int rc = query(i, j, x, k * 2 + 2, (l + r) / 2, r);
		return lc + rc;
	}
}

void solve() {
	for (int i = 0; i < N; i++) {
		nums[i] = A[i];
		sort(nums, nums + N);
		init(0, 0, N);
		for (int i = 0; i < M; i++) {
			int l = I[i], r = J[i] + 1, k = K[i];

			int lb = -1, ub = N - 1;
			while (ub - lb > 1) {
				int md = (ub - lb) / 2;
				int c = query(l, r, nums[md], 0, 0, N);
				if (c >= k) {
					ub = md;
				}
				else {
					lb = md;
				}
			}
			cout << nums[ub] << endl;
		}
	}
}

int main()
{
    return 0;
}

