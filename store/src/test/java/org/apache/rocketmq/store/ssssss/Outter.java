package org.apache.rocketmq.store.ssssss;

public class Outter {
	
	public String name = "outter";
	
	public Inner inner = null;
	
	public Outter() {
	}
	
	public class Inner{
		private String name = "inner";
		public Inner() {}
		public void sayHello() {
			System.out.println("Outter:" + Outter.this.name + " Inner:" + name);
		}
	}

	public static void main(String[] args) {
		Outter outter = new Outter();
		Inner inner = outter.new Inner();
		inner.sayHello();
	}
}
