package com.etc.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class AttendanceWritable implements Writable{
	
	private int cstatus;
	private int astatus;
	
	
	@Override
	public String toString() {
		return cstatus + "\t" + astatus ;
	}
	public AttendanceWritable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AttendanceWritable(int cstatus, int astatus) {
		super();
		this.cstatus = cstatus;
		this.astatus = astatus;
	}
	public int getCstatus() {
		return cstatus;
	}
	public void setCstatus(int cstatus) {
		this.cstatus = cstatus;
	}
	public int getAstatus() {
		return astatus;
	}
	public void setAstatus(int astatus) {
		this.astatus = astatus;
	}
	@Override
	public void readFields(DataInput in) throws IOException {
		this.cstatus=in.readInt();
		this.astatus=in.readInt();
		
	}
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(this.cstatus);
		out.writeInt(this.astatus);
		
	}
	
	
	
}
