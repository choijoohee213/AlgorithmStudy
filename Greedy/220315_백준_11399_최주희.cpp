#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(){
	int n,x,result=0,sum=0;
	cin>>n;
	int a[n];
	for(int i=0; i<n; i++){
		cin>>x;
		a[i]=x;
	}
	
	sort(a,a+n);

	for(int i=0; i<n; i++){
		result+=a[i];
		sum+=result;	
	}
	cout<<sum;
	getchar();
	getchar();
	return 0;
	
}