package model.or;

import java.util.ArrayList;

public class CMHProcess {

	private Integer[] dependencyNo;

	private Boolean[] waitFlag;

	private Integer[] dataSet;

	private ArrayList<Integer> dependentSet;

	private Integer engagingQuery;

	private Boolean engagingQueryFlag;

	public CMHProcess(Integer[] dependencyNo, Boolean[] wait, Integer[] dataSet) {
		super();
		this.dependencyNo = dependencyNo;
		this.waitFlag = wait;
		this.dataSet = dataSet;
	}

	public Integer[] getNum() {
		return dependencyNo;
	}

	public void setNum(Integer[] dependencyNo) {
		this.dependencyNo = dependencyNo;
	}

	public Boolean[] getWait() {
		return waitFlag;
	}

	public void setWait(Boolean[] wait) {
		this.waitFlag = wait;
	}

	public Integer[] getDataSet() {
		return dataSet;
	}

	public void setDataSet(Integer[] dataSet) {
		this.dataSet = dataSet;
	}

	public ArrayList<Integer> getDependentSet() {
		return this.dependentSet;
	}

	public void setDependentSet(ArrayList<Integer> dependentSet) {
		this.dependentSet = dependentSet;
	}

	public Integer getEngagingQuery() {
		return engagingQuery;
	}

	public void setEngagingQuery(Integer engagingQuery) {
		this.engagingQuery = engagingQuery;
	}

	public Boolean getEngagingQueryFlag() {
		return engagingQueryFlag;
	}

	public void setEngagingQueryFlag(Boolean engagingQueryFlag) {
		this.engagingQueryFlag = engagingQueryFlag;
	}

}
