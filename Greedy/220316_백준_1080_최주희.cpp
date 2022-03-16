#include <stdio.h>
int n,m;
int a[51][51];
int b[51][51];

int main(){
    int res=0;
	scanf("%d %d",&n,&m);
	for(int i=0; i<n; i++){
		for(int j=0; j<m; j++){
		    scanf("%1d",&a[i][j]);	
		}
	}
	
	for(int i=0; i<n; i++){
		for(int j=0; j<m; j++){
			scanf("%1d",&b[i][j]);	
	    }
	}

	
	
	for(int i=0; i<n-2; i++){
		for(int j=0; j<m-2; j++){
			if(a[i][j]!=b[i][j]){
				res++;
				for(int x=i; x<=i+2; x++){
					for(int y=j; y<=j+2; y++){
						a[x][y]=1-a[x][y];
					}
				}
			}
		}
	}
	
	for(int i=0; i<n; i++){
		for(int j=0; j<m; j++){
			if(a[i][j]!=b[i][j]) res=-1;
		}
	}
	
	printf("%d",res);
	getchar();
	getchar();
	return 0;
}
