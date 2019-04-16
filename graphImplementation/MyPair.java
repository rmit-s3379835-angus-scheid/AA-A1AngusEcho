package graphImplementation;

import java.util.Objects;

/**
 * Class implement a pair object. Note javafx is not available on the core
 * teaching servers hence we are developing our own.
 *
 * @author Jeffrey Chan, 2019
 */
public class MyPair implements Comparable<MyPair> {
	private String mVert;
	private Integer mWeight;

	public MyPair(String vert, Integer weight) {
		mVert = vert;
		mWeight = weight;
	}

	public MyPair(String vert) {
		mVert = vert;
		mWeight = 0;
	}

	public String getKey() {
		return mVert;
	}

	public Integer getValue() {
		return mWeight;
	}

	@Override
	public boolean equals(Object o) {
		boolean equal = this == o;
		if (equal) {
			return true;
		}

		boolean isNull = o == null;
		boolean notEqual = getClass() != o.getClass();

		if (isNull || notEqual) {
			return false;
		}

		MyPair myPair = (MyPair) o;
		return Objects.equals(mVert, myPair.mVert);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mVert);
	}

	@Override
	public int compareTo(MyPair o) {
		return o.getValue() - this.getValue();
	}

	@Override
	public String toString() {
		return "(" + mVert + "," + mWeight + ")";
	}
} // end of class graphImpl.MyPair
