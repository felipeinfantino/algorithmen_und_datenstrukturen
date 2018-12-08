

// pair<Integer> test = new Pair<Integer>();

public class Pair<T> {
	private T first;
	private T second;
	
	public Pair(T first, T second) {
	 this.first = first;
	 this.second = second;
		
	}

	public T getFirst() {
		return this.first;
	}

	public T getSecond() {
		return this.second;
	}

	public void setFirst(T dt) {
		if(dt.getClass() == this.first.getClass()){
			this.first = dt;
		}
	}

	public void setSecond(T dt) {
		if(dt.getClass() == this.second.getClass()){
			this.second = dt;
		}
	}

	public void swap() {
		T temp = this.first;
		this.first = this.second;
		this.second = temp;
	}

	@Override
	public String toString()  {		
		try{
			return ""+first+ ", "+ second+"";
		}catch(Exception e){
			return "<not implemented>, <not implemented>";
		}
		
	}
}

