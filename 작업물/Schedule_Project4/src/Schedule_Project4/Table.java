package Schedule_Project4;

import java.util.Vector;

class Table implements Cloneable {
	boolean timeTable[][] = new boolean[5][8];
	
	Table() {
		for(int i=0;i<5;i++) {
			for(int j=0;j<8;j++) {
				boolean b=true;
				timeTable[i][j]=b;
			}
		}
	}
	
	Vector <course> courseInfo = new Vector();
	int totalPri=0;//시간표 선호도 총량
	int totalSta=0;//시간표 수강 안정도 총량
	int totalCre=0;//시간표 학점 총량
	
	//Table 초기설정
	void setTable(course c) {
		courseInfo.add(c);
		totalPri=c.priority;
		totalSta=c.stability;
		totalCre=c.creedit;
		
		setTime(c.tv);
	}
	
	//Table에 시간 적용
	void setTime(Vector <time> tv) {
		for(int i=0;i<tv.size();i++) {
			timeTable[tv.elementAt(i).day][tv.elementAt(i).period]=false;
		}
	}
	
	//Table에 Course 추가 가능여부
	boolean addAbleCourse(course c) {
		boolean isAddable =true;
		
		for(int i=0;i<c.tv.size();i++) {
			if(timeTable[c.tv.elementAt(i).day][c.tv.elementAt(i).period]==false) {
				isAddable=false;
				break;
			}
		}
		return isAddable;
	}
	
	//Table에 Course 추가
	void addTable (course c) {
			courseInfo.add(c);
			totalPri+=c.priority;
			totalSta+=c.stability;
			totalCre+=c.creedit;
			
			for(int i=0;i<c.tv.size();i++) {
				timeTable[c.tv.elementAt(i).day][c.tv.elementAt(i).period]=false;
			}
		
	}

	//Table의 Clone 생성
	public Object clone() throws CloneNotSupportedException {
		Table copy= (Table)super.clone();
		copy.timeTable = new boolean[5][8];
		for(int i=0;i<5;i++) {
			for(int j=0;j<8;j++) {
				copy.timeTable[i][j]=timeTable[i][j];
			}
		}
		copy.courseInfo = (Vector <course>)courseInfo.clone();
		
		for(int i=0;i<copy.courseInfo.size();i++) {
			copy.courseInfo.elementAt(i).tv=(Vector <time>)courseInfo.elementAt(i).tv.clone();
			/*for(int j=0;j<copy.courseInfo.elementAt(i).tv.size();j++) {
				copy.courseInfo.elementAt(i).tv.elementAt(j).day=courseInfo.elementAt(i).tv.elementAt(j).day;
				copy.courseInfo.elementAt(i).tv.elementAt(j).period=courseInfo.elementAt(i).tv.elementAt(j).period;
			}*/
		}
		
		return copy;
	}

	//Table의 공강여부 확인
	boolean CanRest (int d) {
		boolean isRest=true;
		
		for(int i=0;i<8;i++) {
			if(timeTable[d][i]==false)
				isRest=false;
			break;
		}
		
		return isRest;
	}
}