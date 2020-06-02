#include <bits/stdc++.h>

using namespace std;

int main() {
 int n;
 long long goal,ans;
 scanf("%d%lld",&n,&goal);
 long long machines[n];

 for(int i=0;i<n;i++) {
  scanf("%lld",&machines[i]);
 }

 long long low=1;
 long long high=1e18;
 while(low<=high) {
  long long mid=(low+high)/2;
  long long done=0;
  for(int i=0;i<n;i++) {
   done+=mid/machines[i];
   if(done>=goal)
    break;
  }

  if(done>=goal) {
   high=mid-1;
   ans=mid;
  } else {
   low=mid+1;
  }
 }

 printf("%lld\n",ans);
 return 0;
}
