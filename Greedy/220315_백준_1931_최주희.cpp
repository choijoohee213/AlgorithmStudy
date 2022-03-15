#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(pair<int, int> a, pair<int,int> b){
	if(a.second == b.second) return a.first < b.first;
	else return a.second < b.second;
}

int main(){
	int n, a, b, endTime, count = 1;
    vector<pair<int, int> > v;
	
	scanf("%d", &n);
	
	for(int k=0; k<n; k++){
		scanf("%d %d", &a, &b);
		v.push_back(make_pair(a,b));
	}
	
	sort(v.begin(), v.end(), compare);	

	endTime = v[0].second;
	
	for(int j=1; j<n; j++){
		if(endTime <= v[j].first){
			count++;
			endTime = v[j].second;
		}
	}
			
	printf("%d", count);
	return 0;
} 

