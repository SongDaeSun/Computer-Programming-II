package Schedule_Project4;

import java.util.Vector;

class Group{
	String GroupName; //그룹 이름
	Group(String name, boolean b){
		GroupName=name;
		isVoid= b;
	}
		Vector<course> cv = new Vector(); //그룹 내의 강의 목록들
	boolean isVoid = false; //연산 유무
	
	int getNum() {
		if (isVoid)
			return cv.size()+1;
		else
			return cv.size();
	}
	boolean isCalculateable() {
		if(cv.size()==0)
			return false;
		else
			return true;
	}
}
