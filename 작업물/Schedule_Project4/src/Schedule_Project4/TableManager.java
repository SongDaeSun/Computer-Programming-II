package Schedule_Project4;

import java.util.Vector;

class TableManager {
	Vector <Table> tv = new Vector(); //시간표로 올려진 강의들
	Vector <Table> original= new Vector();//원본 테이블백터, 소팅만 가능,필터링하지 않음
	
	//초기 시간표 적용
 	void FirstTable(Vector <Group> gv) {
		if(gv.size()==0)
			System.out.println("최소 한개 이상의 강의그룹이 있어야합니다.");
		else {
			for(int i=0;i<gv.elementAt(0).cv.size();i++) {
				Table t= new Table();
				t.setTable(gv.elementAt(0).cv.elementAt(i));
				tv.add(t);
			}
		}
	}
	
	//시간표에 순차적 그룹 적용
	void addingTable(Vector <Group> gv, int index){
		Vector<Table> ntv =new Vector();
			
		for(int i=0;i<tv.size();i++) {
			for(int j=0;j<gv.elementAt(index).cv.size();j++) {
			
				if(tv.elementAt(i).addAbleCourse(gv.elementAt(index).cv.elementAt(j))) {
					Table nt;
					try
					{
						nt=(Table)tv.elementAt(i).clone();							nt.addTable(gv.elementAt(index).cv.elementAt(j));
						ntv.add(nt);
					}
					catch(CloneNotSupportedException e)
					{							
						e.printStackTrace();
					}
				}
			}
		}
		if(gv.elementAt(index).isVoid==true) {
			for(int i=0;i<tv.size();i++) {
				ntv.add(tv.elementAt(i));
			}
		}
		tv.removeAllElements();
		tv=ntv;
	}
	
	//시간표 계산
	void calculateTable(Vector <Group> gv) {
	
		int h;
		int t;
		Group temp;
		for(int i=gv.size()-1;i>0;i--) {
			for(int j=0;j<i;j++) {
				h=gv.elementAt(j).getNum();
				t=gv.elementAt(j+1).getNum();
				if(h>t) {
					temp = gv.elementAt(j+1);
					gv.set(j+1,gv.elementAt(j));
					gv.set(j, temp);
				}
			}
		}
		
		FirstTable(gv);
		for(int i=1;i<gv.size();i++)
			addingTable(gv, i);
		original =(Vector <Table>)tv.clone();
	}

	//선호도 배열
	void sortbyPri() {
		int h;
		int t;
		Table temp;
		for(int i=0;i<tv.size()-1;i++) {
			for(int j=0;j<tv.size()-1-i;j++) {
				
				h=tv.elementAt(j).totalPri;
				t=tv.elementAt(j+1).totalPri;
				if(h<t) {
				temp = tv.elementAt(j);
				tv.set(j,tv.elementAt(j+1));
				tv.set(j+1, temp);
				}
			}
		}
	}

	//안정성 배열
	void sortbySta() {
		int h;
		int t;
		Table temp;
		for(int i=0;i<tv.size()-1;i++) {
			for(int j=0;j<tv.size()-1-i;j++) {
						
				h=tv.elementAt(j).totalSta;
				t=tv.elementAt(j+1).totalSta;
				if(h<t) {
					temp = tv.elementAt(j);
					tv.set(j,tv.elementAt(j+1));
					tv.set(j+1, temp);
				}
			}
		}
	}

	/*
	//TV 프린트
	void printTV() {
			for(int i=0;i<tv.size();i++) {
				for(int j=0;j<tv.elementAt(i).courseInfo.size();j++) {
					System.out.print(tv.elementAt(i).courseInfo.elementAt(j).courseName+"\t");
				}
				System.out.println();
				printTime(tv.elementAt(i));
			}
		}

	//선호도 프린트
	void printPri() {
			for(int i=0;i<tv.size();i++) {
				System.out.println(tv.elementAt(i).totalPri+", "+tv.elementAt(i).totalSta+", "+tv.elementAt(i).totalCre);
			}
		}
*/
	//테이블 벡터 초기화
	void clearTV(){
		tv =(Vector <Table>)original.clone();
	}
	
	//선호도 필터
	void PriFilter(int pri) {
		for(int i=0;i<tv.size();i++) {
			if(tv.elementAt(i).totalPri<pri) {
				tv.removeElementAt(i);
				i--;
			}
		}
	}

	//공강필터
	void DayFilter(int d) {
		for(int i=0;i<tv.size();i++) {
			if(!tv.elementAt(i).CanRest(d)) {
				tv.removeElementAt(i);
				i--;
			}
		}
	}

	/*
	//시간 프린트
	void printTime(Table t) {
			for(int i=0;i<=8;i++) {
				for(int j=0;j<=5;j++) {
					System.out.print(t.timeTable[j][i]+"\t");
				}
			System.out.println();
		}
	}
*/
	//최소 학점 필터
	void minCreFilter(int min) {
		for(int i=0;i<tv.size();i++) {
			if(tv.elementAt(i).totalCre<min) {
				tv.removeElementAt(i);
				i--;
			}
		}
	}
	
	//최대 학점 필터
	void maxCreFilter(int max) {
			for(int i=0;i<tv.size();i++) {
				if(tv.elementAt(i).totalCre>max) {
					tv.removeElementAt(i);
					i--;
				}
			}
		}

	//학점 정렬
	void sortbyCre() {
			int h;
			int t;
			Table temp;
			for(int i=0;i<tv.size()-1;i++) {
				for(int j=0;j<tv.size()-1-i;j++) {
					
					h=tv.elementAt(j).totalCre;
					t=tv.elementAt(j+1).totalCre;
					if(h<t) {
					temp = tv.elementAt(j);
					tv.set(j,tv.elementAt(j+1));
					tv.set(j+1, temp);
					}
				}
			}
		}
}

