#include<iostream>
using namespace std;
int main() {
	//input
	int m,n;
	cout<<"Nhap n : ";
	cin>>n;
	cout<<"Nhap m : ";
	cin>>m;
	int x1[n],x2[m];
	cout<<"Nhap day 1 : ";
	for(int i=0; i<n; i++) {
		cin>>x1[i];
	}
	cout<<"Nhap day 2 : ";
	for(int i=0; i<m; i++) {
		cin>>x2[i];
	}

	//processs
	int l=0;
	for(int i=0; i<n; i++) {
		int r= m-1;
		int mid;
		while(l<r-1) {
			mid = (l+r)/2;
			if(x1[i]<x2[mid]) {
				r=mid;
			} else {
				l=mid;
			}
		}
		if(x1[i]==x2[l] || x1[i]==x2[r]) {
			cout<<x1[i]<<" ";
		}
	}
	return 0;
}
