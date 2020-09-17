#include<iostream>
#include<cstdlib>
#include<stack>
using namespace std;
int main(){
	//input
	stack<int> s;
    for (int i=0; i<10; i++){
    	s.push(rand());
	}
	
	//process
	stack<int> s2;
	while(!s.empty()){
		int element = s.top();
		s.pop();
		while(!s2.empty() && s2.top()<element){
			s.push(s2.top());
			s2.pop();
		}
		s2.push(element);
	}
	
	//output
	for(int i=0;i<10;i++){
		int element = s2.top();
		s2.pop();
		cout<<element<<endl;
	}
	return 0;
}
